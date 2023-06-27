package com.example.game;

import javafx.scene.control.Button;

public class TicTacToeLogic {

    // Check if board is full
    public boolean isBoardFull(Button[][] board) {
        int GRID_SIZE = 3;
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Button button = board[row][col];
                if (button.getText().isEmpty()) {
                    return false; // Return false if an empty spot is found
                }
            }
        }
        return true; // Return true if no empty spots are found (board is full)
    }


    // Check winner
    public boolean checkWin(Button[][]board,char symbol) {

        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            String rowText = board[i][0].getText();
            String colText = board[0][i].getText();

            if (rowText != null && rowText.length() == 1 &&
                    rowText.charAt(0) == symbol &&
                    board[i][1].getText().equals(rowText) &&
                    board[i][2].getText().equals(rowText)) {
                return true;
            }

            if (colText != null && colText.length() == 1 &&
                    colText.charAt(0) == symbol &&
                    board[1][i].getText().equals(colText) &&
                    board[2][i].getText().equals(colText)) {
                return true;
            }
        }

        // Check diagonals
        String topLeft = board[0][0].getText();
        String topRight = board[0][2].getText();

        if (topLeft != null && topLeft.length() == 1 &&
                topLeft.charAt(0) == symbol &&
                board[1][1].getText().equals(topLeft) &&
                board[2][2].getText().equals(topLeft)) {
            return true;
        }

        return topRight != null && topRight.length() == 1 &&
                topRight.charAt(0) == symbol &&
                board[1][1].getText().equals(topRight) &&
                board[2][0].getText().equals(topRight);
    }

}
