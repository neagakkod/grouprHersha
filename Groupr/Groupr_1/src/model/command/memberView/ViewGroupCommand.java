package model.command.memberView;

import java.util.ArrayList;
import java.util.List;

import model.group.IGroup;
import model.groupmembership.IGroupMembership;
import model.groupmembership.mapper.GroupMembershipInputMapper;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;

public class ViewGroupCommand extends DomainCommand {
	public ViewGroupCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void setUp() throws CommandException {
	}

	@Override
	public void process() throws CommandException {
		try {	
			IGroup myGroup = (IGroup)helper.getSessionAttribute("myGroup");
			// get page attribute
    		int page = helper.getInt("page");
    		int pageCount  = (int) Math.ceil( GroupMembershipInputMapper.findCount(myGroup) / 5.0 );
    		helper.setRequestAttribute("pageCount", pageCount);
    		
    		
    		List<IGroupMembership> members = GroupMembershipInputMapper.findByPage(myGroup, page);
			
		 	List<IUser> list = new ArrayList<IUser>();
		 	
		 	for (IGroupMembership member : members)
		 		list.add(member.getMember());
		 	
		 	// garbage collection bro
		 	members = null;
		 	helper.setRequestAttribute("myGroupMates", list); 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void tearDown() throws CommandError {
	}
}
