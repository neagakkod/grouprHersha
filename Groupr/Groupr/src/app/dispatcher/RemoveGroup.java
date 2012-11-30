package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.memberView.RemoveGroupCommand;

import org.dsrg.soenea.domain.command.CommandException;


public class RemoveGroup extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new RemoveGroupCommand(this.myHelper).execute();
			myHelper.setRequestAttribute("success", "Group has been removed successfully!");
			forward("/WEB-INF/jsp/NonMemberView.jsp");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/MemberView.jsp");
		}
	}
}