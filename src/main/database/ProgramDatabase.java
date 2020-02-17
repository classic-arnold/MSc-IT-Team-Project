package main.database;
import java.sql.*;

import exceptions.*;

import main.model.*;


/**
 * ProgramDatabase - creates the connection of postgre database with Eclipse. 
 * And Two methods: insertGameStats(DataGame model): void, static methods to pass values from database to model.
 * 
 * - ProgramDatabase class should be used both for CLI version and GUI version.
 * 
 * @author Team Try-Catch - Bokyung Lee 2431088l
 * 
 * */
public class ProgramDatabase {

	//When running database via remote IP address, release annotation below 3 line
	private static final String url="jdbc:postgresql://52.24.215.108:5432/TryCatch";
	private static final String userID="TryCatch";
	private static final String password="TryCatch";
	
	//when running database via Lab machine, release annotation below 3 line.
//	private static final String url="jdbc:postgresql://localhost:5432/m_19_2431088l";
//	private static final String userID="m_19_2431088l";
//	private static final String password="2431088l";

	private static Connection conn;

	/** 
	 * Count game number
	 * @return
	 * int gameCount 
	 * */
	public static int getGameCount() {
		int result=0;

		try{
			conn=DriverManager.getConnection(url,userID,password);

			Statement stmt=conn.createStatement();

			ResultSet rs=stmt.executeQuery("select " + 
					"count(gameid) as gameCount " +
					"from TopTrumps.gameStats;");

			while(rs.next()) {
				result=rs.getInt("gameCount");
			}

			rs.close();
			stmt.close();

			// close the connection to the database
			conn.close();
		}catch(SQLException e) {
			throw new CannotConnectToDataBaseException();
		} // try-catch exception

		return result;
	}

	/** 
	 * Count games that human won
	 * @return
	 * int humanWon 
	 * */
	public static int getHumanWon() {
		int result=0;

		try{
			conn=DriverManager.getConnection(url,userID,password);

			Statement stmt=conn.createStatement();

			ResultSet rs=stmt.executeQuery("select " + 
					"count(*) filter(where isHumanWon) as humanWon " +
					"from TopTrumps.gameStats;");

			while(rs.next()) {
				result = rs.getInt("humanWon");
			}

			rs.close();
			stmt.close();

			// close the connection to the database
			conn.close();
		}catch(SQLException e) {
			throw new CannotConnectToDataBaseException();
		} // try-catch exception

		return result;
	}

	/** 
	 * Count games that AI won
	 * @return
	 * int AIWon 
	 * */
	public static int getAIWon() {
		int result = 0;
		try{
			conn=DriverManager.getConnection(url,userID,password);

			Statement stmt=conn.createStatement();

			ResultSet rs=stmt.executeQuery("select " +
					"count(*) filter(where not isHumanWon) as AIWon " +
					"from TopTrumps.gameStats;");

			while(rs.next()) {
				result=rs.getInt("AIWon");
			}
			rs.close();
			stmt.close();

			//close the connection to the database
			conn.close();
		}catch(SQLException e) {
			throw new CannotConnectToDataBaseException();
		}//try-catch exception
		return result;
	}

	/** 
	 * Average draws for entire games
	 * @return
	 * double draws 
	 * */
	public static double getDraws() {
		double result = 0;

		try{
			conn=DriverManager.getConnection(url,userID,password);

			Statement stmt=conn.createStatement();

			ResultSet rs=stmt.executeQuery("select " +
					"round(avg(draws)::numeric,1) as averageDraws " +
					"from TopTrumps.gameStats;");

			while(rs.next()) {
				result=rs.getDouble("averageDraws");
			}
			rs.close();
			stmt.close();

			//close the connection to the database
			conn.close();
		}catch(SQLException e) {
			throw new CannotConnectToDataBaseException();
		}//try-catch exception

		return result;
	}

	/** 
	 * Largest round among games
	 * @return
	 * int largestRound 
	 * */
	public static int getLargestRound() {
		int result=0;
		try{
			conn=DriverManager.getConnection(url,userID,password);

			Statement stmt=conn.createStatement();

			ResultSet rs=stmt.executeQuery("select " +
					"max(roundNumber) as largestRound " + 
					"from TopTrumps.gameStats;");

			while(rs.next()) {
				result = rs.getInt("largestRound");
			}
			rs.close();
			stmt.close();

			//close the connection to the database
			conn.close();
		}catch(SQLException e) {
			throw new CannotConnectToDataBaseException();
		}//try-catch exception
		return result;
	}


	/** 
	 * insertion method
	 * 
	 * @inserts values into database
	 * @return null
	 * */
	public static void insertGameStats(DataGame model){
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
			pstmt.setInt(8, model.getRound().getRoundNumber());


			//execute the preparedstatement insert
			pstmt.executeUpdate();
			pstmt.close();
		}catch(SQLException e) {
			throw new CannotConnectToDataBaseException();
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
			throw new CannotConnectToDataBaseException();
		}
	}

}