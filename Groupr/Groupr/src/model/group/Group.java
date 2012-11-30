package model.group;

import java.util.List;

import model.groupmembership.IGroupMembership;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.user.IUser;


public class Group extends DomainObject<Long> implements IGroup {
	private String name, desc;
	private List<IGroupMembership> members;
	
	public Group(long id, String _name, String _desc, List<IGroupMembership> _members, long _version) {
		super(id, _version);
		name = _name;
		desc = _desc;
		members = _members;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String _name) {
		name = _name;
	}

	@Override
	public String getDesc() {
		return desc;
	}

	@Override
	public void setDesc(String _desc) {
		desc = _desc;
	}

	@Override
	public List<IGroupMembership> getMembers() {
		return members;
	}

	@Override
	public void setMembers(List<IGroupMembership> _members) {
		members = _members;
	}

	@Override
	public boolean isMember(IUser user) {
		for (IGroupMembership member : members)
			if (member.getMember().getId() == user.getId())
				return true;
		return false;
	}

}
