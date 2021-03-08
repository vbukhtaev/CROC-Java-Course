package com.company.MusicPlayers;

import java.util.Map;
import java.util.TreeMap;

public class Equipment {

    private static final Map<Integer, AbleToPlay> equipment = new TreeMap<>();

    public static AbleToPlay get(int key) {
        return equipment.get(key);
    }

    public static void fill() {

        equipment.put(1, new USBPlayer("Sony", "NW-E394"));
        equipment.put(2, new USBPlayer("Apple", "iPod Touch"));
        equipment.put(3, new CDPlayer("Philips", "EXP2546"));
        equipment.put(4, new CDPlayer("Sony", "D-NE730"));
        equipment.put(5, new VinylPlayer("Crosley", "Voyager"));
        equipment.put(6, new VinylPlayer("Lenco", "L-30BK"));
    }

    public static void show() {

        System.out.println("------------------------------------------------------------------------");
        System.out.println("Доступные воспроизводящие устройства:");
        for (Map.Entry<Integer, AbleToPlay> entry : equipment.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
    }

    public static int size() {
        return equipment.size();
    }
}
