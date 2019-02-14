package com.gms.tfmedals.logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class MedalResultPair {
    private final List<MedalResult> results;
    private final Location location;

    MedalResultPair(MedalResult left, MedalResult right, Location location) {
        results = Arrays.asList(new MedalResult(left), new MedalResult(right));
        this.location = location;
    }

    List<String> getDuelistNames() {
        return results.stream().map(MedalResult::getDuelistName).collect(Collectors.toList());
    }

    List<Integer> getMedals() {
        return results.stream().map(MedalResult::getMedals).collect(Collectors.toList());
    }

    Location getLocation() {
        return location;
    }

    public static List<MedalResultPair> resultsFromSeed(long seed) {
        List<MedalResultPair> results = new ArrayList<>();

        List<Duelist> duelists = Duelist.allDuelists();

        for (int i = 0; i < allLocations.length; i++) {
            results.add(new MedalResultPair(
                duelistResult(duelists.get(2 * i), seed),
                duelistResult(duelists.get(2 * i + 1), seed),
                allLocations[i]
            ));
        }

        return results;
    }

    private static MedalResult duelistResult(Duelist duelist, long seed) {
        for (int i = 0; i < duelist.getId(); i++) {
            seed = MedalRng.nextSeed(seed);
        }
        int medals = MedalRng.medalRoll(seed) + 1;
        return new MedalResult(duelist, medals);
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
