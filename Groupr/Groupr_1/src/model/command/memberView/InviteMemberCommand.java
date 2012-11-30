package model.command.memberView;

import java.sql.SQLException;

import model.group.IGroup;
import model.groupmembership.mapper.GroupMembershipInputMapper;
import model.invite.InviteFactory;
import model.invite.mapper.InviteInputMapper;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;
import org.dsrg.soenea.uow.MissingMappingException;

public class InviteMemberCommand extends DomainCommand {
	public InviteMemberCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void setUp() throws CommandException {
	}

	
	@Override
	public void process() throws CommandException {
			 User inviter = (User) helper.getAttribute("myUser");
			 IGroup myGr  = (IGroup) helper.getAttribute("myGroup");
			 
			 //GroupProxy myGroup = new GroupProxy(helper.getString("groupName"));
			 String inviteeUsername = helper.getString("inviteeUsername");
			 	
			 User invitee;
			try {
				invitee = UserInputMapper.find(inviteeUsername);
			} catch (SQLException e2) {
				throw new CommandException(e2);
			} catch (MapperException e2) {
				throw new CommandException(e2);
			}
			 
			 try {
				 InviteInputMapper.findByInviterAndInvitee(inviter, invitee);
				 throw new CommandException("User already invited!");
			 } catch (MapperException e) {} catch (SQLException e) {}
			 try {
				 GroupMembershipInputMapper.findByUser(invitee);
				 throw new CommandException("User already in a group");
				 //TELL THE USER THAT THIS DUDE IS ALREADY IN A GROUP
			 } catch (MapperException e) {
				 try {
					InviteFactory.createNew(inviter,invitee,myGr,0);
				} catch (MissingMappingException e1) {
					throw new CommandException(e1);
				} catch (SQLException e1) {
					throw new CommandException(e1);
				} catch (MapperException e1) {
					throw new CommandException(e1);
				}
			 } catch (SQLException e) {
				throw new CommandException(e);
			}
	}
	
	@Override
	public void tearDown() throws CommandError {
	}
}
