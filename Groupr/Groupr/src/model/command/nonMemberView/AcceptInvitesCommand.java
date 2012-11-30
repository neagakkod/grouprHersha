package model.command.nonMemberView;

import java.util.List;

import model.group.GroupProxy;
import model.group.IGroup;
import model.groupmembership.GroupMembership;
import model.groupmembership.GroupMembershipFactory;
import model.invite.IInvite;
import model.invite.mapper.InviteInputMapper;


import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.uow.UoW;

public class AcceptInvitesCommand extends DomainCommand {
	public AcceptInvitesCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void setUp() throws CommandException {
	}
    
    @Override
	public void process() throws CommandException {
    	try {	
		 	User myUser=(User) helper.getAttribute("myUser");
		 	long groupID = helper.getLong("groupID");
		 	GroupMembership gmembership = GroupMembershipFactory.createNew(myUser, new GroupProxy(groupID));
		 	
		 	List<IInvite> invts=InviteInputMapper.findByInvitee(myUser, 0);
		 	for(IInvite invite : invts){
		 		UoW.getCurrent().registerRemoved(invite);
		 	}
		 	
		 	IGroup myGroup = gmembership.getGroup();	
		 	helper.setSessionAttribute("myGroup", myGroup);
		 	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	 @Override
	public void tearDown() throws CommandError {
	}
}
