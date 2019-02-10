package com.gms.tfmedals.logic;

final class Duelist {
    private final String name;
    private final int id;

    Duelist(String name, int id) {
        this.name = name;
        this.id = id;
    }

    String getName() {
        return name;
    }

    int getId() {
        return id;
    }
}
