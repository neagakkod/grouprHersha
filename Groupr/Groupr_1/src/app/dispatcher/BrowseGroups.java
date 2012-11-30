package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.command.CommandException;

import model.command.nonMemberView.BrowseGroupsCommand;

public class BrowseGroups extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new BrowseGroupsCommand(this.myHelper).execute();
			forward("/WEB-INF/jsp/BrowseGroupsView.jsp");
		} catch (final CommandException e) {
			// Set placeholder for ajax, but can't think of
			// situation where this will occur currently
		}
	}
}

