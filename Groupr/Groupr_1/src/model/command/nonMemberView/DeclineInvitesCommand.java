package model.command.nonMemberView;

import model.invite.IInvite;
import model.invite.InviteProxy;
import model.invite.mapper.InviteInputMapper;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.uow.UoW;


public class DeclineInvitesCommand extends DomainCommand {
	public DeclineInvitesCommand(Helper helper) {
        super(helper);
    }
   
	@Override
	public void process() throws CommandException {
		try {	
			User myUser = (User) helper.getAttribute("myUser");
			IInvite invite = new InviteProxy(helper.getLong("inviteID"));
			
			UoW.getCurrent().registerRemoved(invite);
		 	
		 	helper.setRequestAttribute("myInvites", InviteInputMapper.findByInvitee(myUser, 0)); //find all my invites.
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void tearDown() throws CommandError { }
	
	@Override
	public void setUp() throws CommandException { }
}
