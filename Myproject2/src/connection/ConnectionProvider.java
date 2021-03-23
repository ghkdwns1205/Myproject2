package connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

//connection 관리
public class ConnectionProvider {
    public static Connection getConnection() throws ClassNotFoundException {
	Connection con = null;
		
	try { 
		 String url = "jdbc:mysql://3.36.176.60:3306/Myproject";
		 String id = "jun";
		 String pw = "123";
		 String driver = "com.mysql.jdbc.Driver";
		 Class.forName(driver);
		 con = DriverManager.getConnection(url, id, pw);

	    
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return con;
    }
}