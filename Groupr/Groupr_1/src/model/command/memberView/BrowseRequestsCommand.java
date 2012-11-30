package model.command.memberView;

import java.util.ArrayList;
import java.util.List;
import model.group.IGroup;
import model.invite.IInvite;
import model.invite.mapper.InviteInputMapper;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;


public class BrowseRequestsCommand extends DomainCommand {
	public BrowseRequestsCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void setUp() throws CommandException {
	}

	@Override
	public void process() throws CommandException {
		try {	
			// Get my group ID
			IGroup myGroup = (IGroup) helper.getSessionAttribute("myGroup");
			// get page attribute
    		int page = helper.getInt("page");
    		int pageCount  = (int) Math.ceil( InviteInputMapper.findCountRequests(myGroup, 1) / 5.0 );
    		helper.setRequestAttribute("pageCount", pageCount);
    		
			
			// Get all invites for this group ID with kind == 1
			List<IInvite> invites = InviteInputMapper.findAllRequestForGroup(myGroup, 1, page);
			
		 	List<IUser> list = new ArrayList<IUser>();
		 	
		 	for (IInvite invite : invites)
		 		list.add(invite.getInvitee());
		 	
		 	// garbage collection bro
		 	invites = null;
		 	helper.setRequestAttribute("allGroupRequests", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void tearDown() throws CommandError {
	}
}
