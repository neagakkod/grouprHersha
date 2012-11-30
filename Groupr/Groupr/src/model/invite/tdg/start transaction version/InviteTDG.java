package model.invite.tdg;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class InviteTDG {
	public final static String BASE = "Invite";
	public final static String TABLE = DbRegistry.getTablePrefix() + BASE;
	public static PreparedStatement ps=null;
	public static int count;
	public final static String CREATE = 
			"CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
					"idInvite BIGINT NOT NULL PRIMARY KEY, " +
					"inviter BIGINT NOT NULL, " +
					"invitee BIGINT NOT NULL, " +
					"`group` BIGINT NOT NULL, " +
					"version BIGINT NOT NULL DEFAULT 1," +
					"kind INT NOT NULL, " +
					"UNIQUE KEY(inviter,invitee,`group`)) ENGINE=InnoDB DEFAULT CHARSET=utf8;";
	
	public final static String DROP =
			"DROP TABLE IF EXISTS " + TABLE + ";";
	
	public final static String INSERT =
			"INSERT INTO " + TABLE + "(idInvite, inviter, invitee, `group`, `kind`) values(?,?,?,?,?);";
	
	public final static String UPDATE =
			"UPDATE " + TABLE + " SET version=version+1, inviter=?, invitee=?, `group`=?, `kind`=? WHERE idInvite=? AND version=?;";
	
	public final static String DELETE =
			"DELETE FROM " + TABLE + " WHERE idInvite=? AND version=?;";
	
	public static long getMaxId() throws SQLException {
		return UniqueIdFactory.getMaxId(BASE, "idInvite");
	}
	
	public static void createTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), CREATE);
	}
	
	public static void dropTable() throws SQLException {
		SQLLogger.processUpdate(DbRegistry.getDbConnection().createStatement(), DROP);
	}

	public static void insert(long id, long inviter, long invitee, long group, int kind) throws SQLException {
		
		try
	      {
	            // Setup Transaction
				DbRegistry.getDbConnection().setAutoCommit(false);

				ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
				ps.setLong(1, id);
				ps.setLong(2, inviter);
				ps.setLong(3, invitee);
				ps.setLong(4, group);
				ps.setInt(5, kind);
				SQLLogger.processUpdate(ps);
	    		
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

	public static int update(long id, long inviter, long invitee, long group, long version, int kind) throws SQLException {
		
		try
	      {
	            // Setup Transaction
				DbRegistry.getDbConnection().setAutoCommit(false);

				ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
				ps.setLong(1, inviter);
				ps.setLong(2, invitee);
				ps.setLong(3, group);
				ps.setLong(4, id);
				ps.setLong(5, version);
				ps.setInt(6, kind);
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
