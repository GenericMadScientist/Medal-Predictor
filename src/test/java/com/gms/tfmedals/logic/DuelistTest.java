package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DuelistTest {
    @Test
    void constructor_WithNullName_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Duelist(null, 0, House.SLIFER_RED));
    }

    @Test
    void constructor_WithNegativeId_ShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Duelist("Chumley", -1, House.SLIFER_RED));
    }

    @Test
    void getDuelistName_WithChumleyName_ShouldReturnChumley() {
        Duelist chumley = new Duelist("Chumley", 5, House.SLIFER_RED);
        assertEquals(chumley.getName(), "Chumley");
    }

    @Test
    void getDuelistId_WithIdFive_ShouldReturnFive() {
        Duelist chumley = new Duelist("Chumley", 5, House.SLIFER_RED);
        assertEquals(chumley.getId(), 5);
    }

    @Test
    void getHouse_WithHouseSliferRed_ShouldReturnSliferRed() {
        Duelist chumley = new Duelist("Chumley", 5, House.SLIFER_RED);
        assertEquals(chumley.getHouse(), House.SLIFER_RED);
    }

    @Test
    void allDuelists_ShouldStartWithChumley() {
        assertEquals(Duelist.allDuelists().get(0).getName(), "Chumley Huffington");
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
    void allPartners_ShouldGiveListOfSize109() {
        assertEquals(Duelist.allPartners().size(), 109);
    }
}
