package model.profile.tdg;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ProfileTDG {
	protected final static String BASE = "Profile";
	protected final static String TABLE = DbRegistry.getTablePrefix() + BASE;
	
	public static String CREATE =
			"CREATE TABLE IF NOT EXISTS " + TABLE + "( " +
			"`idProfile` BIGINT NOT NULL PRIMARY KEY, " +
			"`firstName` VARCHAR(32) NOT NULL," +
			"`lastName` VARCHAR(32) NOT NULL," +
			"`version` BIGINT NOT NULL DEFAULT 1)" +
			") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	public static String DROP = 
			"DROP TABLE IF EXISTS " + TABLE + ";";
	
	public static String INSERT =
			"INSERT INTO " + TABLE + "(`idProfile`, `firstName`, `lastName`) VALUES(?,?,?);";
	public static String UPDATE =
			"UPDATE " + TABLE + " " +
			"SET `version`=`version`+1, `firstName`=?, `lastName`=? " +
			"WHERE `idProfile`=? AND `version`=?;";
	public static String DELETE =
			"DELETE FROM " + TABLE + " " +
			"WHERE `idProfile`=? AND `version`=?;";
	
	public static void createTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), CREATE);
	}
	
	public static void dropTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), DROP);
	}
	public static PreparedStatement ps=null;
	public static int count;
	
	public static int insert(long _id, String _firstName, String _lastName) throws SQLException {
		try
	      {
	            // Setup Transaction
				DbRegistry.getDbConnection().setAutoCommit(false);

	            ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
	    		ps.setLong(1, _id);
	    		ps.setString(2, _firstName);
	    		ps.setString(3, _lastName);
	    		count = SQLLogger.processUpdate(ps);
	    		
	    		DbRegistry.getDbConnection().commit();
	    		
	      }
	      catch (SQLException ex)          // executes upon SQL exception: This block will perform a rollback()
	      {
	           try
	           {
	               if( DbRegistry.getDbConnection() != null){
	            	   DbRegistry.getDbConnection().rollback();
	               }
	           } catch (SQLException ex2) {
	              System.out.println("Caught SQL Exception: " + ex2);
	           }

	           while (ex != null) {
	               System.out.println ("SQL Exception:  " + ex.getMessage ());
	               ex = ex.getNextException ();
	           }
	      }
	      finally  // This always gets executed when the try block exits with or without an exception: No rollback()
	      {
	           try
	           {
	               if(ps != null)
	               {
	                   ps.close();
	               }

	               if( DbRegistry.getDbConnection() != null){
	            	   DbRegistry.getDbConnection().setAutoCommit(true);
	            	   DbRegistry.getDbConnection().close();
	               }
	               
	           } catch (SQLException exf) {
	              System.out.println("Caught SQL Exception: " + exf);
	           }
	      }
		return count;
		
	}
	
	public static int update(long _id, String _firstname, String _lastname, long _version) throws SQLException {
		
		try
	      {	
	            // Setup Transaction
				DbRegistry.getDbConnection().setAutoCommit(false);

				ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
				ps.setString(1, _firstname);
				ps.setString(2, _lastname);
				ps.setLong(3, _id);
				ps.setLong(4, _version);
	    		count = SQLLogger.processUpdate(ps);
	    		
	    		DbRegistry.getDbConnection().commit();
	    		
	      }
	      catch (SQLException ex)          // executes upon SQL exception: This block will perform a rollback()
	      {
	           try
	           {
	               if( DbRegistry.getDbConnection() != null){
	            	   DbRegistry.getDbConnection().rollback();
	               }
	           } catch (SQLException ex2) {
	              System.out.println("Caught SQL Exception: " + ex2);
	           }

	           while (ex != null) {
	               System.out.println ("SQL Exception:  " + ex.getMessage ());
	               ex = ex.getNextException ();
	           }
	      }
	      finally  // This always gets executed when the try block exits with or without an exception: No rollback()
	      {
	           try
	           {
	               if(ps != null)
	               {
	                   ps.close();
	               }

	               if( DbRegistry.getDbConnection() != null){
	            	   DbRegistry.getDbConnection().setAutoCommit(true);
	            	   DbRegistry.getDbConnection().close();
	               }
	               
	           } catch (SQLException exf) {
	              System.out.println("Caught SQL Exception: " + exf);
	           }
	      }
		return count;
	}
	
	public static int delete(long _id, long _version) throws SQLException {
		
		try
	      {	
	            // Setup Transaction
				DbRegistry.getDbConnection().setAutoCommit(false);

				ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
				ps.setLong(1, _id);
				ps.setLong(2, _version);
	    		count = SQLLogger.processUpdate(ps);
	    		
	    		DbRegistry.getDbConnection().commit();
	    		
	      }
	      catch (SQLException ex)          // executes upon SQL exception: This block will perform a rollback()
	      {
	           try
	           {
	               if( DbRegistry.getDbConnection() != null){
	            	   DbRegistry.getDbConnection().rollback();
	               }
	           } catch (SQLException ex2) {
	              System.out.println("Caught SQL Exception: " + ex2);
	           }

	           while (ex != null) {
	               System.out.println ("SQL Exception:  " + ex.getMessage ());
	               ex = ex.getNextException ();
	           }
	      }
	      finally  // This always gets executed when the try block exits with or without an exception: No rollback()
	      {
	           try
	           {
	               if(ps != null)
	               {
	                   ps.close();
	               }

	               if( DbRegistry.getDbConnection() != null){
	            	   DbRegistry.getDbConnection().setAutoCommit(true);
	            	   DbRegistry.getDbConnection().close();
	               }
	               
	           } catch (SQLException exf) {
	              System.out.println("Caught SQL Exception: " + exf);
	           }
	      }
		return count;
	}
}
