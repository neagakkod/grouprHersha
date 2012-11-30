package model.group.mapper;

import java.sql.SQLException;

import model.group.Group;
import model.group.tdg.GroupTDG;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;

public class GroupOutputMapper extends GenericOutputMapper<Long, Group> {
 	@Override
	public void insert(Group d) throws MapperException {
		try {
			GroupTDG.insert(d.getId(), d.getName(), d.getDesc());
		} catch (SQLException e) {
			throw new MapperException("Could not INSERT Group " + d.getId(), e);
		}
	}

	@Override
	public void update(Group d) throws MapperException {
		try {
			int count = GroupTDG.update(d.getId(), d.getName(), d.getDesc(), d.getVersion());
			if (count == 0)
				throw new LostUpdateException("GroupTDG: id " + d.getId() + " version " + d.getVersion());
		} catch (SQLException e) {
			throw new MapperException("Could not UPDATE Group " + d.getId(), e);
		}
	}

	@Override
	public void delete(Group d) throws MapperException {
		try {
			int count = GroupTDG.delete(d.getId(), d.getVersion());
			if (count == 0)
				throw new LostUpdateException("GroupTDG: id " + d.getId() + " version " + d.getVersion());
		} catch (SQLException e) {
			throw new MapperException("Could not DELETE Group " + d.getId(), e);
		}
	}
}
