package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MedalFilterTest {
    @Test
    void constructor_WithDuplicateDuelists_ShouldThrowIllegalArgumentException() {
        Duelist duelistThree = new Duelist("X", 3);
        MedalResult[] results = {new MedalResult(duelistThree, 1), new MedalResult(duelistThree, 2)};
        assertThrows(IllegalArgumentException.class, () -> new MedalFilter(Arrays.asList(results)));
    }

    @Test
    void constructor_WithNoDuplicateDuelists_ShouldNotThrowException() {
        Duelist duelistThree = new Duelist("X", 3);
        Duelist duelistFour = new Duelist("Y", 4);
        MedalResult[] results = {new MedalResult(duelistThree, 1), new MedalResult(duelistFour, 2)};
        new MedalFilter(Arrays.asList(results));
    }

    @Test
    void results_WithNoMedalResults_ShouldAcceptEverything() {
        PSPSeedRange range = new PSPSeedRange(100, 5);
        MedalFilter filter = new MedalFilter(new ArrayList<>());
        FilterResult results = filter.results(range);
        assertEquals(results.getFirstSeed(), OptionalLong.of(95));
        assertEquals(results.getCount(), 11);
    }

    @Test
    void constructor_WithChangedMedalResults_ShouldNotChange() {
        Duelist duelistOne = new Duelist("A", 1);
        PSPSeedRange range = new PSPSeedRange(100, 5);
        List<MedalResult> results = new ArrayList<>();
        MedalFilter filter = new MedalFilter(results);
        results.add(new MedalResult(duelistOne, 5));
        assertEquals(filter.results(range).getCount(), 11);
    }

    @Test
    void constructor_ShouldIgnoreNullMedals() {
        Duelist duelistOne = new Duelist("A", 1);
        PSPSeedRange range = new PSPSeedRange(100, 5);
        List<MedalResult> results = Collections.singletonList(new MedalResult(duelistOne, null));
        MedalFilter filter = new MedalFilter(results);
        assertEquals(filter.results(range).getCount(), 11);
    }

    @Test
    void results_WithSomeMedalResults_ShouldReturnCorrectResults() {
        PSPSeedRange range = new PSPSeedRange(5000, 5000);
        MedalResult[] medalResults = {
            new MedalResult(new Duelist("A", 1), 5),
            new MedalResult(new Duelist("B", 2), 3),
            new MedalResult(new Duelist("C", 3), 5),
            new MedalResult(new Duelist("D", 4), 1),
            new MedalResult(new Duelist("E", 5), 4),
        };
        MedalFilter filter = new MedalFilter(Arrays.asList(medalResults));
        FilterResult results = filter.results(range);

        assertEquals(results.getFirstSeed(), OptionalLong.of(15));
        assertEquals(results.getCount(), 12);
    }
}
