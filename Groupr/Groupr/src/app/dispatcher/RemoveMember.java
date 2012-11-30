package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.memberView.RemoveMemberCommand;
import model.group.IGroup;

import org.dsrg.soenea.domain.command.CommandException;


public class RemoveMember extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new RemoveMemberCommand(this.myHelper).execute();
			IGroup myGroup = (IGroup) this.myHelper.getSessionAttribute("myGroup");
			myHelper.setRequestAttribute("success", "Removed Successfully!");
			if ( myGroup != null )
				forward("/WEB-INF/jsp/MemberView.jsp");
			else
				forward("/WEB-INF/jsp/NonMemberView.jsp");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());	
			forward("/WEB-INF/jsp/MemberView.jsp");
		}
	}
}