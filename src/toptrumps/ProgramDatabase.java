package toptrumps;
import java.sql.*;

/**
 * Database connecting to Eclipse
 * ProgramDatabase class should be used both for CLI version and GUI version.
 * 
 * Try-Catch Team
 * Bokyung Lee 2431088l
 * 
 * postgresql-9.4-1206-jdbc4.jar
 * */
public class ProgramDatabase {
	protected static String connectionString="jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/";

	public static void main(String[] args) {
		
		String SQL_SELECT="Select * from TopTrumps.gameStats";
		
		try (Connection connection=DriverManager.getConnection(
				connectionString, "m_19_2431088l", "2431088l"); 
				PreparedStatement preparedStatement=connection.prepareStatement(SQL_SELECT)){
			
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				int gameID=resultSet.getInt("GameID");
				int countAIWins=resultSet.getInt("isHumanWon");
				int countHumanWins=resultSet.getInt("isHumanWon");
				int draws=resultSet.getInt("draws");
				int roundNumber=resultSet.getInt("roundNumber");
				
				
				/*				
				 * pass datas that should be printed when Game Statistics run
				*/
//				DataGame data = new DataGame(); // NOTE FROM ARNOLD: DataGame object should not be created multiple times, as this will be a completely different game.
				//we will need to resolve this with the controller.
//				data.setCountGameOverall(gameID);
//				data.setCountAIWins()
			}
			if(connection!=null) {
				System.out.println("Connected to the database");
			}else {
				System.out.println("Failed to make connection");
			}
		}catch(SQLException e) {
			System.err.format("SQL state: %s\n%s", e.getSQLState(), e.getMessage());
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}