package com.company.Players;

import com.company.Storage.Storage;
import com.company.Storage.VinylRecord;

public class VinylPlayer extends MusicPlayer{

    public VinylPlayer(String manufacturer, String model) {
        super(manufacturer, model);
    }

    @Override
    public void play(Storage storage) {
        if (storage instanceof VinylRecord) {
            System.out.println(this + " сейчас проигрывает:\n" + storage.getSong());
        } else {
            System.out.println(this + " не может вопроизвести песню с данного носителя!");
        }
    }

    @Override
    public String toString() {
        return "Винильный проигрыватель " + super.toString();
    }
}
