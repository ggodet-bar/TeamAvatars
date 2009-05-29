package interactional.entity.propertybox;

import interactional.dataref.Type.TypePanel;

import java.awt.Label;
import java.util.ArrayList;

import org.sonata.framework.common.TechnicalLayer;

public class Panel {
    
    private TypePanel typePanel;
    private int identifiant=0;
    private boolean etendu;
    private ArrayList<Label> text;
    private ArrayList<ImageBlock> image;
    private String titrePanel;
    private boolean isVisible ;
    private PropertyBoxGraphics coucheTechnique ;
    
    public Panel(){
    	
        this.typePanel = TypePanel.PANEL_TEXT;
        etendu = false;
        text = new ArrayList<Label>();
        image = new ArrayList<ImageBlock>();
        isVisible = false ;
    }
    
    public Panel(TypePanel typePanel){
        this();
        this.typePanel = typePanel;
        
    }
    
    public Panel(TypePanel typePanel, String titrePanel,int identifiant, TechnicalLayer couche) {
        this(typePanel);
        this.titrePanel = titrePanel;
        this.identifiant = identifiant;
        coucheTechnique = (PropertyBoxGraphics) couche ;
        coucheTechnique.newPanel(identifiant,titrePanel, typePanel);
    }
    
    public void etendrePanel() {
        etendu = true;
        coucheTechnique.expandPanel(identifiant);
    }
    public void ajouteText(final Label text) {
        this.text.add(text);
    }
    public void ajouteImage(final ImageBlock image) {
        this.image.add(image);
    }
    public void reduirePanel() {
        etendu = false;
    }
    public int getId ()
    {
        return this.identifiant;
    }
    public String getTitrePanel ()
    {
        return this.titrePanel;
    }
    public TypePanel getPanelType() {
        return typePanel;
    }
    
    public void setTitrePanel (final String titre){
        this.titrePanel = titre;
    }
    
    public boolean estVisible() {
    	return isVisible ;
    }
    
    public void afficher() {
    	isVisible = true ;
    	coucheTechnique.displayPanel(identifiant);
    }
    
    public void cacher() {
    	isVisible = false ;
    	coucheTechnique.hidePanel(identifiant);
    }
    
    
    public void nettoyer() {
    	if (typePanel.equals(TypePanel.PANEL_IMAGE)) {
    		image.clear() ;
    	} else {
    		text.clear() ;
    	}
    }
    
    public boolean isEtendu() {
    	return etendu ;
    }
}
