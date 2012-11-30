package model.command;

import java.sql.SQLException;

import model.PasswordService;
import model.group.IGroup;
import model.groupmembership.IGroupMembership;
import model.groupmembership.mapper.GroupMembershipInputMapper;
import model.profile.mapper.ProfileInputMapper;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandError;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.Command;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;

public class LoginViewCommand extends Command {
	public LoginViewCommand(Helper helper) {
        super(helper);
    }
    
    @Override
	public void process() throws CommandException {
		String username = "", password = "";	 
	 	try{
	 		username = helper.getString("username");
	 		password = helper.getString("password");
	 	} catch (Exception e) {
	 		e.printStackTrace();
	 	}
	 	
	 	if (password == null || username == null || helper.getSessionId() == null)
	 		throw new CommandException("");
	 	
	 	
	 	password = PasswordService.MD5encode(password);
	 	
	 	
	 	// look into this section for invalid PW to redirect to Groupr/app/home
	 	try {
	 		IUser user = UserInputMapper.find(username, password);
	 		
	 		helper.setSessionAttribute("myUser", user);
	 		helper.setSessionAttribute("myProfile", ProfileInputMapper.find(user));
	 	} catch (SQLException e) {
	 		e.printStackTrace();
	 		throw new CommandException(e);
	 	} catch (MapperException e) {
	 		throw new CommandException("Sorry no username/password combo.");
	 	}
	 	try {
	 		IUser user = (User)helper.getSessionAttribute("myUser");
	 		IGroupMembership myGroupMembership = GroupMembershipInputMapper.findByUser(user);
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
	public void tearDown() throws CommandError { }

	@Override
	public void setUp() throws CommandException { }
}