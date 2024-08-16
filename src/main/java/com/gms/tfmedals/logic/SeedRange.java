package com.gms.tfmedals.logic;

public interface SeedRange {
    /**
     * @return the initial seed to look in the range
     */
    long initialSeed();

    /**
     * @return the gap between consecutive seeds
     */
    long increment();

    /**
     * @return the number of seeds in the range
     */
    long numbOfSeeds();
}
