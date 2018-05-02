/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.ui;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import wordhunt.domain.Game;
import wordhunt.domain.WordhuntService;
import wordhunt.domain.Board;

/**
 *
 * @author mila
 *
 * this class handles the more complicated mouse events
 */
public class UiHelp {

    List<Node> clicked;

    public UiHelp() {
        this.clicked = new ArrayList<>();
    }

    public void updatePuzzle(Game game, GridPane grid, Label word, Label valid) {
        grid.getChildren().clear();

        Character[][] letters = game.getBoard().getBoard();

        for (int x = 0; x < letters.length; x++) {
            for (int y = 0; y < letters[x].length; y++) {
                Button letter = new Button(letters[x][y].toString().toUpperCase());
                letter.setAlignment(Pos.CENTER);
                letter.setMinSize(50, 50);
                letter.setFont(Font.font("Calibri", 15));
                letter.setOnAction(e -> {
                    letterClickAction(game, letter, word, valid);
                });
                grid.add(letter, x, y);
            }
        }
    }
    
    public void letterClickAction(Game game, Button letter, Label word, Label valid) {
        if (!game.gameOver() && game.getCurrentword().isEmpty() && !clicked.contains(letter)
                            || !game.gameOver() && game.getBoard().isNextTo(GridPane.getColumnIndex(letter), GridPane.getRowIndex(letter))) {
                        game.collectLetter(letter.getText().toLowerCase());
                        word.setText(game.buildString(game.getCurrentword()));
                        game.getBoard().setCurrentx(GridPane.getColumnIndex(letter));
                        game.getBoard().setCurrenty(GridPane.getRowIndex(letter));
                        letter.setStyle("-fx-background-color:yellow;");
                        clicked.add(letter);
                    }
                    valid.setText("");
    }

    public void startOrShuffleAction(WordhuntService wordhunt, Label points) {
        if (!wordhunt.getGame().getGameOn()) {
            wordhunt.getGame().getBoard().setBoard();
            wordhunt.getGame().startGame();
        } else {
            wordhunt.getGame().mixBoard();
            points.setText("" + wordhunt.getGame().getPoints());
            wordhunt.getGame().getCurrentword().clear();
        }
    }

    public Timeline startCountdownIfGameNotOn(Timeline countdown, Game game, Label timeLeft) {
        if (!game.getGameOn() && !game.gameOver()) {

            countdown = new Timeline(
                    new KeyFrame(Duration.seconds(1), ee -> {
                        game.tick();
                        timeLeft.setText(game.showTimeMinSec());
                    })
            );
            countdown.setCycleCount(game.getTime());
            countdown.play();

            return countdown;
        }
        return countdown;
    }

    public boolean saveScore(WordhuntService wordhunt, Timeline countdown, Label timeLeft) {
        if (wordhunt.getGame().gameOver()) {
            try {
                wordhunt.createScore(wordhunt.getGame().getPoints(), wordhunt.getLoggedUser(), LocalDate.now());
                return true;
            } catch (Exception ex) {
                Logger.getLogger(WordhuntUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public boolean acceptedWord(Game game, Label word, Label valid, Label points) {
        if (!game.isNewWord(word.getText())) {
            valid.setText("Sana on jo syötetty");
            return false;
        } else if (game.isWord(word.getText())) {
            game.setPoints(word.getText().length());
            valid.setText("Sana hyväksytty");
            points.setText("" + game.getPoints());
            word.setText("");
            return true;
        } else {
            valid.setText("Sana ei hyväksytty");
        }
        return false;
    }

    public void insertNewLetters(GridPane grid, Game game) {

        grid.getChildren().stream().filter(n -> clicked.contains(n))
                .forEach(n -> game.getBoard().newRandomLetter(GridPane.getColumnIndex(n),
                GridPane.getRowIndex(n))
                );
    }

}
