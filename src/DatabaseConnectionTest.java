import java.sql.Connection;

import java.sql.DriverManager;

import java.util.Scanner;



/**

 * This is a simple test class that makes sure that we are able to connect to the database

 * @author richardm

 *

 */

public class DatabaseConnectionTest {



	/**

	 * Main Method - gets the database username and password and then runs a connection test

	 * @param args

	 */

	public static void main(String args[]) {



		// input from standard in

		Scanner input = new Scanner(System.in);



		System.out.print("Enter Username: ");

		String username = input.nextLine();



		System.out.print("Enter Password: ");

		String password = input.nextLine();

		System.out.println();



		input.close();

		Connection c = null;

		try {

			Class.forName("org.postgresql.Driver");

			c = DriverManager

					.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/postgres",

							"m_19_"+username, password);

			c.close();

		} catch (Exception e) {

			e.printStackTrace();

			System.err.println(e.getClass().getName()+": "+e.getMessage());

			System.exit(0);

		}

		System.out.println("Database is online and available");

	}

}

