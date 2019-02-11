package com.gms.tfmedals.logic;

final class MedalRng {
    private final static long RNG_MULT = 0x5851F42D4C957F2DL;
    private final static long RNG_INC = 1;

    private MedalRng() {
    }

    static int medalRoll(long seed) {
        return ((int) ((seed >> 32) & 0x7FFFFFFF)) % 5;
    }

    static long nextSeed(long seed) {
        return RNG_MULT * seed + RNG_INC;
    }

    static long duelistMult(int duelistId) {
        long mult = 1;

        for (int i = 0; i < duelistId; i++) {
            mult *= RNG_MULT;
        }

        return mult;
    }

    static long duelistInc(int duelistId) {
        long inc = 0;

        for (int i = 0; i < duelistId; i++) {
            inc = RNG_MULT * inc + RNG_INC;
        }

        return inc;
    }
}
