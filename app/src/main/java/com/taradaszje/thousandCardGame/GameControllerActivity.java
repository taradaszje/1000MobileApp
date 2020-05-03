package com.taradaszje.thousandCardGame;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.taradaszje.thousandCardGame.service.DatabaseService;
import com.taradaszje.thousandCardGame.service.GameControllerService;

import java.util.Arrays;
import java.util.List;

public class GameControllerActivity extends AppCompatActivity {

    private List<Integer> playerScores = Arrays.asList(0, 0, 0, 0);
    private DatabaseService databaseService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_controller);
        databaseService = GameControllerService.GAME_SERVICE;
        databaseService.fetchPlayersNames(this);
    }

    public void sendData(View view) {
        final EditText editText = findViewById(R.id.editText);
        final String input = editText.getText().toString();
        editText.setText("");
        databaseService.saveData(input);
        Toast.makeText(this, "Sending data :)", Toast.LENGTH_SHORT).show();
    }

    public void sendBomb(View view) {
        switch (view.getId()) {
            case R.id.imageButton3: {
                this.playerScores.set(0, 1001);
                break;
            }
            case R.id.imageButton4: {
                this.playerScores.set(1, 1001);
                break;
            }
            case R.id.imageButton5: {
                this.playerScores.set(2, 1001);
                break;
            }
            case R.id.imageButton6: {
                this.playerScores.set(3, 1001);
                break;
            }
        }
        databaseService.saveData(this.playerScores);
        this.playerScores = Arrays.asList(0, 0, 0, 0);
        Toast.makeText(this, "Sending data :)", Toast.LENGTH_SHORT).show();
    }


}
