package business.entity.member;

import java.awt.Image;
import java.util.EnumMap;

import org.sonata.framework.common.AbstractFactory;
import org.sonata.framework.common.entity.AbstractEntityFactory;

import business.dataref.MemberData;
import business.dataref.TeamPosition;

public class MemberFactory extends AbstractEntityFactory {

	public static final AbstractFactory instance = new MemberFactory() ;
	
	@Override
	public Object creerEntite() {
		Member tmp = new MemberImpl() ;
		return tmp ;
	}
	
	public Member loadMemberFromStruct(EnumMap<MemberData, Object> aMember) {
		Member newMember = (Member) creerEntite() ;
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
