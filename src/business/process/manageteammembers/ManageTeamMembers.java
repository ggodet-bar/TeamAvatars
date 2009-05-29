package business.process.manageteammembers;

import java.awt.Image;
import java.util.EnumMap;
import java.util.List;

import business.dataref.MemberData;
import business.dataref.MemberRoleData;
import business.dataref.TeamPosition;

public interface ManageTeamMembers {

	boolean deleteTeamMember(int memberID) ;
	
	int createTeam(String teamName, List<String> offices) ;
	
	void setTeamOffices(int teamID, List<String> offices) ;
	
	int createTeamMember(int teamID, String firstName, String lastName, String office, String email, TeamPosition position, int mentorID) ;
	
	void setTeamMemberPhotograph(int memberID, Image photograph) ;
	
	boolean loadTeamData(String localization) ;
	
	int[]	getTeamMembers(String teamName) ;
	
	EnumMap<MemberRoleData, Object>	getMemberData(int memberId) ;
}
