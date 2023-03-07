package com.example.tictactoefull;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import java.io.*;
import java.util.Random;
import static com.example.tictactoefull.HelloController.*;

public class Board implements EventHandler {
    static int player = 1;
    static int chainRow = 0;
    static int chainColumn = 0;
    static int chainFirDia = 0;
    static int chainSecDia = 0;
    static int moveCounter = 0;
    static String turn = " ";
    static boolean isWinner = false;
    static String[][] board = new String[boardDim][boardDim];

    public static void exitButton(Event event) throws IOException {
        System.exit(0);
    }

    @Override
    public void handle(Event event) {

        if (player == 1) {
            turn = "X";
            ((Button) event.getSource()).setText(turn);
            ((Button) event.getSource()).setDisable(true);
            labelPlayer.setText("Now is O' turn!");
            player++;
        } else if (player == 2) {
            turn = "O";
            labelPlayer.setText("Now is X' turn!");

            if (computerGame == false) {
                ((Button) event.getSource()).setText(turn);
                ((Button) event.getSource()).setDisable(true);
            } else {
                computerMoves();
            }
            player--;
        }
        //Zapisanie ruchu na tablicy
        board[(GridPane.getColumnIndex(((Button) event.getSource())) != null ? GridPane.getColumnIndex(((Button) event.getSource())) : 0)][(GridPane.getRowIndex(((Button) event.getSource()))) != null ? (GridPane.getRowIndex(((Button) event.getSource()))) : 0] = ((Button) event.getSource()).getText();
        moveCounter++;
        deactivateButtons();
        isFull();
        winInRow();
        winInColumn();
        winFirDia();
        winSecDia();

        //kontrola tablicy w konsoli
   /*     System.out.println(winnerMoves);
        for (int j = 0; j < boardDim; j++) {
            for (int i = 0; i < boardDim; i++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
   */ }
        public void deactivateButtons() {
        if (isWinner) {
            for (int i = 0; i < boardDim; i++) {
                for (int j = 0; j < boardDim; j++) {
                    Button nodeFromGridPane = (Button) getNodeFromGridPane(gridPane, i, j);
                    nodeFromGridPane.setDisable(true);
                }
            }
        }
    }
        public boolean isFull() {
            if (moveCounter == (boardDim * boardDim) && !isWinner) {
                labelPlayer.setText("GAME OVER!");
                labelWinner.setText("End of game - DRAW");
                return true;
            } else {
                return false;
            }
        }
        public void winInRow() {
            for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board.length; col++) {
                    if (board[col][row] == turn) {
                        chainRow++;
                        if (chainRow == winnerMoves) {
                            labelPlayer.setText("GAME OVER!");
                            labelWinner.setText("Player " + turn + " wins in a row");
                            isWinner = true;
                        }
                    } else {
                        chainRow = 0;
                        break;
                    }
                }
            }
            chainRow = 0;
        }
        public void winInColumn() {
            for (int col = 0; col < board.length; col++) {
                for (int row = 0; row < board.length; row++) {
                    if (board[col][row] == turn) {
                        chainColumn++;
                        if (chainColumn == winnerMoves) {
                            labelPlayer.setText("GAME OVER!");
                            labelWinner.setText("Player " + turn + " wins in a column");
                            isWinner = true;
                        }
                    } else {
                        chainColumn = 0;
                        break;
                    }
                }
            }
            chainColumn = 0;
        }
       public void winFirDia() {
           for (int p = 0; p < board.length; p++) {
               if (board[p][p] == turn) {
                   chainFirDia++;
                   if (chainFirDia == winnerMoves) {
                       labelPlayer.setText("GAME OVER!");
                       labelWinner.setText("Player " + turn + " wins diagonally");
                       isWinner = true;
                   } else {
                       chainFirDia = 0;
                   }
               }
           }
               chainFirDia = 0;
           }
    public void winSecDia() {
        for (int p = 0; p < board.length; p++) {
            if (board[(board.length - 1) - p][p] == turn) {
                chainSecDia++;
                if (chainSecDia == winnerMoves) {
                    labelPlayer.setText("GAME OVER!");
                    labelWinner.setText("Player " + turn + " wins diagonally");
                    isWinner = true;
                } else {
                    chainSecDia = 0;
                }
            }
        }
        chainFirDia = 0;
    }
    private void computerMoves() {
        Random random = new Random();
        int computerCol = random.nextInt(boardDim);
        int computerRow = random.nextInt(boardDim);

        if (board[computerCol][computerRow] == null) {
            Button nodeFromGridPane = (Button) getNodeFromGridPane(gridPane, computerCol, computerRow);
            nodeFromGridPane.setText(turn);
            nodeFromGridPane.setDisable(true);
            board[computerCol][computerRow] = turn;
        } else if (board[computerCol][computerRow] != null){
           computerMoves();
        }
    }
    private Node getNodeFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren())
            if (GridPane.getColumnIndex(node) != null
                    && GridPane.getColumnIndex(node) != null
                    && GridPane.getRowIndex(node) == row
                    && GridPane.getColumnIndex(node) == col)
                return node;
        return null;
    }
}
