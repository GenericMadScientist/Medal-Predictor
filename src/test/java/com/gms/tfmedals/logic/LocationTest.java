package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocationTest {
    @Test
    void toString_ShouldNotReturnCapitalisedName() {
        assertEquals(Location.CLASSROOM.toString(), "Classroom");
        assertEquals(Location.CLIFF.toString(), "Cliff");
    }
}
