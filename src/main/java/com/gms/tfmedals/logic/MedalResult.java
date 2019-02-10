package com.gms.tfmedals.logic;

final class MedalResult {
    private final Duelist duelist;
    private final int medals;

    MedalResult(Duelist duelist, int medals) {
        if ((medals < 1) || (medals > 5)) {
            throw new IllegalArgumentException("Medals must lie between one and five");
        }

        this.duelist = duelist;
        this.medals = medals;
    }

    int getDuelistId() {
        return duelist.getId();
    }

    int getMedals() {
        return medals;
    }
}
