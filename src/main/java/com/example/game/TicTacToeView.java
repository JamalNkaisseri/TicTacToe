package com.example.game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToeView extends Application {

    private static final int GRID_SIZE = 3;

    GridPane grid = new GridPane();


    @Override
    public void start(Stage primaryStage) throws Exception {

        printBoard();
        Scene scene = new Scene(grid,300,300);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void printBoard(){
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        //Create 3x3 grid
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = new Button();
                button.setMinSize(80, 80); // Set button size
                grid.add(button, col, row);
            }
        }
    }
}


