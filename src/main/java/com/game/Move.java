package com.game;

import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Move {
    public static Image paper = new Image("file:src/main/resources/five.png");
    public static Image rock = new Image("file:src/main/resources/rock.png");
    public static Image scissors = new Image("file:src/main/resources/scissors.png");

    public static String makeUserMove(int tileNumber) {
        String figure = makeMove(tileNumber, "user");
        return figure;
    }

    public static String makeComputerMove() {
        List<Integer> possibleComputerMoves = new ArrayList<>(Arrays.asList(0, 1, 2));
        Random rand = new Random();
        int x = rand.nextInt(possibleComputerMoves.size());
        int position = possibleComputerMoves.get(x);
        String figure = makeMove(position, "computer");
        return figure;
    }

    public static String makeMove(int position, String player) {
        String figure = "";
        if (position == 0) {
            figure = "paper";
        } else if (position == 1) {
            figure = "rock";
        } else {
            figure = "scissors";
        }
        showChosenFigure(player, figure);
        return figure;
    }

    public static void showChosenFigure(String player, String figure) {
        if (player == "user") {
            GameBoard.userChoice.setText(figure);
            GameBoard.userChoice.setVisible(true);
            GameBoard.userChoice.setDisable(true);
            GameBoard.userChoiceLabel.setVisible(true);
            showOnScreen(player, figure);
        } else {
            GameBoard.computerChoice.setText(figure);
            GameBoard.computerChoice.setVisible(true);
            GameBoard.computerChoice.setDisable(true);
            GameBoard.computerChoiceLabel.setVisible(true);
            showOnScreen(player, figure);
        }
    }

    public static void showOnScreen(String player, String figure) {

        Image userImage = checkImage(player, figure);
        Image computerImage = checkImage(player, figure);

        Figure userFigure = new Figure(userImage);
        Figure computerFigure = new Figure(computerImage);

        if (player == "user") {
            for (int i = 0 ; i < Tile.tileList.size() ; i++) {
                Tile.tileList.get(i).getChildren().clear();
            }
            Tile.tileList.get(0).getChildren().add(userFigure.createFigure());
        } else {
            Tile.tileList.get(2).getChildren().add(computerFigure.createFigure());
        }
    }

    public static Image checkImage(String player, String figure) {
        if (player == "user") {
            if (figure == "paper") {
                return GameBoard.paper;
            } else if (figure == "rock") {
                return GameBoard.rock;
            } else {
                return GameBoard.scissors;
            }
        } else {
            if (figure == "paper") {
                return paper;
            } else if (figure == "rock") {
                return rock;
            } else {
                return scissors;
            }
        }
    }
}
