package interactional.entity.propertybox;

import org.sonata.framework.common.AbstractFactory;
import org.sonata.framework.common.interactional.Displayable;

import java.awt.Color;
import java.awt.Dimension;

import org.sonata.framework.common.entity.AbstractEntityFactory;

/**
 * Fabrique de l'Objet Interactionnel Entit� <code>BoitePropriete</code>.
 * @see PropertyBox BoitePropriete
 * @author godetg
 *
 */
public class PropertyBoxFactory extends AbstractEntityFactory {
    
	/**
	 * Red�finition de la variable instance de <code>AbstractEntityFactory</code>
	 */
    public static final AbstractFactory instance = new PropertyBoxFactory() ;
    
    private static int defaultWidth = 100 ;
    private static int defaultHeight = 150 ;

	private float defaultTransparency;

	private Color defaultBackgroundColor;

	private int defaultFontSize = 12 ;

    
    public PropertyBox creerEntite() {
            PropertyBox tmp = new PropertyBoxImpl();
           
            try {
				((Displayable)tmp).setTechnicalLayer((PropertyBoxGraphics)getGraphics().newInstance());
//				((Displayable)tmp).addControl(getControl(DisplayableConstants.PRIMARY_CONTROL_CLASS), DisplayableConstants.PRIMARY_CONTROL_CLASS);
//				((Displayable)tmp).addControl(getControl(DisplayableConstants.SECONDARY_CONTROL_CLASS), DisplayableConstants.SECONDARY_CONTROL_CLASS);
				((Displayable)tmp).definirTaille(new Dimension(defaultWidth, defaultHeight)) ;
				((Displayable)tmp).definirTransparence(defaultTransparency) ;
				tmp.definirTaillePolice(defaultFontSize) ;
				if (defaultBackgroundColor != null) {
					tmp.definirBaseCouleur(defaultBackgroundColor) ;
				}
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			} catch (InvalidControllerClassException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
            return tmp ;
    }
    
    /**
     * D�termine la largeur de l'ensemble des instances de BoitePropriete.
     * Cette propriete est d�termin�e lors de la cr�ation de l'instance.
     * @param taille la largeur des BoitePropriete
     */
    public void definirLargeurParDefaut (final int taille) {
    	defaultWidth = taille;
    }

	public void definirTransparencyParDefault(float transparency) {
		defaultTransparency = transparency ;
		
	}

	public void definirCouleurFond(Color color) {
		defaultBackgroundColor = color ;
		
	}

	public void definirTailleTexte(int fontSize) {
		defaultFontSize = fontSize ;
		
	}
	
}
