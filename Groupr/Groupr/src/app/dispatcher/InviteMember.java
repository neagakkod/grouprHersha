package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.memberView.InviteMemberCommand;

import org.dsrg.soenea.domain.command.CommandException;

public class InviteMember extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new InviteMemberCommand(this.myHelper).execute();
			myHelper.setRequestAttribute("success", "Member invited successfully!");
			forward("/WEB-INF/jsp/MemberView.jsp");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
			forward("/WEB-INF/jsp/MemberView.jsp");
		}
	}
}