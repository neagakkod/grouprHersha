package model.groupmembership;

import java.sql.SQLException;

import model.group.IGroup;
import model.groupmembership.mapper.GroupMembershipInputMapper;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.dsrg.soenea.domain.user.IUser;


public class GroupMembershipProxy extends DomainObjectProxy<Long, GroupMembership> implements IGroupMembership {
	public GroupMembershipProxy(long id) {
		super(id);
	}

	@Override
	public IUser getMember() {
		return getInnerObject().getMember();
	}

	@Override
	public void setMember(IUser _member) {
		getInnerObject().setMember(_member);
	}

	@Override
	public IGroup getGroup() {
		return getInnerObject().getGroup();
	}

	@Override
	public void setGroup(IGroup _group) {
		getInnerObject().setGroup(_group);
	}

	@Override
	protected GroupMembership getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return GroupMembershipInputMapper.find(id);
		} catch (SQLException e) {
			throw new MapperException(e.getMessage(), e);
		}
	}
}
