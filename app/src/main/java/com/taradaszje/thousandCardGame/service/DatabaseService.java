package com.taradaszje.thousandCardGame.service;

import java.util.List;

public interface DatabaseService {
    void saveData(final String playerScores);
    void saveData(final List<Integer> playerScores);
    List<String> fetchPlayerNames();
}
