package com.company.MusicStorages;

public class VinylRecord extends MusicStorage {

    public VinylRecord(String vinylRecordName) {
        super(vinylRecordName);
    }

    @Override
    public String toString() {
        return "[Виниловая пластинка] " + this.getStorageName();
    }
}
