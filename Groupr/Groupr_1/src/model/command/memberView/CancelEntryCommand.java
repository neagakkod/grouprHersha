package model.command.memberView;

import java.util.List;
import model.group.IGroup;
import model.invite.IInvite;
import model.invite.mapper.InviteInputMapper;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;
import org.dsrg.soenea.uow.UoW;

public class CancelEntryCommand extends DomainCommand {
	public CancelEntryCommand(Helper helper) {
        super(helper);
    }
   
	@Override
	public void process() throws CommandException {
		//get requester id & group id
	 	long requesterId	= helper.getLong("requesterID");
	 	IGroup myGroup 		= (IGroup) helper.getSessionAttribute("myGroup");
	 	
		try {
			//get requester
		 	IUser requester=UserInputMapper.find(requesterId);
			//delete all other requests of that requester
		 	List<IInvite> requests = InviteInputMapper.findRequestGroup(requester, myGroup);
		 	for(IInvite request : requests){
		 		UoW.getCurrent().registerRemoved(request);
		 	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void tearDown() throws CommandError { }
	
	@Override
	public void setUp() throws CommandException { }
}
