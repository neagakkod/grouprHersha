package model.groupmembership;

import java.util.List;

import model.group.IGroup;
import model.groupmembership.mapper.GroupMembershipInputMapper;

import org.dsrg.soenea.domain.proxy.ListProxy;


public class MembershipListProxy extends ListProxy<IGroupMembership> {
	private IGroup group;
	
	public MembershipListProxy(IGroup _group) {
		super();
		group = _group;
	}
	
	@Override
	public List<IGroupMembership> getActualList() throws Exception {
		return GroupMembershipInputMapper.findByGroup(group);
	}
}
