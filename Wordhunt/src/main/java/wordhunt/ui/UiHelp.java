/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.ui;

import java.sql.SQLException;
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
import wordhunt.database.ScoreDao;
import wordhunt.domain.Game;
import wordhunt.domain.WordhuntService;
import wordhunt.domain.Board;
import wordhunt.domain.Score;

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

    /**
     *
     * Updates the current puzzle view
     *
     * @param game the current game
     * @param grid the current grid
     * @param word the current word
     * @param valid the label which shows if the word is valid
     *
     * @return current game
     *
     */
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

    /**
     *
     * Defines the mouse event for clicking a letter: if the letter is a
     * neighbour to the last clicked letter button on the board, the letter is
     * added to the word Also updates necessary labels
     *
     * @param game the current game
     * @param letter the button that the user clicks
     * @param word the current word
     * @param valid the label which shows if the word is valid
     *
     *
     */
    public void letterClickAction(Game game, Button letter, Label word, Label valid) {
        if (!game.gameOver() && game.getCurrentword().isEmpty() && !clicked.contains(letter)
                || !game.gameOver() && game.getBoard().isNextTo(GridPane.getColumnIndex(letter), GridPane.getRowIndex(letter))) {
            game.collectLetter(letter.getText().toLowerCase());
            word.setText(game.buildString());
            game.getBoard().setCurrentx(GridPane.getColumnIndex(letter));
            game.getBoard().setCurrenty(GridPane.getRowIndex(letter));
            letter.setStyle("-fx-background-color:yellow;");
            clicked.add(letter);
        }
        valid.setText("");
    }

    /**
     *
     * Defines the mouse event for the start/shuffle button in the puzzle view:
     * if the game hasn't started the method sets the board and starts the game,
     * otherwise the click shuffles the board and reduces points Also updates
     * necessary labels
     *
     * @param game the current game
     * @param points the label showing the current points
     *
     *
     */
    public void startOrShuffleAction(Game game, Label points) {
        if (!game.getGameOn()) {
            game.getBoard().setBoard();
            game.startGame();
        } else {
            game.mixBoard();
            points.setText("" + game.getPoints());
            game.getCurrentword().clear();
        }
    }

    /**
     *
     * Starts the countdown TimeLine if game is not already on or over and
     * updates necessary labels
     *
     * @param timeline the timeline created in the UI
     * @param game the current game
     * @param timeLeft the label showing the time left in the game
     *
     * @return a "ticking" TimeLine
     *
     */
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

    /**
     *
     * Saves the user's score to when the game ends and updates necessary labels
     *
     * @param wordhunt the current WordhuntService
     * @param backToMain the button that takes the user back to the main view
     * @param timeLeft the label showing the time left in the game
     *
     */
    public void saveScore(WordhuntService wordhunt, Button backToMain, Label timeLeft) {
        try {
            if (wordhunt.createScore()) {
                timeLeft.setText("Game Over\nPisteet:" + wordhunt.getGame().getPoints());
            } else {
                timeLeft.setText("Pisteiden tallennus epäonnistui");
            }
            backToMain.setText("Takaisin valikkoon");
        } catch (Exception ex) {
            Logger.getLogger(UiHelp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * Checks if the submitted word is valid (=not yet submitted and can be
     * found from the wordlist), updates necessary labels
     *
     * @param game the current game
     * @param word the label showing the current word
     * @param valid the label showing if the submitted word is valid
     * @param points the label showing the current points
     *
     * @return true if word is valid and accepted, otherwise false
     *
     */
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

    /**
     *
     * Inserts new random letters to the puzzle gridPane to the children which
     * have been "used" in an accepted word
     *
     * @param grid the gridPane that represents the board
     * @param board the board in the current game
     *
     */
    public void insertNewLetters(GridPane grid, Board board) {

        grid.getChildren().stream().filter(n -> clicked.contains(n))
                .forEach(n -> board.newRandomLetter(GridPane.getColumnIndex(n),
                GridPane.getRowIndex(n))
                );
    }


}
