package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.nonMemberView.RequestEntryCommand;

import org.dsrg.soenea.domain.command.CommandException;

public class RequestEntry extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new RequestEntryCommand(this.myHelper).execute();
			myHelper.setRequestAttribute("success", "Request sent successfully!");
			forward("/WEB-INF/jsp/RequestEntryView.jsp");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/RequestEntryView.jsp");
		}
	}
}