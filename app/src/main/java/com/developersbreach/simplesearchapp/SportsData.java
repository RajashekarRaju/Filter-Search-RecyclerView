package com.developersbreach.simplesearchapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SportsData {

    private final List<String> listData = Arrays.asList(
            "Rugby", "Cricket", "Basketball", "Hockey", "Volleyball", "Esports",
            "Kabaddi", "Baseball", "MMA", "Soccer", "Handball", "Tennis"
    );

    public List<Sports> listData() {
        List<Sports> sportsList = new ArrayList<>();
        for (String currentString : listData) {
            sportsList.add(new Sports(currentString));
        }
        return sportsList;
    }
}
