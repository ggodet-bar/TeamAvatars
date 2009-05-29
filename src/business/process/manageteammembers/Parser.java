package business.process.manageteammembers;

import java.net.URI;
import java.util.EnumMap;
import java.util.List;

import business.dataref.TeamData;

public abstract class Parser {
	
	public static Parser instance ;
	
	public abstract void setURI(URI uri) ;
	
	public abstract List<EnumMap<TeamData, Object>> getTeamStructure() ;
	
}
