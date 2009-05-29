package technical.graphics;

import interactional.design.ImageComponent ;
import interactional.entity.sprite.SpriteTechnicalLayer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;


public class SwingSpriteGraphics implements SpriteTechnicalLayer {

	private Dimension taille;

	private Point position;

	private Component comp;

	private float transparency ;
	
	public void setAsRepresentation(Object representation) {
		this.comp = (Component) representation;
		this.comp.setSize(taille);
		comp.setVisible(false);
	}

	public void setSize(final Dimension taille) {
		this.taille = taille;
		if (comp != null) {
			comp.setSize(this.taille);
			comp.repaint() ;
		} 
	}

	public Dimension getSize() {
		return taille;
	}

	public Object getRepresentation() {

		return comp;
	}

	public void setVisible(boolean trueFalse) {
		comp.setVisible(trueFalse);

	}

	public void definePosition(Point point) {
		this.position = point;
		comp.setLocation(this.position);
		// representation.setLocation(point) ;

	}

	public void defineTransparency(float transparency) {
//		this.transparency = transparency ;
		if (comp != null) {
			((ImageComponent)comp).setTransparency(transparency) ;
		}
		
	}

}
