package business.entity.team;

import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import org.sonata.framework.common.entity.AbstractEntityFactory;
import org.sonata.framework.common.entity.EntityObject;

import business.dataref.TeamPosition;
import business.entity.member.Member;
import business.entity.member.MemberFactory;

public class TeamImpl implements EntityObject, Team {

	private String			teamName ;
	private List<MemberRole>	members ;
	private List<String>	officeList ;
	
	public TeamImpl() {
		members = new ArrayList<MemberRole>() ;
	}
	
	public int addMember(String firstName, String lastName, String office,
			String email, TeamPosition position, int mentorID) {
		int returnValue = -1 ;
		if (isValidOffice(office)) {
			MemberRole newMember = new MemberRole() ;
			
			newMember.setOffice(office) ;
			newMember.setFirstName(firstName) ;
			newMember.setLastName(lastName) ;
			newMember.setEMail(email) ;
			newMember.setOffice(office) ;
			newMember.setTeam(this) ;
			newMember.setTeamPosition(position) ;
			returnValue = newMember.getID() ;
			members.add(newMember) ;
		}
		return returnValue ;
	}
	
	public void deleteMember(int memberID) {
		Member member = (Member) ((AbstractEntityFactory)MemberFactory.instance).rechercher(memberID) ;
		members.remove(member) ;
		assert ((AbstractEntityFactory)MemberFactory.instance).supprimer(memberID) : "Error occurred while deleting member " + memberID;
	}

	public void setMemberPhotograph(int memberID, Image photograph) {
		Member theMember = (Member) ((AbstractEntityFactory) MemberFactory.instance).rechercher(memberID) ;
		assert theMember.getTeam() == this : "Member " + memberID + " not in team " + teamName ;
		
		// TODO A reprendre
		theMember.setPhotograph(photograph) ;
	}
	
	private boolean isValidOffice(String office) {
		return officeList.contains(office) ;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public List<MemberRole> getMembers() {
		return (List<MemberRole>) ((ArrayList<MemberRole>)members).clone();
	}
	
	public void setMembers(List<MemberRole> newMembers) {
		members = newMembers ;
	}

	public List<String> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<String> officeList) {
		this.officeList = (List<String>) ((ArrayList<String>)officeList).clone();
	}

}
