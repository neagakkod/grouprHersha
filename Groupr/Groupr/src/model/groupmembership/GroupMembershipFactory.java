package model.groupmembership;

import java.sql.SQLException;

import model.group.IGroup;
import model.groupmembership.tdg.GroupMembershipTDG;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;


public class GroupMembershipFactory {
	public static GroupMembership createNew(IUser member, IGroup group) throws SQLException, MissingMappingException, MapperException {
		GroupMembership result = new GroupMembership(GroupMembershipTDG.getMaxId(), member, group, 1);
		UoW.getCurrent().registerNew(result);
		return result;
	}
	
	public static GroupMembership createClean(long id, IUser member, IGroup group, long version) throws SQLException {
		GroupMembership result = new GroupMembership(id, member, group, version);
		UoW.getCurrent().registerClean(result);
		return result;
	}
}
