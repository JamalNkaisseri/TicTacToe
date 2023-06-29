package com.example.game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;




public class TicTacToeView extends Application {

    private char currentPlayer = 'X';
    private final char[][] board = new char[3][3];
    private final Button[][] buttons = new Button[3][3];

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane grid = createGameBoard();

        Scene scene = new Scene(grid,500,500);
        primaryStage.setTitle("TicTacToe");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // Create 3x3 grid on which the game will be played on
    private GridPane createGameBoard() {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create buttons for each cell of the game board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = createButton();

                // Store references to the button in a 2D array
                buttons[row][col] = button;

                int finalRow = row;
                int finalCol = col;
                button.setOnAction(event -> makeMove(finalRow, finalCol));

                gridPane.add(button, col, row);
            }
        }
        return gridPane;
    }

    private Button createButton() {
        Button button = new Button();
        button.setMinSize(100, 100);
        button.setFont(Font.font(40));
        return button;
    }

    // Take row and column of the button that was clicked by the player
    private void makeMove(int row, int col) {

        // Check if the button is empty
        if (board[row][col] == 0) {
            board[row][col] = currentPlayer;

            // Place the player move at the specified row and column
            buttons[row][col].setText(String.valueOf(currentPlayer));

            // Check if the game has been won or drawn
            if (isGameWon(currentPlayer)) {
                showResult("Player " + currentPlayer + " wins!");
            } else if (isBoardFull()) {
                showResult("It's a tie!");
            }

            // Switch to the current player and if it is the computer call
            // the computerMove method
            else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                if (currentPlayer == 'O') {
                    makeComputerMove();
                }
            }
        }
    }

    private void makeComputerMove() {
        int[] bestMove = getBestMove();
        makeMove(bestMove[0], bestMove[1]);
    }


    private int[] getBestMove() {

        // Initialize array to store row and col indices
        // of the best move
        int[] bestMove = new int[2];
        int bestScore = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {

                // Check if cell is empty
                if (board[row][col] == 0) {

                    // Set the cell to O to simulate computer move
                    board[row][col] = 'O';
                    int score = minimax(board, 0, false);

                    // Reset cell to empty state
                    board[row][col] = 0;

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = row;
                        bestMove[1] = col;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(char[][] board, int depth, boolean isMaximizingPlayer) {
        if (isGameWon('X')) {
            return -1;
        } else if (isGameWon('O')) {
            return 1;
        } else if (isBoardFull()) {
            return 0;
        }

        int bestScore;
        if (isMaximizingPlayer) {
            bestScore = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == 0) {
                        board[row][col] = 'O';
                        int score = minimax(board, depth + 1, false);
                        board[row][col] = 0;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
        } else {
            bestScore = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col] == 0) {
                        board[row][col] = 'X';
                        int score = minimax(board, depth + 1, true);
                        board[row][col] = 0;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
        }
        return bestScore;
    }

    private boolean isGameWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        return board[0][2] == player && board[1][1] == player && board[2][0] == player;
    }

    private boolean isBoardFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private void showResult(String message) {
        Pane pane = new Pane();
        pane.setPrefSize(300, 300);

        Button resultButton = new Button(message);
        resultButton.setFont(Font.font(20));
        resultButton.setMinSize(200, 50);
        resultButton.setLayoutX(50);
        resultButton.setLayoutY(125);

        pane.getChildren().add(resultButton);

        Scene scene = new Scene(pane);
        Stage stage = new Stage();
        stage.setTitle("Game Over");
        stage.setScene(scene);
        stage.show();
    }

}


