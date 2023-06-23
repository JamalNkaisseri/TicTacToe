package com.example.game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeView extends Application {

    TicTacToeLogic logic = new TicTacToeLogic();

    private static final int GRID_SIZE = 3;

    //Array representation of the game board
    Button[][] board = new Button[GRID_SIZE][GRID_SIZE];

    GridPane grid = new GridPane();


    @Override
    public void start(Stage primaryStage) throws Exception {

        displayBoard();
        Scene scene = new Scene(grid,300,300);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void displayBoard() {
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        // Create 3x3 grid
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = new Button();
                button.setMinSize(80, 80); // Set button size
                grid.add(button, col, row);
                board[row][col] = button; // Store the button in the 2D array

                // Set event handler for button click
                button.setOnMouseClicked(mouseEvent -> handleButtonClick(button));
            }
        }

        // Now you can access the buttons array outside the loop
        // and perform operations on individual buttons if needed
        // For example, buttons[0][0] represents the top-left button.
    }

    private void playerMove(int row,int col){
        Button button = board[row][col];
        button.setText("X");
        button.setStyle("-fx-font-size: 30px;"); // Increase font size
    }

    private void handleButtonClick(Button button){
        int clickedRow = GridPane.getRowIndex(button);
        int clickedCol = GridPane.getColumnIndex(button);
        playerMove(clickedRow,clickedCol);
    }


}


