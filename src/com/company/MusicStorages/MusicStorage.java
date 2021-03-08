package com.company.MusicStorages;

import java.util.Map;
import java.util.TreeMap;

public abstract class MusicStorage {

    private String storageName;
    private Map<Integer, Song> songs;

    public MusicStorage(String storageName) {
        this.storageName = storageName;
        this.songs = new TreeMap<>();
    }

    public void addSong(String author, String title) {
        this.songs.put(songs.keySet().size() + 1, new Song(author, title));
    }

    private class Song {

        private String author;
        private String title;

        public Song(String author, String title) {
            this.author = author;
            this.title = title;
        }

        @Override
        public String toString() {
            return author + " - \"" + title + "\"";
        }
    }

    public String getStorageName() {
        return storageName;
    }

    public Map<Integer, Song> getSongs() {
        return songs;
    }

    public void showSongs() {

        if (this.songs.isEmpty()) {
            System.out.println("Носитель пуст!");

        } else {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Треки на этом носителе:");
            for (Map.Entry<Integer, Song> entry : songs.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
        }
    }

    public int size() {
        return this.songs.size();
    }

    public boolean isEmpty() {
        return this.songs.isEmpty();
    }
}
