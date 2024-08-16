package com.gms.tfmedals.logic;

enum House {
    /**
     * Generic duelists in Slifer Red.
     */
    SLIFER_RED,
    /**
     * Generic duelists in Ra Yellow.
     */
    RA_YELLOW,
    /**
     * Generic duelists in Obelisk Blue.
     */
    OBELISK_BLUE,
    /**
     * Generic teacher duelists.
     */
    TEACHER,
    /**
     * Main characters.
     */
    MAIN_CHARACTER;

    @Override
    public String toString() {
        switch (this) {
            case SLIFER_RED:
                return "SR";
            case RA_YELLOW:
                return "RY";
            case OBELISK_BLUE:
                return "OB";
            case TEACHER:
                return "T";
            case MAIN_CHARACTER:
                return "MC";
            default:
                throw new IllegalArgumentException("Impossible House value");
        }
    }
}
