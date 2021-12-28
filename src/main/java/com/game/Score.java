package com.game;

public class Score {
    public static void checkWhoWins(String userFigure, String computerFigure) {
        int winner = 3;
        String gameInfo = "";

        if (userFigure == computerFigure) {
            gameInfo = "Both players has chosen " + userFigure + ". Nobody wins.";
            winner = 0;

        } else {
            if (userFigure == "paper") {

                if (computerFigure == "rock") {
                    userFigure = formatString(userFigure);
                    gameInfo = userFigure + " covers " + computerFigure + ". User wins.";
                    winner = 1;
                } else {
                    computerFigure = formatString(computerFigure);
                    gameInfo = computerFigure + " cuts " + userFigure + ". Computer wins.";
                    formatString(computerFigure);
                    winner = 2;
                }

            } else if (userFigure == "rock") {

                if (computerFigure == "scissors") {
                    userFigure = formatString(userFigure);
                    gameInfo = userFigure + " breaks " + computerFigure + ". User wins.";
                    winner = 1;
                } else {
                    computerFigure = formatString(computerFigure);
                    gameInfo = computerFigure + " covers " + userFigure + ". Computer wins.";
                    winner = 2;
                }

            } else {

                if (computerFigure == "paper") {
                    userFigure = formatString(userFigure);
                    gameInfo = userFigure + " cuts " + computerFigure + ". User wins.";
                    winner = 1;
                } else {
                    computerFigure = formatString(computerFigure);
                    gameInfo = computerFigure + " breaks " + userFigure + ". Computer wins.";
                    winner = 2;
                }
            }
        }

        GameBoard.gameInfo.setText(gameInfo);
        GameBoard.restartBtn.setDisable(false);
        GameBoard.continueBtn.setDisable(false);
        GameBoard.continueBtn.setVisible(true);
        addScore(1, winner);
    }

    public static void addScore(int points, int winner) {
        if (winner == 1) {
            int x = Integer.parseInt(GameBoard.playerScore.getText());
            x += points;
            GameBoard.playerScore.setText(Integer.toString(x));
        } else if (winner == 2) {
            int x = Integer.parseInt(GameBoard.computerScore.getText());
            x += points;
            GameBoard.computerScore.setText(Integer.toString(x));
        } else {
            return;
        }
        GameBoard.endGame();
    }

    public static String formatString(String text) {
        String firsLetter = text.substring(0, 1);
        text = text.substring(1, text.length());
        firsLetter = firsLetter.toUpperCase();
        return firsLetter + text;
    }
}
