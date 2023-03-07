package com.example.tictactoefull;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.IOException;
import static javafx.geometry.Pos.CENTER;

public class HelloController {

    public static int winnerMoves;
    public static int boardDim;
    public static boolean computerGame;
    public static int buttonSize;
    public static int letterSize;
    public static GridPane gridPane = new GridPane();
    public static Label labelPlayer = new Label("Let's play! X beggin");
    public static Label labelWinner = new Label(" ");
    private Stage stage;
    private Scene scene;
    private Parent root;


        ObservableList<String> boardSizeList = FXCollections.observableArrayList("3X3 Board", "10x10 Board");
        ObservableList<String> opponentList = FXCollections.observableArrayList("1 vs 1", "1 vs computer");

        @FXML
        private ChoiceBox boardSize;
        @FXML
        private ChoiceBox opponent;

        @FXML
        protected void onHelloButtonClick() {
            boardSize.setItems(boardSizeList);
            opponent.setItems(opponentList);
        }

        public void createBoard(ActionEvent event) throws IOException {
            opponentChoice();

            if (boardSize.getValue() == "3X3 Board") {
                boardDim = 3;
                winnerMoves = 3;
                buttonSize = 150;
                letterSize = 36;
            } else if (boardSize.getValue() == "10x10 Board") {
                boardDim = 10;
                winnerMoves = 5;
                buttonSize = 50;
                letterSize = 18;
            }

            root = FXMLLoader.load(getClass().getResource("board.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            try {
                VBox vBox = new VBox();
                vBox.setAlignment(CENTER);
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("board.fxml"));
                Scene scene = new Scene(vBox, 520, 700);
                //Labele
                vBox.getChildren().add(labelPlayer);
                vBox.getChildren().add(labelWinner);
                labelWinner.setPrefHeight(50.0);
                labelWinner.setPrefWidth(500.0);
                labelWinner.setAlignment(CENTER);
                labelWinner.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
                labelPlayer.setPrefHeight(50.0);
                labelPlayer.setPrefWidth(500.0);
                labelPlayer.setAlignment(CENTER);
                labelPlayer.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 36));
                //GridPane
                vBox.getChildren().add(gridPane);
                gridPane.getParent();
                gridPane.setAlignment(CENTER);
                gridPane.setGridLinesVisible(true);
                //Button
                Button[][] buttons = new Button[boardDim][boardDim];
                for (int i = 0; i < boardDim; i++) {
                    for (int j = 0; j < boardDim; j++) {
                        buttons[i][j] = new Button(" ");
                        buttons[i][j].setPrefSize(buttonSize, buttonSize);
                        buttons[i][j].setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, letterSize));
                        buttons[i][j].setOnAction((EventHandler<ActionEvent>) new Board());
                        gridPane.add(buttons[i][j], i, j);
                    }
                }
                Button exitB = new Button("Exit");
                vBox.getChildren().add(exitB);
                exitB.setFont(Font.font("verdana", FontWeight.MEDIUM, FontPosture.REGULAR, 18));
                exitB.setOnAction(event1 -> {
                    try {
                        Board.exitButton(event1);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });

                stage.setTitle("Tic Tac Toe Game!");
                stage.setScene(scene);
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
            public void opponentChoice() {
                if (opponent.getValue() == "1 vs 1" ) {
                    computerGame = false;
                } else if (opponent.getValue() == "1 vs computer" ) {
                    computerGame = true;
                }
            }
        }


