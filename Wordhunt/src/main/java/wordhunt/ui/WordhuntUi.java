/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordhunt.ui;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import wordhunt.database.Database;
import wordhunt.database.ScoreDao;
import wordhunt.database.UserDao;
import wordhunt.domain.Puzzle;
import wordhunt.domain.Wordhunt;

/**
 *
 * @author katamila
 */
public class WordhuntUi extends Application {

    private Wordhunt wordhunt;

    private Scene mainScene;
    private Scene newUserScene;
    private Scene loginScene;
    private Scene puzzleScene;

    private Label menuLabel = new Label();

    @Override
    public void init() throws Exception {
        Database database = new Database("jdbc:sqlite:database.db");
        database.init();
        UserDao users = new UserDao(database);
        ScoreDao scores = new ScoreDao(database);
        // alustetaan sovelluslogiikka 
        wordhunt = new Wordhunt(users, scores);
    }

    @Override
    public void start(Stage primaryStage) {
        // login scene

        VBox loginPane = new VBox(10);
        HBox inputPane = new HBox(10);
        loginPane.setPadding(new Insets(10));
        Label loginLabel = new Label("Username");
        TextField usernameInput = new TextField();

        inputPane.getChildren().addAll(loginLabel, usernameInput);
        Label loginMessage = new Label();

        Button loginButton = new Button("Login");
        Button createButton = new Button("Create new user");
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            menuLabel.setText(username + " logging in...");
            try {
                if (wordhunt.login(username)) {
                    loginMessage.setText("");
                    primaryStage.setScene(mainScene);
                    usernameInput.setText("");
                } else {
                    loginMessage.setText("user does not exist");
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

        loginPane.getChildren().addAll(loginMessage, inputPane, loginButton, createButton);

        loginScene = new Scene(loginPane, 300, 250);

        // new createNewUserScene
        VBox newUserPane = new VBox(10);
        newUserPane.setPadding(new Insets(10));
        HBox newUsernamePane = new HBox(10);
        newUsernamePane.setPadding(new Insets(10));
        TextField newUsernameInput = new TextField();
        Label newUsernameLabel = new Label("Username");
        newUsernameLabel.setPrefWidth(100);
        newUsernamePane.getChildren().addAll(newUsernameLabel, newUsernameInput);

        HBox newNamePane = new HBox(10);
        newNamePane.setPadding(new Insets(10));
        TextField newNameInput = new TextField();
        Label newNameLabel = new Label("Name");
        newNameLabel.setPrefWidth(100);
        newNamePane.getChildren().addAll(newNameLabel, newNameInput);

        Label userCreationMessage = new Label();

        Button createNewUserButton = new Button("Create");
        createNewUserButton.setPadding(new Insets(10));

        createNewUserButton.setOnAction(e -> {
            String username = newUsernameInput.getText();
            String name = newNameInput.getText();

            if (username.length() == 2 || name.length() < 2) {
                userCreationMessage.setText("Username or name too short");
                userCreationMessage.setTextFill(Color.RED);
            } else {
                try {
                    if (wordhunt.createUser(username, name)) {
                        userCreationMessage.setText("");
                        loginMessage.setText("New user created");
                        loginMessage.setTextFill(Color.GREEN);
                        primaryStage.setScene(loginScene);
                    } else {
                        userCreationMessage.setText("Username is already taken");
                        userCreationMessage.setTextFill(Color.RED);
                    }
                } catch (Exception ex) {
                    Logger.getLogger(WordhuntUi.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        
        Button backToLogin = new Button("Back to login");
        backToLogin.setPadding(new Insets(10));

        backToLogin.setOnAction(e -> {

            primaryStage.setScene(loginScene);

        });


        newUserPane.getChildren().addAll(userCreationMessage, newUsernamePane, newNamePane, createNewUserButton, backToLogin);

        newUserScene = new Scene(newUserPane, 300, 250);

        // main scene
        VBox mainPane = new VBox(10);
        mainPane.setPadding(new Insets(10));

        Label mainLabel = new Label("Not much to see here... yet.");

        Button createPuzzle = new Button("Create test puzzle");
        createPuzzle.setPadding(new Insets(10));

        createPuzzle.setOnAction(e -> {

            primaryStage.setScene(puzzleScene);

        });

        mainPane.getChildren().addAll(mainLabel, createPuzzle);

        mainScene = new Scene(mainPane, 300, 250);

        // (test) puzzle scene
        VBox puzzlePane = new VBox(10);
        puzzlePane.setPadding(new Insets(10));

        Label puzzleLabel = new Label("Test puzzle");
        
        Puzzle test =  new Puzzle(10, 10);
        test.setBoard();
        
        Label puzzle = new Label(test.toString());
        
        Button backToMain = new Button("Back");
        backToMain.setPadding(new Insets(10));

        backToMain.setOnAction(e -> {

            primaryStage.setScene(mainScene);

        });

        puzzlePane.getChildren().addAll(puzzleLabel, puzzle, backToMain);

        puzzleScene = new Scene(puzzlePane, 300, 250);

        primaryStage.setTitle("Wordhunt");
        primaryStage.setScene(loginScene);
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
