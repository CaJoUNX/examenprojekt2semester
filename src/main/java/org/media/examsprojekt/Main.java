package org.media.examsprojekt;

import Database.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main extends Application {
    public static Stage primaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),Var.Window.WIDTH, Var.Window.HEIGHT);
        primaryStage.setTitle("Hello!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();

    }


    public static void searchMatch() throws SQLException, ClassNotFoundException {
        Data d = new Data();
        ResultSet rs = d.read("*", "tblRequests","" );
        class temporaryRequestData{
            int id;
            String workLocation;
            int department;
            public temporaryRequestData(int id, String workLocation, int department) {}
        }
        while (rs.next()) {
            int requestID = rs.getInt("flRequestID");
            String workLocation = rs.getString("workLocation");
        }


    }
}