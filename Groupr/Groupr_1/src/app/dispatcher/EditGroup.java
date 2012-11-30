package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.memberView.EditGroupCommand;

import org.dsrg.soenea.domain.command.CommandException;


public class EditGroup extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new EditGroupCommand(this.myHelper).execute();
			myHelper.setRequestAttribute("success", "Great Success!");
			forward("/WEB-INF/jsp/MemberView.jsp");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/MemberView.jsp");
			// finally
		}
	}
}