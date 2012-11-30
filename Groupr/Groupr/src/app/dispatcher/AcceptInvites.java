package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.command.CommandException;

import model.command.nonMemberView.AcceptInvitesCommand;

public class AcceptInvites extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new AcceptInvitesCommand(this.myHelper).execute();
			myHelper.setRequestAttribute("success", "Joined the group!");
			forward("/WEB-INF/jsp/MemberView.jsp");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/NonMemberView.jsp");
		}
	}
}

