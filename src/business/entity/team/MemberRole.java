package business.entity.team;

import java.awt.Image;
import java.util.EnumMap;

import org.sonata.framework.common.SymphonyRole;
import org.sonata.framework.common.entity.EntityObject;
import org.sonata.framework.common.entity.EntityObjectServices;

import business.dataref.MemberData;
import business.dataref.MemberRoleData;
import business.dataref.TeamPosition;
import business.entity.member.Member;
import business.entity.member.MemberImpl;

public class MemberRole implements SymphonyRole {

	private Member member ;
	
	private String office ;
	
//	private List<MemberRole>
	
	public static MemberRole loadFromStruct(EnumMap<MemberRoleData, Object> structure) {
		EnumMap<MemberData, Object> newStructure = new EnumMap(structure) ;
		Member aMember = MemberImpl.loadMemberFromStruct(newStructure) ;
		
		MemberRole newMember = new MemberRole() ;
		newMember.member = aMember ;
		newMember.setOffice((String) structure.get(MemberRoleData.OFFICE)) ;
		return newMember ;
	}
	
	public static MemberRole search(int memberID) {
//		MemberRole theRole = null ;
//		Member aMember = MemberFactory.instance.rechercher(memberID) ;
//		if (aMember != null) {
//			for ()
//		}
		return null ;
	}
	
	public EntityObject getTargetSObject() {
		return (EntityObject) member ;
	}
	
	public String getOffice() {
		return office ;
	}
	
	public void setOffice(String office) {
		this.office = office;
	}
	
	public String getFirstName() {
		return member.getFirstName() ;
	}
	
	public String getLastName() {
		return member.getLastName() ;
	}
	
	public String getEMail() {
		return member.getEMail() ;
	}
	
	public Team getTeam() {
		return member.getTeam() ;
	}
	
	public Image getPhotograph() {
		return member.getPhotograph() ;
	}
	
	public TeamPosition getTeamPosition() {
		return member.getTeamPosition() ;
	}
	
	public void setFirstName(String firstName) {
		member.setFirstName(firstName) ;
	}

	public void setLastName(String lastName) {
		member.setLastName(lastName) ;
	}

	public void setEMail(String mail) {
		member.setEMail(mail) ;
	}
	
	public void setTeam(Team team) {
		member.setTeam(team) ;
	}
	
	public void setPhotograph(Image photograph) {
		member.setPhotograph(photograph) ;
	}	
	
	public void setTeamPosition(TeamPosition position){
		member.setTeamPosition(position) ;
	}

	public int getID() {
		return ((EntityObjectServices)member).getID() ;
	}

}
