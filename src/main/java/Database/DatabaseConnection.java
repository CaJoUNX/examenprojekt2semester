package Database;

import java.sql.*;

public class DatabaseConnection {
    Connection connection;
    private static Connection con;
    private static PreparedStatement ps;
    private static ResultSet rs;
    private static String ipaddress;
    private static String port;
    private static String databaseName;
    private static String userName;
    private static String password;

    public DatabaseConnection(String ipaddress, String port, String databaseName, String userName, String password) {
        //Data are getting stored in Variables to establish reconnection in case of connection trouble.
        this.ipaddress = ipaddress;
        this.port = port;
        this.databaseName = databaseName;
        this.userName = userName;
        this.password = password;





    }

    public void close(){
        con = null;


    }


    public void open() throws ClassNotFoundException, SQLException {
        try{

            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            DriverManager.getConnection("jdbc:sqlserver://"+ ipaddress + ":"+port+";databaseName="+databaseName,userName,password);
        }
        catch(SQLException e){
            System.out.println(e.getStackTrace() + " : " + e.getMessage());
        }
    }

    public ResultSet executeQuery(String query) throws SQLException{
        ps = con.prepareStatement(query);
        rs = ps.executeQuery();
        return rs;
    }







}
