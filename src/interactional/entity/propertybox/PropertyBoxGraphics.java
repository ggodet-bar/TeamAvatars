package interactional.entity.propertybox;

import interactional.dataref.Type.TypePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.net.URI;

import org.sonata.framework.common.TechnicalLayer;

/**
 *
 * @author Manu Tarantini
 */
public interface PropertyBoxGraphics extends TechnicalLayer {
    
    
    
    public int addImage(int id, URI image, String id_acteur,Dimension imageSize);

    public void newPanel(int id,String titrePanel, TypePanel panelType);

    public void setDimension (Dimension size);

    public void setTitre(String titre, Color c);

    public void setTitreEtendu(String titre, Color c);
    
    public void setFontSize(int pointSize) ;

    public void setTranparence(float trans);
    
    public void setBaseColor (Color c);
    
    public void addText(int id,String text,Color c);
    
    public void removeItem (int id);
    
    public void collapsePanel (int id);
    
    public void expandPanel (int id);

	public void setVisible(boolean b);
	
	public Object getRepresentation();
	
	public Object getPanelRepresentation(int id) ;

	public void setLocation(Point coordonnees);
	
	public void setVisibleExtendedTitle(boolean trueFalse);

	public void displayPanel(int id);

	public void hidePanel(int id);
    
}
