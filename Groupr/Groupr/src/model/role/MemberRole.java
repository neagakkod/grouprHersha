package model.role;

import org.dsrg.soenea.domain.role.IRole;
import org.dsrg.soenea.domain.role.Role;

public class MemberRole extends Role implements IRole {
	private static final long MEMBER_ROLE_ID = 2l;
	private static final String ROLE_NAME = "MemberRole";
	
	public MemberRole() {
		super(MEMBER_ROLE_ID, 1, ROLE_NAME);
	}

	@Override
	public String getName() {
		return ROLE_NAME;
	}

	@Override
	public Long getId() {
		return MEMBER_ROLE_ID;
	}

	@Override
	public long getVersion() {
		return 1;
	}
}
