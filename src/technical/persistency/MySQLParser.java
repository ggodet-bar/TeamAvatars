package technical.persistency;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import business.dataref.MemberData;
import business.dataref.MemberRoleData;
import business.dataref.TeamData;
import business.process.manageteammembers.Parser;

public class MySQLParser extends Parser {

	private Connection conn ;
	
	@Override
	public void setURI(URI uri) {
		try {
			ClassLoader.getSystemClassLoader().loadClass("com.mysql.jdbc.Driver").newInstance() ;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(uri.toString(), "dummy", "") ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	@Override
	public List<EnumMap<TeamData, Object>> getTeamStructure() {
		Statement stmt = null; ResultSet rs = null ;
		List<EnumMap<TeamData, Object>> result = new ArrayList<EnumMap<TeamData, Object>>() ;
		EnumMap<TeamData, Object> tmpMap ;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		try {
			rs = stmt.executeQuery("SELECT id, name FROM teams") ;
			while (rs.next()) {
				tmpMap = new EnumMap<TeamData, Object>(TeamData.class) ;
				tmpMap.put(TeamData.NAME, rs.getString("name")) ;
				// TODO Reste de l'initialisation
				List<String> offices = new ArrayList<String>() ;
				Statement stmt2 = conn.createStatement() ;
				ResultSet rs2 = stmt2.executeQuery("SELECT name FROM offices WHERE team_id =" + rs.getString("id")) ;
				while(rs2.next()) {
					offices.add(rs2.getString("name")) ;
				}
				tmpMap.put(TeamData.OFFICES, offices) ;
				
				List<EnumMap<MemberRoleData, Object>> members = new ArrayList<EnumMap<MemberRoleData, Object>>() ;
				EnumMap<MemberRoleData, Object> tmpMember ;
				
				stmt2 = conn.createStatement() ;
				rs2 = stmt2.executeQuery("SELECT m.id, m.firstName, m.lastName, m.email, m.photo_url, m.position, m.mentor_id, o.name FROM members m INNER JOIN offices o ON o.id = m.office_id WHERE o.team_id = " + rs.getString("id")) ;
				while(rs2.next()) {
					tmpMember = new EnumMap<MemberRoleData, Object>(MemberRoleData.class) ;
					tmpMember.put(MemberRoleData.ID, rs2.getString("id")) ;
					tmpMember.put(MemberRoleData.FIRST_NAME, rs2.getString("firstName")) ;
					tmpMember.put(MemberRoleData.LAST_NAME, rs2.getString("lastName")) ;
					tmpMember.put(MemberRoleData.EMAIL, rs2.getString("email")) ;
					tmpMember.put(MemberRoleData.FIRST_NAME, rs2.getString("firstName")) ;
					tmpMember.put(MemberRoleData.OFFICE, rs2.getString("name")) ;
					tmpMember.put(MemberRoleData.PHOTO, loadImage(rs2.getString("photo_url"))) ;
					
					tmpMember.put(MemberRoleData.POSITION, rs2.getString("position")) ;
					tmpMember.put(MemberRoleData.MENTOR, rs2.getInt("mentor_id")) ;
					members.add(tmpMember) ;
				}
				tmpMap.put(TeamData.MEMBERS, members) ;
				
				result.add(tmpMap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return result;
	}

	private Image loadImage(String image_url) {
		File imageFile = new File(image_url) ;
		Image theImage = null ;
		try {
			theImage = ImageIO.read(imageFile) ;
		} catch (IOException e) {
			Logger.getAnonymousLogger().severe("Could not load image with url: " + image_url + "\n" + e.getMessage()) ;
		}
		return theImage ;
	}

}
