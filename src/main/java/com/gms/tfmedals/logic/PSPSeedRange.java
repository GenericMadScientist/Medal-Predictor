package com.gms.tfmedals.logic;

public final class PSPSeedRange implements SeedRange {
    private static final long INITIAL_SEED_MASK = 0xFFFFFFFFL;
    private static final long MAXIMUM_RANGE_SIZE = 0x100000000L;

    private final long firstSeed;
    private final long seedCount;

    /**
     * A default PSP seed range given no information.
     */
    public PSPSeedRange() {
        firstSeed = 0;
        seedCount = MAXIMUM_RANGE_SIZE;
    }

    /**
     * A PSP seed range from a clock time and uncertainty.
     *
     * @param clockTimeMicros   clock time the player clicked, in microseconds
     * @param uncertaintyMicros uncertainty in clockTimeMicros estimate, in
     *                          microseconds
     */
    public PSPSeedRange(final long clockTimeMicros, final long uncertaintyMicros) {
        firstSeed = (clockTimeMicros - uncertaintyMicros) & INITIAL_SEED_MASK;
        if (uncertaintyMicros >= (MAXIMUM_RANGE_SIZE / 2)) {
            seedCount = MAXIMUM_RANGE_SIZE;
        } else if (uncertaintyMicros < 0) {
            throw new IllegalArgumentException("Uncertainty should be non-negative");
        } else {
            seedCount = 2 * uncertaintyMicros + 1;
        }
    }

    @Override
    public long initialSeed() {
        return firstSeed;
    }

    @Override
    public long increment() {
        return 1;
    }

    @Override
    public long numbOfSeeds() {
        return seedCount;
    }
}
