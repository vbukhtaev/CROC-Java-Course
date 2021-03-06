package com.company.Players;

import com.company.Storage.Storage;

public abstract class MusicPlayer {

    private String manufacturer;
    private String model;

    public MusicPlayer(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public abstract void play(Storage storage);

    @Override
    public String toString() {
        return "\"" + this.getManufacturer() + " " + this.getModel() + "\"";
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }
}
