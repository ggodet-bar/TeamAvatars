package business.entity.member;

import java.awt.Image;
import java.util.EnumMap;

import org.sonata.framework.common.entity.AbstractEntityFactory;
import org.sonata.framework.common.entity.EntityObject;

import business.dataref.MemberData;
import business.dataref.TeamPosition;
import business.entity.team.Team;

public class MemberImpl implements EntityObject, Member {

	private String firstName, lastName, eMail ;
	private Team team ;
	private Image photograph ;
	private TeamPosition	position ;
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setEMail(String mail) {
		eMail = mail;
	}
	
	public void setTeam(Team team) {
		this.team = team ;
	}
	
	public String getEMail() {
		return eMail ;
	}

	public String getFirstName() {
		return firstName ;
	}

	public String getLastName() {
		return lastName ;
	}


	public Team getTeam() {
		return team ;
	}

	public Image getPhotograph() {
		return photograph ;
	}

	public void setPhotograph(Image photograph) {
		this.photograph = photograph ;
	}

	public void setTeamPosition(TeamPosition position) {
		this.position = position;
	}

	public TeamPosition getTeamPosition() {
		return position;
	}
	
	
	public static Member loadMemberFromStruct(EnumMap<MemberData, Object> aMember) {
		Member newMember = (Member) AbstractEntityFactory.instance.createEntity(Member.class) ;
		newMember.setFirstName((String) aMember.get(MemberData.FIRST_NAME)) ;
		newMember.setLastName((String)aMember.get(MemberData.LAST_NAME)) ;
		newMember.setEMail((String)aMember.get(MemberData.EMAIL)) ;
		
		String posStr = (String) aMember.get(MemberData.POSITION) ;
		TeamPosition position = null ;
		if ("team_leader".equals(posStr)) {
			position = TeamPosition.TEAM_LEADER ;
		} else if ("permanent".equals(posStr)) {
			position = TeamPosition.PERMANENT ;
		} else if ("phd_student".equals(posStr)) {
			position = TeamPosition.PHD_STUDENT ;
		} else if ("intern".equals(posStr)) {
			position = TeamPosition.INTERN ;
		} else if ("engineer".equals(posStr)) {
			position = TeamPosition.ENGINEER ;
		}
		newMember.setTeamPosition(position) ;
//		newMember.setOffice((String) aMember.get(MemberData.OFFICE)) ;
		
		Image photo = (Image) aMember.get(MemberData.PHOTO) ;
		newMember.setPhotograph(photo) ;
		
		
		// TODO Gérer le mentor_id !
		
		return newMember ;
	}

}
