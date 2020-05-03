package com.taradaszje.thousandCardGame.service;

import android.app.Activity;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.taradaszje.thousandCardGame.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

// singleton for database operation
public class GameControllerService implements DatabaseService {

    public static DatabaseService GAME_SERVICE = new GameControllerService();
    private List<String> names = new ArrayList<>();
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
    public void fetchPlayersNames(Activity activity) {
        databaseReference.child("players").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Map<String, Object>> elements = dataSnapshot
                        .getValue(new GenericTypeIndicator<List<Map<String, Object>>>() {
                        });
                elements.forEach(map -> names.add((String) map.get("name")));

                TextView player1 = activity.findViewById(R.id.textView);
                player1.setText(names.get(0));
                TextView player2 = activity.findViewById(R.id.textView2);
                player2.setText(names.get(1));
                TextView player3 = activity.findViewById(R.id.textView3);
                player3.setText(names.get(2));
                TextView player4 = activity.findViewById(R.id.textView4);
                if (names.size() > 3) {
                    player4.setText(names.get(3));
                }else{
                    player4.setText("");
                    ImageButton igButton = activity.findViewById(R.id.imageButton6);
                    igButton.setClickable(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private List<Integer> convertPlayersScore(final String textFieldInput) {
        return Stream.of(textFieldInput.split(" ")).mapToInt(Integer::parseInt).boxed().collect(toList());
    }
}
