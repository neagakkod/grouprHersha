package model.profile;

import org.dsrg.soenea.domain.interf.IDomainObject;

public interface IProfile extends IDomainObject<Long> {
	public String getFirstName();
	public void setFirstName(String _firstname);
	public String getLastName();
	public void setLastName(String _lastname);
}
