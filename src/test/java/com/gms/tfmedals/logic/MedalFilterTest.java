package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalLong;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MedalFilterTest {
    @Test
    void constructor_WithDuplicateDuelists_ShouldThrowIllegalArgumentException() {
        MedalResult[] results = {new MedalResult(3, 1), new MedalResult(3, 2)};
        assertThrows(IllegalArgumentException.class, () -> new MedalFilter(Arrays.asList(results)));
    }

    @Test
    void constructor_WithNoDuplicateDuelists_ShouldNotThrowException() {
        MedalResult[] results = {new MedalResult(3, 1), new MedalResult(4, 2)};
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
        PSPSeedRange range = new PSPSeedRange(100, 5);
        List<MedalResult> results = new ArrayList<>();
        MedalFilter filter = new MedalFilter(results);
        results.add(new MedalResult(1, 5));
        assertEquals(filter.results(range).getCount(), 11);
    }

    @Test
    void results_WithSomeMedalResults_ShouldReturnCorrectResults() {
        PSPSeedRange range = new PSPSeedRange(5000, 5000);
        MedalResult[] medalResults = {
            new MedalResult(1, 5),
            new MedalResult(2, 3),
            new MedalResult(3, 5),
            new MedalResult(4, 1),
            new MedalResult(5, 4),
        };
        MedalFilter filter = new MedalFilter(Arrays.asList(medalResults));
        FilterResult results = filter.results(range);

        assertEquals(results.getFirstSeed(), OptionalLong.of(15));
        assertEquals(results.getCount(), 12);
    }
}
