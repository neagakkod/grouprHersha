package model.groupmembership.tdg;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GroupMembershipTDG {
	protected final static String BASE = "GroupMembership";
	protected final static String TABLE = DbRegistry.getTablePrefix() + BASE;
	public static PreparedStatement ps=null;
	public static int count;
	private static String CREATE =
			"CREATE TABLE IF NOT EXISTS " + TABLE + "( " +
			"idGroupMembership BIGINT NOT NULL PRIMARY KEY, " +
			"member BIGINT NOT NULL UNIQUE," +
			"groupID BIGINT NOT NULL," +
			"version BIGINT NOT NULL DEFAULT 1) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	private static String DROP = 
			"DROP TABLE IF EXISTS " + TABLE + ";";
	
	private static String INSERT =
			"INSERT INTO " + TABLE + "(idGroupMembership, member, groupID) VALUES(?,?,?);";
	private static String UPDATE =
			"UPDATE " + TABLE + " SET version=version+1, member=?, groupID=? WHERE idGroupMembership=? AND version=?;";
	private static String DELETE =
			"DELETE FROM " + TABLE + " WHERE idGroupMembership=? AND version=?;";
	
	public static long getMaxId() throws SQLException {
		return UniqueIdFactory.getMaxId(BASE, "idGroupMembership");
	}
	
	public static void createTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), CREATE);
	}
	
	public static void dropTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), DROP);
	}

	public static void insert(long id, long member, long group) throws SQLException {
		
		try
	      {
	            // Setup Transaction
				DbRegistry.getDbConnection().setAutoCommit(false);

				ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
				ps.setLong(1, id);
				ps.setLong(2, member);
				ps.setLong(3, group);
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
	}

	public static int update(long id, long member, long group, long version) throws SQLException {
		
		try
	      {
	            // Setup Transaction
				DbRegistry.getDbConnection().setAutoCommit(false);

				ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
				ps.setLong(1, member);
				ps.setLong(2, group);
				ps.setLong(3, id);
				ps.setLong(4, version);
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

	public static int delete(long id, long version) throws SQLException {
		
		try
	      {
	            // Setup Transaction
				DbRegistry.getDbConnection().setAutoCommit(false);

				ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
				ps.setLong(1, id);
				ps.setLong(2, version);
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
