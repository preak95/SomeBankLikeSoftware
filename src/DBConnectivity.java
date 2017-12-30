import java.sql.*;
public class DBConnectivity {	
	Connection connect(String username, String password) {
		Connection myCon = null;
		try {
			String dbURL = "jdbc:mysql://localhost:3306/banking";
			username = "root";
			password = "myrootpasswordisstrong";
			myCon = DriverManager.getConnection(dbURL, username, password);
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		return myCon;
	}
}
