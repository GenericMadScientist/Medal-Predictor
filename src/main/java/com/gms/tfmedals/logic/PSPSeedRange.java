package com.gms.tfmedals.logic;

public final class PSPSeedRange implements SeedRange {
    private final long firstSeed;
    private final long seedCount;

    public PSPSeedRange() {
        firstSeed = 0;
        seedCount = 0x100000000L;
    }

    public PSPSeedRange(long clockTimeMicros, long uncertaintyMicros) {
        firstSeed = (clockTimeMicros - uncertaintyMicros) & 0xFFFFFFFFL;
        if (uncertaintyMicros > 0x7FFFFFFFL) {
            seedCount = 0x100000000L;
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
