package com.taradaszje.thousandCardGame.service;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// singleton for database operation
public class GameControllerService implements DatabaseService {

    public static DatabaseService GAME_SERVICE = new GameControllerService();

    private DatabaseReference databaseReference;

    private GameControllerService() {
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    @Override
    public void saveData(final String textFieldInput) {
        final List<Integer> scores = convertPlayersScore(textFieldInput);
        databaseReference.child("gameRow").setValue(scores);
        databaseReference.child("gameRows").push().setValue(scores);
    }

    @Override
    public void saveData(final List<Integer> playerScores) {
        databaseReference.child("gameRow").setValue(playerScores);
        databaseReference.child("gameRows").push().setValue(playerScores);
    }

    //todo fetchPlayerNames
    @Override
    public List<String> fetchPlayerNames() {
        return null;
    }

    private List<Integer> convertPlayersScore(final String textFieldInput) {
        return Stream.of(textFieldInput.split(" ")).mapToInt(Integer::parseInt).boxed().collect(toList());
    }

}
