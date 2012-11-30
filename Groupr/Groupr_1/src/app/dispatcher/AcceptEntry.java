package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.command.CommandException;

import model.command.memberView.AcceptEntryCommand;

public class AcceptEntry extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new AcceptEntryCommand(this.myHelper).execute();
			myHelper.setRequestAttribute("success", "User has been added to the group!");
			forward("/WEB-INF/jsp/MemberView.jsp");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/MemberView.jsp");
		}
	}
}

