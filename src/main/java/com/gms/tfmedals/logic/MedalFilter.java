package com.gms.tfmedals.logic;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public final class MedalFilter {
    private final static long RNG_MULT = 0x5851F42D4C957F2DL;
    private final static long RNG_INC = 1;
    private final static long TASK_INCREMENT_SIZE = 0x800000;

    private final long[] medalMults;
    private final long[] medalIncs;
    private final int[] medalRolls;

    public MedalFilter(Collection<MedalResult> medalResults) {
        if (hasDuplicateDuelist(medalResults)) {
            throw new IllegalArgumentException("Duplicate duelist");
        }

        List<MedalResult> nonNullMedalResults = medalResults.stream()
            .filter(x -> x.getMedals() != null)
            .collect(Collectors.toList());

        medalMults = nonNullMedalResults.stream()
            .mapToLong(x -> duelistMult(x.getDuelistId()))
            .toArray();
        medalIncs = nonNullMedalResults.stream()
            .mapToLong(x -> duelistInc(x.getDuelistId()))
            .toArray();
        medalRolls = nonNullMedalResults.stream()
            .mapToInt(x -> x.getMedals() - 1)
            .toArray();
    }

    private static boolean hasDuplicateDuelist(Collection<MedalResult> medalResults) {
        Set<Integer> duelists = new HashSet<>();

        for (MedalResult result : medalResults) {
            if (!duelists.add(result.getDuelistId())) {
                return true;
            }
        }

        return false;
    }

    private static long duelistMult(int duelistId) {
        long mult = 1;

        for (int i = 0; i < duelistId; i++) {
            mult *= RNG_MULT;
        }

        return mult;
    }

    private static long duelistInc(int duelistId) {
        long inc = 0;

        for (int i = 0; i < duelistId; i++) {
            inc = RNG_MULT * inc + RNG_INC;
        }

        return inc;
    }

    public FilterResult results(SeedRange range) {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<FilterResult>> callables = callablesList(range);
        return getResultFromCallables(executor, callables);
    }

    private List<Callable<FilterResult>> callablesList(SeedRange range) {
        List<Callable<FilterResult>> callables = new ArrayList<>();

        for (long i = 0; i < range.numbOfSeeds(); i += TASK_INCREMENT_SIZE) {
            long initSeed = range.initialSeed() + i * range.increment();
            long taskSize = Math.min(TASK_INCREMENT_SIZE, range.numbOfSeeds() - i);
            callables.add(() -> resultsFromSubrange(initSeed, taskSize, range.increment()));
        }

        return callables;
    }

    private FilterResult resultsFromSubrange(long initSeed, long numbOfSeeds, long increment) {
        FilterResult results = new FilterResult();

        long seed = initSeed;

        for (long i = 0; i < numbOfSeeds; i++) {
            if (isMatchingSeed(seed)) {
                results.addSeed(seed);
            }

            seed = (seed + increment) & 0xFFFFFFFFL;
        }

        return results;
    }

    private boolean isMatchingSeed(long seed) {
        for (int i = 0; i < medalRolls.length; i++) {
            if (medalRoll(seed, i) != medalRolls[i]) {
                return false;
            }
        }

        return true;
    }

    private int medalRoll(long seed, int checkNumber) {
        seed = medalMults[checkNumber] * seed + medalIncs[checkNumber];
        return ((int) ((seed >> 32) & 0x7FFFFFFF)) % 5;
    }

    private FilterResult getResultFromCallables(ExecutorService executor,
                                                List<Callable<FilterResult>> callables) {
        List<FilterResult> results = new ArrayList<>();

        try {
            for (Future<FilterResult> future : executor.invokeAll(callables)) {
                results.add(future.get());
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        return FilterResult.combine(results);
    }
}
