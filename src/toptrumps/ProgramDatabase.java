package toptrumps;
import java.sql.*;


/**
 * ProgramDatabase - creates the connection of postgre database with Eclipse. 
 * And Two methods: insertGameStats(DataGame model): void, selectGameStats(): void
 * getters for passing statistical values to DataGame class (model).
 * 
 * - ProgramDatabase class should be used both for CLI version and GUI version.
 * 
 * @author Team Try-Catch - Bokyung Lee 2431088l
 * 
 * */
public class ProgramDatabase {

	//When running database via lab computer, release annotation below 3 line
	//	private static final String url="jdbc:postgresql://localhost:5432/m_19_2431088l";
	//	private static final String userID="m_19_2431088l";
	//	private static final String password="2431088l";

	//When running database via remote IP address, release annotation below 3 line
//	private static final String url="jdbc:postgresql://52.24.215.108:5432/TryCatch";
//	private static final String userID="TryCatch";
//	private static final String password="TryCatch";
	
	//When running database via Estelle's database.
	private static final String url="jdbc:postgresql://localhost:5432/TopTrump";
	private static final String userID="postgres";
	private static final String password="qmffldqmffld3";
	private static Connection conn;

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
	static void insertGameStats(DataGame model){
		String SQL="INSERT INTO TOPTRUMPS.GAMESTATS "
				+"VALUES (default, ?,?,?,?,?,?,?,?)";
		try{
			conn=DriverManager.getConnection(url,userID,password);
			PreparedStatement pstmt=conn.prepareStatement(SQL);

			//get boolean value for isHumanWon
			if(model.getGameWinner().getName()!="You") {
				pstmt.setBoolean(1, false);
			}else {
				pstmt.setBoolean(1, true);
			}

			//get int value for humanScore, AI1Score, AI2Score, AI3Score, AI4Score respectively
			//should modify to GUI. when the number of AI players are less than 4. like:
			//DataGame model = DataGame.resetAndGetInstance(3); 
			for(int i=0;i<model.getAllPlayers().length;i++) {
				pstmt.setInt(2+i, model.getAllPlayers()[i].getScore());
			}
			
			if(model.getAllPlayers().length<=4) {
				pstmt.setInt(6, 0);
				if(model.getAllPlayers().length<=3) {
					pstmt.setInt(5, 0);
					if(model.getAllPlayers().length<=2) {
						pstmt.setInt(4, 0);
					}
				}
			}

			//get int value for draws
			pstmt.setInt(7, model.getNumberOfDraws());

			//get int value for total round number
			pstmt.setInt(8, model.getRoundNumber());
				
			
			//execute the preparedstatement insert
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * use clearDB when testing
	 * */
	public static void clearDB(DataGame model) {
           String SQL="delete from toptrumps.gamestats where true;";

           try{
                   conn=DriverManager.getConnection(url,userID,password);
                   PreparedStatement pstmt=conn.prepareStatement(SQL);
                   pstmt.executeUpdate();
                   pstmt.close();
           }catch(SQLException e) {
                   e.printStackTrace();
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
	void selectGameStats(){
		try{
			conn=DriverManager.getConnection(url,userID,password);

			Statement stmt=conn.createStatement();
			
			ResultSet rs=stmt.executeQuery("select \r\n" + 
					"	count(gameid) as gameCount, \r\n" + 
					"	count(*) filter(where isHumanWon) as humanWon, \r\n" + 
					"	count(*) filter(where not isHumanWon) as AIWon,\r\n" + 
					"	round(avg(draws)::numeric,1) as averageDraws, \r\n" + 
					"	max(roundNumber) as largestRound\r\n" + 
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

	/**
	 * main static method to test database connection when testing
	 * */
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

		try {
			conn=DriverManager.getConnection(url,userID,password);
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