package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DuelistTest {
    @Test
    void constructor_WithNullName_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Duelist(null, 0, Location.CLASSROOM));
    }

    @Test
    void constructor_WithNegativeId_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
            () -> new Duelist("Chumley", -1, Location.CLASSROOM));
    }

    @Test
    void getDuelistName_WithChumleyName_ShouldReturnChumley() {
        Duelist chumley = new Duelist("Chumley", 5, Location.CLASSROOM);
        assertEquals(chumley.getName(), "Chumley");
    }

    @Test
    void getDuelistId_WithIdFive_ShouldReturnFive() {
        Duelist chumley = new Duelist("Chumley", 5, Location.CLASSROOM);
        assertEquals(chumley.getId(), 5);
    }

    @Test
    void getLocation_WithLocationClassroom_ShouldReturnClassroom() {
        Duelist chumley = new Duelist("Chumley", 5, Location.CLASSROOM);
        assertEquals(chumley.getLocation(), Location.CLASSROOM);
    }

    @Test
    void allDuelists_ShouldStartWithJaden() {
        assertEquals(Duelist.allDuelists().get(0).getName(), "Jaden Yuki");
    }

    @Test
    void allDuelists_ShouldGiveListOfSize88() {
        assertEquals(Duelist.allDuelists().size(), 88);
    }

    @Test
    void allPartners_ShouldStartWithAbidos() {
        assertEquals(Duelist.allPartners().get(0), "Abidos the Third");
    }

    @Test
    void allPartners_ShouldGiveListOfSize108() {
        assertEquals(Duelist.allPartners().size(), 108);
    }
}
