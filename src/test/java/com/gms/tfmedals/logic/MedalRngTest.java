package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedalRngTest {
    @Test
    void medalRoll_ShouldReturnMedalsMinusOne() {
        assertEquals(MedalRng.medalRoll(0), 0);
        assertEquals(MedalRng.medalRoll(0x10000000000L), 1);
    }

    @Test
    void nextSeed_ShouldReturnNextSeed() {
        assertEquals(MedalRng.nextSeed(0), 1);
        assertEquals(MedalRng.nextSeed(1), 0x5851F42D4C957F2EL);
    }

    @Test
    void duelistMult_ShouldReturnPowerOfMult() {
        assertEquals(MedalRng.duelistMult(0), 1);
        assertEquals(MedalRng.duelistMult(1), 0x5851F42D4C957F2DL);
    }

    @Test
    void duelistInc_ShouldReturnNewInc() {
        assertEquals(MedalRng.duelistInc(0), 0);
        assertEquals(MedalRng.duelistInc(1), 1);
    }
}
