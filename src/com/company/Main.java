package com.company;

import com.company.Players.MusicPlayer;
import com.company.Players.VinylPlayer;
import com.company.Storage.CD;
import com.company.Storage.Storage;
import com.company.Storage.VinylRecord;

public class Main {

    /**
     * Homework #2
     * @version 1.0
     * @author Bukhtaev Vladislav
     */

    /*
        Задание.
        Необходимо разработать музыкальную систему.
        Существует несколько звуковоспроизводящих устройств (виниловый проигрыватель, CD, универсальный плеер и т.д.).
        Существует несколько носителей музыкальных композиций (пластинка, CD, флешка).
        Существуют несколько песен, у которых есть имя исполнителя (группы) и название.
        Звуковоспроизводящее устройство должно выводить в консоль информацию о том,
        что за устройство воспроизводит песню, ее исполнителя (группу) и название.
        В случае, если устройство не может воспроизвести песню с требуемого носителя, выводить соответствующее сообщение.
     */

    public static void main(String[] args) {

        MusicPlayer musicPlayer1 = new VinylPlayer("Грамофон", "9000");
        Storage storage1 = new VinylRecord("AC/DC", "Highway to Hell");
        Storage storage2 = new CD("AC/DC", "Highway to Hell");
        musicPlayer1.play(storage1);
        musicPlayer1.play(storage2);
    }
}
