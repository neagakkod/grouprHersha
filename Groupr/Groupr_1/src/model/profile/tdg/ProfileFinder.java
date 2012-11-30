package model.profile.tdg;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.logging.SQLLogger;
import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ProfileFinder {
	public final static String SELECT =
			"SELECT idProfile, firstName, lastName, version FROM " + ProfileTDG.TABLE + " WHERE idProfile=?;";
	public final static String SELECT_ALL =
			"SELECT idProfile, firstName, lastName, version FROM " + ProfileTDG.TABLE + ";";
	public final static String SELECT_BY_NAME =
			"SELECT idProfile, firstName, lastName, version FROM " + ProfileTDG.TABLE + " WHERE firstName=? AND lastName=?;";
	
	public static ResultSet find(long id) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT);
		ps.setLong(1, id);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findAll() throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_ALL);
		return SQLLogger.processQuery(ps);
	}
	
	public static ResultSet findByName(String _firstname, String _lastname) throws SQLException {
		PreparedStatement ps = DbRegistry.getDbConnection().prepareStatement(SELECT_BY_NAME);
		ps.setString(1, _firstname);
		ps.setString(2, _lastname);
		return SQLLogger.processQuery(ps);
	}
}
