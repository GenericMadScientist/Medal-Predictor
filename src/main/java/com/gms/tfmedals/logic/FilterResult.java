package com.gms.tfmedals.logic;

import java.util.List;
import java.util.OptionalLong;

public final class FilterResult {
    private long firstSeed = 0;
    private boolean hasSeed = false;
    private long count = 0;

    FilterResult() {
    }

    /**
     * @return the first seed in the result, if any is present
     */
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

    void addSeed(final long seed) {
        if (!hasSeed) {
            firstSeed = seed;
            hasSeed = true;
        }

        count++;
    }

    static FilterResult combine(final List<FilterResult> results) {
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
