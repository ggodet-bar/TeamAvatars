package interactional.process.manageavatarscene;

import interactional.dataref.Type.TypeLienActeurBoite;
import interactional.entity.propertybox.PropertyBox;
import interactional.entity.propertybox.PropertyBoxFactory;
import interactional.entity.sprite.Sprite;
import interactional.entity.sprite.SpriteFactory;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import org.sonata.framework.common.TechnicalLayer;
import org.sonata.framework.common.entity.EntityObjectServices;
import org.sonata.framework.common.process.ProcessObject;

public class ManageAvatarSceneImpl implements ProcessObject, ManageAvatarScene {
	
    private List<TypeLienActeurBoite> relationSpriteBoite ;
    private List<List<Integer>>	disks ;	// Disk_id -> list of sprite-pbox ids
    private List<Integer> diskDiameters ; // Disk_id -> Disk diameter (external), used for calculating the layout
    private AvatarSceneGraphics	technicalLayer ;
    private Dimension sceneSize = new Dimension(800, 800) ;
    private int shadowBlur = 12 ;
	
    public ManageAvatarSceneImpl() {
    	relationSpriteBoite = new ArrayList<TypeLienActeurBoite>() ;
    	disks = new ArrayList<List<Integer>>() ;
    	diskDiameters = new ArrayList<Integer>() ;
    }
    
	public void initializeScene() {
		technicalLayer.setBlur(shadowBlur) ;
		technicalLayer.initializeScene() ;
		
	}
	
	public void setTechnicalLayer(TechnicalLayer technicalLayer) {
		this.technicalLayer = (AvatarSceneGraphics) technicalLayer ;
	}
	
	public void setShadowBlur(int blurLevel) {
		assert blurLevel >= 1 : "Inconsistent blur value: " + blurLevel ;
		shadowBlur = blurLevel ;
		technicalLayer.setBlur(shadowBlur) ;
	}

	public void setSceneSize(Dimension sceneSize) {
		assert sceneSize.width > 0 && sceneSize.height > 0 : "Inconsistence scene size: height = " + sceneSize.height + ", width = " + sceneSize.width ;
		this.sceneSize = sceneSize ;
		technicalLayer.setSceneSize(this.sceneSize) ;
		
	}

	public int addDiskToScene(int internalDiameter, int externalDiameter,
			Color diskColor) {
		assert internalDiameter >= 0 && externalDiameter > 0 && internalDiameter < externalDiameter : "Diameter inconsistency" ;
		assert diskColor != null : "Disk color should be defined!" ;
		
		int resultValue = -1;
		List<Integer> idList = new ArrayList<Integer>() ;
		if (disks.add(idList)) {
			resultValue = disks.indexOf(idList) ;
			diskDiameters.add(resultValue, externalDiameter) ;
			technicalLayer.addDisk(resultValue, internalDiameter, externalDiameter, diskColor) ;
 		}
		
		return resultValue ;
	}

	public int addSpriteBoxToDisk(int diskID, Image spriteImg, String boxCaption) {
		TypeLienActeurBoite link = new TypeLienActeurBoite() ;
		Sprite sprite = SpriteFactory.instance.creerEntite() ;
		sprite.definirRepresentation(spriteImg) ;
		
		PropertyBox pBox = ((PropertyBoxFactory) PropertyBoxFactory.instance).creerEntite() ;
		
		link.identifiantActeur = ((EntityObjectServices)sprite).getID() ;
		link.identifiantBoite = ((EntityObjectServices)pBox).getID() ;
		link.actifInactif = true ;
		
		// TODO Calcul du positionnement des sprites
		// On utilisera les coordonnées polaires !
		
		return 0;
	}

}
