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

	private static final String url="jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/m_19_2431088l";
	private static final String userID="m_19_2431088l";
	private static final String password="2431088l";
	//	private final String url="jdbc:postgresql://localhost:5432/postgres";
	//	private final String userID="postgres";
	//	private final String password="qmffldqmffld3";

	private int gameCount;
	private int humanWon;
	private int AIWon;
	private double draws;
	private int largestRound;

	//Getters
	/** 
	 * Count game number
	 * @return
	 * int gameCount 
	 * */
	public int getGameCount() {
		return gameCount;
	}

	/** 
	 * Count games that human won
	 * @return
	 * int humanWon 
	 * */
	public int getHumanWon() {
		return humanWon;
	}

	/** 
	 * Count games that AI won
	 * @return
	 * int AIWon 
	 * */
	public int getAIWon() {
		return AIWon;
	}

	/** 
	 * Average draws for entire games
	 * @return
	 * int draws 
	 * */
	public double getDraws() {
		return draws;
	}

	/** 
	 * Largest round among games
	 * @return
	 * int largestRound 
	 * */
	public int getLargestRound() {
		return largestRound;
	}

	
	
	/**
	 *  Connection method 
	 *  @returns connection (DriverManager.getConnection)
	 *  */
	public static Connection connection() throws SQLException{
		return DriverManager.getConnection(url,userID,password);
	}



	/** 
	 * insertion method
	 * 
	 * @return 0 or 1 (long).
	 * @throws SQLException 
	 * 
	 * @inserts values 
	 * 'isHumanWon:Boolean', 
	 * 'humanScore:int', 
	 * 'AI1Score:int', 
	 * 'AI2Score:int', 
	 * 'AI3Score:int', 
	 * 'AI4Score:int', 
	 * 'draws:int', 
	 * 'roundNumber:int' 
	 * into Database table.
	 * */
	public void insertGameStats(DataGame model, Connection conn) throws SQLException {
		String SQL="INSERT INTO TOPTRUMPS.GAMESTATS "
				+"VALUES (default, ?,?,?,?,?,?,?,?)";

		try{
			PreparedStatement pstmt=conn.prepareStatement(SQL);

			//get boolean value for isHumanWon
			if(model.getGameWinner().getName()!="You") {
				pstmt.setBoolean(1, false);
			}else {
				pstmt.setBoolean(1, true);
			}

			//get int value for humanScore, AI1Score, AI2Score, AI3Score, AI4Score respectively
			for(int i=0;i<model.getAllPlayers().length;i++) {
				pstmt.setInt(2+i, model.getAllPlayers()[i].getScore());
			}

			//get int value for draws
			pstmt.setInt(7, model.getNumberOfDraws());

			//get int value for total round number
			pstmt.setInt(8, model.getRoundNumber());

			//execute the preparedstatement insert
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			throw e;
		}
	}

	
	
	/**
	 * selection method
	 * 
	 * @select
	 * 'how many games were played',
	 * 'counts for human won',
	 * 'counts for AI won',
	 * 'counts for draws',
	 * 'largestRound'
	 */	
	public void selectGameStats(DataGame model, Connection conn) {
		try{
			Statement stmt=conn.createStatement();

			ResultSet rs=stmt.executeQuery("select" + 
					"	count(gameid) as gameCount," + 
					"	count(*) filter(where isHumanWon) as humanWon," + 
					"	count(*) filter(where not isHumanWon) as AIWon," + 
					"	round(avg(draws)::numeric,1) as averageDraws," + 
					"	max(roundNumber) as largestRound" + 
					"from TopTrumps.gameStats;");

			while(rs.next()) {
				gameCount=rs.getInt("gameCount");
				humanWon=rs.getInt("humanWon");
				AIWon=rs.getInt("AIWon");
				draws=rs.getDouble("averageDraws");
				largestRound=rs.getInt("largestRound");
			}
			rs.close();
			stmt.close();

			//close the connection to the database
			conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}//try-catch exception


	}


	
	
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

		//connect to the yacata.dcs.gla.ac.uk server, on port=5432
		try {
			connection();
			System.out.println("Opened database successfully");
		}catch(SQLException e) {
			System.out.println("Connection Failed!");
			e.printStackTrace();
			return;
		}catch(Exception e) {
			System.exit(0);
		}//try-catch exception


	}
}