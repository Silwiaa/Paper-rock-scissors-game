package com.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameBoard {
    private BorderPane root = new BorderPane();

    private GridPane topGrid = new GridPane();
    private GridPane rightGrid = new GridPane();
    private GridPane grid = new GridPane();
    private GridPane bottomGrid = new GridPane();

    private ColumnConstraints column = new ColumnConstraints(100);
    private RowConstraints row = new RowConstraints(100);

    public static TextField playerScore = new TextField();
    public static TextField computerScore = new TextField();
    private Label computerScoreLabel = new Label("Computer score:");
    private Label playerScoreLabel = new Label("Player score:");

    public static Image paper = new Image("file:src/main/resources/five-fingers.png");
    public static Image scissors = new Image("file:src/main/resources/cross-fingers.png");
    public static Image rock = new Image("file:src/main/resources/nose.png");

    public static TextField userChoice = new TextField();
    public static TextField computerChoice = new TextField();
    public static Label userChoiceLabel = new Label("User choice is: ");
    public static Label computerChoiceLabel = new Label("Computer choice is: ");
    public static Label gameInfo = new Label("Choose your move");
    public static Button restartBtn = new Button();
    public static Button continueBtn = new Button();

    public BorderPane createBoard() {

        //Set font and other formatting for created grids
        formattingGrid(topGrid);
        formattingGrid(rightGrid);
        formattingGrid(grid);
        formattingGrid(bottomGrid);

        //Add children to topGrid

        formattingGridLabels(gameInfo);

        topGrid.add(gameInfo, 0, 0);

        //Add children to right grid
        formattingGridLabels(computerScoreLabel);
        formattingGridLabels(playerScoreLabel);

        playerScore.setText("0");
        playerScore.setDisable(true);
        computerScore.setText("0");
        computerScore.setDisable(true);
        restartBtn.setText("  RESTART  ");
        continueBtn.setText("  CONTINUE  ");

        setOnAction(restartBtn);
        setOnAction(continueBtn);
        restartBtn.setVisible(false);
        continueBtn.setVisible(false);

        rightGrid.add(playerScore, 1, 0);
        rightGrid.add(playerScoreLabel, 0, 0);

        rightGrid.add(computerScoreLabel, 0, 1);
        rightGrid.add(computerScore, 1, 1);

        rightGrid.add(restartBtn, 0, 2);
        rightGrid.add(continueBtn, 0, 3);

        //Add children to middle grid
        grid.getRowConstraints().add(row);
        for (int i = 0; i < 3; i++) {
            grid.getColumnConstraints().add(column);
            Tile tile = new Tile(i);
            Tile.tileList.add(tile);
            grid.add(tile, i, 0);
        }

        setStartFigures();

        //Add children to bottom grid
        formattingGridLabels(userChoiceLabel);
        formattingGridLabels(computerChoiceLabel);

        bottomGrid.add(userChoiceLabel, 0, 1);
        bottomGrid.add(userChoice, 1, 1);
        bottomGrid.add(computerChoiceLabel, 0, 2);
        bottomGrid.add(computerChoice, 1, 2);
        userChoiceLabel.setVisible(false);
        userChoice.setVisible(false);
        computerChoiceLabel.setVisible(false);
        computerChoice.setVisible(false);

        //Add grids for the main Border Pane
        root.setTop(topGrid);
        root.setRight(rightGrid);
        root.setCenter(grid);
        root.setBottom(bottomGrid);

        return root;
    }

    private void formattingGrid(GridPane gridToDecorate) {
        gridToDecorate.setBackground(new Background(
                new BackgroundFill(Color.web("#446C9D"), new CornerRadii(0), new Insets(0))));
        gridToDecorate.setPadding(new Insets(30));
        gridToDecorate.setAlignment(Pos.CENTER);

        if (gridToDecorate == grid) {
            gridToDecorate.setBackground(new Background(
                    new BackgroundFill(Color.web("#FFF"), new CornerRadii(0), new Insets(0))));
            gridToDecorate.setPadding(new Insets(30));
        }
        gridToDecorate.setVgap(10.0);
        gridToDecorate.setHgap(10.0);
    }

    private void formattingGridLabels(Label label) {
        label.setTextFill(Color.web("#FFF"));
        label.setFont(new Font("Roboto", 18));
        label.setPadding(new Insets(2));
    }

    public void setStartFigures() {
        for (int i = 0 ; i < Tile.tileList.size() ; i++) {
            Tile.tileList.get(i).getChildren().clear();
        }

        Figure figure1 = new Figure(paper);
        Figure figure2 = new Figure(rock);
        Figure figure3 = new Figure(scissors);

        Tile.tileList.get(0).getChildren().add(figure1.createFigure());
        Tile.tileList.get(1).getChildren().add(figure2.createFigure());
        Tile.tileList.get(2).getChildren().add(figure3.createFigure());
    }

    private void setOnAction(Button button) {
        button.setOnAction(event -> {
            if (button == restartBtn) {
                String info = "This action will restart the game. Press OK to continue";
                Alert restartAlert = new Alert(Alert.AlertType.CONFIRMATION, info, ButtonType.YES, ButtonType.NO);
                restartAlert.setTitle("Restart the game");
                restartAlert.showAndWait();
                if (restartAlert.getResult() == ButtonType.YES) {
                    restartGame();
                    setStartFigures();
                    restartBtn.setDisable(true);
                    continueBtn.setDisable(true);
                } else {
                    return;
                }
            } else {
                setStartFigures();
                continueBtn.setDisable(true);
            }
        });
    }

    public static void endGame() {
        int userPoints = Integer.parseInt(playerScore.getText());
        int computerPoints = Integer.parseInt(computerScore.getText());

        if (userPoints == 10 || computerPoints == 10) {
            if (userPoints == computerPoints) {
                gameInfo.setText("Nobody wins the game - both players has the same points");
            } else if (userPoints > computerPoints) {
                gameInfo.setText( "User wins the game with score: " + userPoints + " to " + computerPoints + ".");
            } else {
                gameInfo.setText( "Computer wins the game with score: " + computerPoints + " to " + userPoints + ".");
            }
            restartGame();
        }
    }

    public static void restartGame() {
        playerScore.setText("0");
        computerScore.setText("0");
        continueBtn.setVisible(false);
    }
}
