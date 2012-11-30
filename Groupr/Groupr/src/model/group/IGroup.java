package model.group;

import java.util.List;

import model.groupmembership.IGroupMembership;

import org.dsrg.soenea.domain.interf.IDomainObject;
import org.dsrg.soenea.domain.user.IUser;


public interface IGroup extends IDomainObject<Long> {
	public String getName();
	public void setName(String _name);
	public String getDesc();
	public void setDesc(String _desc);
	public List<IGroupMembership> getMembers();
	public void setMembers(List<IGroupMembership> _members);
	public boolean isMember(IUser user);
}
