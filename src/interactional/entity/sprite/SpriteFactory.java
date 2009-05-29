
package interactional.entity.sprite;

import java.awt.Dimension;
import java.util.logging.Logger;

import org.sonata.framework.common.entity.AbstractEntityFactory;
import org.sonata.framework.common.interactional.Displayable;

public class SpriteFactory extends AbstractEntityFactory{
    
    public static final SpriteFactory instance = new SpriteFactory() ;
    

    
    private Dimension tailleSprites ;  
    
    public Sprite creerEntite() {
            Sprite tmp = new SpriteImpl();
            try {
				((Displayable)tmp).setTechnicalLayer((SpriteTechnicalLayer)getGraphics().newInstance());
			} catch (InstantiationException e) {
				Logger.getAnonymousLogger().severe(e.getMessage()) ;
			} catch (IllegalAccessException e) {
				Logger.getAnonymousLogger().severe(e.getMessage()) ;
			}
            return tmp ;
    }
    
//    public void affecterRessource(final Sprite sprite, final String identifiant) throws ResourceDispatchException {
//    	((Displayable)sprite).definirTaille(ImageProxy.instance.getSizeOfResource(identifiant)) ;
//    	sprite.definirRepresentation(ImageProxy.instance.getRessource(identifiant, true));
//    	
//    }
    
    public void definirTailleSprites(final Dimension tailleSprite) {
    	this.tailleSprites = tailleSprite ;
    }

}
