package model.invite;

import model.group.IGroup;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.user.IUser;


public class Invite extends DomainObject<Long> implements IInvite {
	private IUser inviter, invitee;
	private IGroup group;
	private int kind;
	
	public Invite(long _id, IUser _inviter, IUser _invitee, IGroup _group, long _version, int _kind) {
		super(_id, _version);
		inviter = _inviter;
		invitee = _invitee;
		group = _group;
		kind = _kind;
	}

	@Override
	public IUser getInviter() {
		return inviter;
	}

	@Override
	public void setInviter(IUser _inviter) {
		inviter = _inviter;
	}

	@Override
	public IUser getInvitee() {
		return invitee;
	}
	
	@Override
	public void setKind(int _kind) {
		kind = _kind;
	}

	@Override
	public int getKind() {
		return kind;
	}

	@Override
	public void setInvitee(IUser _invitee) {
		invitee = _invitee;
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
