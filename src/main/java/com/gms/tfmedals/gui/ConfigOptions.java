package com.gms.tfmedals.gui;

import javafx.beans.property.*;
import org.json.JSONObject;

final class ConfigOptions {
    private final ObjectProperty<Console> console = new SimpleObjectProperty<>(Console.PS2);
    private final StringProperty partner = new SimpleStringProperty("");
    private final BooleanProperty filterLowMedals = new SimpleBooleanProperty(false);
    private final IntegerProperty pspTimerDelay = new SimpleIntegerProperty(0);
    private final IntegerProperty pspTimerUncertainty = new SimpleIntegerProperty(0);

    ConfigOptions() {
    }

    JSONObject toJson() {
        JSONObject jo = new JSONObject();
        jo.put("console", console.getValue());
        jo.put("partner", partner.getValue());
        jo.put("filterLowMedals", filterLowMedals.get());
        jo.put("pspTimerDelay", pspTimerDelay.get());
        jo.put("pspTimerUncertainty", pspTimerUncertainty.get());
        return jo;
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
