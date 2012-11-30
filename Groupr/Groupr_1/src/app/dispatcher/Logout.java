package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;
//
public class Logout extends GFDispatcher {
	@Override
	public void execute() throws ServletException, IOException {
		//new LogoutCommand(this.myHelper).execute();
		forward("/LoginView.jsp");
	}
}