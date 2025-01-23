package Controller.DBController;
import java.sql.*;

public class ConnectionProvider {
    public static Connection getCon() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/livestream","root","pwd");
            System.out.println("Connected");
            return con;
        }
        catch(Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
