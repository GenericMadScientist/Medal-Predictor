package com.gms.tfmedals.logic;

public final class MedalResult {
    private final Duelist duelist;
    private Integer medals;

    public MedalResult(Duelist duelist, Integer medals) {
        throwExceptionIfMedalsInvalid(medals);
        this.duelist = duelist;
        this.medals = medals;
    }

    MedalResult(MedalResult result) {
        duelist = result.duelist;
        medals = result.medals;
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

    public Location getLocation() {
        return duelist.getLocation();
    }

    public Integer getMedals() {
        return medals;
    }

    public void setMedals(Integer medals) {
        throwExceptionIfMedalsInvalid(medals);
        this.medals = medals;
    }

    static MedalResult duelistResult(Duelist duelist, long seed) {
        for (int i = 0; i < duelist.getId(); i++) {
            seed = MedalRng.nextSeed(seed);
        }
        int medals = MedalRng.medalRoll(seed) + 1;
        return new MedalResult(duelist, medals);
    }
}
