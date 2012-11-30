package model.group.tdg;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GroupTDG {
	public final static String BASE = "Group";
	public final static String TABLE = DbRegistry.getTablePrefix() + BASE;
	
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
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setLong(1, id);
		ps.setString(2, name);
		ps.setString(3, desc);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int update(long id, String name, String desc, long version) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
		ps.setString(1, name);
		ps.setString(2, desc);
		ps.setLong(3, id);
		ps.setLong(4, version);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}
	
	public static int delete(long id, long version) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(DELETE);
		ps.setLong(1, id);
		ps.setLong(2, version);
		int count = SQLLogger.processUpdate(ps);
		ps.close();
		return count;
	}

	public static long getMaxId() throws SQLException {
		return UniqueIdFactory.getMaxId(BASE, "idGroup");
	}
}
