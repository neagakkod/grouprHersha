package model.command.memberView;

import model.group.IGroup;
import model.groupmembership.GroupMembership;
import model.groupmembership.mapper.GroupMembershipInputMapper;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.uow.UoW;

public class EditGroupCommand extends DomainCommand {
	public EditGroupCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void setUp() throws CommandException { }

	@Override
	public void process() throws CommandException {
		try {	
			 	String gName = helper.getString("gName");
			 	String gDescr = helper.getString("gDescr");
			 	
			 	User myUser = (User) helper.getAttribute("myUser");
			 	
			 	GroupMembership gmembership = GroupMembershipInputMapper.findByUser(myUser);
			 	IGroup myGroup = gmembership.getGroup();			 	
			 	
			 	myGroup.setName(gName);
			 	myGroup.setDesc(gDescr);
			 	UoW.getCurrent().registerDirty(myGroup);
			 	
			 	helper.setSessionAttribute("myGroup", myGroup);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	@Override
	public void tearDown() throws CommandError { }
}
