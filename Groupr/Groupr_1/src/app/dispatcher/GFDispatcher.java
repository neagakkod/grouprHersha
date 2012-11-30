package app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

public abstract class GFDispatcher extends Dispatcher 
{		
	protected Long getParamAndSetAsLongAttr(String pName) throws ServletException {
		try {
			String paramAsString = this.myRequest.getParameter(pName);
			if (paramAsString == null) {
				paramAsString = (String) this.myHelper.getAttribute(pName);
			}
			long param = Long.parseLong(paramAsString);
			this.myHelper.setRequestAttribute(pName, param);
			return param;
		} catch (NumberFormatException e) {
			throw new ServletException("invalid " + pName, e);
		}
	}

	/**
	 * Redirects to: <context-path><servlet-path>/p
	 * @param p the last part of the path to be redirected to.
	 * @throws IOException
	 */
	public void relativeRedirect(String p) throws IOException {
	  String path = this.myRequest.getContextPath() + this.myRequest.getServletPath() + "/" + p;
	  this.myResponse.sendRedirect(path);
	}

}
