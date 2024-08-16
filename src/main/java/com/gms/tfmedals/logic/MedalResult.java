package com.gms.tfmedals.logic;

public final class MedalResult {
    private static final int MAX_MEDALS_PER_DUEL = 5;

    private final Duelist duelist;
    private Integer medals;

    /**
     * Constructs a MedalResult from a duelist and medals, if medals is valid.
     *
     * @param duelist the duelist the medals come from
     * @param medals  the number of medals offered by the duelist
     */
    public MedalResult(final Duelist duelist, final Integer medals) {
        throwExceptionIfMedalsInvalid(medals);
        this.duelist = duelist;
        this.medals = medals;
    }

    MedalResult(final MedalResult result) {
        duelist = result.duelist;
        medals = result.medals;
    }

    private static void throwExceptionIfMedalsInvalid(final Integer medals) {
        if ((medals != null) && ((medals < 1) || (medals > MAX_MEDALS_PER_DUEL))) {
            throw new IllegalArgumentException("Medals must lie between one and five");
        }
    }

    int getDuelistId() {
        return duelist.getId();
    }

    public String getDuelistName() {
        return duelist.getName();
    }

    House getHouse() {
        return duelist.getHouse();
    }

    public Integer getMedals() {
        return medals;
    }

    /**
     * Sets the number of medals, throwing if the medal count is not a valid amount.
     *
     * @param medals the number of medals
     */
    public void setMedals(final Integer medals) {
        throwExceptionIfMedalsInvalid(medals);
        this.medals = medals;
    }

    static MedalResult duelistResult(final Duelist duelist, final long seed) {
        long seedForMedalRoll = seed;
        for (int i = 0; i < duelist.getId(); i++) {
            seedForMedalRoll = MedalRng.nextSeed(seedForMedalRoll);
        }
        int medals = MedalRng.medalRoll(seedForMedalRoll) + 1;
        return new MedalResult(duelist, medals);
    }
}
