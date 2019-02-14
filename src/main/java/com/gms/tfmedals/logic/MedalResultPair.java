package com.gms.tfmedals.logic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class MedalResultPair {
    private final int medalsYield;
    private final Location location;
    private final String duelistNames;
    private final String housesString;
    private final String medalsString;
    private final Set<String> members;

    MedalResultPair(MedalResult left, MedalResult right, Location location) {
        this.location = location;
        duelistNames = left.getDuelistName() + '\n' + right.getDuelistName();
        housesString = left.getHouse().toString() + '\n' + right.getHouse();
        medalsString = left.getMedals().toString() + '\n' + right.getMedals();
        medalsYield = Math.max(left.getMedals(), right.getMedals());

        members = new HashSet<>();
        members.add(left.getDuelistName());
        members.add(right.getDuelistName());
    }

    public static MedalResultPair dummyPair(String header) {
        return new MedalResultPair(header);
    }

    private MedalResultPair(String dummyName) {
        duelistNames = dummyName;
        housesString = null;
        location = null;
        medalsString = null;
        medalsYield = 0;
        members = null;
    }

    public String getDuelistNames() {
        return duelistNames;
    }

    public boolean hasMember(String member) {
        return members.contains(member);
    }

    public String getHousesString() {
        return housesString;
    }

    public String getMedalsString() {
        return medalsString;
    }

    public int getMedalYield() {
        return medalsYield;
    }

    public Location getLocation() {
        return location;
    }

    public static List<MedalResultPair> resultsFromSeed(long seed) {
        List<MedalResultPair> results = new ArrayList<>();

        List<Duelist> duelists = Duelist.allDuelists();

        for (int i = 0; i < allLocations.length; i++) {
            results.add(new MedalResultPair(
                MedalResult.duelistResult(duelists.get(2 * i), seed),
                MedalResult.duelistResult(duelists.get(2 * i + 1), seed),
                allLocations[i]
            ));
        }

        return results;
    }

    private static final Location[] allLocations = {
        Location.CLASSROOM,
        Location.CLASSROOM,
        Location.CLASSROOM,
        Location.MAIN_GATE,
        Location.MAIN_GATE,
        Location.MAIN_GATE,
        Location.STORE,
        Location.STORE,
        Location.STORE,
        Location.DUEL_FIELD,
        Location.DUEL_FIELD,
        Location.DUEL_FIELD,
        Location.DUEL_FIELD,
        Location.SLIFER_RED_DORM,
        Location.SLIFER_RED_DORM,
        Location.SLIFER_RED_DORM,
        Location.SLIFER_RED_DORM,
        Location.SLIFER_RED_DORM,
        Location.SLIFER_RED_DORM,
        Location.SLIFER_RED_DORM,
        Location.CLIFF,
        Location.CLIFF,
        Location.CLIFF,
        Location.HARBOR,
        Location.HARBOR,
        Location.HARBOR,
        Location.HARBOR,
        Location.RA_YELLOW_DORM,
        Location.RA_YELLOW_DORM,
        Location.OBELISK_BLUE_BOYS_DORM,
        Location.OBELISK_BLUE_BOYS_DORM,
        Location.OBELISK_BLUE_GIRLS_DORM,
        Location.OBELISK_BLUE_GIRLS_DORM,
        Location.OBELISK_BLUE_GIRLS_DORM,
        Location.BEACH,
        Location.BEACH,
        Location.BEACH,
        Location.ABANDONED_DORM,
        Location.FOREST,
        Location.FOREST,
        Location.FOREST,
        Location.FOREST,
        Location.VOLCANO,
        Location.VOLCANO
    };
}
