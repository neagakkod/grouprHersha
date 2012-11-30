package model.groupmembership.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.group.GroupProxy;
import model.group.IGroup;
import model.groupmembership.GroupMembership;
import model.groupmembership.GroupMembershipFactory;
import model.groupmembership.GroupMembershipProxy;
import model.groupmembership.IGroupMembership;
import model.groupmembership.tdg.GroupMembershipFinder;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.UserProxy;


public class GroupMembershipInputMapper implements IdentityBasedProducer {
	public static List<IGroupMembership> findAll() throws SQLException, MapperException {
		ArrayList<IGroupMembership> list = new ArrayList<IGroupMembership>();
		ResultSet rs = GroupMembershipFinder.findAll();
		while (rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idGroupMembership"), GroupMembership.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new GroupMembershipProxy(rs.getLong("idGroupMembership")));
		}
		return list;
	}
	
	public static List<IGroupMembership> findByGroup(IGroup group) throws SQLException, MapperException {
		ArrayList<IGroupMembership> list = new ArrayList<IGroupMembership>();
		ResultSet rs = GroupMembershipFinder.findByGroup(group.getId());
		while (rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idGroupMembership"), GroupMembership.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new GroupMembershipProxy(rs.getLong("idGroupMembership")));
		}
		return list;
	}
	
	public static GroupMembership findByUser(IUser user) throws SQLException, MapperException {
		ResultSet rs = GroupMembershipFinder.findByUser(user.getId());
		if (!rs.next())
			throw new MapperException("Member is not in group!");
		try {
			return IdentityMap.get(rs.getLong("idGroupMembership"), GroupMembership.class);
		} catch (DomainObjectNotFoundException e) { }
		return getGroupMembership(rs);
	}
	
	@IdentityBasedProducerMethod
	public static GroupMembership find(long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, GroupMembership.class);
		} catch (DomainObjectNotFoundException e) { }
		ResultSet rs = GroupMembershipFinder.find(id);
		if (!rs.next())
			throw new MapperException("No such group member (id=" + id + ") exists!");
		return getGroupMembership(rs);
	}
	
	public static GroupMembership getGroupMembership(ResultSet rs) throws SQLException {
		return GroupMembershipFactory.createClean(
				rs.getLong("idGroupMembership"),
				new UserProxy(rs.getLong("member")), 
				new GroupProxy(rs.getLong("groupID")),
				rs.getLong("version"));
	}

	public static List<IGroupMembership> findGroupMates(IUser user) throws SQLException, MapperException {
		IGroupMembership membership = findByUser(user);
		return findByGroup(membership.getGroup());
	}
	// pagination
	public static List<IGroupMembership> findByPage(IGroup group, int page) throws SQLException, MapperException {
		ArrayList<IGroupMembership> list = new ArrayList<IGroupMembership>();
		ResultSet rs = GroupMembershipFinder.findByPage(group.getId(), page);
		while (rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idGroupMembership"), GroupMembership.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new GroupMembershipProxy(rs.getLong("idGroupMembership")));
		}
		return list;
	}

	public static int findCount(IGroup group) throws SQLException, MapperException {
		int count = 0;
		ResultSet rs = GroupMembershipFinder.findCount(group.getId());
		if (rs.next())
			count = rs.getInt("total");
		return count;
	}
	
}
