package model.profile;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.uow.UoW;

public class ProfileFactory {
	public static Profile createNew(IUser user, String _firstname, String _lastname) throws SQLException, MapperException {
		Profile profile = new Profile(user.getId(), _firstname, _lastname);
		UoW.getCurrent().registerNew(profile);
		return profile;
	}
	
	public static Profile createClean(long id, String firstname, String lastname, long version) {
		Profile profile = new Profile(id, firstname, lastname, version);
		UoW.getCurrent().registerClean(profile);
		return profile;
	}
}
