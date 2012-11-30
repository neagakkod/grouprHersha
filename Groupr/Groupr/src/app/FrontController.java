package app;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.group.Group;
import model.group.mapper.GroupOutputMapper;
import model.groupmembership.GroupMembership;
import model.groupmembership.mapper.GroupMembershipOutputMapper;
import model.invite.Invite;
import model.invite.mapper.InviteOutputMapper;
import model.profile.Profile;
import model.profile.mapper.ProfileOutputMapper;

import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.domain.user.mapper.UserOutputMapper;
import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.registry.Registry;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.service.threadLocal.ThreadLocalTracker;
import org.dsrg.soenea.uow.MapperFactory;
import org.dsrg.soenea.uow.UoW;

import app.dispatcher.GFDispatcher;


public class FrontController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private String fqCommand = "app.dispatcher.";

	@Override
	public void init() throws ServletException {
		super.init();
		prepareDbRegistry("");
	}

	private void setupUoW() {
		MapperFactory mapper = new MapperFactory();
		mapper.addMapping(User.class, UserOutputMapper.class);
		mapper.addMapping(Profile.class, ProfileOutputMapper.class);
		mapper.addMapping(Invite.class, InviteOutputMapper.class);
		mapper.addMapping(GroupMembership.class, GroupMembershipOutputMapper.class);
		mapper.addMapping(Group.class, GroupOutputMapper.class);
		UoW.newCurrent();
		UoW.initMapperFactory(mapper);
	}

	public static void prepareDbRegistry(String db_id) {
		MySQLConnectionFactory f = new MySQLConnectionFactory(null, null, null, null);
		try {
			f.defaultInitialization(db_id);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		DbRegistry.setConFactory(db_id, f);
		String tablePrefix;
		try {
			tablePrefix = Registry.getProperty(db_id+"mySqlTablePrefix");
		} catch (Exception e1) {
			e1.printStackTrace();
			tablePrefix = "";
		}
		if(tablePrefix == null) {
			tablePrefix = "";
		}
		DbRegistry.setTablePrefix(db_id, tablePrefix);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		GFDispatcher fc = getFrontCommand(request);
		if (fc == null) {
			String msg = "Invalid or unspecified command";
			if (request.getParameter("command") != null)
				msg += " (" + request.getParameter("command") + ")";
			response.sendError(HttpServletResponse.SC_NOT_FOUND, msg);
		} else {
			fc.init(request, response);
			fc.execute();
		}
		
	}

	private GFDispatcher getFrontCommand(HttpServletRequest request) {
		try {
			
			String command =  (String) request.getAttribute("command");
			if (command == null)
				command = request.getParameter("command");
			if (command == null || command.isEmpty()) {
				return null;
			}
			return (GFDispatcher) Class.forName(fqCommand+command).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		preProcessRequest(request, response);
		try {
			processRequest(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			postProcessRequest(request, response);
		}
	}

	protected void preProcessRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setupUoW();
	}
	
	protected void postProcessRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Exception e = null;
		try {
			UoW.getCurrent().commit();
			DbRegistry.closeDbConnectionIfNeeded();
		} catch (Exception e1) {
			e = e1;
		}
		ThreadLocalTracker.purgeThreadLocal();
		if (e != null)
			throw new ServletException("postProcessRequest database close", e);
	}
}
