package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PS2SeedRangeTest {
    @Test
    public void initialSeed_ShouldReturnZero() {
        PS2SeedRange range = new PS2SeedRange();
        assertEquals(range.initialSeed(), 0);
    }

    @Test
    public void increment_ShouldReturn100h() {
        PS2SeedRange range = new PS2SeedRange();
        assertEquals(range.increment(), 0x100);
    }

    @Test
    public void numbOfSeeds_ShouldReturn1000000h() {
        PS2SeedRange range = new PS2SeedRange();
        assertEquals(range.numbOfSeeds(), 0x1000000);
    }
}
