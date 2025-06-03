package UI.Login;

import Database.Data;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.media.examsprojekt.Var;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginController {


    @FXML
    Pane login_root;


    public void initialize() {
        Pane loginContainer = new Pane();
        Pane Background = new Pane();
        double containerSize = 0.45;
        loginContainer.setPrefSize(Var.WINDOW_WIDTH  * containerSize, Var.WINDOW_HEIGHT * containerSize);
        Background.setPrefSize(Var.WINDOW_WIDTH * containerSize, Var.WINDOW_HEIGHT * containerSize);
        Background.setStyle("-fx-background-color: #ffffff;");
        Background.setOpacity(0.4);
        loginContainer.setLayoutX(Var.WINDOW_WIDTH / 2 - loginContainer.getPrefWidth() / 2);
        loginContainer.setLayoutY(Var.WINDOW_HEIGHT / 2 - loginContainer.getPrefHeight() / 2);
        loginContainer.getChildren().addAll(Background);
        VBox inputContainer = new VBox();
        inputContainer.setPrefSize(loginContainer.getPrefWidth(), loginContainer.getPrefHeight());
        inputContainer.setAlignment(Pos.CENTER);
        inputContainer.setStyle("-fx-background-color: transparent; -fx-border-color: red");
        inputContainer.setLayoutX(0);
        inputContainer.setLayoutY(0);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setMaxSize(loginContainer.getPrefWidth() / 4, 20);
        TextField pwdField = new PasswordField();
        pwdField.setPromptText("Password");
        pwdField.setMaxSize(loginContainer.getPrefWidth() / 4, 20);
        Button loginButton = new Button("Login");
        loginButton.setMaxSize(loginContainer.getPrefWidth() / 6, 20);
        loginButton.setOnAction(event -> {processLogin(usernameField.getText(), pwdField.getText());});
        inputContainer.getChildren().addAll(usernameField, pwdField, loginButton);
        loginContainer.getChildren().add(inputContainer);
        login_root.getChildren().add(loginContainer);

    }
    private void processLogin(String username, String password) throws SQLException {
        Data d = new Data();
        if (d.count("login", "username = " + username) == 1) {
            ResultSet result = d.read("password", "login", "username =" + username );
            result.next();
            if (result.getString("password").equals(password)) {
                //loginAction

            }

        }
    }
}