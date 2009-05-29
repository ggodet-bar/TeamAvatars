package interactional.entity.propertybox;
import interactional.dataref.Exceptions.ErreurEtatApplication;
import interactional.dataref.Type.TypePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.Point;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.sonata.framework.common.TechnicalLayer;
import org.sonata.framework.common.entity.EntityObject;
import org.sonata.framework.common.interactional.Displayable;

/**
 * Impl�mentation de l'Objet Entit� BoitePropriete
 * 
 * @see PropertyBox BoitePropriete
 * @author godetg
 *
 */
public class PropertyBoxImpl implements PropertyBox,EntityObject, Displayable {
    private String titre;
    private String titreEtendu;
    private Color baseCouleur;
    
    /*
     * Variables de traitement de l'aspect <code>Displayable</code> de l'Objet Interactionnel
     */
    private Point coordonnees;
    private Dimension dimension;
	private float orientation;
    private boolean estVisible;
    private float transparence;
    
    /*
     * Fin des variables
     */
    
    /**
     * Liste des panels de la <code>BoitePropriete</code>
     */
    private final Map<Integer, Panel> mapPanel;
    
    /**
     * Compte le nombre de panels <code>BoitePropriete</code>. Sert
     * �galement d'identifiant pour l'attribut <code>mapPanel</code>
     */
    private int compteur;

    private static final String PANEL_REF_ERROR = "Le panel n'est pas r�f�renc�" ;
    private static final String PANEL_TYPE_ERROR = "Erreur : Mauvais Type de Panel" ;
    
    
    private PropertyBoxGraphics coucheTechnique ;
	private int taillePolice;

    
    public PropertyBoxImpl() {
        mapPanel = new HashMap<Integer, Panel>();
        compteur = 0;
        baseCouleur = Color.BLACK;
//        dimension = new Dimension(200,200);
        estVisible = false;
    }
    

	public void afficher() {
    	estVisible = true ;
    	coucheTechnique.setVisible(true);
    }
    
    public void etendreProprietes(final int panelNumber) throws ErreurEtatApplication {
        Panel myPanel = (Panel)mapPanel.get(panelNumber);
        if (myPanel == null){
        	throw new ErreurEtatApplication(PANEL_REF_ERROR);
        }
        else {
            myPanel.etendrePanel();        	
        }
    }
    
    public void reduireProprietes(final int panelNumber) throws ErreurEtatApplication {
        Panel myPanel = (Panel)mapPanel.get(panelNumber);
        if (myPanel == null){
        	throw new ErreurEtatApplication(PANEL_REF_ERROR);
        }
        else {
            myPanel.reduirePanel();
            coucheTechnique.collapsePanel(panelNumber);
        }
    }
    
    public void ajouterTexte(final int panelNumber, final String text,
			final Color couleurText) throws ErreurEtatApplication {
		Panel myPanel = (Panel) mapPanel.get(panelNumber);
		if (myPanel == null) {
			throw new ErreurEtatApplication(PANEL_REF_ERROR);
			
		} else {
			if (myPanel.getPanelType().equals(TypePanel.PANEL_TEXT)) {
				Label label = new Label(text);
				label.setForeground(couleurText);
				myPanel.ajouteText(label);
				coucheTechnique.addText(panelNumber, text, couleurText);

			} else {
				throw new ErreurEtatApplication(PANEL_TYPE_ERROR);
			}
		}
	}
    
    public void ajouterImage(final int panelNumber ,final URI image, final String label, final Dimension imageSize) throws ErreurEtatApplication {
        Panel myPanel = (Panel)mapPanel.get(panelNumber);
        if (myPanel == null) {
        	throw new ErreurEtatApplication(PANEL_REF_ERROR);
            
        }else{
        	if (myPanel.getPanelType().equals(TypePanel.PANEL_IMAGE)) {
                myPanel.ajouteImage(new ImageBlock(image,label));
                coucheTechnique.addImage(panelNumber,image,label,imageSize);
            }else {
                throw new ErreurEtatApplication(PANEL_TYPE_ERROR);
            }
        }
    }
    
    public void nettoyerPanel(final int panelNumber) throws ErreurEtatApplication {
        Panel myPanel = (Panel)mapPanel.get(panelNumber);
        if (myPanel == null) {
        	throw new ErreurEtatApplication(PANEL_REF_ERROR);

        }else{
        	myPanel.nettoyer() ;
        	coucheTechnique.removeItem(panelNumber);
        }
    }
    
    public int ajouterPanel(final TypePanel typePanel,final String titrePanel) {
    	Panel myPanel = new Panel(typePanel,titrePanel,compteur, coucheTechnique); 
        mapPanel.put(compteur,myPanel);
        compteur++;
        return myPanel.getId();
    }
    
    public void definirTitre(final String titre, final Color color){
        this.titre = titre;
        coucheTechnique.setTitre(titre,color);
    }
    
    public String obtenirTitre() {
    	return titre ;
    }
    
    public void definirTitreEtendu(final String titre, final Color color){
        this.titreEtendu = titre;
        coucheTechnique.setTitreEtendu(titre,color);
    }
    
    public String obtenirTitreEtendu() {
    	return titreEtendu ;
    }
    
    public void definirBaseCouleur(final Color couleur){
        this.baseCouleur = couleur;
        coucheTechnique.setBaseColor(this.baseCouleur);
    }
    
    public void definirTaillePolice(int taillePolice) {
    	this.taillePolice = taillePolice ;
    	coucheTechnique.setFontSize(taillePolice) ;
    }
    
    public void definirTransparence(final float transparence){
        this.transparence = transparence;
        coucheTechnique.setTranparence(transparence);
    }

    public void definirTaille(final Dimension dimension) {
        this.dimension = dimension;
        coucheTechnique.setDimension(this.dimension);
    }


    public void definirPosition(final Point point) {
        this.coordonnees = point;
        this.coucheTechnique.setLocation(coordonnees);
    }


	public void cacher() {
		estVisible = false ;
		coucheTechnique.setVisible(false);
	}

	public boolean isVisible() {
		return estVisible ;
	}

	public Point obtenirPosition() {
		return coordonnees ;
	}

	public Object obtenirRepresentation() {
		return coucheTechnique.getRepresentation() ;
	}

	public Dimension obtenirTaille() {
		return dimension ;
	}

	public float obtenirTransparence() {
		return this.transparence ;
	}

	public void definirOrientation(final float orientation) {
		this.orientation = orientation ; 
	}
	
	public float obtenirOrientation() {
		return orientation ;
	}
	

	public void setTechnicalLayer(final TechnicalLayer technicalLayer) {
		this.coucheTechnique = (PropertyBoxGraphics) technicalLayer ;
		
	}


	public void developperBoite() {
		coucheTechnique.setVisibleExtendedTitle(true);
		
	}

	public void reduireBoite() {
		coucheTechnique.setVisibleExtendedTitle(false);
		
	}

	public void afficherPanel(final int panelNumber) {
		mapPanel.get(panelNumber).afficher() ;
	}

	public void afficherTousLesPanels() {
		for(Panel singlePanel : mapPanel.values()) {
			singlePanel.afficher() ;
		}
		
	}

	public void cacherPanel(final int panelNumber) {
		mapPanel.get(panelNumber).cacher() ;
		
	}

	public void cacherTousLesPanels() {
		for(Panel singlePanel : mapPanel.values()) {
			singlePanel.cacher() ;
		}
		
	}

	public boolean estPanelEtendu(final int idPanel) {
		Panel panel = mapPanel.get(idPanel);
		return panel.isEtendu() ;
	}
}
