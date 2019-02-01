package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PSPSeedRangeTest {
    @Test
    public void constructor_WithNegativeUncertainty_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new PSPSeedRange(0, -1));
    }

    @Test
    public void initialSeed_WithNoTime_ShouldReturnZero() {
        PSPSeedRange range = new PSPSeedRange();
        assertEquals(range.initialSeed(), 0);
    }

    @Test
    public void initialSeed_WithTime_ShouldReturnTimeMinusUncertainty() {
        PSPSeedRange firstRange = new PSPSeedRange(0x1000000, 10000000);
        PSPSeedRange secondRange = new PSPSeedRange(0x1000000, 5000000);

        assertEquals(firstRange.initialSeed(), 0x1000000L - 10000000L);
        assertEquals(secondRange.initialSeed(), 0x1000000L - 5000000L);
    }

    @Test
    public void initialSeed_WithTime_ShouldUnderflowAs32Bit() {
        PSPSeedRange range = new PSPSeedRange(0, 1);
        assertEquals(range.initialSeed(), 0xFFFFFFFFL);
    }

    @Test
    public void increment_ShouldReturnOne() {
        PSPSeedRange range = new PSPSeedRange();
        assertEquals(range.increment(), 1);
    }

    @Test
    public void numbOfSeeds_WithNoTime_ShouldReturn100000000h() {
        PSPSeedRange range = new PSPSeedRange();
        assertEquals(range.numbOfSeeds(), 0x100000000L);
    }

    @Test
    public void numbOfSeeds_WithTime_ShouldReturnTwoTimesPlusOne() {
        PSPSeedRange range = new PSPSeedRange(0x1000000, 10);
        assertEquals(range.numbOfSeeds(), 21);
    }

    @Test
    public void numbOfSeeds_WithTime_ShouldMaxAt100000000h() {
        PSPSeedRange range = new PSPSeedRange(0, 0x900000000L);
        assertEquals(range.numbOfSeeds(), 0x100000000L);
    }
}
