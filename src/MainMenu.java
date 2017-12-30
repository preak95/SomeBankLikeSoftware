import java.util.*;
import java.sql.*;

public class MainMenu {
	
	Customer customer = new Customer(); 
	
	
	public static void main(String[] args) throws SQLException {
		Customer customer = new Customer();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the future");
		System.out.println("Login:\nEnter Username");
		
		//Login: Entering user name and password
		String uName = scanner.nextLine();
		System.out.println("Enter Password");
		String pass = scanner.nextLine();
		customer =  customer.login(uName, pass);
		 
		customer.sequence();
		/*
		if(set == 1) {
			Statement st = customer.connection.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM " + customer.session.username);
			System.out.println("FRIEND      |     AMOUNT     ");
			while(rs.next()) {
				System.out.println("     " + rs.getString("Friend") + "            " + rs.getString("Amount"));			
			}
		}
		
		int c = 0;
		switch(c) {
		case 1:{
			
		}
			
		}*/
	}
}
