package model.group;

import java.sql.SQLException;
import java.util.List;

import model.group.tdg.GroupTDG;
import model.groupmembership.IGroupMembership;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;


public class GroupFactory {
	public static Group createNew(String _name, String _desc, List<IGroupMembership> _members) throws SQLException, MissingMappingException, MapperException {
		Group group = new Group(GroupTDG.getMaxId(), _name, _desc, _members, 1);
		UoW.getCurrent().registerNew(group);
		return group;
	}
	
	public static Group createClean(long id, String name, String desc, List<IGroupMembership> members, long version) throws SQLException {
		Group group = new Group(id, name, desc, members, version);
		UoW.getCurrent().registerClean(group);
		return group;
	}
}
