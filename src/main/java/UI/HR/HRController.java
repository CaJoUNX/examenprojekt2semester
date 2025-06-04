package UI.HR;

import Database.Data;
import UI.User.Requests;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;

import java.sql.ResultSet;
import java.sql.SQLException;


public class HRController {
    @FXML
    TableView requestTable;
    public void initialize() throws SQLException, ClassNotFoundException {
        requestTable.setEditable(false);
        Data d = new Data();
        ResultSet rs = d.read("*", "tblRequest", "inner join tblEmployee on FK_fldEmployeeID = tblEmployee.fldEmployeeID right join tblDepartments on FK_fldDepartment = tblDepartments.fldDepartmentID inner join tblWorkLocations on FK_fldWorkLocation = tblWorkLocations.fldWorkLocationID inner join tblCity on FK_fldPostalCode = tblCity.fldPostCode ");
        while (rs.next()) {
            String fullName = rs.getString("fldFirstName") + " " + rs.getString("fldLastName");
            String DepartmentName = rs.getString("fldDepartmentName");
            String WorkLocationName = rs.getString("fldWorkLocationName");
            String CityName = rs.getString("fldCityName");
            String PostalCode = rs.getString("fldPostalCode");


        }
    }
}
