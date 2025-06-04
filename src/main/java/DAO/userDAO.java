package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class userDAO {
    public int id;
    public String firstName;
    public String lastName;
    public String street;
    public int houseNumber;
    public int postalCode;
    public String city;

    public userDAO() {


    }
    public String[] readFull() {
        String[] data = new String[6];
        data[0] = firstName;
        data[1] = lastName;
        data[2] = street;
        data[3] = String.valueOf(houseNumber);
        data[4] = String.valueOf(postalCode);
        data[5] = city;
        return data;
    }
    public void pull(int id) throws SQLException, ClassNotFoundException {
        Database.Data d = new Database.Data();


        ResultSet name = d.read("fldFirstName, fldSurname, FK_fldEmployeeAddress", "tblEmployee", " WHERE fldEmployeeID = '" + id + "'");
        name.next();
        int addressID = name.getInt("FK_fldEmployeeAddress");
        ResultSet address = d.read("*", "tblEmployeeAddress", " WHERE fldEmployeeAddressID = '" + addressID + "'");
        address.next();
        firstName = name.getString("fldFirstName");
        lastName = name.getString("fldSurname");
        street = address.getString("fldStreet");
        houseNumber = address.getInt("fldHouseNumber");
        postalCode = address.getInt("FK_fldCity");
        ResultSet cityrs = d.read("fldCityName", "tblCity", " WHERE fldPostCode = '" + postalCode + "'");
        cityrs.next();
        this.city = cityrs.getString("fldCityName");




    }
}
