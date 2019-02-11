package com.gms.tfmedals.logic;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public final class MedalFilter {
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
            .mapToLong(x -> MedalRng.duelistMult(x.getDuelistId()))
            .toArray();
        medalIncs = nonNullMedalResults.stream()
            .mapToLong(x -> MedalRng.duelistInc(x.getDuelistId()))
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
            if (nthMedalRoll(seed, i) != medalRolls[i]) {
                return false;
            }
        }

        return true;
    }

    private int nthMedalRoll(long seed, int n) {
        seed = medalMults[n] * seed + medalIncs[n];
        return MedalRng.medalRoll(seed);
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
