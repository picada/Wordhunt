/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableSet;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javax.swing.ButtonModel;
import wordhunt.domain.Game;

/**
 *
 * @author mila
 */
public class UiHelp {

    List<Node> clicked;

    public UiHelp() {
        this.clicked = new ArrayList<>();
    }

    public void updatePuzzle(Game puzzle, GridPane grid, Label word, Label valid) {
        grid.getChildren().clear();

        Character[][] letters = puzzle.getBoard();

        for (int x = 0; x < letters.length; x++) {
            for (int y = 0; y < letters[x].length; y++) {
                Button letter = new Button(letters[x][y].toString().toUpperCase());
                letter.setAlignment(Pos.CENTER);
                letter.setMinSize(50, 50);
                letter.setFont(Font.font("Calibri", 15));
                letter.setOnAction(e -> {
                    if (puzzle.getCurrentword().isEmpty() && !clicked.contains(letter)|| puzzle.isNextTo(GridPane.getColumnIndex(letter), GridPane.getRowIndex(letter))) {
                        puzzle.collectLetter(letter.getText().toLowerCase());
                        System.out.println(puzzle.buildString(puzzle.getCurrentword()));
                        word.setText(puzzle.buildString(puzzle.getCurrentword()));
                        puzzle.setCurrentx(GridPane.getColumnIndex(letter));
                        puzzle.setCurrenty(GridPane.getRowIndex(letter));
                        letter.setStyle("-fx-background-color:yellow;");
                        clicked.add(letter);
                    }
                    valid.setText("");
                });
                grid.add(letter, x, y);
            }
        }

    }

    public void insertNewLetters(GridPane grid, Game puzzle) {

        grid.getChildren().stream().filter(n -> clicked.contains(n))
                .forEach(n -> puzzle.newRandomLetter(GridPane.getColumnIndex(n), 
                        GridPane.getRowIndex(n))
                );
    }

}
