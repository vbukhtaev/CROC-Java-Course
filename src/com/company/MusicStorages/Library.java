package com.company.MusicStorages;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Library {

    private static final Map<Integer, MusicStorage> library = new TreeMap<>();

    public static MusicStorage get(int key) {
        return library.get(key);
    }

    public static void fill() {

        // Создаем наши носители.
        MusicStorage storage1 = new CompactDisk("Old School Metal");
        storage1.addSong("AC/DC", "Thunderstruck");
        storage1.addSong("AC/DC", "TNT");
        storage1.addSong("KISS", "I Love It Loud");
        storage1.addSong("Running Wild", "Riding the Storm");
        storage1.addSong("Metallica", "One");

        MusicStorage storage2 = new CompactDisk("00s Metal Classics");
        storage2.addSong("Sabaton", "Ghost Division");
        storage2.addSong("Alestorm", "Keelhauled");
        storage2.addSong("Rammstein", "AMERIKA");
        storage2.addSong("AC/DC", "Stiff Upper Lip");
        storage2.addSong("Metallica", "St.Angel");

        MusicStorage storage3 = new CompactDisk("00s Rock Anthems");
        storage3.addSong("Linkin Park", "In the End");
        storage3.addSong("The White Stripes", "Seven Nation Army");
        storage3.addSong("AC/DC", "Rock N Roll Train");
        storage3.addSong("Green Day", "American Idiot");
        storage3.addSong("Nickelback", "Rockstar");

        MusicStorage storage4 = new FlashDrive("Daily Mix");
        storage4.addSong("American Authors", "Brick by Brick");
        storage4.addSong("Scope", "Carry On");
        storage4.addSong("Creedence Clearwater Revival", "Run Through The Jungle");
        storage4.addSong("The Doors", "Riders on the Storm");
        storage4.addSong("Inner Circle", "Bad Boys");

        MusicStorage storage5 = new FlashDrive("Hot Road Trip");
        storage5.addSong("Airbourne", "Runnin' Wild");
        storage5.addSong("AC/DC", "HighWay to Hell");
        storage5.addSong("Metallica", "Enter Sandman");
        storage5.addSong("AC/DC", "Thunderstruck");
        storage5.addSong("Black Sabbath", "Paranoid");

        MusicStorage storage6 = new VinylRecord("Jazz Discovered");
        storage6.addSong("Postmodern Jukebox", "Scott Bradlee s");
        storage6.addSong("Postmodern Jukebox", "Like a Prayer");
        storage6.addSong("Oscar Peterson", "You Look Good To Me");
        storage6.addSong("Frank Sinatra", "I've Goy You Under My Skin");
        storage6.addSong("Natalie Cole", "Autumn Leaves");

        MusicStorage storage7 = new VinylRecord("Creedence Clearwater Revival");
        storage7.addSong("Creedence Clearwater Revival", "Fortunate Son");
        storage7.addSong("Creedence Clearwater Revival", "Run Through The Jungle");
        storage7.addSong("Creedence Clearwater Revival", "Bad Mood Rising");
        storage7.addSong("Creedence Clearwater Revival", "Green River");
        storage7.addSong("Creedence Clearwater Revival", "Who'll Stop The Rain");

        MusicStorage storage8 = new VinylRecord("П. И. Чайковский Anthology");
        storage8.addSong("П. И. Чайковский", "Вальс цветов");
        storage8.addSong("П. И. Чайковский", "Танец Феи Драже");
        storage8.addSong("П. И. Чайковский", "Танец маленьких лебедей");
        storage8.addSong("П. И. Чайковский", "Славянский марш");
        storage8.addSong("П. И. Чайковский", "Детский альбом");
        storage8.addSong("П. И. Чайковский", "Времена года");
        storage8.addSong("П. И. Чайковский", "Спящая красавица");

        library.put(1, storage1);
        library.put(2, storage2);
        library.put(3, storage3);
        library.put(4, storage4);
        library.put(5, storage5);
        library.put(6, storage6);
        library.put(7, storage7);
        library.put(8, storage8);

    }

    public static void addMusicStorage(String storageName, int storageType) {

        switch (storageType) {
            case 1:
                library.put(
                        (library.keySet().size() + 1),
                        new VinylRecord(storageName)
                );
                break;
            case 2:
                library.put(
                        (library.keySet().size() + 1),
                        new CompactDisk(storageName)
                );
                break;
            case 3:
                library.put(
                        (library.keySet().size() + 1),
                        new FlashDrive(storageName)
                );
                break;
            default:
                System.out.println("Ошибка ввода!");
        }
    }

    public static void show() {

        System.out.println("------------------------------------------------------------------------");
        System.out.println("Доступные носители:");
        for (Map.Entry<Integer, MusicStorage> entry : library.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    public static int size() {
        return library.size();
    }
}
