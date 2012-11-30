package model.groupmembership.tdg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GroupMembershipFinder {
	private final static String SELECT =
			"SELECT idGroupMembership, member, groupID, version FROM " + GroupMembershipTDG.TABLE + " WHERE idGroupMembership=?;";
	
	private final static String SELECT_BY_GROUP =
			"SELECT idGroupMembership, member, groupID, version FROM " + GroupMembershipTDG.TABLE + " WHERE groupID=?;";
	
	private final static String SELECT_BY_USER =
			"SELECT idGroupMembership, member, groupID, version FROM " + GroupMembershipTDG.TABLE + " WHERE member=?;";
	
	private final static String SELECT_ALL =
			"SELECT idGroupMembership, member, groupID, version FROM " + GroupMembershipTDG.TABLE + ";";
	
	private final static String SELECT_BY_PAGE =
		"SELECT idGroupMembership, member, groupID, version FROM " + GroupMembershipTDG.TABLE + " WHERE groupID=? LIMIT ?,5;";

	private final static String SELECT_COUNT =
		"SELECT COUNT(idGroupMembership) AS total FROM " + GroupMembershipTDG.TABLE + " WHERE groupID=?;"; 

	public static ResultSet findAll() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		return SQLLogger.processQuery(ps);
	}

	public static ResultSet findByGroup(Long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_GROUP);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findByUser(Long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_USER);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}

	public static ResultSet find(long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}

	// pagination
	public static ResultSet findByPage(Long id, int page) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_PAGE);
		ps.setLong(1, id);
		ps.setInt(2, (page-1)*5);
		return SQLLogger.processQuery(ps);
	}
	public static ResultSet findCount(Long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_COUNT);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}	
}
