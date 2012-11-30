package model.groupmembership;

import model.group.IGroup;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.user.IUser;


public class GroupMembership extends DomainObject<Long> implements IGroupMembership {
	private IUser member;
	private IGroup group;
	
	public GroupMembership(long id, IUser _member, IGroup _group, long version) {
		super(id, version);
		member = _member;
		group = _group;
	}

	@Override
	public IUser getMember() {
		return member;
	}

	@Override
	public void setMember(IUser _member) {
		member = _member;
	}

	@Override
	public IGroup getGroup() {
		return group;
	}

	@Override
	public void setGroup(IGroup _group) {
		group = _group;
	}
	
}
