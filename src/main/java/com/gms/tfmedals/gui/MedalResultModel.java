package com.gms.tfmedals.gui;

public final class MedalResultModel {
    private final String duelistName;
    private Integer medals;

    MedalResultModel(String duelistName, Integer medals) {
        this.duelistName = duelistName;
        this.medals = medals;
    }

    public String getDuelistName() {
        return duelistName;
    }

    public Integer getMedals() {
        return medals;
    }

    void setMedals(Integer medals) {
        this.medals = medals;
    }
}
