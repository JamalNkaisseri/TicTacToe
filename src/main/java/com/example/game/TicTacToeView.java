package com.example.game;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

// The current problem is within computerMove(),minimax()

public class TicTacToeView extends Application {

    TicTacToeLogic logic = new TicTacToeLogic();

    private static final int GRID_SIZE = 3;


    //Array representation of the game board


    Button[][] board = new Button[GRID_SIZE][GRID_SIZE];

    GridPane grid = new GridPane();

    private boolean playerTurn;

    private static final char PLAYER_SYMBOL = 'X';
    private static final char COMPUTER_SYMBOL = 'O';
    private static final char EMPTY_SYMBOL = ' ';



    @Override
    public void start(Stage primaryStage) throws Exception {

        setupDisplay();
        play();
        Scene scene = new Scene(grid,300,300);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private static class Move{
        int row;
        int col;
        int score;

        public Move(int row,int col,int score){
            this.row = row;
            this.col = col;
            this.score = score;
        }
    }

    private Move minimax(int depth, boolean maximizingPlayer) {
        if (logic.checkWin(board,PLAYER_SYMBOL)) {
            return new Move(-1, -1, -10);
        } else if (logic.checkWin(board,COMPUTER_SYMBOL)) {
            return new Move(-1, -1, 10);
        } else if (logic.isBoardFull(board)) {
            return new Move(-1, -1, 0);
        }

        Move bestMove = new Move(-1, -1, maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE);

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (board[row][col].getText().equals(String.valueOf(EMPTY_SYMBOL))) {
                    board[row][col].setText(maximizingPlayer ? String.valueOf(COMPUTER_SYMBOL) : String.valueOf(PLAYER_SYMBOL));
                    Move move = minimax( depth + 1, !maximizingPlayer);
                    board[row][col].setText(String.valueOf(EMPTY_SYMBOL));

                    if (maximizingPlayer) {
                        if (move.score == 10) { // Computer wins
                            bestMove.row = row;
                            bestMove.col = col;
                            bestMove.score = move.score;
                            return bestMove; // Return immediately if a winning move is found
                        } else if (move.score > bestMove.score) {
                            bestMove.row = row;
                            bestMove.col = col;
                            bestMove.score = move.score;
                        }
                    } else {
                        if (move.score == -10) { // Player wins
                            bestMove.row = row;
                            bestMove.col = col;
                            bestMove.score = move.score;
                            return bestMove; // Return immediately if a winning move is found
                        } else if (move.score < bestMove.score) {
                            bestMove.row = row;
                            bestMove.col = col;
                            bestMove.score = move.score;
                        }
                    }
                }
            }
        }

        return bestMove;
    }
    public void computerMove() {
        Move bestMove = minimax( 0, true);
        board[bestMove.row][bestMove.col].setText(String.valueOf(COMPUTER_SYMBOL));
    }

    // Set up the 3x3 grid on which the game will be played
    private void setupDisplay() {
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
            }

            // Now you can access the buttons array outside the loop
            // and perform operations on individual buttons if needed
            // For example, buttons[0][0] represents the top-left button.
        }
    }

    private void displayBoard() {

        // Set event handlers for button clicks
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = board[row][col];
                button.setOnMouseClicked(mouseEvent -> handleButtonClick(button));
            }
        }
    }


    private void playerMove(int row, int col) {
        Button button = board[row][col];
        button.setText("X");
        button.setStyle("-fx-font-size: 30px;"); // Increase font size
    }


    private void handleButtonClick(Button button){
        int clickedRow = GridPane.getRowIndex(button);
        int clickedCol = GridPane.getColumnIndex(button);
        playerMove(clickedRow,clickedCol);
    }

    private void waitForPlayerMove() {
        // Create a CompletableFuture to handle the player's move
        CompletableFuture<Button> playerMoveFuture = new CompletableFuture<>();

        // Set event handlers for button clicks
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = board[row][col];
                button.setOnMouseClicked(event -> playerMoveFuture.complete(button));
            }
        }

        try {
            // Wait for the player's move by blocking until a button is clicked
            Button clickedButton = playerMoveFuture.get();
            int row = GridPane.getRowIndex(clickedButton);
            int col = GridPane.getColumnIndex(clickedButton);

            // Process the player's move (e.g., update game state)
            playerMove(row, col);

            // Reset event handlers for buttons
            for ( row = 0; row < GRID_SIZE; row++) {
                for ( col = 0; col < GRID_SIZE; col++) {
                    Button button = board[row][col];
                    button.setOnMouseClicked(mouseEvent -> handleButtonClick(button));
                }
            }

        } catch (InterruptedException | ExecutionException e) {
            // Handle any exceptions that occurred during the player's move
            e.printStackTrace();
        }
    }

    private void play() {
        displayBoard();
    }





}


