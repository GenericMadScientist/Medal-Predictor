package com.gms.tfmedals.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Duelist {
    private final String name;
    private final int id;
    private final Location location;

    public Duelist(String name, int id, Location location) {
        if (name == null) {
            throw new IllegalArgumentException("Name should be a not null string");
        }

        if (id < 0) {
            throw new IllegalArgumentException("Duelist ID must be non-negative");
        }

        this.name = name;
        this.id = id;
        this.location = location;
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }

    Location getLocation() {
        return location;
    }

    public static List<Duelist> allDuelists() {
        return new ArrayList<>(Arrays.asList(DUELISTS));
    }

    public static List<String> allPartners() {
        return new ArrayList<>(Arrays.asList(PARTNERS));
    }

    private final static Duelist[] DUELISTS = new Duelist[] {
        new Duelist("Jaden Yuki", 1, Location.SLIFER_RED_DORM),
        new Duelist("Syrus Truesdale", 2, Location.OBELISK_BLUE_BOYS_DORM),
        new Duelist("Chumley Huffington", 3, Location.CLASSROOM),
        new Duelist("Alexis Rhodes", 4, Location.HARBOR),
        new Duelist("Chazz Princeton", 5, Location.SLIFER_RED_DORM),
        new Duelist("Bastion Misawa", 6, Location.HARBOR),
        new Duelist("Zane Truesdale", 7, Location.OBELISK_BLUE_BOYS_DORM),
        new Duelist("Vellian Crowler", 8, Location.CLASSROOM),
        new Duelist("Lyman Banner", 9, Location.BEACH),
        new Duelist("Damon", 18, Location.VOLCANO),
        new Duelist("Jasmine", 20, Location.OBELISK_BLUE_GIRLS_DORM),
        new Duelist("Mindy", 21, Location.OBELISK_BLUE_GIRLS_DORM),
        new Duelist("Blair", 23, Location.FOREST),
        new Duelist("Paradox Brothers: Para", 24, Location.CLIFF),
        new Duelist("Paradox Brothers: Dox", 25, Location.CLIFF),
        new Duelist("Slade Princeton", 31, Location.HARBOR),
        new Duelist("Jagger Princeton", 32, Location.HARBOR),
        new Duelist("Fonda Fontaine", 35, Location.BEACH),
        new Duelist("The Gambler", 36, Location.FOREST),
        new Duelist("Yasmin", 37, Location.VOLCANO),
        new Duelist("Wade", 41, Location.VOLCANO),
        new Duelist("Georg", 42, Location.VOLCANO),
        new Duelist("Hose", 43, Location.MAIN_GATE),
        new Duelist("Dean", 44, Location.MAIN_GATE),
        new Duelist("Lioside", 45, Location.ABANDONED_DORM),
        new Duelist("Gillian", 49, Location.SLIFER_RED_DORM),
        new Duelist("KENYoU", 50, Location.STORE),
        new Duelist("Moses", 51, Location.RA_YELLOW_DORM),
        new Duelist("Terence", 52, Location.SLIFER_RED_DORM),
        new Duelist("Ivan", 53, Location.SLIFER_RED_DORM),
        new Duelist("Giry", 54, Location.MAIN_GATE),
        new Duelist("Michael", 55, Location.MAIN_GATE),
        new Duelist("Taku", 56, Location.CLIFF),
        new Duelist("Bawnji", 57, Location.STORE),
        new Duelist("Walter", 58, Location.SLIFER_RED_DORM),
        new Duelist("Hayden", 59, Location.SLIFER_RED_DORM),
        new Duelist("Rei", 60, Location.SLIFER_RED_DORM),
        new Duelist("Zanny", 61, Location.SLIFER_RED_DORM),
        new Duelist("Nathan", 62, Location.SLIFER_RED_DORM),
        new Duelist("Alvaro", 63, Location.FOREST),
        new Duelist("Rio", 64, Location.FOREST),
        new Duelist("Tryston", 65, Location.HARBOR),
        new Duelist("Loggy", 66, Location.HARBOR),
        new Duelist("Kura", 67, Location.BEACH),
        new Duelist("Aite", 68, Location.BEACH),
        new Duelist("Sigthor", 69, Location.CLIFF),
        new Duelist("Joshua", 70, Location.CLIFF),
        new Duelist("Hiro", 71, Location.CLASSROOM),
        new Duelist("Laurence", 72, Location.CLASSROOM),
        new Duelist("Khadim", 73, Location.DUEL_FIELD),
        new Duelist("Shannon", 74, Location.DUEL_FIELD),
        new Duelist("Yan", 75, Location.RA_YELLOW_DORM),
        new Duelist("Faty", 76, Location.DUEL_FIELD),
        new Duelist("Craig", 77, Location.HARBOR),
        new Duelist("Pipin", 78, Location.DUEL_FIELD),
        new Duelist("Deron", 79, Location.FOREST),
        new Duelist("Enzo", 80, Location.SLIFER_RED_DORM),
        new Duelist("Swin", 81, Location.SLIFER_RED_DORM),
        new Duelist("Deloge", 82, Location.RA_YELLOW_DORM),
        new Duelist("Vyga", 83, Location.CLASSROOM),
        new Duelist("Colin", 84, Location.CLASSROOM),
        new Duelist("Rune", 85, Location.RA_YELLOW_DORM),
        new Duelist("Marcel", 86, Location.OBELISK_BLUE_GIRLS_DORM),
        new Duelist("Ray", 87, Location.OBELISK_BLUE_GIRLS_DORM),
        new Duelist("Bivin", 101, Location.CLIFF),
        new Duelist("Pablo", 102, Location.FOREST),
        new Duelist("Mathew", 103, Location.HARBOR),
        new Duelist("Corey", 104, Location.OBELISK_BLUE_BOYS_DORM),
        new Duelist("Simon", 105, Location.BEACH),
        new Duelist("Nova", 106, Location.OBELISK_BLUE_BOYS_DORM),
        new Duelist("Rock", 108, Location.STORE),
        new Duelist("Dante", 109, Location.STORE),
        new Duelist("Truly", 110, Location.DUEL_FIELD),
        new Duelist("Seydina", 112, Location.DUEL_FIELD),
        new Duelist("Woody", 113, Location.FOREST),
        new Duelist("Hide", 114, Location.FOREST),
        new Duelist("Jaime", 116, Location.SLIFER_RED_DORM),
        new Duelist("Syun", 117, Location.SLIFER_RED_DORM),
        new Duelist("Anca", 118, Location.BEACH),
        new Duelist("Rie", 119, Location.OBELISK_BLUE_GIRLS_DORM),
        new Duelist("Maki", 120, Location.OBELISK_BLUE_GIRLS_DORM),
        new Duelist("Celia", 122, Location.MAIN_GATE),
        new Duelist("Disa", 124, Location.STORE),
        new Duelist("Masha", 125, Location.STORE),
        new Duelist("Jewels", 126, Location.MAIN_GATE),
        new Duelist("Andrea", 128, Location.DUEL_FIELD),
        new Duelist("Lily", 130, Location.DUEL_FIELD),
        new Duelist("Yumi", 135, Location.ABANDONED_DORM)
    };

    private final static String[] PARTNERS = new String[] {
        "Abidos the Third",
        "Aite",
        "Alexis Rhodes",
        "Alvaro",
        "Amnael",
        "Anca",
        "Andrea",
        "Atticus Rhodes",
        "Bastion Misawa",
        "Bawnji",
        "Bivin",
        "Blair",
        "Brown",
        "Camula",
        "Celia",
        "Chazz Princeton",
        "Chumley Huffington",
        "Colin",
        "Corey",
        "Craig",
        "Damon",
        "Dante",
        "Dark Magician Girl",
        "Dean",
        "Deloge",
        "Deron",
        "Disa",
        "Don Zaloog",
        "Emperor.Umiuma",
        "Enzo",
        "Faty",
        "Fonda Fontaine",
        "Georg",
        "Gillian",
        "Giry",
        "Hayden",
        "Hide",
        "Hiro",
        "Hose",
        "Inoso",
        "Ivan",
        "Jaden Yuki",
        "Jagger Princeton",
        "Jaime",
        "Jasmine",
        "Jewels",
        "Jinzo",
        "Joshua",
        "Kagemaru",
        "KENYoU",
        "Khadim",
        "Kura",
        "Laurence",
        "Lily",
        "Lioside",
        "Loggy",
        "Lyman Banner",
        "Maki",
        "Marcel",
        "Masha",
        "Mathew",
        "Michael",
        "Mimicry",
        "Mindy",
        "Mokuma",
        "Moses",
        "Nathan",
        "Nightshroud",
        "Nova",
        "Pablo",
        "Paradox Brothers: Dox",
        "Paradox Brothers: Para",
        "Pipin",
        "Ray",
        "Rei",
        "Rie",
        "Rio",
        "Rock",
        "Rune",
        "Sadie",
        "Sanzyudai Yuki",
        "Senjome",
        "Seydina",
        "Shannon",
        "Sigthor",
        "Simon",
        "Sironos",
        "Slade Princeton",
        "Swin",
        "Syrus Truesdale",
        "Syun",
        "Taku",
        "Tania",
        "Terence",
        "The Gambler",
        "Titan",
        "Truly",
        "Tryston",
        "Vellian Crowler",
        "Vyga",
        "Wade",
        "Walter",
        "Woody",
        "Yan",
        "Yasmin",
        "Yumi",
        "Zane Truesdale",
        "Zanny"
    };
}