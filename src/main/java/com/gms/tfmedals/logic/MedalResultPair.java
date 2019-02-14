package com.gms.tfmedals.logic;

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
}
