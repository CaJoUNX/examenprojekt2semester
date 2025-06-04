package UI.User;

import DAO.requestDAO;
import Database.Data;
import org.media.examsprojekt.Var;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Requests {


    public static List<requestDAO> getRequests() throws SQLException, ClassNotFoundException {
        List<requestDAO> requests = new ArrayList<>();
        Data data = new Data();
        ResultSet requestsRS = data.read("fldRequestID, FK_fldLocationID, fldRequestDate","tblRequest"," WHERE FK_fldEmployeeID = '" + Var.Session.userID + "'");

        while (requestsRS.next()) {

            int locationID = requestsRS.getInt("FK_fldLocationID");
            ResultSet locationRS = data.read("fldStreetName, fldHouseNumber, FK_fldPostalCode","tblWorkLocations"," WHERE fldWorkLocationID =  '" + locationID + "'");
            locationRS.next();
            String streetName = locationRS.getString("fldStreetName");
            String houseNumber = locationRS.getString("fldHouseNumber");
            int postalCode = locationRS.getInt("FK_fldPostalCode");
            ResultSet cityRS = data.read("fldCityName","tblCity"," WHERE fldPostCode =  '" + postalCode + "'");
            cityRS.next();
            String cityName = cityRS.getString("fldCityName");
            requests.add(new requestDAO(requestsRS.getInt("fldRequestID"),streetName + " " + houseNumber + ", " + cityName, requestsRS.getString("fldRequestDate") ));

        }
        return requests;
    }
    public static void create(int selectedLocation) throws SQLException, ClassNotFoundException {
        Data d = new Data();
        d.create("tblRequest", new String[]{"FK_fldEmployeeID", "FK_fldLocationID", "fldRequestDate"}, new String[] {  String.valueOf(Var.Session.userID), String.valueOf(selectedLocation), "'" + LocalDateTime.now() + "'"});
    }
}
