package com.example.tictactoe;

public class Game {
    enum players {
        PLAYER1("Player one"),
        PLAYER2("Player two"),
        NONE("None");

        String name;

        players(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    players currentPlayer;
    players[][] field;

    public Game() {
        this.currentPlayer = players.PLAYER1;
        this.field = new players[3][3];
    }

    public void restart() {
        this.currentPlayer = players.PLAYER1;
        this.field = new players[3][3];
    }

    public void step(int x, int y) {
        field[x][y] = currentPlayer;
        changePlayer();
    }

    public void changePlayer() {
        if (currentPlayer == players.PLAYER1) {
            currentPlayer = players.PLAYER2;
        } else {
            currentPlayer = players.PLAYER1;
        }
    }

    public players checkWin() {
        // while game is in the progress, then null, else player1 or player2 if somebody won and none if nobody won
        int emptyCells = 0;
        boolean wondr;
        boolean wondl;
        boolean wonh;
        boolean wonv;
        for (players player : new players[]{players.PLAYER1, players.PLAYER2}) {
            // diagonal lines
            wondr = true;
            wondl = true;
            for (int i = 0; i < 3; i++) {
                if (field[i][i] != player) wondr = false;
                if (field[i][2 - i] != player) wondl = false;
            }
            if (wondl || wondr) {
                return player;
            }
            // horizontal and vertical lines
            for (int i = 0; i < 3; i++) {
                wonh = true;
                wonv = true;
                for (int j = 0; j < 3; j++) {
                    if (field[i][j] == null) emptyCells++;
                    if (field[i][j] != player) wonh = false;
                    if (field[j][i] != player) wonv = false;
                }
                if (wonh || wonv) {
                    return player;
                }
            }
        }
        if (emptyCells == 0) {
            return players.NONE;
        }
        return null;
    }
}
