package model.invite;

import java.sql.SQLException;

import model.group.IGroup;
import model.invite.mapper.InviteInputMapper;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.dsrg.soenea.domain.user.IUser;


public class InviteProxy extends DomainObjectProxy<Long, Invite> implements IInvite {

	public InviteProxy(long id) {
		super(id);
	}

	@Override
	public IUser getInviter() {
		return getInnerObject().getInviter();
	}

	@Override
	public void setInviter(IUser _inviter) {
		getInnerObject().setInviter(_inviter);
	}

	@Override
	public IUser getInvitee() {
		return getInnerObject().getInvitee();
	}

	@Override
	public void setInvitee(IUser _invitee) {
		getInnerObject().setInvitee(_invitee);
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
	public int getKind() {
		return getInnerObject().getKind();
	}

	@Override
	public void setKind(int _kind) {
		getInnerObject().setKind(_kind);
	}
	
	@Override
	protected Invite getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return InviteInputMapper.find(getId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
}
