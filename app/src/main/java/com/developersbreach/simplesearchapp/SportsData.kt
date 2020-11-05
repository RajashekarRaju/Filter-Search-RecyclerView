package com.developersbreach.simplesearchapp


fun sportsList(): List<Sports> {

    val sportsList = ArrayList<Sports>()

    listOf(
        "Rugby", "Cricket", "Basketball", "Hockey", "Volleyball", "Esports", "Kabaddi",
        "Baseball", "MMA", "Soccer", "Handball", "Tennis",
    ).forEach {
        sportsList.add(Sports(it))
    }

    return sportsList
}