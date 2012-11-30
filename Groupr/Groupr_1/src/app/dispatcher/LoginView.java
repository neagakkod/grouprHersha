package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.LoginViewCommand;
import model.group.IGroup;
import model.role.AdminRole;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.user.User;


public class LoginView extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		String content;
		if( myRequest.getMethod().equalsIgnoreCase( "get" ) ){
			User myUser= (User) this.myHelper.getSessionAttribute("myUser");
			IGroup myGroup = (IGroup) this.myHelper.getSessionAttribute("myGroup");
			
			if(myUser == null) {
				content="/LoginView.jsp";
			}
			else if(myUser.hasRole(AdminRole.class)) {
				content="/WEB-INF/jsp/AdminView.jsp";
			}
	 		else if (myGroup == null) {
	 			content="/WEB-INF/jsp/NonMemberView.jsp";
	 		}
	 		else {
	 			content="/WEB-INF/jsp/MemberView.jsp";
	 		}
			forward(content);
		} else
			executePost();
	}
	
	public void executePost() throws ServletException, IOException{
		String content=null;
		try {
			new LoginViewCommand(this.myHelper).execute();
			User myUser= (User) this.myHelper.getSessionAttribute("myUser");
			IGroup myGroup = (IGroup) this.myHelper.getSessionAttribute("myGroup");
			
			// is an admin
			if(myUser.hasRole(AdminRole.class)) {
				content="/WEB-INF/jsp/AdminView.jsp";
	 			forward(content);
	 		}
			// is a loser
			else if (myGroup == null) {
	 			content="/WEB-INF/jsp/NonMemberView.jsp";
	 			forward(content);
	 		}
			// has frenz
	 		else {
	 			content="/WEB-INF/jsp/MemberView.jsp";
	 			forward(content);
	 		}
		} catch (CommandException e) {
				myHelper.setRequestAttribute("message", e.getMessage());
				forward("/LoginView.jsp");
		}
	}
}