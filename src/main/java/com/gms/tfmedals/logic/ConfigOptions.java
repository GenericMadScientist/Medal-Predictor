package com.gms.tfmedals.logic;

import javafx.beans.property.*;
import org.json.JSONObject;

public final class ConfigOptions {
    private final ObjectProperty<Console> console = new SimpleObjectProperty<>(Console.PS2);
    private final StringProperty partner = new SimpleStringProperty("-");
    private final BooleanProperty filterLowMedals = new SimpleBooleanProperty(false);
    private final IntegerProperty pspTimerDelay = new SimpleIntegerProperty(0);
    private final IntegerProperty pspTimerUncertainty = new SimpleIntegerProperty(0);
    private Long lastTime = null;

    /**
     * Constructor for default options.
     */
    public ConfigOptions() {
    }

    /**
     * Reads in the options from a JSON object.
     *
     * @param json the parsed JSON to read options from
     */
    public void readFromJson(final JSONObject json) {
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

    /**
     * @return the options in JSON form
     */
    public JSONObject toJson() {
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

    /**
     * @return property wrapping the console
     */
    public ObjectProperty<Console> consoleProperty() {
        return console;
    }

    public String getPartner() {
        return partner.getValue();
    }

    /**
     * @return property wrapping the partner
     */
    public StringProperty partnerProperty() {
        return partner;
    }

    public boolean getFilterLowMedals() {
        return filterLowMedals.get();
    }

    /**
     * @return property wrapping whether to filter out low medals
     */
    public BooleanProperty filterLowMedalsProperty() {
        return filterLowMedals;
    }

    public int getPspTimerDelay() {
        return pspTimerDelay.get();
    }

    /**
     * @return property wrapping the PSP timer delay
     */
    public IntegerProperty pspTimerDelayProperty() {
        return pspTimerDelay;
    }

    public int getPspTimerUncertainty() {
        return pspTimerUncertainty.get();
    }

    /**
     * @return property wrapping the PSP timer uncertainty
     */
    public IntegerProperty pspTimerUncertaintyProperty() {
        return pspTimerUncertainty;
    }

    public Long getLastTime() {
        return lastTime;
    }

    public void setLastTime(final Long newLastTime) {
        lastTime = newLastTime;
    }
}
