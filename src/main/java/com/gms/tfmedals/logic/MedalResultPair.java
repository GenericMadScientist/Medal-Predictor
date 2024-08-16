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

    /**
     * @param left         firstt duelist and their medals
     * @param right        second duelist and their medals
     * @param pairLocation location of the duelist pair
     */
    MedalResultPair(final MedalResult left, final MedalResult right, final Location pairLocation) {
        this.location = pairLocation;
        duelistNames = left.getDuelistName() + '\n' + right.getDuelistName();
        housesString = left.getHouse().toString() + '\n' + right.getHouse();
        medalsString = left.getMedals().toString() + '\n' + right.getMedals();
        medalsYield = Math.max(left.getMedals(), right.getMedals());

        members = new HashSet<>();
        members.add(left.getDuelistName());
        members.add(right.getDuelistName());
    }

    /**
     * @param header location name
     * @return dummy pair to display location header in tree view
     */
    public static MedalResultPair dummyPair(final String header) {
        return new MedalResultPair(header);
    }

    private MedalResultPair(final String dummyName) {
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

    /**
     * @param member name of duelist to check
     * @return whether the duelist is in the pair
     */
    public boolean hasMember(final String member) {
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

    /**
     * @param seed the RNG state before medal generation
     * @return the medals assigned to every overworld duelist pair
     */
    public static List<MedalResultPair> resultsFromSeed(final long seed) {
        List<MedalResultPair> results = new ArrayList<>();

        List<Duelist> duelists = Duelist.allDuelists();

        for (int i = 0; i < ALL_LOCATIONS.length; i++) {
            results.add(new MedalResultPair(
                    MedalResult.duelistResult(duelists.get(2 * i), seed),
                    MedalResult.duelistResult(duelists.get(2 * i + 1), seed),
                    ALL_LOCATIONS[i]));
        }

        return results;
    }

    /**
     * Locations of duelist pairs in overworld.
     */
    private static final Location[] ALL_LOCATIONS = {
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
