package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

public class error404 extends GFDispatcher{
	public void execute() throws ServletException, IOException {
			forward("/WEB-INF/jsp/404.jsp");
	}
}