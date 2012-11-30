package model.invite.tdg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class InviteFinder {
	public static String SELECT =
			"SELECT idInvite, inviter, invitee, `group`, version, `kind` FROM " + InviteTDG.TABLE + " WHERE idInvite=?;";
	public static String SELECT_BY_INVITER =
			"SELECT idInvite, inviter, invitee, `group`, version, `kind` FROM " + InviteTDG.TABLE + " WHERE inviter=?;";
	public static String SELECT_BY_INVITEE =
			"SELECT idInvite, inviter, invitee, `group`, version, `kind` FROM " + InviteTDG.TABLE + " WHERE invitee=? AND `kind`=?;";
	public static String SELECT_BY_GROUP =
			"SELECT idInvite, inviter, invitee, `group`, version, `kind` FROM " + InviteTDG.TABLE + " WHERE group=?;";
	public static String SELECT_BY_INVITER_AND_INVITEE =
			"SELECT idInvite, inviter, invitee, `group`, version, `kind` FROM " + InviteTDG.TABLE + " WHERE inviter=? AND invitee=?;";
	public static String SELECT_ALL =
			"SELECT idInvite, inviter, invitee, `group`, version, `kind` FROM " + InviteTDG.TABLE + ";";
	public static String SELECT_COUNT =
		"SELECT COUNT(idInvite) AS total FROM " + InviteTDG.TABLE + " WHERE `kind`=?;";
	
	// For requests
	public static String SELECT_ALL_REQUESTS_FOR_GROUP =
			"SELECT idInvite, inviter, invitee, `group`, version, `kind` FROM " + InviteTDG.TABLE + " WHERE `kind`=1 AND `group`=?;";
	public static String SELECT_REQUEST_EXISTS = 
			"SELECT idInvite, inviter, invitee, `group`, version, `kind` FROM " + InviteTDG.TABLE + " WHERE invitee=? AND `group`=? AND `kind`=1;";
	public static String SELECT_BY_INVITEE_PAGE =
			"SELECT idInvite, inviter, invitee, `group`, version, `kind` FROM " + InviteTDG.TABLE + " WHERE invitee=? AND `kind`=? LIMIT ?, 5;";
	public static String SELECT_COUNT_REQUESTS =
			"SELECT COUNT(idInvite) AS total FROM " + InviteTDG.TABLE + " WHERE `group`=? AND `kind`=?;";
	public static String SELECT_PAGE_REQUESTS_FOR_GROUP =
			"SELECT idInvite, inviter, invitee, `group`, version, `kind` FROM " + InviteTDG.TABLE + " WHERE `kind`=1 AND `group`=? LIMIT ?,5;";

	public static ResultSet findAllByKind(int _kind, long _groudID) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL_REQUESTS_FOR_GROUP);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet find(long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findByInviter(long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_INVITER);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findByInvitee(long id, int kind) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_INVITEE);
		ps.setLong(1, id);
		ps.setInt(2, kind);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findByInviteeAndInviter(long inviter, long invitee) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_INVITER_AND_INVITEE);
		ps.setLong(1, inviter);
		ps.setLong(2, invitee);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findAll() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findByGroup(long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_GROUP);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}

	public static ResultSet findRequestGroup(long userID, long groupID) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_REQUEST_EXISTS);
		ps.setLong(1, userID);
		ps.setLong(2, groupID);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findAllRequestForGroup(long groupID, int page) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_PAGE_REQUESTS_FOR_GROUP);
		ps.setLong(1, groupID);
		ps.setInt(2, (page-1)*5);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findByInviteePage(long id, int kind, int page) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_INVITEE_PAGE);
		ps.setLong(1, id);
		ps.setInt(2, kind);
		ps.setInt(3, (page-1)*5);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findCount(int type) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_COUNT);
		ps.setInt(1, type);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findCountRequests(long id, int type) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_COUNT_REQUESTS);
		ps.setLong(1, id);
		ps.setInt(2, type);
		return SQLLogger.processQuery(ps);
	}
}
