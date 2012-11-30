package model.group.tdg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class GroupFinder {
	public static String SELECT =
			"SELECT idGroup, name, `desc`, version FROM " + GroupTDG.TABLE + " WHERE idGroup=?;";
	public static String SELECT_BY_NAME =
			"SELECT idGroup, name, `desc`, version FROM " + GroupTDG.TABLE + " WHERE name=?;";
	public static String SELECT_ALL =
			"SELECT idGroup, name, `desc`, version FROM " + GroupTDG.TABLE + ";";
	public static String SELECT_PAGE =
			"SELECT idGroup, name, `desc`, version FROM " + GroupTDG.TABLE + " LIMIT ?, 5;";
	public static String SELECT_COUNT =
			"SELECT COUNT(idGroup) AS total FROM " + GroupTDG.TABLE + ";";
	
	public static ResultSet find(long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findAll() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findPage(int page) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_PAGE);
		ps.setInt(1, (page-1)*5);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findCount() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_COUNT);
		return SQLLogger.processQuery(ps);
	}
	public static ResultSet findByName(String name) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_NAME);
		ps.setString(1, name);
		return SQLLogger.processQuery(ps);
	}
}
