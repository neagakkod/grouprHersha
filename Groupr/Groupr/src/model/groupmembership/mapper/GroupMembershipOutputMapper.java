package model.groupmembership.mapper;

import java.sql.SQLException;

import model.groupmembership.GroupMembership;
import model.groupmembership.tdg.GroupMembershipTDG;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

public class GroupMembershipOutputMapper extends GenericOutputMapper<Long, GroupMembership> {

	@Override
	public void insert(GroupMembership d) throws MapperException {
		try {
			GroupMembershipTDG.insert(d.getId(), d.getMember().getId(), d.getGroup().getId());
		} catch (SQLException e) {
			throw new MapperException("Could not INSERT GroupMembership " + d.getId(), e);
		}
	}

	@Override
	public void update(GroupMembership d) throws MapperException {
		try {
			int count = GroupMembershipTDG.update(d.getId(), d.getMember().getId(), d.getGroup().getId(), d.getVersion());
			if (count == 0)
				throw new LostUpdateException("GroupMembershipTDG: id " + d.getId() + " version " + d.getVersion());
		} catch (SQLException e) {
			throw new MapperException("Could not UPDATE GroupMembership " + d.getId(), e);
		}
	}

	@Override
	public void delete(GroupMembership d) throws MapperException {
		try {
			int count = GroupMembershipTDG.delete(d.getId(), d.getVersion());
			if (count == 0)
				throw new LostUpdateException("GroupMembershipTDG: id " + d.getId() + " version " + d.getVersion());
		} catch (SQLException e) {
			throw new MapperException("Could not DELETE GroupMembership " + d.getId(), e);
		}
	}
}
