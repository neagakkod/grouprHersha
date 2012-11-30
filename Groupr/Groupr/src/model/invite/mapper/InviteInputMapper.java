package model.invite.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.UserProxy;
import model.group.GroupProxy;
import model.group.IGroup;
import model.invite.IInvite;
import model.invite.Invite;
import model.invite.InviteFactory;
import model.invite.InviteProxy;
import model.invite.tdg.InviteFinder;

public class InviteInputMapper {

	public static ArrayList<IInvite> findAll() throws SQLException, MapperException {
		ArrayList<IInvite> list = new ArrayList<IInvite>();
		ResultSet rs = InviteFinder.findAll();
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idInvite"), Invite.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new InviteProxy(rs.getLong("idInvite")));
		}
		return list;
	}
	
	public static ArrayList<IInvite> findByInviter(IUser inviter) throws SQLException, MapperException {
		ArrayList<IInvite> list = new ArrayList<IInvite>();
		ResultSet rs = InviteFinder.findByInviter(inviter.getId());
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idInvite"), Invite.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new InviteProxy(rs.getLong("idInvite")));
		}
		return list;
	}
	
	public static IInvite findByInviterAndInvitee(IUser inviter, IUser invitee) throws SQLException, MapperException {
		ResultSet rs = InviteFinder.findByInviteeAndInviter(inviter.getId(), invitee.getId());
		if (!rs.next())
			throw new MapperException("No such Invite!");
		try {
			return IdentityMap.get(rs.getLong("idInvite"), Invite.class);
		} catch (DomainObjectNotFoundException e) { }
		return getInvite(rs);
	}
	
	public static ArrayList<IInvite> findByGroup(IGroup group) throws SQLException, MapperException {
		ArrayList<IInvite> list = new ArrayList<IInvite>();
		ResultSet rs = InviteFinder.findByGroup(group.getId());
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idInvite"), Invite.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new InviteProxy(rs.getLong("idInvite")));
		}
		return list;
	}
	
	@IdentityBasedProducerMethod
	public static Invite find(long id) throws SQLException, MapperException {
		try {
			return IdentityMap.get(id, Invite.class);
		} catch (DomainObjectNotFoundException e) { }
		ResultSet rs = InviteFinder.find(id);
		if (!rs.next())
			throw new MapperException("Invite with id " + id + "does not exists.");
		return getInvite(rs);
	}
	
	private static Invite getInvite(ResultSet rs) throws SQLException {
		Invite result = InviteFactory.createClean(rs.getLong("idInvite"),
				new UserProxy(rs.getLong("inviter")),
				new UserProxy(rs.getLong("invitee")),
				new GroupProxy(rs.getLong("group")),
				rs.getLong("version"),
				rs.getInt("kind"));
		return result;
	}

	// For requests
	public static ArrayList<IInvite> findRequestGroup(IUser tmpUser, IGroup tmpGroup) throws SQLException, MapperException {
		ArrayList<IInvite> list = new ArrayList<IInvite>();
		ResultSet rs = InviteFinder.findRequestGroup(tmpUser.getId(),tmpGroup.getId());
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idInvite"), Invite.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new InviteProxy(rs.getLong("idInvite")));
		}
		return list;
	}
	
	public static ArrayList<IInvite> findAllRequestForGroup(IGroup tmpGroup, int kind, int page) throws SQLException, ObjectRemovedException {
		ArrayList<IInvite> list = new ArrayList<IInvite>();
		ResultSet rs = InviteFinder.findAllRequestForGroup(tmpGroup.getId(), page);
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idInvite"), Invite.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new InviteProxy(rs.getLong("idInvite")));
		}
		return list;
	}
	public static ArrayList<IInvite> findByInvitee(IUser invitee, int kind) throws SQLException, MapperException {
		ArrayList<IInvite> list = new ArrayList<IInvite>();
		ResultSet rs = InviteFinder.findByInvitee(invitee.getId(), kind);
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idInvite"), Invite.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new InviteProxy(rs.getLong("idInvite")));
		}
		return list;
	}
	
	public static int findCount(int type) throws SQLException, MapperException {
		int count = 0;
		ResultSet rs = InviteFinder.findCount(type);
		if (rs.next())
			count = rs.getInt("total");
		return count;
	}
	
	public static int findCountRequests(IGroup group, int type) throws SQLException, MapperException {
		int count = 0;
		ResultSet rs = InviteFinder.findCountRequests(group.getId(), type);
		if (rs.next())
			count = rs.getInt("total");
		return count;
	}
	
	public static ArrayList<IInvite> findByInviteePage(IUser invitee, int kind, int page) throws SQLException, MapperException {
		ArrayList<IInvite> list = new ArrayList<IInvite>();
		ResultSet rs = InviteFinder.findByInviteePage(invitee.getId(), kind, page);
		while(rs.next()) {
			try {
				list.add(IdentityMap.get(rs.getLong("idInvite"), Invite.class));
				continue;
			} catch (DomainObjectNotFoundException e) { }
			list.add(new InviteProxy(rs.getLong("idInvite")));
		}
		return list;
	}
}
