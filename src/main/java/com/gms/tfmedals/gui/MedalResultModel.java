package com.gms.tfmedals.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MedalResultModel {
    private final StringProperty duelistName;
    private Integer medals;

    MedalResultModel(String duelistName, Integer medals) {
        this.duelistName = new SimpleStringProperty(duelistName);
        this.medals = medals;
    }

    public String getDuelistName() {
        return duelistName.get();
    }

    public void setDuelistName(String name) {
        duelistName.set(name);
    }

    public Integer getMedals() {
        return medals;
    }

    public void setMedals(Integer medals) {
        this.medals = medals;
    }
}
