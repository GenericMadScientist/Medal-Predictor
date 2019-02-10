package com.gms.tfmedals.logic;

final class PS2SeedRange implements SeedRange {
    PS2SeedRange() {
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
