package com.example.game;

import javafx.scene.control.Button;

public class TicTacToeLogic {

    // Check if board is full
    public boolean isBoardFull(Button[][] buttonCell, int GRID_SIZE){
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                Button button = buttonCell[row][col];
                if(button.getText().isEmpty()){
                    return true;
                }
            }
        }
        return false;
    }

    // Check winner
    public boolean checkWin(Button[][] buttonCell, int GRID_SIZE, char symbol) {
        // Check rows and columns
        for (int i = 0; i < GRID_SIZE; i++) {
            String rowText = buttonCell[i][0].getText();
            String colText = buttonCell[0][i].getText();

            if (rowText != null && rowText.length() == 1 &&
                    rowText.charAt(0) == symbol &&
                    buttonCell[i][1].getText().equals(rowText) &&
                    buttonCell[i][2].getText().equals(rowText)) {
                return true;
            }

            if (colText != null && colText.length() == 1 &&
                    colText.charAt(0) == symbol &&
                    buttonCell[1][i].getText().equals(colText) &&
                    buttonCell[2][i].getText().equals(colText)) {
                return true;
            }
        }

        // Check diagonals
        String topLeft = buttonCell[0][0].getText();
        String topRight = buttonCell[0][2].getText();

        if (topLeft != null && topLeft.length() == 1 &&
                topLeft.charAt(0) == symbol &&
                buttonCell[1][1].getText().equals(topLeft) &&
                buttonCell[2][2].getText().equals(topLeft)) {
            return true;
        }

        return topRight != null && topRight.length() == 1 &&
                topRight.charAt(0) == symbol &&
                buttonCell[1][1].getText().equals(topRight) &&
                buttonCell[2][0].getText().equals(topRight);
    }

}
