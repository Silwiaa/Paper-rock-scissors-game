package com.game;

import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;

import java.util.*;

public class Tile extends StackPane {
    private int position;
    public static List<Tile> tileList = new ArrayList<>();
    private MouseButton mouseButton;

    public Tile(int possition) {
        this.position = possition;
        onClickPane();
    }

    public int getPossition() {
        return position;
    }

    public void onClickPane() {
        setOnMouseClicked(event -> {
            mouseButton = event.getButton();
            if (mouseButton == MouseButton.PRIMARY) {
                int tileNumber = getPossition();
                GameBoard.restartBtn.setVisible(true);
                String userFigure = Move.makeUserMove(tileNumber);
                String computerFigure = Move.makeComputerMove();
                System.out.println("User figure: " + userFigure);
                System.out.println("Computer figure: " + computerFigure);
                Score.checkWhoWins(userFigure, computerFigure);
            }
        });
    }
}
