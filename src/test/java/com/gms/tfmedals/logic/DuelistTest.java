package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DuelistTest {
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
}
