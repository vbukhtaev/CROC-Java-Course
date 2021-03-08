package com.company.MusicStorages;

public class CompactDisk extends MusicStorage {

    public CompactDisk(String compactDiskName) {
        super(compactDiskName);
    }

    @Override
    public String toString() {
        return "[Компакт-диск] " + this.getStorageName();
    }
}
