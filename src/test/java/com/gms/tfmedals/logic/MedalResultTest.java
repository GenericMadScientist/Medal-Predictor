package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MedalResultTest {
    @Test
    void constructor_WithDuelistAndMedals_ShouldReturnThemFromGetters() {
        MedalResult result = new MedalResult(new Duelist("A", 7, Location.CLIFF), 4);

        assertEquals(result.getDuelistId(), 7);
        assertEquals(result.getDuelistName(), "A");
        assertEquals(result.getLocation(), Location.CLIFF);
        assertEquals(result.getMedals(), Integer.valueOf(4));
    }

    @Test
    void constructor_WithInvalidMedals_ShouldThrowIllegalArgumentException() {
        Duelist duelistSeven = new Duelist("A", 7, Location.CLIFF);
        assertThrows(IllegalArgumentException.class, () -> new MedalResult(duelistSeven, 0));
        assertThrows(IllegalArgumentException.class, () -> new MedalResult(duelistSeven, 6));
    }

    @Test
    void constructor_WithValidMedals_ShouldNotThrowException() {
        new MedalResult(new Duelist("A", 7, Location.CLIFF), 1);
        new MedalResult(new Duelist("A", 7, Location.CLIFF), 5);
    }

    @Test
    void copyConstructor_ShouldMakeNewCopy() {
        MedalResult resultOne = new MedalResult(new Duelist("A", 7, Location.CLIFF), 1);
        MedalResult resultTwo = new MedalResult(resultOne);

        assertEquals(resultOne.getDuelistId(), resultTwo.getDuelistId());
        assertEquals(resultOne.getDuelistName(), resultTwo.getDuelistName());
        assertEquals(resultOne.getLocation(), resultTwo.getLocation());
        assertEquals(resultOne.getMedals(), resultTwo.getMedals());
    }

    @Test
    void setMedals_WithInvalidMedals_ShouldThrowIllegalArgumentException() {
        MedalResult result = new MedalResult(new Duelist("A", 7, Location.CLIFF), 4);
        assertThrows(IllegalArgumentException.class, () -> result.setMedals(0));
        assertThrows(IllegalArgumentException.class, () -> result.setMedals(6));
    }

    @Test
    void setMedals_ShouldChangeResultOfGetMedals() {
        MedalResult result = new MedalResult(new Duelist("A", 7, Location.CLIFF), 4);
        result.setMedals(5);
        assertEquals(result.getMedals(), Integer.valueOf(5));
    }

    @Test
    void duelistResult_WithIdOneAndSeedZero_ShouldGiveZeroMedals() {
        MedalResult result = MedalResult.duelistResult(new Duelist("A", 1, Location.CLIFF), 0);
        assertEquals(result.getMedals(), Integer.valueOf(1));
    }
}
