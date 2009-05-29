package interactional.entity.sprite;

import java.awt.Dimension;
import java.awt.Point;

import org.sonata.framework.common.TechnicalLayer;


public interface SpriteTechnicalLayer extends TechnicalLayer {
//	public final static SpriteTechnicalLayer instance = new BatikSpriteGraphics() ;
	
	public void setAsRepresentation(Object representation) ;

	public void setSize(Dimension taille);

	public Object getRepresentation();

	public void setVisible(boolean trueFalse);

	public void definePosition(Point point);

	public void defineTransparency(float transparency) ;

}
