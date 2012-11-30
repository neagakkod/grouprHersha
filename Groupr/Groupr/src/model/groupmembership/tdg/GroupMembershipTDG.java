package model.groupmembership.tdg;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.dsrg.soenea.service.UniqueIdFactory;
import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GroupMembershipTDG {
	protected final static String BASE = "GroupMembership";
	protected final static String TABLE = DbRegistry.getTablePrefix() + BASE;
	
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
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(INSERT);
		ps.setLong(1, id);
		ps.setLong(2, member);
		ps.setLong(3, group);
		SQLLogger.processUpdate(ps);
		ps.close();
	}

	public static int update(long id, long member, long group, long version) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(UPDATE);
		ps.setLong(1, member);
		ps.setLong(2, group);
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
}
