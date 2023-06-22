package com.example.game;
import javafx.scene.control.Button;

public class TicTacToeLogic {

    // Check if board is full
    public boolean isBoardFull(Button[][] buttonCell,int GRID_SIZE){
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
    public boolean checkWin(Button[][] buttonCell,int GRID_SIZE,char symbol){

        // Check rows and columns
        for(int i = 0;i < GRID_SIZE;i++){

            if(buttonCell[i][0].getText().charAt(0) == symbol &&
                    buttonCell[i][1].getText().charAt(0) == symbol &&
                    buttonCell[i][2].getText().charAt(0) == symbol){
                return true;
            }

            if(buttonCell[0][i].getText().charAt(0) == symbol &&
                    buttonCell[1][i].getText().charAt(0) == symbol &&
                    buttonCell[2][i].getText().charAt(0) == symbol){
                return true;
            }
        }

        // Check diagonals
        if(buttonCell[0][0].getText().charAt(0) == symbol &&
                buttonCell[1][1].getText().charAt(0) == symbol &&
                buttonCell[2][2].getText().charAt(0) == symbol){
            return true;
        }

        return buttonCell[0][2].getText().charAt(0) == symbol &&
                buttonCell[1][1].getText().charAt(0) == symbol &&
                buttonCell[2][0].getText().charAt(0) == symbol;
    }
}
