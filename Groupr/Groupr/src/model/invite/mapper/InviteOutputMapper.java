package model.invite.mapper;

import java.sql.SQLException;

import model.invite.Invite;
import model.invite.tdg.InviteTDG;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

public class InviteOutputMapper extends GenericOutputMapper<Long, Invite> {
	
	@Override
	public void insert(Invite d) throws MapperException {
		try {
			InviteTDG.insert(d.getId(), d.getInviter().getId(), d.getInvitee().getId(), d.getGroup().getId(), d.getKind());
		} catch (SQLException e) {
			throw new MapperException("Could not INSERT Invite " + d.getId(), e);
		}
	}

	@Override
	public void update(Invite d) throws MapperException {
		try {
			int count = InviteTDG.update(d.getId(), d.getInviter().getId(), d.getInvitee().getId(), d.getGroup().getId(), d.getVersion(), d.getKind());
			if (count == 0)
				throw new LostUpdateException("InviteTDG: id " + d.getId() + " version " + d.getVersion());
		} catch (SQLException e) {
			throw new MapperException("Could not UPDATE Invite " + d.getId(), e);
		}
	}

	@Override
	public void delete(Invite d) throws MapperException {
		try {
			int count = InviteTDG.delete(d.getId(), d.getVersion());
			if (count == 0)
				throw new LostUpdateException("InviteTDG: id " + d.getId() + " version " + d.getVersion());
		} catch (SQLException e) {
			throw new MapperException("Could not DELETE Invite " + d.getId(), e);
		}
	}
}
