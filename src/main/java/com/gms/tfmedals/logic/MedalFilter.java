package com.gms.tfmedals.logic;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public final class MedalFilter {
    private static final long INIT_SEED_MASK = 0xFFFFFFFFL;
    private static final long TASK_INCREMENT_SIZE = 0x800000;

    private final long[] medalMults;
    private final long[] medalIncs;
    private final int[] medalRolls;

    /**
     * Constructor for a filter from known medal counts.
     *
     * @param medalResults the known medal counts
     */
    public MedalFilter(final Collection<MedalResult> medalResults) {
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

    private static boolean hasDuplicateDuelist(final Collection<MedalResult> medalResults) {
        Set<Integer> duelists = new HashSet<>();

        for (MedalResult result : medalResults) {
            if (!duelists.add(result.getDuelistId())) {
                return true;
            }
        }

        return false;
    }

    /**
     * @param range the range of seeds to look in
     * @return the number of matching seeds, and one seed that matches
     */
    public FilterResult results(final SeedRange range) {
        ExecutorService executor = Executors.newWorkStealingPool();
        List<Callable<FilterResult>> callables = callablesList(range);
        return getResultFromCallables(executor, callables);
    }

    private List<Callable<FilterResult>> callablesList(final SeedRange range) {
        List<Callable<FilterResult>> callables = new ArrayList<>();

        for (long i = 0; i < range.numbOfSeeds(); i += TASK_INCREMENT_SIZE) {
            long initSeed = range.initialSeed() + i * range.increment();
            long taskSize = Math.min(TASK_INCREMENT_SIZE, range.numbOfSeeds() - i);
            callables.add(() -> resultsFromSubrange(initSeed, taskSize, range.increment()));
        }

        return callables;
    }

    private FilterResult resultsFromSubrange(final long initSeed, final long numbOfSeeds, final long increment) {
        FilterResult results = new FilterResult();

        long seed = initSeed;

        for (long i = 0; i < numbOfSeeds; i++) {
            if (isMatchingSeed(seed)) {
                results.addSeed(seed);
            }

            seed = (seed + increment) & INIT_SEED_MASK;
        }

        return results;
    }

    private boolean isMatchingSeed(final long seed) {
        for (int i = 0; i < medalRolls.length; i++) {
            if (nthMedalRoll(seed, i) != medalRolls[i]) {
                return false;
            }
        }

        return true;
    }

    private int nthMedalRoll(final long seed, final int n) {
        long nthSeed = medalMults[n] * seed + medalIncs[n];
        return MedalRng.medalRoll(nthSeed);
    }

    private FilterResult getResultFromCallables(final ExecutorService executor,
            final List<Callable<FilterResult>> callables) {
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
