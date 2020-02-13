package toptrumps;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;




/**

 * This is a simple test class that makes sure that we are able to connect to the database

 * @author Bokyung Lee

 *

 */
public class CheckDatabase {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ProgramDatabase postgre=new ProgramDatabase();
		postgre.selectGameStats();

//		check selection. 
//		What about changing selectGameStats method returns 5 values in array so that model could call it?
//		Currently, model needs to call every single values by using getters like below:
		System.out.println("Number of games played overall: "+postgre.getGameCount()
		+"\nHow many times the computer has won: "+postgre.getAIWon()
		+"\nHow many times the human has won: "+postgre.getHumanWon()
		+"\nThe average number of draws: "+postgre.getDraws()
		+"\nThe largest number of rounds played in a single game: "+postgre.getLargestRound());


		Connection c = null;

		try {

			Class.forName("org.postgresql.Driver");

			c = DriverManager.getConnection("jdbc:postgresql://52.24.215.108:5432/TryCatch","TryCatch", "TryCatch");

//			c = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/postgres", username, password);

			c.close();

		} catch (Exception e) {

			e.printStackTrace();

			System.err.println(e.getClass().getName()+": "+e.getMessage());

			System.exit(0);

		}

		System.out.println("Database is online and available");


	}

}
