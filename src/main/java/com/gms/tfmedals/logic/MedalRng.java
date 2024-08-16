package com.gms.tfmedals.logic;

final class MedalRng {
    private static final int MAX_MEDALS_PER_DUEL = 5;
    private static final long RNG_MULT = 0x5851F42D4C957F2DL;
    private static final long RNG_INC = 1;
    private static final long RNG_ROLL_BIT_MASK = 0x7FFFFFFF00000000L;
    private static final int RNG_ROLL_SHIFT = 32;

    private MedalRng() {
    }

    static int medalRoll(final long seed) {
        return ((int) ((seed & RNG_ROLL_BIT_MASK) >> RNG_ROLL_SHIFT)) % MAX_MEDALS_PER_DUEL;
    }

    static long nextSeed(final long seed) {
        return RNG_MULT * seed + RNG_INC;
    }

    static long duelistMult(final int duelistId) {
        long mult = 1;

        for (int i = 0; i < duelistId; i++) {
            mult *= RNG_MULT;
        }

        return mult;
    }

    static long duelistInc(final int duelistId) {
        long inc = 0;

        for (int i = 0; i < duelistId; i++) {
            inc = RNG_MULT * inc + RNG_INC;
        }

        return inc;
    }
}
