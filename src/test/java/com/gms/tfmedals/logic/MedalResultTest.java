package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MedalResultTest {
    @Test
    void constructor_WithDuelistAndMedals_ShouldReturnThemFromGetters() {
        MedalResult result = new MedalResult(new Duelist("A", 7, Location.CLIFF), 4);

        assertEquals(result.getDuelistId(), 7);
        assertEquals(result.getMedals(), Integer.valueOf(4));
        assertEquals(result.getDuelistName(), "A");
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
    void resultsFromSeed_WithSeedZero_ShouldReturnCorrectMedals() {
        List<MedalResult> results = MedalResult.resultsFromSeed(0);
        assertEquals(results.get(0).getMedals(), Integer.valueOf(1));
        assertEquals(results.get(1).getMedals(), Integer.valueOf(4));
        assertEquals(results.get(2).getMedals(), Integer.valueOf(4));
        assertEquals(results.get(9).getMedals(), Integer.valueOf(1));
    }
}
