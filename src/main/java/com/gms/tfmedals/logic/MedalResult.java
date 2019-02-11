package com.gms.tfmedals.logic;

import java.util.ArrayList;
import java.util.List;

public final class MedalResult {
    private final static long RNG_MULT = 0x5851F42D4C957F2DL;
    private final static long RNG_INC = 1;

    private final Duelist duelist;
    private Integer medals;

    public MedalResult(Duelist duelist, Integer medals) {
        throwExceptionIfMedalsInvalid(medals);
        this.duelist = duelist;
        this.medals = medals;
    }

    private static void throwExceptionIfMedalsInvalid(Integer medals) {
        if ((medals != null) && ((medals < 1) || (medals > 5))) {
            throw new IllegalArgumentException("Medals must lie between one and five");
        }
    }

    int getDuelistId() {
        return duelist.getId();
    }

    public String getDuelistName() {
        return duelist.getName();
    }

    public Integer getMedals() {
        return medals;
    }

    public void setMedals(Integer medals) {
        throwExceptionIfMedalsInvalid(medals);
        this.medals = medals;
    }

    public static List<MedalResult> resultsFromSeed(long seed) {
        List<MedalResult> results = new ArrayList<>();

        for (Duelist duelist : Duelist.allDuelists()) {
            long seedCopy = seed;
            for (int i = 0; i < duelist.getId(); i++) {
                seedCopy = RNG_MULT * seedCopy + RNG_INC;
            }
            int medals = (int) ((seedCopy >> 32) & 0x7FFFFFFF) % 5 + 1;
            results.add(new MedalResult(duelist, medals));
        }

        return results;
    }
}
