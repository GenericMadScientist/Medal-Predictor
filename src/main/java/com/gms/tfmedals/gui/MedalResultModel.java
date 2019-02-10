package com.gms.tfmedals.gui;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MedalResultModel {
    private final SimpleStringProperty duelistName;
    private final SimpleIntegerProperty medals;

    public MedalResultModel(String duelistName, Integer medals) {
        this.duelistName = new SimpleStringProperty(duelistName);
        this.medals = new SimpleIntegerProperty(medals);
    }

    public String getDuelistName() {
        return duelistName.get();
    }

    public void setDuelistName(String name) {
        duelistName.set(name);
    }

    public Integer getMedals() {
        return medals.getValue();
    }

    public void setMedals(Integer medals) {
        this.medals.set(medals);
    }
}
