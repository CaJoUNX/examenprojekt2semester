package Database;

import org.media.examsprojekt.Var;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Data {
    private DatabaseConnection dbc;
    public Data() throws SQLException, ClassNotFoundException {
        dbc = new DatabaseConnection(Var.Database.IPADDRESS, String.valueOf(Var.Database.PORT), Var.Database.DATABASE, Var.Database.USERNAME, Var.Database.PASSWORD);
        dbc.open();
    }
    public int create(String table, String[] colums, String[] data ) throws SQLException {
        try {
            String prepColums = "";
            String prepData = "";
            for (int i = 0; i < colums.length; i++) {
                prepColums += colums[i] + ",";
            }
            for (int i = 0; i < data.length; i++) {
                prepData += data[i] + ",";
            }
            dbc.executeQuery("INSERT INTO " + table + "(" + prepColums + ") VALUES (" + prepData + ")");
            return 1;
        }
        catch (SQLException e) {
            System.out.println(e.getStackTrace() + " "+e.getMessage());
            return -1;
        }

    }
    public ResultSet read(String data, String table, String conditions) throws SQLException {
        try {
            ResultSet rs = dbc.executeQuery("SELECT " + data +  " FROM " + table + " WHERE" +conditions);
            return rs;
        }
        catch (SQLException e) {
            System.out.println(e.getStackTrace() + "" + e.getMessage());
            return null;
        }
    }
    public int update(String table, String[] data, String conditions) throws SQLException {
        String prepData = "";

        try {
            for (int i = 0; i < data.length; i++) {
                prepData += data[i] + ",";
            }
            dbc.executeQuery("UPDATE " + table + " SET " + prepData + " WHERE " + conditions);
            return 1;
        }
        catch (Exception e) {
            System.out.println(e.getStackTrace() + " " + e.getMessage());
            return -1;
        }

    }
    public int delete(String table, String condition) throws SQLException {
        try {
            dbc.executeQuery("DELETE FROM " + table + " WHERE " + condition);
            return 1;
        }
        catch (SQLException e) {
            System.out.println(e.getStackTrace() + " " + e.getMessage());
            return -1;
        }

    }
    public int count (String table, String condition) throws SQLException {
        dbc.executeQuery("SELECT COUNT(*) FROM " + table + " WHERE " + condition);
        ResultSet rs = dbc.executeQuery("SELECT COUNT(*) FROM " + table);
        rs.next();
        return rs.getInt(1);
    }


}
