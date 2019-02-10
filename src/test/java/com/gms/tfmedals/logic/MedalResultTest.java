package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MedalResultTest {
    @Test
    void constructor_WithDuelistAndMedals_ShouldReturnThemFromGetters() {
        MedalResult result = new MedalResult(new Duelist("A", 7), 4);

        assertEquals(result.getDuelistId(), 7);
        assertEquals(result.getMedals(), 4);
    }

    @Test
    void constructor_WithInvalidMedals_ShouldThrowIllegalArgumentException() {
        Duelist duelistSeven = new Duelist("A", 7);
        assertThrows(IllegalArgumentException.class, () -> new MedalResult(duelistSeven, 0));
        assertThrows(IllegalArgumentException.class, () -> new MedalResult(duelistSeven, 6));
    }

    @Test
    void constructor_WithValidMedals_ShouldNotThrowException() {
        new MedalResult(new Duelist("A", 7), 1);
        new MedalResult(new Duelist("A", 7), 5);
    }
}
