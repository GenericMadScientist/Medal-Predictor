package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
    void getMedalsString_ShouldReturnBothMedals() {
        MedalResultPair pair = new MedalResultPair(
            new MedalResult(new Duelist("A", 7, Location.CLIFF), 1),
            new MedalResult(new Duelist("B", 8, Location.CLASSROOM), 2),
            Location.CLASSROOM
        );
        assertEquals(pair.getMedalsString(), "1\n2");
    }

    @Test
    void getMedalsString_WithMedalsChangedAfter_ShouldReturnOldMedals() {
        MedalResult resultOne = new MedalResult(new Duelist("A", 7, Location.CLIFF), 1);
        MedalResultPair pair = new MedalResultPair(
            resultOne,
            new MedalResult(new Duelist("B", 8, Location.CLASSROOM), 2),
            Location.CLASSROOM
        );
        resultOne.setMedals(3);
        assertEquals(pair.getMedalsString(), "1\n2");
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

    @Test
    void resultsFromSeed_WithSeedZero_ShouldReturnCorrectMedals() {
        List<MedalResultPair> results = MedalResultPair.resultsFromSeed(0);
        assertEquals(results.get(0).getMedalsString(), "4\n3");
        assertEquals(results.size(), 44);
    }
}