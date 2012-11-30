package model.group;

import java.sql.SQLException;
import java.util.List;

import model.group.mapper.GroupInputMapper;
import model.groupmembership.IGroupMembership;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.dsrg.soenea.domain.user.IUser;


public class GroupProxy extends DomainObjectProxy<Long, Group> implements IGroup {
	
	public GroupProxy(Long id) {
		super(id);
	}

	@Override
	public String getName() {
		return getInnerObject().getName();
	}

	@Override
	public void setName(String _name) {
		getInnerObject().setName(_name);
	}

	@Override
	public String getDesc() {
		return getInnerObject().getDesc();
	}

	@Override
	public void setDesc(String _desc) {
		getInnerObject().setDesc(_desc);
	}

	@Override
	public List<IGroupMembership> getMembers() {
		return getInnerObject().getMembers();
	}

	@Override
	public void setMembers(List<IGroupMembership> _members) {
		getInnerObject().setMembers(_members);
	}

	@Override
	public boolean isMember(IUser user) {
		return getInnerObject().isMember(user);
	}

	@Override
	protected Group getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return GroupInputMapper.find(id);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
}
