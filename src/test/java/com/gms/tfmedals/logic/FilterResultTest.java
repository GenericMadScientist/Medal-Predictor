package com.gms.tfmedals.logic;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.OptionalLong;
import java.util.logging.Filter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FilterResultTest {
    @Test
    public void getFirstSeed_WithNoResults_ShouldNotBePresent() {
        FilterResult result = new FilterResult();
        assertFalse(result.getFirstSeed().isPresent());
    }

    @Test
    public void getFirstSeed_WithOneAddedSeed_ShouldReturnAddedSeed() {
        FilterResult result = new FilterResult();
        result.addSeed(1);
        assertEquals(result.getFirstSeed(), OptionalLong.of(1));
    }

    @Test
    public void getFirstSeed_WithTwoAddedSeeds_ShouldReturnFirstAddedSeed() {
        FilterResult result = new FilterResult();
        result.addSeed(1);
        result.addSeed(2);
        assertEquals(result.getFirstSeed(), OptionalLong.of(1));
    }

    @Test
    public void getCount_WithNoResults_ShouldReturnZero() {
        FilterResult result = new FilterResult();
        assertEquals(result.getCount(), 0);
    }

    @Test
    public void getCount_WithOneAddedSeed_ShouldReturnOne() {
        FilterResult result = new FilterResult();
        result.addSeed(1);
        assertEquals(result.getCount(), 1);
    }

    @Test
    public void getCount_WithTwoAddedSeeds_ShouldReturnTwo() {
        FilterResult result = new FilterResult();
        result.addSeed(1);
        result.addSeed(2);
        assertEquals(result.getCount(), 2);
    }

    @Test
    public void combine_WithNoResults_ShouldReturnNoResult() {
        FilterResult finalResult = FilterResult.combine(new ArrayList<>());
        assertEquals(finalResult.getCount(), 0);
        assertEquals(finalResult.getFirstSeed(), OptionalLong.empty());
    }

    @Test
    public void combine_WithOneResult_ShouldReturnSameResult() {
        FilterResult result = new FilterResult();
        result.addSeed(1);
        result.addSeed(2);

        FilterResult finalResult = FilterResult.combine(Arrays.asList(result));

        assertEquals(finalResult.getFirstSeed(), OptionalLong.of(1));
        assertEquals(finalResult.getCount(), 2);
    }

    @Test
    public void combine_WithTwoResults_ShouldReturnCombinedResult() {
        FilterResult firstResult = new FilterResult();
        firstResult.addSeed(3);
        firstResult.addSeed(4);
        FilterResult secondResult = new FilterResult();
        secondResult.addSeed(1);

        FilterResult finalResult = FilterResult.combine(Arrays.asList(firstResult, secondResult));

        assertEquals(finalResult.getFirstSeed(), OptionalLong.of(3));
        assertEquals(finalResult.getCount(), 3);
    }
}
