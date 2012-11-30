package model.profile;

import org.dsrg.soenea.domain.DomainObject;

public class Profile extends DomainObject<Long> implements IProfile {
	private String firstName, lastName;

	public Profile(long id, String _firstName, String _lastName, long _version) {
		super(id, _version);
		firstName = _firstName;
		lastName = _lastName;
	}
	
	public Profile(long id, String _firstName, String _lastName) {
		this(id, _firstName, _lastName, 1);
	}
	
	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String _firstname) {
		firstName = _firstname;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String _lastname) {
		lastName = _lastname;
	}
	
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	
}
