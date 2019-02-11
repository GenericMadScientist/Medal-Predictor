package com.gms.tfmedals.logic;

public enum Location {
    CLASSROOM,
    MAIN_GATE,
    STORE,
    DUEL_FIELD,
    SLIFER_RED_DORM,
    CLIFF,
    HARBOR,
    RA_YELLOW_DORM,
    OBELISK_BLUE_BOYS_DORM,
    OBELISK_BLUE_GIRLS_DORM,
    FOREST,
    ABANDONED_DORM,
    VOLCANO,
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
        }

        throw new IllegalArgumentException("Impossible location value");
    }
}
