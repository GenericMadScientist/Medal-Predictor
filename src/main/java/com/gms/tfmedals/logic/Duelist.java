package com.gms.tfmedals.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class Duelist {
    private final String name;
    private final int id;

    Duelist(String name, int id) {
        if (name == null) {
            throw new IllegalArgumentException("Name should be a not null string");
        }

        if (id < 0) {
            throw new IllegalArgumentException("Duelist ID must be non-negative");
        }

        this.name = name;
        this.id = id;
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }

    public static List<Duelist> allDuelists() {
        return new ArrayList<>(Arrays.asList(DUELISTS));
    }

    private final static Duelist[] DUELISTS = new Duelist[] {
        new Duelist("Jaden Yuki", 1),
        new Duelist("Syrus Truesdale", 2),
        new Duelist("Chumley Huffington", 3),
        new Duelist("Alexis Rhodes", 4),
        new Duelist("Chazz Princeton", 5),
        new Duelist("Bastion Misawa", 6),
        new Duelist("Zane Truesdale", 7),
        new Duelist("Vellian Crowler", 8),
        new Duelist("Lyman Banner", 9),
        new Duelist("Damon", 18),
        new Duelist("Jasmine", 20),
        new Duelist("Mindy", 21),
        new Duelist("Blair", 23),
        new Duelist("Paradox Brothers: Para", 24),
        new Duelist("Paradox Brothers: Dox", 25),
        new Duelist("Slade Princeton", 31),
        new Duelist("Jagger Princeton", 32),
        new Duelist("Fonda Fontaine", 35),
        new Duelist("The Gambler", 36),
        new Duelist("Yasmin", 37),
        new Duelist("Wade", 41),
        new Duelist("Georg", 42),
        new Duelist("Hose", 43),
        new Duelist("Dean", 44),
        new Duelist("Lioside", 45),
        new Duelist("Gillian", 49),
        new Duelist("KENYoU", 50),
        new Duelist("Moses", 51),
        new Duelist("Terence", 52),
        new Duelist("Ivan", 53),
        new Duelist("Giry", 54),
        new Duelist("Michael", 55),
        new Duelist("Taku", 56),
        new Duelist("Bawnji", 57),
        new Duelist("Walter", 58),
        new Duelist("Hayden", 59),
        new Duelist("Rei", 60),
        new Duelist("Zanny", 61),
        new Duelist("Nathan", 62),
        new Duelist("Alvaro", 63),
        new Duelist("Rio", 64),
        new Duelist("Tryston", 65),
        new Duelist("Loggy", 66),
        new Duelist("Kura", 67),
        new Duelist("Aite", 68),
        new Duelist("Sigthor", 69),
        new Duelist("Joshua", 70),
        new Duelist("Hiro", 71),
        new Duelist("Laurence", 72),
        new Duelist("Khadim", 73),
        new Duelist("Shannon", 74),
        new Duelist("Yan", 75),
        new Duelist("Faty", 76),
        new Duelist("Craig", 77),
        new Duelist("Pipin", 78),
        new Duelist("Deron", 79),
        new Duelist("Enzo", 80),
        new Duelist("Swin", 81),
        new Duelist("Deloge", 82),
        new Duelist("Vyga", 83),
        new Duelist("Colin", 84),
        new Duelist("Rune", 85),
        new Duelist("Marcel", 86),
        new Duelist("Ray", 87),
        new Duelist("Bivin", 101),
        new Duelist("Pablo", 102),
        new Duelist("Mathew", 103),
        new Duelist("Corey", 104),
        new Duelist("Simon", 105),
        new Duelist("Nova", 106),
        new Duelist("Rock", 108),
        new Duelist("Dante", 109),
        new Duelist("Truly", 110),
        new Duelist("Seydina", 112),
        new Duelist("Woody", 113),
        new Duelist("Hide", 114),
        new Duelist("Jaime", 116),
        new Duelist("Syun", 117),
        new Duelist("Anca", 118),
        new Duelist("Rie", 119),
        new Duelist("Maki", 120),
        new Duelist("Celia", 122),
        new Duelist("Disa", 124),
        new Duelist("Masha", 125),
        new Duelist("Jewels", 126),
        new Duelist("Andrea", 128),
        new Duelist("Lily", 130),
        new Duelist("Yumi", 135)
    };
}