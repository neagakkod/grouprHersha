package model.command.memberView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.group.IGroup;
import model.groupmembership.GroupMembership;
import model.groupmembership.IGroupMembership;
import model.groupmembership.mapper.GroupMembershipInputMapper;
import model.invite.IInvite;
import model.invite.mapper.InviteInputMapper;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.UserProxy;
import org.dsrg.soenea.uow.MissingMappingException;
import org.dsrg.soenea.uow.UoW;


public class RemoveMemberCommand extends DomainCommand {
	public RemoveMemberCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void setUp() throws CommandException {
	}

	@Override
	public void process() throws CommandException {

			IUser me = (IUser) helper.getSessionAttribute("myUser");
		 	IUser memberToBeRemoved = new UserProxy(helper.getLong("memberID"));
		 	
		 	GroupMembership membershipToBeRemoved, myMembership;
		 	
		 	List<IGroupMembership> thisGroupMemberships = new ArrayList<IGroupMembership>();
		 	
		 	try {
		 		membershipToBeRemoved = GroupMembershipInputMapper.findByUser(memberToBeRemoved);
		 		myMembership = GroupMembershipInputMapper.findByUser(me);
		 		thisGroupMemberships = GroupMembershipInputMapper.findByGroup(myMembership.getGroup());
		 	} catch (MapperException e) {
		 		throw new CommandException(e.getMessage());
		 	} catch (SQLException e) {
		 		throw new CommandException(e);
		 	}
		 	
		 	if (myMembership.getGroup().getId() != membershipToBeRemoved.getGroup().getId())
		 		throw new CommandException("Get your own group, loser!");
		 	
		 	try {
		 		List<IInvite> invites = InviteInputMapper.findByInviter(memberToBeRemoved);
		 		for(IInvite invite : invites)
		 			UoW.getCurrent().registerRemoved(invite);
		 	} catch (MapperException e) { 
		 		throw new CommandException(e);
		 	} catch (SQLException e) { 
		 		throw new CommandException(e);
		 	}
		 	
	 		try {
				UoW.getCurrent().registerRemoved(membershipToBeRemoved);
				if (thisGroupMemberships.isEmpty())
					UoW.getCurrent().registerRemoved(myMembership.getGroup());
			} catch (MissingMappingException e) {
				throw new CommandException(e);
			} catch (MapperException e) {
				throw new CommandException(e);
			}
			
			if (thisGroupMemberships.size() == 1)
				try {
					UoW.getCurrent().registerRemoved(myMembership.getGroup());
				} catch (MissingMappingException e) {
					throw new CommandException(e);
				} catch (MapperException e) {
					throw new CommandException(e);
				}
		 	
		 	if (me.getId() == memberToBeRemoved.getId())
		 		helper.setSessionAttribute("myGroup", null);
		 	
		 	try {
		 		IGroupMembership myGroupMembership = GroupMembershipInputMapper.findByUser(me);
		 		IGroup myGroup = myGroupMembership.getGroup();
		 		helper.setSessionAttribute("myGroup", myGroup);
		 	} catch (SQLException e) {
		 		e.printStackTrace();
		 		throw new CommandException(e);
		 	} catch (MapperException e) {
		 		helper.setSessionAttribute("myGroup", null);
		 	}

	}
	@Override
	public void tearDown() throws CommandError {
	}
}
