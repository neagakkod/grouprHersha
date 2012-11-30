package model.invite;

import model.group.IGroup;

import org.dsrg.soenea.domain.interf.IDomainObject;
import org.dsrg.soenea.domain.user.IUser;


public interface IInvite extends IDomainObject<Long> {
	public IUser getInviter();
	public void setInviter(IUser _inviter);
	public IUser getInvitee();
	public void setInvitee(IUser _invitee);
	public IGroup getGroup();
	public void setGroup(IGroup _group);
	public int getKind();
	public void setKind(int _kind);
}
