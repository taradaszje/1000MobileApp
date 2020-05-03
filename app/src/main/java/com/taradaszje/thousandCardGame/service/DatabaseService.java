package com.taradaszje.thousandCardGame.service;

import android.app.Activity;

import java.util.List;

public interface DatabaseService {
    void saveData(final String playerScores);
    void saveData(final List<Integer> playerScores);
    void fetchPlayersNames(Activity activity);
}
