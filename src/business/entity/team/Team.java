package business.entity.team;

import java.util.List;

import business.dataref.TeamPosition;

public interface Team {
	int addMember(String firstName, String lastName, String office, String email, TeamPosition position, int memberID) ;
	String getTeamName() ;

	void setTeamName(String teamName) ;

//	List<Member> getMembers() ;
	
	void setMembers(List<MemberRole> newMembers) ;
	
	void deleteMember(int memberID) ;

	public List<String> getOfficeList() ;

	public void setOfficeList(List<String> officeList) ;
}
