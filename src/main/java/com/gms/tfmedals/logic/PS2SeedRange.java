package com.gms.tfmedals.logic;

public final class PS2SeedRange implements SeedRange {
    public PS2SeedRange() {
    }

    @Override
    public long initialSeed() {
        return 0;
    }

    @Override
    public long increment() {
        return 0x100;
    }

    @Override
    public long numbOfSeeds() {
        return 0x1000000;
    }
}
