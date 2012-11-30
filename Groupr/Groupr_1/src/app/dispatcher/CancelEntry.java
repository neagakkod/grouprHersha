package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.memberView.CancelEntryCommand;

import org.dsrg.soenea.domain.command.CommandException;


public class CancelEntry extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new CancelEntryCommand(this.myHelper).execute();
			myHelper.setRequestAttribute("success", "Request Cancelled Successfully!");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
		} finally {
			forward("/WEB-INF/jsp/MemberView.jsp");
		}
	}
}