package com.company.MusicPlayers;

public abstract class MusicPlayer implements AbleToPlay{

    private String manufacturer;
    private String model;

    public MusicPlayer(String manufacturer, String model) {
        this.manufacturer = manufacturer;
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }
}
