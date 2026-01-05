# TicTacToe Game with Minimax AI

This is a TicTacToe game developed using Java and JavaFX. The game includes an AI player that uses the Minimax algorithm to make intelligent moves.

## Features
- Single-player game against an AI opponent
- Interactive graphical user interface (GUI) built with JavaFX
- AI player that uses the Minimax algorithm for intelligent moves
- The AI player is unbeatable when played optimally

## Requirements
- Java Development Kit (JDK) 8 or above
- JavaFX library

## How to Run
1. Make sure you have the Java Development Kit (JDK) installed on your system.
2. Clone this repository or download the source code.
3. Open a terminal or command prompt and navigate to the project directory.
4. Compile the Java source files using the following command:
   ```
   javac --module-path /path/to/javafx-sdk-X.X.X/lib --add-modules javafx.controls,javafx.fxml TicTacToe.java
   ```
   Note: Replace `/path/to/javafx-sdk-X.X.X` with the actual path to your JavaFX SDK installation directory.
5. Run the game using the following command:
   ```
   java --module-path /path/to/javafx-sdk-X.X.X/lib --add-modules javafx.controls,javafx.fxml TicTacToe
   ```
   Note: Again, replace `/path/to/javafx-sdk-X.X.X` with the actual path to your JavaFX SDK installation directory.

## How to Play
1. The game is played on a 3x3 grid.
2. You are the "X" player, and the AI is the "O" player.
3. To make a move, click on an empty cell on the grid.
4. The game will alternate turns between you and the AI player.
5. The goal is to get three of your symbols in a row (horizontally, vertically, or diagonally) before the AI does.
6. If all cells are filled and there is no winner, the game is considered a draw.

## Minimax Algorithm
The Minimax algorithm is used by the AI player to determine the best move to make. It considers all possible moves and assigns a score to each move, assuming the opponent also plays optimally. The AI player then chooses the move with the highest score, guaranteeing the best possible outcome or, at worst, a draw.

## Credits
This project is developed by me. If you have any questions or suggestions, please feel free to contact me.

X - @Jamxl98

Enjoy playing the TicTacToe game against the unbeatable AI!
