package model.invite;

import java.sql.SQLException;

import model.group.IGroup;
import model.invite.tdg.InviteTDG;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;


public class InviteFactory {
	public static Invite createNew(IUser _inviter, IUser _invitee, IGroup _group, int _kind) throws SQLException, MissingMappingException, MapperException {
		Invite invite = new Invite(InviteTDG.getMaxId(), _inviter, _invitee, _group, 1, _kind);
		UoW.getCurrent().registerNew(invite);
		return invite;
	}
	
	public static Invite createClean(long id, IUser _inviter, IUser _invitee, IGroup _group, long version, int _kind) {
		Invite invite = new Invite(id, _inviter, _invitee, _group, version, _kind);
		UoW.getCurrent().registerClean(invite);
		return invite;
	}

}
