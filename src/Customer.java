import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*
;/* Here we see the customer class which will deal with the functions that the user may perform 

 * Some examples as of now would be:
 * 1. Add friend: to have transaction 
 * */

public class Customer {
 Session session;
 Connection connection;
 String name;
 Customer login(String username, String password){
		try {
			DBConnectivity conn = new DBConnectivity();
			connection = conn.connect("root", "pass");
			Statement stat = connection.createStatement();
			ResultSet rs = stat.executeQuery("SELECT * FROM Members WHERE uName = \""+ username + "\" AND Password = \"" + password + "\"");
			if(!rs.isBeforeFirst()) {
				System.out.println("Wrong credentials!");
				return null;
			}
			else {
				rs.next();
				this.session = new Session(username, password);
				name = rs.getString("Name");
				return this;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();		
			return null;
		}
		
	}
 
 void sequence() {
		int choice = 1;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter your choice:\n 1. Check your Name\n 2. Check your pending amounts\n 3. Add friend\n0. Exit");
		while(choice != 0) {
			System.out.println("Enter your choice");
			choice = scanner.nextInt();
			
			switch(choice) {
			case 1: {
					System.out.println("Your name: " + this.session.username);
				}
			break;
			
			case 2: {
					try {
						Statement st = this.connection.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM " + this.session.username);
						System.out.println("FRIEND      |     AMOUNT     ");
						while(rs.next()) {
							System.out.println("     " + rs.getString("Friend") + "            " + rs.getString("Amount"));			
						}
					}
					catch(Exception e) {
						System.out.println("ERROR!!!");
					}
				}
			break;
			
			case 3: {
					//Add a friend to your list
					System.out.println("Enter Friends name to add");
					Scanner sc = new Scanner(System.in);
					String friend = sc.nextLine();
					int set = this.addFriend(friend);
				}
			break;
			
			case 0: choice = 0;
			break;
			default: System.out.println("Enter correct  choice");
			}
		}
	}

 
 int addFriend(String friend) {
	 DBConnectivity conn = new DBConnectivity();
	 Connection connection1 = conn.connect("root", "pass");
	 try {
		 	//System.out.println("This is a debug statement 1");
		    Statement stat = connection1.createStatement();
		    //System.out.println("This is a debug statement 2");
		    ResultSet rs = stat.executeQuery("SELECT * FROM Members WHERE uName = \""+ friend + "\"");
		    //System.out.println("This is a debug statement 3");
	 		if(!rs.isBeforeFirst()) {
	 			System.out.println("Friend not found");
	 		}
	 		else {
				rs.next();
				//This means that the user is found. You can now add this user to your friend list
				try {
					stat.executeUpdate("INSERT INTO "+ session.username + " VALUES('"+ friend + "', " + 0 +" )" );
					return 1;
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Some error occured 2");
					e.printStackTrace();
				}	
	 		}
	 }
	 catch(Exception e) {
		System.out.println("Some error occured 1");
		e.printStackTrace();
     }
	 return 0;
 }
 
}
