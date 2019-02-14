package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MedalResultPairTest {
    @Test
    void getDuelistNames_ShouldReturnBothNames() {
        MedalResultPair pair = new MedalResultPair(
            new MedalResult(new Duelist("A", 7, Location.CLIFF), 1),
            new MedalResult(new Duelist("B", 8, Location.CLASSROOM), 2),
            Location.CLASSROOM
        );
        assertEquals(pair.getDuelistNames(), Arrays.asList("A", "B"));
    }

    @Test
    void getMedals_ShouldReturnBothMedals() {
        MedalResultPair pair = new MedalResultPair(
            new MedalResult(new Duelist("A", 7, Location.CLIFF), 1),
            new MedalResult(new Duelist("B", 8, Location.CLASSROOM), 2),
            Location.CLASSROOM
        );
        assertEquals(pair.getMedals(), Arrays.asList(1, 2));
    }

    @Test
    void getMedals_WithMedalsChangedAfter_ShouldReturnOldMedals() {
        MedalResult resultOne = new MedalResult(new Duelist("A", 7, Location.CLIFF), 1);
        MedalResultPair pair = new MedalResultPair(
            resultOne,
            new MedalResult(new Duelist("B", 8, Location.CLASSROOM), 2),
            Location.CLASSROOM
        );
        resultOne.setMedals(3);
        assertEquals(pair.getMedals(), Arrays.asList(1, 2));
    }

    @Test
    void getLocation_ShouldReturnLocation() {
        MedalResultPair pair = new MedalResultPair(
            new MedalResult(new Duelist("A", 7, Location.CLIFF), 1),
            new MedalResult(new Duelist("B", 8, Location.CLASSROOM), 2),
            Location.CLASSROOM
        );
        assertEquals(pair.getLocation(), Location.CLASSROOM);
    }
}
