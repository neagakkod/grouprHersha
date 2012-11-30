package app.dispatcher;

import java.io.IOException;
import javax.servlet.ServletException;

import model.command.adminView.ImportCsvCommand;

import org.dsrg.soenea.domain.command.CommandException;


public class ImportCsv extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		try {
			new ImportCsvCommand(this.myHelper, this.myRequest).execute();
			myHelper.setRequestAttribute("success", "Great Success!");
		} catch (final CommandException e) {
			myHelper.setRequestAttribute("message", e.getMessage());
		} finally {
			forward("/WEB-INF/jsp/AdminView.jsp");
		}
	}
}