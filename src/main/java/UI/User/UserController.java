package UI.User;

import DAO.requestDAO;
import DAO.userDAO;
import Database.Data;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserController  {
    Data data = new Data();
    @FXML
    TextField firstNameField;
    @FXML
    TextField lastNameField;
    @FXML
    TextField streetField;
    @FXML
    TextField houseNumberField;
    @FXML
    TextField postalCodeField;
    @FXML
    TextField passwordField;
    @FXML
    TextField confirmPasswordField;
    @FXML
    TextField cityField;
    @FXML
    ListView<String> requestList;
    @FXML
    ChoiceBox<String> selectLocation;
    List<requestDAO> requestDAOS = new ArrayList<>();
    public UserController() throws SQLException, ClassNotFoundException  {
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        fillUserInfo();
        fillRequests();
        loadLocationSelection();

    }
    public void loadLocationSelection() throws SQLException, ClassNotFoundException {
        Data d = new Data();

        ResultSet locationsRS = d.read("*", "tblWorkLocations", "");

        while (locationsRS.next()) {
            ResultSet cityRS = d.read("fldCityName", "tblCity", " WHERE fldPostCode = '" + locationsRS.getString("FK_fldPostalCode") + "'" );
            cityRS.next();

            selectLocation.getItems().add(locationsRS.getString("fldStreetName") + " " + locationsRS.getString("fldHouseNumber") + ", " + cityRS.getString("fldCityName"));
        }

    }


    public void fillUserInfo() throws SQLException, ClassNotFoundException {

        firstNameField.setText(userInfo.get().firstName);
        lastNameField.setText(userInfo.get().lastName);
        streetField.setText(userInfo.get().street);
        houseNumberField.setText(String.valueOf(userInfo.get().houseNumber));
        postalCodeField.setText(String.valueOf(userInfo.get().postalCode));
        cityField.setText(userInfo.get().city);
    }
    public void fillRequests() throws SQLException, ClassNotFoundException {

         requestDAOS = Requests.getRequests();
        for (requestDAO r : requestDAOS) {
            requestList.getItems().add(r.getLocation() + " " +  r.getRequestDate());

        }
    }

    public void sendRequest(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Requests.create(selectLocation.getSelectionModel().getSelectedIndex());
        fillRequests();


    }

    public void deleteRequest(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {

        Data d = new Data();
        d.delete("tblRequest", " WHERE fldRequestID = " + requestDAOS.get(requestList.getSelectionModel().getSelectedIndex()).getID());
        requestList.getItems().remove(requestList.getSelectionModel().getSelectedIndex());

    }

    public void updateUserInfo(MouseEvent mouseEvent) {
        if (!passwordField.getText().isEmpty() || !confirmPasswordField.getText().isEmpty()) {
            if (passwordField.getText().equals(confirmPasswordField.getText())) {


            }

        }
    }
}
