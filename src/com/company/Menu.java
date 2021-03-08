package com.company;

import com.company.MusicPlayers.AbleToPlay;
import com.company.MusicPlayers.Equipment;
import com.company.MusicStorages.Library;
import com.company.MusicStorages.MusicStorage;

import static com.company.Input.inputInteger;
import static com.company.Input.inputString;

public class Menu {

    public static void chooseAction() {

        System.out.println(
                "------------------------------------------------------------------------\n" +
                "Выберите действие:\n" +
                "[1] - Воспроизвести трек из библиотеки.\n" +
                "[2] - Добавить носитель в библиотеку.\n" +
                "[3] - Добавить трек в библиотеку.\n" +
                "[4] - Просмотреть библиотеку.\n" +
                "[5] - Выйти."
        );

        switch (inputInteger("Ваш выбор: ", 5)) {

            case 1:
                chooseMusic();
                break;

            case 2:
                addStorage();
                break;

            case 3:
                addMusic();
                break;

            case 4:
                showLibrary();
                break;

            case 5:
                System.out.println("[Выход]");
                break;
        }
    }

    private static void chooseMusic() {

        Library.show();
        int storageNumber = inputInteger("Введите номер носителя: ", Library.size());

        Equipment.show();
        int playerNumber = inputInteger("Введите номер воспроизводящего устройства: ", Equipment.size());

        MusicStorage musicStorage = Library.get(storageNumber);
        AbleToPlay ableToPlay = Equipment.get(playerNumber);

        ableToPlay.play(musicStorage);

        chooseAction();
    }

    private static void addStorage() {

        String storageName = inputString("Введите название носителя: ");

        System.out.println("Введите тип носителя:\n" +
                "[1] - Виниловая платинка.\n" +
                "[2] - Компакт-диск.\n" +
                "[3] - USB.\n");

        int storageType = inputInteger("Ввод: ", 3);

        Library.addMusicStorage(storageName, storageType);

        System.out.println("Носитель успешно добавлен в библиотеку!");

        chooseAction();
    }

    private static void addMusic() {
        Library.show();
        MusicStorage storage = Library.get(inputInteger("Введите номер носителя: ", Library.size()));
        storage.addSong(
                inputString("Введите автора трека: "),
                inputString("Введите название трека: ")
        );
        chooseAction();
    }

    private static void showLibrary() {
        Library.show();
        MusicStorage storage = Library.get(inputInteger("Введите номер носителя: ", Library.size()));
        storage.showSongs();
        chooseAction();
    }
}
