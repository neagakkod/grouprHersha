package model.command.memberView;

import java.util.List;

import model.group.GroupProxy;
import model.groupmembership.IGroupMembership;
import model.invite.IInvite;
import model.invite.mapper.InviteInputMapper;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;


public class RemoveGroupCommand extends DomainCommand {
	public RemoveGroupCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void setUp() throws CommandException {
	}

	@Override
	public void process() throws CommandException {
		try {	
		 	GroupProxy myGroup = new GroupProxy(helper.getLong("myGroup"));
		 	List<IGroupMembership> members = myGroup.getMembers();
		 	List<IInvite> invites = InviteInputMapper.findByGroup(myGroup);
		 	for(IGroupMembership member : members)
		 		UoW.getCurrent().registerRemoved(member);
		 	for(IInvite invite : invites)
		 		UoW.getCurrent().registerRemoved(invite);
		 	UoW.getCurrent().registerRemoved(myGroup);
		 	helper.setSessionAttribute("myGroup", null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void tearDown() throws CommandError {		
	}
}
