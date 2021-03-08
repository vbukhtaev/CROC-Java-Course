package com.company.MusicPlayers;

import com.company.MusicStorages.MusicStorage;
import com.company.MusicStorages.VinylRecord;

import static com.company.Input.inputInteger;

public class VinylPlayer extends MusicPlayer {

    public VinylPlayer(String manufacturer, String model) {
        super(manufacturer, model);
    }

    @Override
    public void play(MusicStorage storage) {

        if (storage instanceof VinylRecord) {

            if (storage.isEmpty()) {
                System.out.println("Носитель пуст!");

            } else {
                storage.showSongs();
                int songNumber = inputInteger("Введите номер песни: ", storage.size());
                System.out.println(this + " сейчас воспроизводит:\n" + storage.getSongs().get(songNumber) + " с виниловой пластинки");
            }

        } else {
            System.out.println(this + " не может вопроизвести трек с данного носителя!");
        }
    }

    @Override
    public String toString() {
        return "Винильный проигрыватель " + this.getManufacturer() + " " + this.getModel();
    }
}
