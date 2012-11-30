package model.profile.mapper;

import java.sql.SQLException;

import model.profile.Profile;
import model.profile.tdg.ProfileTDG;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;


public class ProfileOutputMapper extends GenericOutputMapper<Long, Profile> {
	
	@Override
	public void insert(Profile d) throws MapperException {
		try {
			ProfileTDG.insert(d.getId(), d.getFirstName(), d.getLastName());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public void update(Profile d) throws MapperException {
		try {
			int count = ProfileTDG.update(d.getId(), d.getFirstName(), d.getLastName(), d.getVersion());
			if (count == 0)
				throw new LostUpdateException("Wrong version of Profile!");
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public void delete(Profile d) throws MapperException {
		try {
			int count = ProfileTDG.delete(d.getId(), d.getVersion());
			if (count == 0)
				throw new LostUpdateException("Wrong version of Profile!");
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
}
