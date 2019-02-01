package com.gms.tfmedals.logic;

import java.util.List;
import java.util.OptionalLong;

public final class FilterResult {
    private long firstSeed = 0;
    private boolean hasSeed = false;
    private long count = 0;

    public FilterResult() {
    }

    public OptionalLong getFirstSeed() {
        if (!hasSeed) {
            return OptionalLong.empty();
        } else {
            return OptionalLong.of(firstSeed);
        }
    }

    public long getCount() {
        return count;
    }

    public void addSeed(long seed) {
        if (!hasSeed) {
            firstSeed = seed;
            hasSeed = true;
        }

        count++;
    }

    public static FilterResult combine(List<FilterResult> results) {
        FilterResult finalResult = new FilterResult();

        for (FilterResult subresult : results) {
            if (!finalResult.hasSeed && subresult.hasSeed) {
                finalResult.firstSeed = subresult.firstSeed;
                finalResult.hasSeed = true;
            }

            finalResult.count += subresult.count;
        }

        return finalResult;
    }
}
