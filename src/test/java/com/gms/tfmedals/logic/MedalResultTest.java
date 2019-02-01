package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MedalResultTest {
    @Test
    public void constructor_WithDuelistAndMedals_ShouldReturnThemFromGetters() {
        MedalResult result = new MedalResult(7, 4);

        assertEquals(result.getDuelistId(), 7);
        assertEquals(result.getMedals(), 4);
    }

    @Test
    public void constructor_WithNegativeDuelist_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new MedalResult(-1, 5));
    }

    @Test
    public void constructor_WithInvalidMedals_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new MedalResult(7, 0));
        assertThrows(IllegalArgumentException.class, () -> new MedalResult(7, 6));
    }

    @Test
    public void constructor_WithValidDuelistAndMedals_ShouldNotThrowException() {
        new MedalResult(7, 1);
        new MedalResult(7, 5);
    }
}
