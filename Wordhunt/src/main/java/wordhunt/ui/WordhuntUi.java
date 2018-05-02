/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.ui;

import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import wordhunt.database.Database;
import wordhunt.database.ScoreDao;
import wordhunt.database.UserDao;
import wordhunt.domain.Game;
import wordhunt.domain.Score;
import wordhunt.domain.WordhuntService;

/**
 *
 * @author katamila
 */
public class WordhuntUi extends Application {

    private WordhuntService wordhunt;

    private Scene mainScene;
    private Scene newUserScene;
    private Scene loginScene;
    private Scene puzzleScene;
    private Scene ruleScene;
    private Scene scoreScene;

    private Label menuLabel = new Label();
    private Label timeLeft = new Label();
    private Label userTopTen = new Label();
    private Label topTen = new Label();

    private Timeline countdown;

    private UiHelp help;

    @Override
    public void init() throws Exception {
        Database database = new Database("jdbc:sqlite:database.db");
        database.init();
        UserDao users = new UserDao(database);
        ScoreDao scores = new ScoreDao(database);

        // alustetaan sovelluslogiikka 
        wordhunt = new WordhuntService(users, scores);
        help = new UiHelp();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // login scene

        VBox loginPane = new VBox(10);
        loginPane.setPadding(new Insets(10));

        Label loginOrCreateMessage = new Label("Kirjaudu sisään tai luo uusi tunnus");
        Label create = new Label("Luo uusi käyttäjätunnus");
        Label loginLabel = new Label("Käyttäjätunnus");
        TextField usernameInput = new TextField();

        Label loginMessage = new Label("");

        Button loginButton = new Button("Kirjaudu sisään");
        Button createButton = new Button("Luo uusi käyttäjä");
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            menuLabel.setText(username + " kirjaudutaan sisään...");
            try {
                if (wordhunt.login(username)) {
                    loginMessage.setText("");
                    menuLabel.setText("Tervetuloa " + wordhunt.getLoggedUser().getName() + "!");
                    primaryStage.setScene(mainScene);
                    usernameInput.setText("");
                } else {
                    loginMessage.setText("käyttäjää ei ole olemassa");
                    loginMessage.setTextFill(Color.RED);
                }
            } catch (Exception ex) {
                Logger.getLogger(WordhuntUi.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        createButton.setOnAction(e -> {
            usernameInput.setText("");
            primaryStage.setScene(newUserScene);
        });

        usernameInput.setMaxWidth(100);
        loginPane.setAlignment(Pos.CENTER);
        loginPane.getChildren().addAll(loginOrCreateMessage, loginMessage, loginLabel, usernameInput, loginButton, createButton);

        loginScene = new Scene(loginPane, 800, 700);

        // createNewUserScene
        VBox newUserPane = new VBox(10);
        newUserPane.setPadding(new Insets(10));

        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Käyttäjätunnus (4-10 merkkiä)");
        newUsernameLabel.setPrefWidth(100);

        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("Nimi");
        newNameLabel.setPrefWidth(100);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("Luo");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();

            if (username.length() < 4 || name.length() < 2) {
                userCreationMessage.setText("Käyttäjätunnus tai nimi liian lyhyt");
                userCreationMessage.setTextFill(Color.RED);
            } else {
                try {
                    if (wordhunt.createUser(username, name)) {
                        userCreationMessage.setText("");
                        loginMessage.setText("Uusi käyttäjä luotu");
                        loginMessage.setTextFill(Color.GREEN);
                        primaryStage.setScene(loginScene);
                    } else {
                        userCreationMessage.setText("Käyttäjätunnus on jo varattu");
                        userCreationMessage.setTextFill(Color.RED);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(WordhuntUi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

        Button backToLogin = new Button("Takaisin kirjautumiseen");
        backToLogin.setPadding(new Insets(10));

        backToLogin.setOnAction(e -> {

            primaryStage.setScene(loginScene);

        });

        newUserPane.getChildren().addAll(create, userCreationMessage, newUsernameLabel, newUsernameInput, newNameLabel, newNameInput, createNewUserButton, backToLogin);

        newUsernameInput.setMaxWidth(150);
        newNameInput.setMaxWidth(150);
        newUsernameLabel.setAlignment(Pos.CENTER);
        newUsernameLabel.setMinWidth(200);
        newNameLabel.setAlignment(Pos.CENTER);
        newUserPane.setAlignment(Pos.CENTER);
        newUserScene = new Scene(newUserPane, 800, 700);

        // main scene
        VBox mainPane = new VBox(10);
        mainPane.setPadding(new Insets(10));

        Button puzzleView = new Button("Pelinäkymään");
        puzzleView.setPadding(new Insets(10));

        Button ruleView = new Button("Säännöt");
        ruleView.setPadding(new Insets(10));
        
        Button highscoreView = new Button("Huipputulokset");
        highscoreView.setPadding(new Insets(10));

        puzzleView.setOnAction(e -> {

            wordhunt.setGame(10, 10, "sanalista.txt");
            primaryStage.setScene(puzzleScene);
            timeLeft.setText(wordhunt.getGame().showTimeMinSec());

        });

        ruleView.setOnAction(e -> {

            primaryStage.setScene(ruleScene);

        });
        
        highscoreView.setOnAction(e -> {

            primaryStage.setScene(scoreScene);
            try {
                userTopTen.setText(wordhunt.printUserTopTen());
            } catch (Exception ex) {
                Logger.getLogger(WordhuntUi.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                topTen.setText(wordhunt.printTopTen());
            } catch (Exception ex) {
                Logger.getLogger(WordhuntUi.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        Button logoutButton = new Button("Kirjaudu ulos");
        logoutButton.setPadding(new Insets(10));

        logoutButton.setOnAction(e -> {
            wordhunt.logout();
            primaryStage.setScene(loginScene);
        });

        mainPane.getChildren().addAll(menuLabel, puzzleView, ruleView, highscoreView, logoutButton);

        mainPane.setAlignment(Pos.CENTER);
        mainScene = new Scene(mainPane, 800, 700);

        // rule scene
        VBox rulePane = new VBox(10);
        rulePane.setPadding(new Insets(10));

        Label header = new Label("Pelin säännöt");
        header.setStyle("-fx-font-weight: bold;");
        Label rules = new Label(wordhunt.rulesFromFile("rulesFI.txt"));
        Button back = new Button("Takaisin päänäkymään");
        back.setPadding(new Insets(10));

        back.setOnAction(e -> {

            primaryStage.setScene(mainScene);

        });

        rulePane.getChildren().addAll(header, rules, back);

        rulePane.setAlignment(Pos.CENTER);
        rules.setTextAlignment(TextAlignment.CENTER);
        ruleScene = new Scene(rulePane, 800, 700);
        
        // highscore scene
        
        BorderPane scorePane = new BorderPane();
        scorePane.setPadding(new Insets(10));
        
        VBox personalHighscores = new VBox(10);
        personalHighscores.setPadding(new Insets(10));
        Label personalHighscoreLabel = new Label("Omat parhaat tulokset");
        personalHighscoreLabel.setPadding(new Insets(10));
        personalHighscores.setMinWidth(400);
        
        personalHighscores.getChildren().addAll(personalHighscoreLabel, userTopTen);
        
        VBox allHighscores = new VBox(10);
        allHighscores.setPadding(new Insets(10));
        Label allHighscoresLabel = new Label("Kaikkien pelaajien parhaat tulokset");
        allHighscoresLabel.setPadding(new Insets(10));
        allHighscores.setMinWidth(400);
        
        allHighscores.getChildren().addAll(allHighscoresLabel, topTen);
        
        Button backFromScores = new Button("Takaisin päävalikkoon");
        backFromScores.setPadding(new Insets(10));
        
        backFromScores.setOnAction(e -> {

            primaryStage.setScene(mainScene);

        });

        personalHighscores.setAlignment(Pos.CENTER);
        allHighscores.setAlignment(Pos.CENTER);
        scorePane.setBottom(backFromScores);
        scorePane.setLeft(personalHighscores);
        scorePane.setRight(allHighscores);

        scoreScene = new Scene(scorePane, 800, 700);


        // (test) puzzle scene
        BorderPane puzzlePane = new BorderPane();
        puzzlePane.setPadding(new Insets(20));

        // labels and buttons for current word + send word + clear word, sets to the right
        VBox newWord = new VBox(10);
        newWord.setPadding(new Insets(10));

        Label wordLabel = new Label("Tämänhetkinen sana:");
        Label word = new Label("");
        Button sendWord = new Button("Syötä sana");
        sendWord.setPadding(new Insets(10));
        Button clearWord = new Button("Tyhjennä valinnat");
        clearWord.setPadding(new Insets(10));
        Label valid = new Label("");
        Label pointLabel = new Label("Pisteet:");
        Label points = new Label("");

        newWord.getChildren().addAll(wordLabel, word, sendWord, clearWord, valid, pointLabel, points);

        // creates the grid where the puzzle itself is placed with startOrShuffle
        GridPane puzzle = new GridPane();

        // options (Start/Shuffle and Back to Main)
        HBox options = new HBox(10);
        options.setPadding(new Insets(10));

        Button startOrShuffle = new Button("Aloita peli");
        startOrShuffle.setPadding(new Insets(10));

        Button backToMain = new Button("Takaisin valikkoon");
        backToMain.setPadding(new Insets(10));

        HBox bottom = new HBox(10);

        timeLeft.setStyle("-fx-font-weight: bold; -fx-font-size: 30;");
        bottom.getChildren().addAll(timeLeft);


        // functionality for start/shuffle, starts countdown when game started
        
        startOrShuffle.setOnAction(e -> {

            countdown = help.startCountdownIfGameNotOn(countdown, wordhunt.getGame(), timeLeft);

            countdown.setCycleCount(wordhunt.getGame().getTime());
            countdown.play();

            countdown.setOnFinished(ee -> {
                countdown.stop();

                if (help.saveScore(wordhunt, countdown, timeLeft)) {
                    backToMain.setText("Takaisin valikkoon");
                    timeLeft.setText("Game Over\nPisteet:" + wordhunt.getGame().getPoints());
                }

            });

            help.startOrShuffleAction(wordhunt, points);

            word.setText("");
            startOrShuffle.setText("Sekoita pelilauta");
            backToMain.setText("Keskeytä peli (tulosta ei tallenneta)");
            valid.setText("");

            help.updatePuzzle(wordhunt.getGame(), puzzle, word, valid);

            primaryStage.setScene(puzzleScene);

        });
        
        // takes the user back to main

        backToMain.setOnAction(e -> {

            primaryStage.setScene(mainScene);
            puzzle.getChildren().clear();
            backToMain.setText("Takaisin valikkoon");
            startOrShuffle.setText("Aloita peli");
            points.setText("");
            countdown.stop();

        });

        options.getChildren().addAll(startOrShuffle, backToMain);

        // checks if the word is valid and saves points if true
        
        sendWord.setOnAction(e -> {

            if (help.acceptedWord(wordhunt.getGame(), word, valid, points)) {
                help.insertNewLetters(puzzle, wordhunt.getGame());
                help.updatePuzzle(wordhunt.getGame(), puzzle, word, valid);
            }

        });
        
        // clears current word

        clearWord.setOnAction(e -> {

            wordhunt.getGame().getCurrentword().clear();
            help.updatePuzzle(wordhunt.getGame(), puzzle, word, valid);
            word.setText("");
        });

        puzzlePane.setTop(options);
        puzzlePane.setCenter(puzzle);
        puzzlePane.setRight(newWord);
        puzzlePane.setBottom(bottom);
        bottom.setAlignment(Pos.CENTER);
        newWord.setAlignment(Pos.TOP_LEFT);
        newWord.setPrefWidth(250);

        puzzleScene = new Scene(puzzlePane, 800, 700);

        primaryStage.setTitle("Wordhunt");
        primaryStage.setScene(loginScene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.out.println("Closing");
            System.out.println(wordhunt.getLoggedUser());
//            if (wordhunt.getLoggedUser()!=null) {
//                e.consume();   
//            }

        });
    }

    @Override
    public void stop() {
        System.out.println("Application closing down");
    }

    public static void main(String[] args) {
        launch(args);
    }

}
