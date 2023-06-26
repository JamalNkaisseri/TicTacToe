package com.example.game;

import javafx.scene.control.Button;

public class TicTacToeLogic {

    private static final char PLAYER_SYMBOL = 'X';
    private static final char COMPUTER_SYMBOL = 'O';
    private static final char EMPTY_SYMBOL = ' ';

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

    // Minimax algorithm
    private Move minimax(Button[][] board, int depth, boolean maximizingPlayer) {
        if (checkWin(board,PLAYER_SYMBOL)) {
            return new Move(-1, -1, -10);
        } else if (checkWin(board,COMPUTER_SYMBOL)) {
            return new Move(-1, -1, 10);
        } else if (isBoardFull(board)) {
            return new Move(-1, -1, 0);
        }

        Move bestMove = new Move(-1, -1, maximizingPlayer ? Integer.MIN_VALUE : Integer.MAX_VALUE);

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col].getText().equals(String.valueOf(EMPTY_SYMBOL))) {
                    board[row][col].setText(maximizingPlayer ? String.valueOf(COMPUTER_SYMBOL) : String.valueOf(PLAYER_SYMBOL));
                    Move move = minimax(board, depth + 1, !maximizingPlayer);
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



    // Check if board is full
    public boolean isBoardFull(Button[][] board){

        int GRID_SIZE = 3;
        for(int row = 0;row < GRID_SIZE;row++){
            for(int col = 0;col < GRID_SIZE;col++){
                Button button = board[row][col];
                if(button.getText().isEmpty()){
                    return true;
                }
            }
        }
        return false;
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
