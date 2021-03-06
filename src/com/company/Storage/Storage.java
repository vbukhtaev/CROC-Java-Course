package com.company.Storage;

public abstract class Storage implements Comparable<Storage>{

    private Song song;

    public Storage(String author, String title) {
        this.song = new Song(author, title);
    }

    public static class Song {

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

    public Song getSong() {
        return song;
    }

    @Override
    public int compareTo(Storage another) {

        int result = this.song.author.compareTo(another.song.author);
        if (result == 0) {
            result = this.song.title.compareTo(another.song.title);
        }

        return result;
    }
}
