package model.command.nonMemberView;

import java.sql.SQLException;

import model.group.IGroup;
import model.group.mapper.GroupInputMapper;
import model.invite.InviteFactory;
import model.invite.mapper.InviteInputMapper;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.DomainCommand;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.User;

public class RequestEntryCommand extends DomainCommand {
	public RequestEntryCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void setUp() throws CommandException {
	}

	
	@Override
	public void process() throws CommandException {
		//get my id
		User requester = (User) helper.getAttribute("myUser");
		long reqGroupId  = helper.getLong("groupID");
			 
		IGroup reqGroup;
		try {
			reqGroup=GroupInputMapper.find(reqGroupId);	 
		} catch (SQLException e2) {
			throw new CommandException(e2);
		} catch (MapperException e2) {
			throw new CommandException(e2);
		}
				
		User acceptor = new User(0, null, null);
			
		try {
			if (InviteInputMapper.findRequestGroup(requester, reqGroup).isEmpty())
				InviteFactory.createNew(acceptor,requester,reqGroup,1);
			else
				throw new CommandException("Group already requested!");
		} catch (MapperException e) {
			throw new CommandException(e);
		} catch (SQLException e) {
			throw new CommandException(e);
		}
	 
	}
	
	@Override
	public void tearDown() throws CommandError {
	}
}
