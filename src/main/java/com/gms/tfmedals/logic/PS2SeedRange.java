package com.gms.tfmedals.logic;

public final class PS2SeedRange implements SeedRange {
    private static final long RANGE_SIZE = 0x1000000;
    private static final long SEED_INCREMENT = 0x100;

    /**
     * The PS2 seed range.
     */
    public PS2SeedRange() {
    }

    @Override
    public long initialSeed() {
        return 0;
    }

    @Override
    public long increment() {
        return SEED_INCREMENT;
    }

    @Override
    public long numbOfSeeds() {
        return RANGE_SIZE;
    }
}
