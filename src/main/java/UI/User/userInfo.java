package UI.User;

import DAO.userDAO;
import org.media.examsprojekt.Var;

import java.sql.SQLException;

public class userInfo {
    public static userDAO get() throws SQLException, ClassNotFoundException {
        userDAO daoUD = new userDAO();
        daoUD.pull(Var.Session.userID);

        return daoUD;
    }

    public static void set(String firstName, String lastName, String password, String street, int houseNumber, int postCode, String city) throws SQLException, ClassNotFoundException {

    }
}
