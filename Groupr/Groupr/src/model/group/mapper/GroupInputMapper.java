package model.group.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.group.Group;
import model.group.GroupFactory;
import model.group.GroupProxy;
import model.group.IGroup;
import model.group.tdg.GroupFinder;
import model.groupmembership.MembershipListProxy;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;


public class GroupInputMapper implements IdentityBasedProducer {
	
	public static ArrayList<IGroup> findAll() throws SQLException, MapperException {
		ArrayList<IGroup> list = new ArrayList<IGroup>();
		ResultSet rs = GroupFinder.findAll();
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idGroup"), Group.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new GroupProxy(rs.getLong("idGroup")));
		}
		return list;
	}
	
	public static int findCount() throws SQLException, MapperException {
		int count = 0;
		ResultSet rs = GroupFinder.findCount();
		if (rs.next())
			count = rs.getInt("total");
		return count;
	}
	
	public static ArrayList<IGroup> findPage(int page) throws SQLException, MapperException {
		ArrayList<IGroup> list = new ArrayList<IGroup>();
		ResultSet rs = GroupFinder.findPage(page);
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idGroup"), Group.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new GroupProxy(rs.getLong("idGroup")));
		}
		return list;
	}
	
	public static Group findByName(Group group) throws SQLException, MapperException {
		ResultSet rs = GroupFinder.findByName(group.getName());
		if (!rs.next())
			throw new MapperException("No Group with this name!");
		try {
			return IdentityMap.get(rs.getLong("idGroup"), Group.class);
		} catch (DomainObjectNotFoundException e) { }
		return getGroup(rs);
	}
	
	@IdentityBasedProducerMethod
	public static Group find(long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Group.class);
		} catch (DomainObjectNotFoundException e) { }
		ResultSet rs = GroupFinder.find(id);
		if (!rs.next())
			throw new MapperException("Group with id " + id + "does not exists.");
		return getGroup(rs);
	}
	
	private static Group getGroup(ResultSet rs) throws SQLException {
		long id = rs.getLong("idGroup");
		IGroup group = new GroupProxy(id);
		Group result = GroupFactory.createClean(rs.getLong("idGroup"),
				rs.getString("name").trim(),
				rs.getString("desc").trim(),
				new MembershipListProxy(group),
				rs.getLong("version"));
		return result;
	}
}
