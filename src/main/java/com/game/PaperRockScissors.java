package com.game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class PaperRockScissors extends Application {
    BorderPane root = new BorderPane();

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Create game board and main root for scene
        GameBoard board = new GameBoard();
        root = board.createBoard();

        Scene scene = new Scene(root, 910, 610);
        primaryStage.setTitle("Paper Rock Scissors");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
