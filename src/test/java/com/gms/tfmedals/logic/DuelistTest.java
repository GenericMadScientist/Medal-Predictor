package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DuelistTest {
    @Test
    void constructor_WithNullName_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Duelist(null, 0));
    }

    @Test
    void constructor_WithNegativeId_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Duelist("Chumley", -1));
    }

    @Test
    void getDuelistName_WithChumleyName_ShouldReturnChumley() {
        Duelist chumley = new Duelist("Chumley", 5);
        assertEquals(chumley.getName(), "Chumley");
    }

    @Test
    void getDuelistId_WithIdFive_ShouldReturnFive() {
        Duelist chumley = new Duelist("Chumley", 5);
        assertEquals(chumley.getId(), 5);
    }

    @Test
    void allDuelists_ShouldStartWithJaden() {
        assertEquals(Duelist.allDuelists().get(0).getName(), "Jaden Yuki");
    }

    @Test
    void allDuelists_ShouldGiveListOfSize88() {
        assertEquals(Duelist.allDuelists().size(), 88);
    }
}
