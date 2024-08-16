package com.gms.tfmedals.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Duelist {
    private final House house;
    private final String name;
    private final int id;

    /**
     * Duelist constructor.
     *
     * @param name  duelist's name
     * @param id    duelist's id, used for medal generation
     * @param house broad category of the duelist, for easy identification
     */
    public Duelist(final String name, final int id, final House house) {
        if (name == null) {
            throw new IllegalArgumentException("Name should be a not null string");
        }

        if (id < 0) {
            throw new IllegalArgumentException("Duelist ID must be non-negative");
        }

        this.house = house;
        this.name = name;
        this.id = id;
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }

    House getHouse() {
        return house;
    }

    /**
     * @return the list of all duelists that appear in the overworld
     */
    public static List<Duelist> allDuelists() {
        return new ArrayList<>(Arrays.asList(DUELISTS));
    }

    /**
     * @return the list of all potential partners
     */
    public static List<String> allPartners() {
        return new ArrayList<>(Arrays.asList(PARTNERS));
    }

    private static final Duelist[] DUELISTS = new Duelist[] {
            new Duelist("Chumley Huffington", 3, House.MAIN_CHARACTER),
            new Duelist("Vellian Crowler", 8, House.MAIN_CHARACTER),
            new Duelist("Hiro", 71, House.SLIFER_RED),
            new Duelist("Laurence", 72, House.SLIFER_RED),
            new Duelist("Vyga", 83, House.RA_YELLOW),
            new Duelist("Colin", 84, House.RA_YELLOW),
            new Duelist("Dean", 44, House.TEACHER),
            new Duelist("Hose", 43, House.TEACHER),
            new Duelist("Giry", 54, House.SLIFER_RED),
            new Duelist("Michael", 55, House.SLIFER_RED),
            new Duelist("Celia", 122, House.OBELISK_BLUE),
            new Duelist("Jewels", 126, House.OBELISK_BLUE),
            new Duelist("Bawnji", 57, House.SLIFER_RED),
            new Duelist("KENYoU", 50, House.SLIFER_RED),
            new Duelist("Dante", 109, House.OBELISK_BLUE),
            new Duelist("Rock", 108, House.OBELISK_BLUE),
            new Duelist("Disa", 124, House.OBELISK_BLUE),
            new Duelist("Masha", 125, House.OBELISK_BLUE),
            new Duelist("Shannon", 74, House.SLIFER_RED),
            new Duelist("Khadim", 73, House.SLIFER_RED),
            new Duelist("Lily", 130, House.OBELISK_BLUE),
            new Duelist("Andrea", 128, House.OBELISK_BLUE),
            new Duelist("Faty", 76, House.RA_YELLOW),
            new Duelist("Pipin", 78, House.RA_YELLOW),
            new Duelist("Truly", 110, House.OBELISK_BLUE),
            new Duelist("Seydina", 112, House.OBELISK_BLUE),
            new Duelist("Syun", 117, House.OBELISK_BLUE),
            new Duelist("Jaime", 116, House.OBELISK_BLUE),
            new Duelist("Chazz Princeton", 5, House.MAIN_CHARACTER),
            new Duelist("Jaden Yuki", 1, House.MAIN_CHARACTER),
            new Duelist("Nathan", 62, House.SLIFER_RED),
            new Duelist("Zanny", 61, House.SLIFER_RED),
            new Duelist("Swin", 81, House.RA_YELLOW),
            new Duelist("Enzo", 80, House.RA_YELLOW),
            new Duelist("Rei", 60, House.SLIFER_RED),
            new Duelist("Gillian", 49, House.SLIFER_RED),
            new Duelist("Terence", 52, House.SLIFER_RED),
            new Duelist("Ivan", 53, House.SLIFER_RED),
            new Duelist("Walter", 58, House.SLIFER_RED),
            new Duelist("Hayden", 59, House.SLIFER_RED),
            new Duelist("Bivin", 101, House.OBELISK_BLUE),
            new Duelist("Taku", 56, House.SLIFER_RED),
            new Duelist("Paradox Brothers: Para", 24, House.MAIN_CHARACTER),
            new Duelist("Paradox Brothers: Dox", 25, House.MAIN_CHARACTER),
            new Duelist("Joshua", 70, House.SLIFER_RED),
            new Duelist("Sigthor", 69, House.SLIFER_RED),
            new Duelist("Jagger Princeton", 32, House.MAIN_CHARACTER),
            new Duelist("Slade Princeton", 31, House.MAIN_CHARACTER),
            new Duelist("Craig", 77, House.RA_YELLOW),
            new Duelist("Mathew", 103, House.OBELISK_BLUE),
            new Duelist("Loggy", 66, House.SLIFER_RED),
            new Duelist("Tryston", 65, House.SLIFER_RED),
            new Duelist("Alexis Rhodes", 4, House.MAIN_CHARACTER),
            new Duelist("Bastion Misawa", 6, House.MAIN_CHARACTER),
            new Duelist("Yan", 75, House.SLIFER_RED),
            new Duelist("Rune", 85, House.RA_YELLOW),
            new Duelist("Moses", 51, House.SLIFER_RED),
            new Duelist("Deloge", 82, House.RA_YELLOW),
            new Duelist("Corey", 104, House.OBELISK_BLUE),
            new Duelist("Nova", 106, House.OBELISK_BLUE),
            new Duelist("Zane Truesdale", 7, House.MAIN_CHARACTER),
            new Duelist("Syrus Truesdale", 2, House.MAIN_CHARACTER),
            new Duelist("Jasmine", 20, House.MAIN_CHARACTER),
            new Duelist("Mindy", 21, House.MAIN_CHARACTER),
            new Duelist("Ray", 87, House.RA_YELLOW),
            new Duelist("Marcel", 86, House.RA_YELLOW),
            new Duelist("Rie", 119, House.OBELISK_BLUE),
            new Duelist("Maki", 120, House.OBELISK_BLUE),
            new Duelist("Lyman Banner", 9, House.MAIN_CHARACTER),
            new Duelist("Fonda Fontaine", 35, House.MAIN_CHARACTER),
            new Duelist("Anca", 118, House.OBELISK_BLUE),
            new Duelist("Simon", 105, House.OBELISK_BLUE),
            new Duelist("Kura", 67, House.SLIFER_RED),
            new Duelist("Aite", 68, House.SLIFER_RED),
            new Duelist("Yumi", 135, House.OBELISK_BLUE),
            new Duelist("Lioside", 45, House.TEACHER),
            new Duelist("Deron", 79, House.RA_YELLOW),
            new Duelist("Pablo", 102, House.OBELISK_BLUE),
            new Duelist("The Gambler", 36, House.MAIN_CHARACTER),
            new Duelist("Blair", 23, House.MAIN_CHARACTER),
            new Duelist("Rio", 64, House.SLIFER_RED),
            new Duelist("Alvaro", 63, House.SLIFER_RED),
            new Duelist("Hide", 114, House.OBELISK_BLUE),
            new Duelist("Woody", 113, House.OBELISK_BLUE),
            new Duelist("Wade", 41, House.TEACHER),
            new Duelist("Georg", 42, House.TEACHER),
            new Duelist("Yasmin", 37, House.MAIN_CHARACTER),
            new Duelist("Damon", 18, House.MAIN_CHARACTER)
    };

    private static final String[] PARTNERS = new String[] {
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
