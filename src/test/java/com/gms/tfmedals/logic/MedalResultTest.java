package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MedalResultTest {
    @Test
    void constructor_WithDuelistAndMedals_ShouldReturnThemFromGetters() {
        MedalResult result = new MedalResult(new Duelist("A", 7, House.SLIFER_RED), 4);

        assertEquals(result.getDuelistId(), 7);
        assertEquals(result.getDuelistName(), "A");
        assertEquals(result.getHouse(), House.SLIFER_RED);
        assertEquals(result.getMedals(), Integer.valueOf(4));
    }

    @Test
    void constructor_WithInvalidMedals_ShouldThrowIllegalArgumentException() {
        Duelist duelistSeven = new Duelist("A", 7, House.SLIFER_RED);
        assertThrows(IllegalArgumentException.class, () -> new MedalResult(duelistSeven, 0));
        assertThrows(IllegalArgumentException.class, () -> new MedalResult(duelistSeven, 6));
    }

    @Test
    void constructor_WithValidMedals_ShouldNotThrowException() {
        new MedalResult(new Duelist("A", 7, House.OBELISK_BLUE), 1);
        new MedalResult(new Duelist("A", 7, House.OBELISK_BLUE), 5);
    }

    @Test
    void copyConstructor_ShouldMakeNewCopy() {
        MedalResult resultOne = new MedalResult(new Duelist("A", 7, House.OBELISK_BLUE), 1);
        MedalResult resultTwo = new MedalResult(resultOne);

        assertEquals(resultOne.getDuelistId(), resultTwo.getDuelistId());
        assertEquals(resultOne.getDuelistName(), resultTwo.getDuelistName());
        assertEquals(resultOne.getMedals(), resultTwo.getMedals());
    }

    @Test
    void setMedals_WithInvalidMedals_ShouldThrowIllegalArgumentException() {
        MedalResult result = new MedalResult(new Duelist("A", 7, House.OBELISK_BLUE), 4);
        assertThrows(IllegalArgumentException.class, () -> result.setMedals(0));
        assertThrows(IllegalArgumentException.class, () -> result.setMedals(6));
    }

    @Test
    void setMedals_ShouldChangeResultOfGetMedals() {
        MedalResult result = new MedalResult(new Duelist("A", 7, House.OBELISK_BLUE), 4);
        result.setMedals(5);
        assertEquals(result.getMedals(), Integer.valueOf(5));
    }

    @Test
    void duelistResult_WithIdOneAndSeedZero_ShouldGiveZeroMedals() {
        MedalResult result = MedalResult.duelistResult(new Duelist("A", 1, House.OBELISK_BLUE), 0);
        assertEquals(result.getMedals(), Integer.valueOf(1));
    }
}
