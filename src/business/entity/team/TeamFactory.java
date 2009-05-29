package business.entity.team;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import org.sonata.framework.common.AbstractFactory;
import org.sonata.framework.common.entity.AbstractEntityFactory;

import business.dataref.MemberRoleData;
import business.dataref.TeamData;

public class TeamFactory extends AbstractEntityFactory {

	public static final AbstractFactory instance = new TeamFactory() ;
	
	@Override
	public Object creerEntite() {
		return new TeamImpl() ;
	}
	
	public Team loadTeamFromStruct(EnumMap<TeamData, Object> aTeam) {
		Team newTeam = (Team) creerEntite() ;
		newTeam.setTeamName((String) aTeam.get(TeamData.NAME)) ;
		newTeam.setOfficeList((List<String>) aTeam.get(TeamData.OFFICES)) ;
		List<MemberRole> members = new ArrayList<MemberRole>() ;
		for (EnumMap<MemberRoleData, Object> aMember : (List<EnumMap<MemberRoleData, Object>>)aTeam.get(TeamData.MEMBERS)) {
			members.add(MemberRole.loadFromStruct(aMember)) ;
			//members.add(((MemberFactory) MemberFactory.instance).loadMemberFromStruct(aMember)) ;
			
		}
		newTeam.setMembers(members) ;
		return newTeam ;
	}

}
