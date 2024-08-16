package com.gms.tfmedals.logic;

public enum Location {
    /**
     * Location of duelist pairs in the Classroom.
     */
    CLASSROOM,
    /**
     * Location of duelist pairs at the Main Gate.
     */
    MAIN_GATE,
    /**
     * Location of duelist pairs in the Store.
     */
    STORE,
    /**
     * Location of duelist pairs at the Duel Field.
     */
    DUEL_FIELD,
    /**
     * Location of duelist pairs at the Slifer Red Dorm.
     */
    SLIFER_RED_DORM,
    /**
     * Location of duelist pairs at the Cliffs.
     */
    CLIFF,
    /**
     * Location of duelist pairs at the Harbor.
     */
    HARBOR,
    /**
     * Location of duelist pairs at the Ra Yellow Dorm.
     */
    RA_YELLOW_DORM,
    /**
     * Location of duelist pairs at the Obelisk Blue Boys Dorm.
     */
    OBELISK_BLUE_BOYS_DORM,
    /**
     * Location of duelist pairs at the Obelisk Blue Girls Dorm.
     */
    OBELISK_BLUE_GIRLS_DORM,
    /**
     * Location of duelist pairs at the Forest.
     */
    FOREST,
    /**
     * Location of duelist pairs in the Abandoned Dorm.
     */
    ABANDONED_DORM,
    /**
     * Location of duelist pairs at the Volcano.
     */
    VOLCANO,
    /**
     * Location of duelist pairs at the Beach.
     */
    BEACH;

    @Override
    public String toString() {
        switch (this) {
            case CLASSROOM:
                return "Classroom";
            case MAIN_GATE:
                return "Main Gate";
            case STORE:
                return "Store";
            case DUEL_FIELD:
                return "Duel Field";
            case SLIFER_RED_DORM:
                return "Slifer Red Dorm";
            case CLIFF:
                return "Cliff";
            case HARBOR:
                return "Harbor";
            case RA_YELLOW_DORM:
                return "Ra Yellow Dorm";
            case OBELISK_BLUE_BOYS_DORM:
                return "Obelisk Blue Boys Dorm";
            case OBELISK_BLUE_GIRLS_DORM:
                return "Obelisk Blue Girls Dorm";
            case FOREST:
                return "Forest";
            case ABANDONED_DORM:
                return "Abandoned Dorm";
            case VOLCANO:
                return "Volcano";
            case BEACH:
                return "Beach";
            default:
                throw new IllegalArgumentException("Impossible location value");
        }
    }
}
