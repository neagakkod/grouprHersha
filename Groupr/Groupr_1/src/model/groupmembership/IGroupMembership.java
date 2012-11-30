package model.groupmembership;

import model.group.IGroup;

import org.dsrg.soenea.domain.interf.IDomainObject;
import org.dsrg.soenea.domain.user.IUser;


public interface IGroupMembership extends IDomainObject<Long> {
	public IUser getMember();
	public void setMember(IUser _member);
	public IGroup getGroup();
	public void setGroup(IGroup _group);
}
