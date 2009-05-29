package business.process.manageteammembers;

import java.awt.Image;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Logger;

import org.sonata.framework.common.entity.AbstractEntityFactory;
import org.sonata.framework.common.entity.EntityObjectServices;
import org.sonata.framework.common.process.ProcessObject;

import business.dataref.MemberRoleData;
import business.dataref.TeamData;
import business.dataref.TeamPosition;
import business.entity.member.Member;
import business.entity.member.MemberFactory;
import business.entity.team.Team;
import business.entity.team.TeamFactory;

public class ManageTeamMembersImpl implements ManageTeamMembers, ProcessObject {

	private List<Team> teams ;
	
//	private Parser parser ;
	
	public ManageTeamMembersImpl() {
		teams = new ArrayList<Team>() ;
	}
	
	@Override
	public int createTeam(String teamName, List<String> offices) {
		Team team = (Team) ((TeamFactory) TeamFactory.instance).creerEntite() ;
		team.setTeamName(teamName) ;
		team.setOfficeList(offices) ;
		teams.add(team) ;
		return ((EntityObjectServices)team).getID() ;
	}

	@Override
	public int createTeamMember(int teamID, String firstName, String lastName,
			String office, String email, TeamPosition position, int mentorID) {
		assert ((AbstractEntityFactory) MemberFactory.instance).rechercher(mentorID) != null : "Mentor does not exist" ;
		Team team = (Team) ((AbstractEntityFactory) TeamFactory.instance).rechercher(teamID) ;
		int memberID = team.addMember(firstName, lastName, office, email, position, mentorID) ;
		return memberID;
	}

	@Override
	public void setTeamMemberPhotograph(int memberID, Image photograph) {
		Member member = (Member) ((AbstractEntityFactory) MemberFactory.instance).rechercher(memberID) ;
		member.setPhotograph(photograph) ;
	}

	@Override
	public void setTeamOffices(int teamID, List<String> offices) {
		Team team = (Team) ((AbstractEntityFactory) TeamFactory.instance).rechercher(teamID) ;
		if (offices != null) {
			team.setOfficeList(offices) ;
		}
	}
	
	@Override
	public boolean deleteTeamMember(int memberID) {
		Member member = (Member) ((AbstractEntityFactory) MemberFactory.instance).rechercher(memberID) ;
		boolean returnValue = (member != null) ;
		if (returnValue) {
			Team team = member.getTeam() ;
			team.deleteMember(memberID) ;
		}
		return returnValue ;
	}

	@Override
	public boolean loadTeamData(String localization) {
		boolean returnValue = false ;
		try {
			Parser.instance.setURI(new URI(localization)) ;
			List<EnumMap<TeamData, Object>> rawData = Parser.instance.getTeamStructure() ;

			for (EnumMap<TeamData, Object> aTeam : rawData) {
				teams.add(((TeamFactory) TeamFactory.instance).loadTeamFromStruct(aTeam)) ;
			}
			returnValue = true ;
		} catch (URISyntaxException e) {
			Logger.getAnonymousLogger().severe("Could not open the database!\n" + e.getMessage()) ;
		}
		
		return returnValue ;
	}

	public void setPersistence(Parser parser) {
		Parser.instance = parser ;
	}

	@Override
	public EnumMap<MemberRoleData, Object> getMemberData(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[] getTeamMembers(String teamName) {
		
		// TODO Auto-generated method stub
		return null;
	}
}
