package model.profile;

import java.sql.SQLException;

import model.profile.mapper.ProfileInputMapper;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;


public class ProfileProxy extends DomainObjectProxy<Long, Profile> implements IProfile {

	public ProfileProxy(long id) {
		super(id);
	}

	@Override
	public String getFirstName() {
		return getInnerObject().getFirstName();
	}

	@Override
	public void setFirstName(String _firstname) {
		getInnerObject().setFirstName(_firstname);
	}

	@Override
	public String getLastName() {
		return getInnerObject().getLastName();
	}

	@Override
	public void setLastName(String _lastname) {
		getInnerObject().setLastName(_lastname);
	}

	@Override
	protected Profile getFromMapper(Long id) throws MapperException, DomainObjectCreationException {
		try {
			return ProfileInputMapper.find(getId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
}
