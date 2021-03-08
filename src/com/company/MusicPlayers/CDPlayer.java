package com.company.MusicPlayers;

import com.company.MusicStorages.CompactDisk;
import com.company.MusicStorages.MusicStorage;

import static com.company.Input.inputInteger;

public class CDPlayer extends MusicPlayer {

    public CDPlayer(String manufacturer, String model) {
        super(manufacturer, model);
    }

    @Override
    public void play(MusicStorage storage) {

        if (storage instanceof CompactDisk) {

            if (storage.isEmpty()) {
                System.out.println("Носитель пуст!");

            } else {
                storage.showSongs();
                int songNumber = inputInteger("Введите номер песни: ", storage.size());
                System.out.println(this + " сейчас воспроизводит:\n" + storage.getSongs().get(songNumber) + " с компакт-диска");
            }

        } else  {
            System.out.println(this + " не может вопроизвести трек с данного носителя!");
        }
    }

    @Override
    public String toString() {
        return "CD проигрыватель " + this.getManufacturer() + " " + this.getModel();
    }
}
