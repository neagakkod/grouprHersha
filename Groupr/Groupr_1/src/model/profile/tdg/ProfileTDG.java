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
	
	public static int insert(long _id, String _firstName, String _lastName) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setLong(1, _id);
		ps.setString(2, _firstName);
		ps.setString(3, _lastName);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int update(long _id, String _firstname, String _lastname, long _version) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setString(1, _firstname);
		ps.setString(2, _lastname);
		ps.setLong(3, _id);
		ps.setLong(4, _version);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int delete(long _id, long _version) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setLong(1, _id);
		ps.setLong(2, _version);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
}
