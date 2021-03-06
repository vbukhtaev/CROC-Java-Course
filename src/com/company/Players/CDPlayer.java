package com.company.Players;

import com.company.Storage.CD;
import com.company.Storage.Storage;

public class CDPlayer extends MusicPlayer {

    public CDPlayer(String manufacturer, String model) {
        super(manufacturer, model);
    }

    @Override
    public void play(Storage storage) {
        if (storage instanceof CD) {
            System.out.println(this + " сейчас проигрывает:\n" + storage.getSong());
        } else {
            System.out.println(this + " не может вопроизвести песню с данного носителя!");
        }
    }

    @Override
    public String toString() {
        return "CD проигрыватель " + super.toString();
    }
}
