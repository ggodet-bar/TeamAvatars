package common;

import interactional.process.manageavatarscene.ManageAvatarScene;
import interactional.process.manageavatarscene.ManageAvatarSceneFactory;

import java.awt.Color;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.sonata.framework.common.process.AbstractProcessFactory;

import business.process.manageteammembers.ManageTeamMembers;
import business.process.manageteammembers.ManageTeamMembersFactory;

// TODO: Manage the mentor_id stuff!
public class Main {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	    System.setProperty("sun.java2d.noddraw", "true");
	       try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}  
        Initializer.instance.loadPropertiesFile("/Users/godetg/Documents/workspace/TeamAvatars/teamAvatar.properties");
        Initializer.instance.setupTechnicalClasses();
//        Initializer.instance.setupSOParameters() ;
//		Initializer.instance.setIsUnitTesting(true) ;
		
		try {
			new Main() ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/*
	 * Might correspond to an actual controller (standard MVC style)
	 */
	public Main() throws Exception {
		ManageTeamMembers omp = (ManageTeamMembers) ((AbstractProcessFactory) ManageTeamMembersFactory.instance).creerProcessus() ;
		ManageAvatarScene oip = (ManageAvatarScene) ((ManageAvatarSceneFactory) ManageAvatarSceneFactory.instance).creerProcessus();
		if (!omp.loadTeamData("jdbc:mysql://localhost/TeamAvatars?useUnicode=yes&characterEncoding=UTF-8")) {
			throw new Exception("Error occurred while loading data!") ;
		}
		
		oip.initializeScene() ;
//		oip.setSceneSize(new Dimension(800, 800)) ;
		
		oip.addDiskToScene(0, 20, Color.green.darker()) ;
		oip.addDiskToScene(300, 360, Color.green.darker()) ;
		oip.addDiskToScene(680, 760, Color.green.darker()) ;
		
		
		int[] memberID = omp.getTeamMembers("EHCI") ;
		
		
//		oip.addSpriteBoxToDisk(1, , boxCaption)

		
		System.out.println("Finished loading the team data!") ;
	}

}
