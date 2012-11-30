package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import model.command.memberView.ViewGroupCommand;

import org.dsrg.soenea.domain.command.CommandException;


public class ViewGroup extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new ViewGroupCommand(this.myHelper).execute();
			forward("/WEB-INF/jsp/ViewGroupView.jsp");
		} catch (final CommandException e) {
			//ajax placeholder for future reference
		}
	}
}