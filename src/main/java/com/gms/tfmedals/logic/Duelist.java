package com.gms.tfmedals.logic;

final class Duelist {
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
}
