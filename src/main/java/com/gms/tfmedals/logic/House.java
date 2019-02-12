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
                return "Slifer Red";
            case RA_YELLOW:
                return "Ra Yellow";
            case OBELISK_BLUE:
                return "Obelisk Blue";
            case TEACHER:
                return "Teacher";
            case MAIN_CHARACTER:
                return "Main Character";
        }

        throw new IllegalArgumentException("Impossible House value");
    }
}
