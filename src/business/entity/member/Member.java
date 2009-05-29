package business.entity.member;

import java.awt.Image;

import business.dataref.TeamPosition;
import business.entity.team.Team;

public interface Member {
	
	String getFirstName() ;
	String getLastName() ;
	String getEMail() ;
	Team getTeam() ;
	Image getPhotograph() ;
	TeamPosition getTeamPosition() ;
	
	void setFirstName(String firstName) ;

	void setLastName(String lastName) ;

	void setEMail(String mail) ;
	
	void setTeam(Team team) ;
	
	void setPhotograph(Image photograph) ;
	
	void setTeamPosition(TeamPosition position);
}
