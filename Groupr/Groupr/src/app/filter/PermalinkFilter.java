package app.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class PermalinkFilter implements Filter {
	

	/**
	 * Attempt to extract the command from the request's pathInfo. Note that
	 * this method might set request attributes.
	 * 
	 * @param request
	 * @return null or the command as a simple class name (i.e. not fully
	 *         qualified with the package).
	 */
	
	
	private void getCommandFromPathInfoAndMaybeSetSomeAttributes(HttpServletRequest request) {	
		String command = null;
		
		String path = request.getPathInfo();
		
		if (path == null)
			System.out.println("the path is null");
		
		if ("/home".equals(path)) {
			command = "LoginView";
		}
				
		else {
			// update to 404 pages like /Groupr/app/x/y*
			Pattern pattern = Pattern.compile("^/([A-Za-z]+$|[A-Za-z]/(\\w+)$)");
			Matcher matcher = pattern.matcher(path);
			
			if (matcher.find()) {
				String cmd=matcher.group(1);
				if("BrowseGroups".equals(cmd)) {
					command = "BrowseGroups";
				} 
				else if("BrowseInvites".equals(cmd)) {
					command = "BrowseInvites";
				}
				else if("CreateGroup".equals(cmd)) {
					command = "CreateGroup";
				}
				else if("AcceptInvites".equals(cmd)) {
					command = "AcceptInvites";
				}
				else if("DeclineInvites".equals(cmd)) {
					command = "DeclineInvites";
				}
				else if("InviteMember".equals(cmd)) {
					command = "InviteMember";
				}
				else if("ViewGroup".equals(cmd)) {
					command = "ViewGroup";
				}
				else if("BrowseNewUsers".equals(cmd)) {
					command = "BrowseNewUsers";
				}
				else if("RemoveGroup".equals(cmd)) {
					command = "RemoveGroup";
				}
				else if("EditGroup".equals(cmd)) {
					command = "EditGroup";
				}
				else if("ImportCsv".equals(cmd)) {
					command = "ImportCsv";
				}
				else if("RemoveMember".equals(cmd)) {
					command = "RemoveMember";
				}
				else if("RequestEntry".equals(cmd)) {
					command = "RequestEntry";
				}
				else if("CancelEntry".equals(cmd)) {
					command = "CancelEntry";
				}
				else if("AcceptEntry".equals(cmd)) {
					command = "AcceptEntry";
				}
				else if("BrowseRequests".equals(cmd)) {
					command = "BrowseRequests";
				}
			}
			else {
				command = "error404";
			}
		}
		if (command != null)
			request.setAttribute("command", command);
	}

	@Override
	public void destroy() { }

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,	FilterChain chain) throws IOException, ServletException {
		getCommandFromPathInfoAndMaybeSetSomeAttributes((HttpServletRequest)request);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException { }
}
