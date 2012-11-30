package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.command.CommandException;

import model.command.nonMemberView.CreateGroupCommand;

public class CreateGroup extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new CreateGroupCommand(this.myHelper).execute();
			myHelper.setRequestAttribute("success", "Great Success!");
			forward("/WEB-INF/jsp/MemberView.jsp");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/NonMemberView.jsp");
		}
	}
}