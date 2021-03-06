package com.company;

import com.company.Storage.Storage;

import java.util.Set;
import java.util.TreeSet;

public class Library {
    private static final Set<Storage> library = new TreeSet<>();

    public static Set<Storage> getLibrary() {
        return library;
    }
}
