package com.company.MusicStorages;

public class FlashDrive extends MusicStorage {

    public FlashDrive(String flashDriveName) {
        super(flashDriveName);
    }

    @Override
    public String toString() {
        return "[USB] " + this.getStorageName();
    }
}
