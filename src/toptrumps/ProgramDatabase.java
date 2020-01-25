package toptrumps;
import java.sql.*;

/*
 * Database connecting to Eclipse
 * ProgramDatabase class should be used both for CLI version and GUI version.
 * 
 * Try-Catch Team
 * Bokyung Lee 2431088l
 * 
 * postgresql-9.4-1206-jdbc4.jar
 * */
public class ProgramDatabase {
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
			connection=DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/m_19_2431088l", "m_19_2431088l", "2431088l");
			System.out.println("Opened database successfully");
			
			stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery("SELECT * FROM TopTrumps.GameStats;");
			while(rs.next()) {
				int gameID=rs.getInt("gameID");
				boolean isHumanWon=rs.getBoolean("isHumanWon");
				int humanScore=rs.getInt("humanScore");
				int AI1=rs.getInt("AI1Score");
				int AI2=rs.getInt("AI2Score");
				int AI3=rs.getInt("AI3Score");
				int AI4=rs.getInt("AI4Score");
				int draws=rs.getInt("draws");
				int roundNumber=rs.getInt("roundNumber");
				
				System.out.println(gameID+"/d"+isHumanWon+"/d"+humanScore+"/d"+AI1+"/d"+AI2+"/d"+AI3+"/d"+AI4+"/d"+draws+"/d"+roundNumber);
			}
			rs.close();
			stmt.close();
			connection.close();
		}catch(SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
//			return;
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

	//	
	//	protected static String connectionString="jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/";
	//
	//	
	//	
	//	public static void main(String[] args) {
	//		
	//		String SQL_SELECT="Select * from TopTrumps.gameStats";
	//		
	//		try (Connection connection=DriverManager.getConnection(
	//				connectionString, "m_19_2431088l", "2431088l"); 
	//				PreparedStatement preparedStatement=connection.prepareStatement(SQL_SELECT)){
	//			
	//			ResultSet resultSet=preparedStatement.executeQuery();
	//			
	//			while(resultSet.next()) {
	//				int gameID=resultSet.getInt("GameID");
	//				int countAIWins=resultSet.getInt("isHumanWon");
	//				int countHumanWins=resultSet.getInt("isHumanWon");
	//				int draws=resultSet.getInt("draws");
	//				int roundNumber=resultSet.getInt("roundNumber");
	//				
	//				
	//				/*				
	//				 * pass datas that should be printed when Game Statistics run
	//				*/
	////				DataGame data = new DataGame(); // NOTE FROM ARNOLD: DataGame object should not be created multiple times, as this will be a completely new game.
	//				//we will need to resolve this with the controller. Remember to remove this.
	////				data.setCountGameOverall(gameID);
	////				data.setCountAIWins()
	//			}
	//			if(connection!=null) {
	//				System.out.println("Connected to the database");
	//			}else {
	//				System.out.println("Failed to make connection");
	//			}
	//		}catch(SQLException e) {
	//			System.err.format("SQL state: %s\n%s", e.getSQLState(), e.getMessage());
	//		}catch(Exception e) {
	//			e.printStackTrace();
	//		}
	//	}
}