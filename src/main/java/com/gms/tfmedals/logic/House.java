package com.gms.tfmedals.logic;

enum House {
    SLIFER_RED,
    RA_YELLOW,
    OBELISK_BLUE,
    TEACHER,
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
        }

        throw new IllegalArgumentException("Impossible House value");
    }
}
