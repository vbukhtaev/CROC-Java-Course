package com.company.MusicPlayers;

import com.company.MusicStorages.FlashDrive;
import com.company.MusicStorages.MusicStorage;

import static com.company.Input.inputInteger;

public class USBPlayer extends MusicPlayer{

    public USBPlayer(String manufacturer, String model) {
        super(manufacturer, model);
    }

    @Override
    public void play(MusicStorage storage) {

        if (storage instanceof FlashDrive) {

            if (storage.isEmpty()) {
                System.out.println("Носитель пуст!");

            } else {
                storage.showSongs();
                int songNumber = inputInteger("Введите номер песни: ", storage.size());
                System.out.println(this + " сейчас воспроизводит:\n" + storage.getSongs().get(songNumber) + " с USB флешки");
            }

        } else  {
            System.out.println(this + " не может вопроизвести трек с данного носителя!");
        }
    }

    @Override
    public String toString() {
        return "USB проигрыватель " + this.getManufacturer() + " " + this.getModel();
    }
}
