package UI.Login;

import Database.DatabaseConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.media.examsprojekt.Main;
import org.media.examsprojekt.Var;

import java.io.IOException;
import java.sql.SQLException;


public class LoginController {

    private int departmentID;
    @FXML
    Pane login_root;


    public void initialize() throws SQLException, ClassNotFoundException {
       if (checkDatabaseConnection()) {
           initializeLoginScreen();
       }
       else {
           System.out.println("Database not connected");
       }

    }

    public void initializeLoginScreen() {
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
        loginButton.setOnAction(event -> {
            try {
                processLogin(usernameField.getText(), pwdField.getText());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException | IOException e) {
                throw new RuntimeException(e);
            }
        });
        inputContainer.getChildren().addAll(usernameField, pwdField, loginButton);
        loginContainer.getChildren().add(inputContainer);
        login_root.getChildren().add(loginContainer);
    }





    private void processLogin(String username, String password) throws SQLException, ClassNotFoundException, IOException {
        FXMLLoader loader;
        if (checkCredentials(username, password)) {
            if (departmentID == 1) {
                loader = new FXMLLoader(Main.class.getResource("HR.fxml"));
                Scene scene = new Scene(loader.load(), Var.WINDOW_WIDTH, Var.WINDOW_HEIGHT);
                Main.primaryStage.setScene(scene);
                Main.primaryStage.show();

            }
            else {
                loader = new FXMLLoader(Main.class.getResource("User.fxml"));
                Scene scene = new Scene(loader.load(), Var.WINDOW_WIDTH, Var.WINDOW_HEIGHT);
                Main.primaryStage.setScene(scene);
                Main.primaryStage.show();


            }

        }
        else {
            Label wrongCredentials = new Label("Sorry but your Username or Password is incorrect");

        }

    }
    private boolean checkCredentials(String username, String password) throws SQLException, ClassNotFoundException {

//        Data d = new Data();
//
//        if (d.count("tblEmployee", "fldUsername = " + username) == 1) {
//            ResultSet result = d.read("password", "", "username =" + username );
//            result.next();
//            if (result.getString("password").equals(password)) {
//                //returns true when the Credentials match the Database
//
//                return true;
//            }
//
//        }
//        return false;
        departmentID = 0;
        return true;
    }
    public boolean checkDatabaseConnection() throws ClassNotFoundException, SQLException {
        DatabaseConnection dbc = new DatabaseConnection(Var.Database.IPADDRESS, String.valueOf(Var.Database.PORT), Var.Database.DATABASE, Var.Database.USERNAME, Var.Database.PASSWORD);
        try {
            dbc.open();
            Thread.sleep(300);
            dbc.close();
            return true;

        }
        catch (SQLException e) {
            return false;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}


