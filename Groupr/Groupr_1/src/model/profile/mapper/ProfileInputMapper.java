package model.profile.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.profile.IProfile;
import model.profile.Profile;
import model.profile.ProfileFactory;
import model.profile.ProfileProxy;
import model.profile.tdg.ProfileFinder;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.dsrg.soenea.domain.user.IUser;


public class ProfileInputMapper implements IdentityBasedProducer {
	public static ArrayList<IProfile> findAll() throws SQLException, MapperException {
		ArrayList<IProfile> list = new ArrayList<IProfile>();
		ResultSet rs = ProfileFinder.findAll();
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idProfile"), Profile.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new ProfileProxy(rs.getLong("idProfile")));
		}
		return list;
	}
	
	public static ArrayList<IProfile> findByName(Profile profile) throws SQLException, MapperException {
		ArrayList<IProfile> list = new ArrayList<IProfile>();
		ResultSet rs = ProfileFinder.findByName(profile.getFirstName(), profile.getLastName());
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idProfile"), Profile.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new ProfileProxy(rs.getLong("idProfile")));
		}
		if (list.isEmpty())
			throw new MapperException("No Profile with this FirstName/LastName exists!");
		return list;
	}
	
	public static Profile find(IUser user) throws SQLException, MapperException {
		return find(user.getId());
	}
	
	@IdentityBasedProducerMethod
	public static Profile find(long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Profile.class);
		} catch (DomainObjectNotFoundException e) { }
		ResultSet rs = ProfileFinder.find(id);
		if (!rs.next())
			throw new MapperException("Profile with id " + id + "does not exists.");
		return getProfile(rs);
	}
	
	private static Profile getProfile(ResultSet rs) throws SQLException {
		return ProfileFactory.createClean(rs.getLong("idProfile"),
				rs.getString("firstName").trim(),
				rs.getString("lastName").trim(),
				rs.getLong("version"));
	}
}
