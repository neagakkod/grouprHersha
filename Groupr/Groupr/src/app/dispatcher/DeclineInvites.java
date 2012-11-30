package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.nonMemberView.DeclineInvitesCommand;

import org.dsrg.soenea.domain.command.CommandException;


public class DeclineInvites extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new DeclineInvitesCommand(this.myHelper).execute();
			myHelper.setRequestAttribute("success", "Shot down Successfully!");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
		} finally {
			forward("/WEB-INF/jsp/NonMemberView.jsp");
		}
	}
}