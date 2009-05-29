package interactional.process.manageavatarscene;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

public interface ManageAvatarScene {

	void initializeScene();
	
	void setSceneSize(Dimension sceneSize) ;
	
	void setShadowBlur(int blurLevel) ;
	
	int addDiskToScene(int internalDiameter, int externalDiameter, Color diskColor) ;
	
	int addSpriteBoxToDisk(int diskID, Image spriteImg, String boxCaption) ;
	
//	int addPanelToPropertyBox(int boxID, String panelTitle) ;
//	
//	void addTextToPropertyBox(int boxID, int panelID, String content) ;
//	
//	boolean removeSpriteBox(int boxID) ;

}
