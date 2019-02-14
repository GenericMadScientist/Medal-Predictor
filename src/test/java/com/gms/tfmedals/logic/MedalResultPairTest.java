package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedalResultPairTest {
    @Test
    void dummyPair_ShouldReturnNameOnly() {
        MedalResultPair dummyResult = MedalResultPair.dummyPair("header");
        assertEquals(dummyResult.getDuelistNames(), "header");
        assertEquals(dummyResult.getMedalYield(), 0);
        assertNull(dummyResult.getHousesString());
        assertNull(dummyResult.getMedalsString());
        assertNull(dummyResult.getLocation());
    }

    @Test
    void getDuelistNames_ShouldReturnBothNames() {
        MedalResultPair pair = new MedalResultPair(
            new MedalResult(new Duelist("A", 7, House.MAIN_CHARACTER), 1),
            new MedalResult(new Duelist("B", 8, House.MAIN_CHARACTER), 2),
            Location.CLASSROOM
        );
        assertEquals(pair.getDuelistNames(), "A\nB");
    }

    @Test
    void getHousesString_ShouldReturnBothHouses() {
        MedalResultPair pair = new MedalResultPair(
            new MedalResult(new Duelist("A", 7, House.MAIN_CHARACTER), 1),
            new MedalResult(new Duelist("B", 8, House.MAIN_CHARACTER), 2),
            Location.CLASSROOM
        );
        assertEquals(pair.getHousesString(),
            House.MAIN_CHARACTER.toString() + '\n' + House.MAIN_CHARACTER);
    }

    @Test
    void getMedalsString_ShouldReturnBothMedals() {
        MedalResultPair pair = new MedalResultPair(
            new MedalResult(new Duelist("A", 7, House.MAIN_CHARACTER), 1),
            new MedalResult(new Duelist("B", 8, House.MAIN_CHARACTER), 2),
            Location.CLASSROOM
        );
        assertEquals(pair.getMedalsString(), "1\n2");
    }

    @Test
    void getMedalsString_WithMedalsChangedAfter_ShouldReturnOldMedals() {
        MedalResult resultOne = new MedalResult(new Duelist("A", 7, House.MAIN_CHARACTER), 1);
        MedalResultPair pair = new MedalResultPair(
            resultOne,
            new MedalResult(new Duelist("B", 8, House.MAIN_CHARACTER), 2),
            Location.CLASSROOM
        );
        resultOne.setMedals(3);
        assertEquals(pair.getMedalsString(), "1\n2");
    }

    @Test
    void getMedalYield_WithMedalsThreeAndFour_ShouldReturnFour() {
        MedalResultPair pair = new MedalResultPair(
            new MedalResult(new Duelist("A", 7, House.MAIN_CHARACTER), 3),
            new MedalResult(new Duelist("B", 8, House.MAIN_CHARACTER), 4),
            Location.CLASSROOM
        );
        assertEquals(pair.getMedalYield(), 4);
    }

    @Test
    void getLocation_ShouldReturnLocation() {
        MedalResultPair pair = new MedalResultPair(
            new MedalResult(new Duelist("A", 7, House.MAIN_CHARACTER), 1),
            new MedalResult(new Duelist("B", 8, House.MAIN_CHARACTER), 2),
            Location.CLASSROOM
        );
        assertEquals(pair.getLocation(), Location.CLASSROOM);
    }

    @Test
    void hasMember_ShouldOnlyReturnTrueForMembers() {
        MedalResultPair pair = new MedalResultPair(
            new MedalResult(new Duelist("A", 7, House.MAIN_CHARACTER), 1),
            new MedalResult(new Duelist("B", 8, House.MAIN_CHARACTER), 2),
            Location.CLASSROOM
        );
        assertTrue(pair.hasMember("A"));
        assertTrue(pair.hasMember("B"));
        assertFalse(pair.hasMember("C"));
    }

    @Test
    void resultsFromSeed_WithSeedZero_ShouldReturnCorrectMedals() {
        List<MedalResultPair> results = MedalResultPair.resultsFromSeed(0);
        assertEquals(results.get(0).getMedalsString(), "4\n3");
        assertEquals(results.size(), 44);
    }
}
