package com.gms.tfmedals.logic;

final class MedalResult {
    private final int duelistId;
    private final int medals;

    MedalResult(int duelistId, int medals) {
        if (duelistId < 0) {
            throw new IllegalArgumentException("Duelist ID must be non-negative");
        }

        if ((medals < 1) || (medals > 5)) {
            throw new IllegalArgumentException("Medals must lie between one and five");
        }

        this.duelistId = duelistId;
        this.medals = medals;
    }

    int getDuelistId() {
        return duelistId;
    }

    int getMedals() {
        return medals;
    }
}
