package com.company.Players;

import com.company.Storage.FlashDrive;
import com.company.Storage.Storage;

public class USBPlayer extends MusicPlayer{

    public USBPlayer(String manufacturer, String model) {
        super(manufacturer, model);
    }

    @Override
    public void play(Storage storage) {
        if (storage instanceof FlashDrive) {
            System.out.println(this + " сейчас проигрывает:\n" + storage.getSong());
        } else {
            System.out.println(this + " не может вопроизвести песню с данного носителя!");
        }
    }

    @Override
    public String toString() {
        return "USB проигрыватель " + super.toString();
    }
}
