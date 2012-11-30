package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.nonMemberView.BrowseInvitesCommand;

import org.dsrg.soenea.domain.command.CommandException;;


public class BrowseInvites extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new BrowseInvitesCommand(this.myHelper).execute();
			forward("/WEB-INF/jsp/BrowseInvitesView.jsp");
		} catch (final CommandException e) {
			// Set placeholder for ajax, but can't think of
			// situation where this will occurr currently
		}
	}
}

