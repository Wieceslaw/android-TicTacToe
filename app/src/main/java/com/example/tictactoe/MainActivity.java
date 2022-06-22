package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView label = findViewById(R.id.textView);
        Game game = new Game();
        List<Button> buttons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int buttonID = getResources().getIdentifier("button_" + i + j, "id", getPackageName());
                Button button = (Button) findViewById(buttonID);
                buttons.add(button);
                int finalI = i;
                int finalJ = j;
                button.setOnClickListener(v -> {
                    if (game.currentPlayer == Game.players.PLAYER1) {
                        button.setText(getString(R.string.button_player_1));
                    } else {
                        button.setText(getString(R.string.button_player_2));
                    }
                    game.step(finalI, finalJ);
                    Game.players winner = game.checkWin();
                    button.setEnabled(false);
                    if (winner != null) {
                        label.setText(winner + " won!");
                        for (Button btn: buttons) {
                            btn.setEnabled(false);
                        }
                    } else {
                        label.setText(game.currentPlayer + " go!");
                    }
                });
            }
        }
        Button restartButton = findViewById(R.id.button_restart);
        restartButton.setOnClickListener(v -> {
            game.restart();
            for (Button button: buttons) {
                button.setEnabled(true);
                button.setText("");
            }
            label.setText(getString(R.string.start));
        });
    }
}