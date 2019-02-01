package com.gms.tfmedals.logic;

public final class MedalResult {
    private final int duelistId;
    private final int medals;

    public MedalResult(int duelistId, int medals) {
        if (duelistId < 0) {
            throw new IllegalArgumentException("Duelist ID must be non-negative");
        }

        if ((medals < 1) || (medals > 5)) {
            throw new IllegalArgumentException("Medals must lie between one and five");
        }

        this.duelistId = duelistId;
        this.medals = medals;
    }

    public int getDuelistId() {
        return duelistId;
    }

    public int getMedals() {
        return medals;
    }
}
