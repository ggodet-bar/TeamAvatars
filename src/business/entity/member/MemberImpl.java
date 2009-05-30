package business.entity.member;

import java.awt.Image;

import org.sonata.framework.common.entity.EntityObject;

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

}
