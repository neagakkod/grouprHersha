package model.group.tdg;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GroupTDG {
	public final static String BASE = "Group";
	public final static String TABLE = DbRegistry.getTablePrefix() + BASE;
	public static PreparedStatement ps=null;
	public static int count;
	public final static String CREATE = 
			"CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
					"`idGroup` BIGINT NOT NULL PRIMARY KEY, " +
					"`name` VARCHAR(128) NOT NULL, " +
					"`desc` TEXT NOT NULL, " +
					"`version` BIGINT NOT NULL DEFAUL 1," +
					"UNIQUE(`name`))," +
					"ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	public final static String DROP =
			"DROP TABLE IF EXISTS " + TABLE + ";";
	
	public final static String INSERT =
			"INSERT INTO " + TABLE + "(idGroup, name, `desc`) values(?,?,?);";
	
	public final static String UPDATE =
			"UPDATE " + TABLE + " SET version=version+1, name=?, `desc`=? WHERE idGroup=? AND version=?;";
	
	public final static String DELETE =
			"DELETE FROM " + TABLE + " WHERE idGroup=? AND version=?;";
	
	public static void createTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), CREATE);
	}
	
	public static void dropTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), DROP);
	}
	
	public static int insert(long id, String name, String desc) throws SQLException {
		
		try
	      {
	            // Setup Transaction
				DbRegistry.getDbConnection().setAutoCommit(false);

				ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
				ps.setLong(1, id);
				ps.setString(2, name);
				ps.setString(3, desc);
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
	
	public static int update(long id, String name, String desc, long version) throws SQLException {
		
		try
	      {
	            // Setup Transaction
				DbRegistry.getDbConnection().setAutoCommit(false);

				ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
				ps.setString(1, name);
				ps.setString(2, desc);
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

	public static long getMaxId() throws SQLException {
		return UniqueIdFactory.getMaxId(BASE, "idGroup");
	}
}
