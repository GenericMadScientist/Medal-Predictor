package com.gms.tfmedals.gui;

import javafx.beans.property.*;
import org.json.JSONObject;

final class ConfigOptions {
    private final ObjectProperty<Console> console = new SimpleObjectProperty<>(Console.PS2);
    private final StringProperty partner = new SimpleStringProperty("-");
    private final BooleanProperty filterLowMedals = new SimpleBooleanProperty(false);
    private final IntegerProperty pspTimerDelay = new SimpleIntegerProperty(0);
    private final IntegerProperty pspTimerUncertainty = new SimpleIntegerProperty(0);

    ConfigOptions() {
    }

    void readFromJson(JSONObject json) {
        Console newConsole = json.getEnum(Console.class, "console");
        String newPartner = json.getString("partner");
        boolean newFilterLowMedals = json.getBoolean("filterLowMedals");
        int newPspTimerDelay = json.getInt("pspTimerDelay");
        int newPspTimerUncertainty = json.getInt("pspTimerUncertainty");

        console.setValue(newConsole);
        partner.setValue(newPartner);
        filterLowMedals.set(newFilterLowMedals);
        pspTimerDelay.set(newPspTimerDelay);
        pspTimerUncertainty.set(newPspTimerUncertainty);
    }

    JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("console", console.getValue());
        json.put("partner", partner.getValue());
        json.put("filterLowMedals", filterLowMedals.get());
        json.put("pspTimerDelay", pspTimerDelay.get());
        json.put("pspTimerUncertainty", pspTimerUncertainty.get());
        return json;
    }

    public Console getConsole() {
        return console.getValue();
    }

    public void setConsole(Console console) {
        this.console.setValue(console);
    }

    public ObjectProperty<Console> consoleProperty() {
        return console;
    }

    public String getPartner() {
        return partner.getValue();
    }

    public void setPartner(String partner) {
        this.partner.setValue(partner);
    }

    public StringProperty partnerProperty() {
        return partner;
    }

    public boolean getFilterLowMedals() {
        return filterLowMedals.get();
    }

    public void setFilterLowMedals(boolean filterLowMedals) {
        this.filterLowMedals.set(filterLowMedals);
    }

    public BooleanProperty filterLowMedalsProperty() {
        return filterLowMedals;
    }

    public int getPspTimerDelay() {
        return pspTimerDelay.get();
    }

    public void setPspTimerDelay(int pspTimerDelay) {
        this.pspTimerDelay.set(pspTimerDelay);
    }

    public IntegerProperty pspTimerDelayProperty() {
        return pspTimerDelay;
    }

    public int getPspTimerUncertainty() {
        return pspTimerUncertainty.get();
    }

    public void setPspTimerUncertainty(int pspTimerUncertainty) {
        this.pspTimerUncertainty.set(pspTimerUncertainty);
    }

    public IntegerProperty pspTimerUncertaintyProperty() {
        return pspTimerUncertainty;
    }
}
