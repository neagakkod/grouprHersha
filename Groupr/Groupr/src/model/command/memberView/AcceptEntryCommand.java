package model.command.memberView;

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
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;
import org.dsrg.soenea.uow.UoW;

public class AcceptEntryCommand extends DomainCommand {
	public AcceptEntryCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void setUp() throws CommandException {
	}
    
    @Override
	public void process() throws CommandException {
    	try {	
    		//get requester id & group id
		 	long requesterId	= helper.getLong("requesterID");
		 	long groupID 		= ((IGroup) helper.getSessionAttribute("myGroup")).getId();
		 	//get requester
		 	User requester=UserInputMapper.find(requesterId);
		 	
		 	//add the requester to my group
		 	GroupMembership gmembership = GroupMembershipFactory.createNew(requester, new GroupProxy(groupID));
		 	
		 	//delete all other requests of that requester
		 	List<IInvite> requests = InviteInputMapper.findByInvitee(requester, 1);
		 	for(IInvite request : requests){
		 		UoW.getCurrent().registerRemoved(request);
		 	}
		 	
		 	//reset my group members session attribute
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
