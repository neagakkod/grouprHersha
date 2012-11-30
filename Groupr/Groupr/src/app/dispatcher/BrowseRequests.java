package app.dispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import model.command.memberView.BrowseRequestsCommand;
import org.dsrg.soenea.domain.command.CommandException;


public class BrowseRequests extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new BrowseRequestsCommand(this.myHelper).execute();
			forward("/WEB-INF/jsp/BrowseRequestsView.jsp");
		} catch (final CommandException e) {
			//ajax placeholder for future reference
		}
	}
}