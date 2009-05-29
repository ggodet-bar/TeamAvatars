package interactional.entity.sprite;

import org.sonata.framework.common.interactional.Displayable;

import java.awt.Dimension;
import java.awt.Point;

import org.sonata.framework.common.TechnicalLayer;
import org.sonata.framework.common.entity.EntityObject;

/**
 * Impl�mentation de l'Objet Entit� Sprite
 * 
 * @see Sprite Sprite
 * @author godetg
 *
 */
public class SpriteImpl implements Sprite,EntityObject, Displayable {
 
	private Dimension taille ;
	private Point position ;
    private float coeff; 
    private SpriteTechnicalLayer technicalLayer ;
	private boolean isVisible;
	private float transparence;
	private float orientation;
    
    public SpriteImpl()
    {
        coeff = 1.f;
        transparence = 1.f ;
    }

    
    public float calculCoeffMagnification ()
    {
        return coeff;
    }


	public void definirTaille(Dimension taille) {
		this.taille = taille ;
		technicalLayer.setSize(this.taille) ;
	}
	
	public Dimension obtenirTaille() {
		return taille ;
	}


	public void definirRepresentation(Object representation) {
		technicalLayer.setAsRepresentation(representation) ;
	}


	public Object obtenirRepresentation() {
		return technicalLayer.getRepresentation() ;
	}


	public void afficher() {
		technicalLayer.setVisible(true) ;
		technicalLayer.defineTransparency(transparence) ;
		isVisible = true ;
	}


	public void cacher() {
		technicalLayer.setVisible(false);
		isVisible = false ;
	}


	public void definirPosition(Point point) {
		this.position = point ;
		technicalLayer.definePosition(point) ;
	}


	public void definirTransparence(float transparence) {
		this.transparence = transparence ;
		if (isVisible) {
			technicalLayer.defineTransparency(transparence) ;
		}
		
	}


	public boolean isVisible() {
		return isVisible ;
	}



	public Point obtenirPosition() {
		return position ;
	}


	public float obtenirTransparence() {
		return transparence ;
	}


	public void setTechnicalLayer(TechnicalLayer technicalLayer) {
		this.technicalLayer = (SpriteTechnicalLayer) technicalLayer ;
	}


	public void definirOrientation(final float orientation) {
		this.orientation = orientation ; 
	}
	
	public float obtenirOrientation() {
		return orientation ;
	}
    
}
