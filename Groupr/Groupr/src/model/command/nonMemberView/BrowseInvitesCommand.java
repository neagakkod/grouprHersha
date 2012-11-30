package model.command.nonMemberView;

import model.invite.mapper.InviteInputMapper;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.User;

public class BrowseInvitesCommand extends DomainCommand {
	public BrowseInvitesCommand(Helper helper) {
        super(helper);
    }
    @Override
	public void setUp() throws CommandException {
	}

	@Override
	public void process() throws CommandException {
    	try {	
    		// get page attribute
    		int page = helper.getInt("page");
    		int pageCount  = (int) Math.ceil( InviteInputMapper.findCount(0) / 5.0 );
    		helper.setRequestAttribute("pageCount", pageCount);
    		
    		User myUser=(User) helper.getAttribute("myUser");
		 	// Find all invites (not including requests; 0)
    		helper.setRequestAttribute("myInvites", InviteInputMapper.findByInviteePage(myUser, 0, page)); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void tearDown() throws CommandError {
	}
}
