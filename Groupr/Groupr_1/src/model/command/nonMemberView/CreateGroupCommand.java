package model.command.nonMemberView;

import java.util.ArrayList;
import java.util.List;

import model.group.Group;
import model.group.GroupFactory;
import model.groupmembership.GroupMembership;
import model.groupmembership.GroupMembershipFactory;
import model.groupmembership.IGroupMembership;

import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.User;


public class CreateGroupCommand extends DomainCommand {
	public CreateGroupCommand(Helper helper) {
        super(helper);
    }

	@Override
	public void process() throws CommandException {

		try {	
		 	User myUser=(User) helper.getAttribute("myUser");
		 	
		 	String gName = helper.getString("gName");
		 	String gDesc = helper.getString("gDescr");
		 	
		 	Group myGroup = GroupFactory.createNew(gName, gDesc, null);
		 			 	
		 	GroupMembership myGmembership = GroupMembershipFactory.createNew(myUser, myGroup);
		 	List<IGroupMembership> listGM = new ArrayList<IGroupMembership>();
		 	listGM.add(myGmembership);
		 	
		 	myGroup.setMembers(listGM);
		 	
		 	helper.setSessionAttribute("myGroup", myGroup);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void tearDown() throws CommandError { }
	
	@Override
	public void setUp() throws CommandException { }
}
