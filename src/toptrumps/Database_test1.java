package toptrumps;

import java.sql.*;

public class Database_test1 {
				
		public static void main(String[] args) {
			//load the JDBC driver
			try {
				Class.forName("org.postgresql.Driver");
			}catch(ClassNotFoundException e) {
				System.out.println("Could not find JDBC Driver");
				e.printStackTrace();
				return;
			}//try-catch exception

			//the driver is loaded
			System.out.println("PostgreSQL JDBC Driver found!");

			//proceed with a database connection
			Connection connection = null;
			Statement stmt=null;
			
			//connect to the yacata.dcs.gla.ac.uk server, on port=5432
			try {
				connection=DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "qmffldqmffld3");
//				private final String url="jdbc:postgresql://localhost:5432/postgre";
//				private final String userID="postgre";
//				private final String password="qmffldqmffld3";
				
				System.out.println("Opened database successfully");
				
			}catch(SQLException e) {
				System.out.println("Connection Failed!");
				e.printStackTrace();
				return;
			}catch(Exception e) {
				System.exit(0);
			}//try-catch exception
			

			//connection to the database is done
			if(connection!=null) {
				try {
					System.out.println("Controlling your database");

					//do not forget to close the connection to the database
					connection.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}//try-catch exception
			}else {
				System.out.println("Failed to establish connection!");
			}//if-else
		}
}
