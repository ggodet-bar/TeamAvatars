package technical.graphics;

import interactional.dataref.Type.TypePanel;
import interactional.entity.propertybox.PropertyBoxGraphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.net.URI;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;



public class SwingPropertyBox implements  PropertyBoxGraphics{

	//Attributs
    private Color baseCouleur;
    private Font boiteFont ;
    private int transparence;
    private int idPanel;
    private int x=0;
    private int y=0;
    private Point offset;
    private Dimension size;
//    private Dimension sizeExp;
//Attributs Swing
    private JPanel boite ;	//
    private JLabel labTitre;
    private JLabel labTitreEtendu;
    private HashMap<Integer, JPanel> mapPanel;
    private JPanel conteneur;  //Panel Contenant les diff�rents panels
    private JPanel panelTitreConteneur; //Panel contenant les Titres
    private JPanel panelTitre;
    private JPanel panelTitreEtendu;
    
//    private int panelTitreHeight, panelTitreEtenduHeight ;
 //Constantes pour les panels et la marge pour l'extension des panels
    private final int PANEL_TITRE_ETENDU = 999;
    private final int PANEL_CONTENEUR = 998;
    private final static int MARGE_PANEL = 26;
    private final static int DEFAULT_FONT_SIZE = 12 ;
    
  //Constructeur
    public SwingPropertyBox() {
 
    	boiteFont = new Font("SansSerif", Font.PLAIN, DEFAULT_FONT_SIZE) ;
    	
        mapPanel = new HashMap<Integer, JPanel>();
        baseCouleur = Color.BLACK;
        boite = new JPanel() {
        	/**
			 * 
			 */
			private static final long serialVersionUID = 0L;

			public void paintComponent(Graphics graphics) {
				super.paintComponents(graphics) ;
				paintBoite(graphics) ;
        	}

			private void paintBoite(Graphics graphics) {
				Graphics2D g = (Graphics2D)graphics.create();
	            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
	                               RenderingHints.VALUE_ANTIALIAS_ON);
	            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
	                               RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
	            g.setRenderingHint(RenderingHints.KEY_RENDERING,
	                               RenderingHints.VALUE_RENDER_QUALITY);
	            g.setColor(baseCouleur);
	            g.fill(new RoundRectangle2D.Float(0, 0, size.width, size.height,20f,20f)) ;
	            
	            g.setColor(baseCouleur.darker()) ;
	            g.draw(new RoundRectangle2D.Float(0, 0, size.width, size.height, 20f, 20f)) ;
				g.dispose() ;
			} 
        };
        
     //Instanciation des diff�rents panels et des Layouts
        boite.setVisible(false);
        
        // Infame bidouille garantissant le repaint de la boite
        boite.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0, 0)));
//        boite.setBorder(BorderFactory.createLineBorder(Color.black));
        
        boite.setLayout(new BorderLayout());
        panelTitre = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitreEtendu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelTitreConteneur = new JPanel(new BorderLayout());
        
        
        conteneur = new JPanel();
        conteneur.setLayout(new BoxLayout(this.conteneur,BoxLayout.Y_AXIS));
        conteneur.setVisible(false);
        
        panelTitreConteneur.add(panelTitre,BorderLayout.NORTH);
        panelTitreConteneur.add(panelTitreEtendu,BorderLayout.SOUTH);
        
        boite.add(panelTitreConteneur,BorderLayout.NORTH);
        boite.add(conteneur,BorderLayout.CENTER);
        
        transparence = 255 ;
        
        labTitre = new JLabel();
        
        labTitre.setForeground(baseCouleur);
        labTitreEtendu = new JLabel();
        labTitreEtendu.setForeground(baseCouleur);
        
        panelTitre.add(labTitre);
        panelTitreEtendu.add(labTitreEtendu);
        panelTitreEtendu.setVisible(false);
        labTitre.setAlignmentX(Component.CENTER_ALIGNMENT);
        labTitreEtendu.setAlignmentX(Component.CENTER_ALIGNMENT);   

    }
    
    
    private int computeBoiteHeight() {
    	int taille = (int) (boiteFont.getSize()) ;	// Taille initiale, uniquement le titre
    	if (panelTitreEtendu.isVisible()) {
    		taille += (int) (boiteFont.getSize()) ;
    	}
//    	taille += panelTitreConteneur.getHeight() ;
    	if (conteneur.isVisible()) {
    		for (Component singleComponent : conteneur.getComponents()) {
    			if (singleComponent.isVisible()) {
    				taille += (int) (boiteFont.getSize()) ;
    				if (((MyPanel)singleComponent).isPanelExpanded()) {
    					// TODO Calcul de la taille avec panel etendu
    					if (((MyPanel)singleComponent).panelType == TypePanel.PANEL_TEXT) {
    						taille+= (int) (boiteFont.getSize() * 1.5) * 4 ;
    					}
    					
    				}
    			}
    				
    		}
    	}
    	
    	return taille ;
    }
    
    public void setTitre(String titre,Color c) {
        setLabel(labTitre, titre, c) ;
        
        // TODO Gestion automatique de la hauteur de la boite ?
        size.height = computeBoiteHeight() ;
        boite.validate() ;
//        boite.repaint() ;
    }
    
    public void setTitreEtendu(String titre,Color c) {
    	setLabel(labTitreEtendu, titre, c);
    }
    
    public void setFontSize(int pointSize) {
    	boiteFont = new Font("SansSerif", Font.PLAIN, pointSize) ;
    }
    
    private void setLabel(JLabel label, String titre, Color c) {
    	label.setText(titre);
    	label.setFont(boiteFont) ;
        if (transparence < 255) {
        	label.setOpaque(false);
        }
        label.setForeground(new Color(c.getRed(), c.getGreen(), c.getBlue(), transparence));
        boite.validate() ;
    }
    
    public void setTranparence(float trans) {
        transparence = (int) (trans * 255);
        if (trans < 1.0f) {
        	boite.setOpaque(false);
        }
        if (baseCouleur != null) {
        	baseCouleur = new Color(baseCouleur.getRed(), baseCouleur.getGreen(), baseCouleur.getBlue(), transparence) ;
        }
        if (boite != null) {
        	boite.repaint() ;
        }
    }
    
    public void setVisibleExtendedTitle(boolean trueFalse) {
    	panelTitreEtendu.setVisible(trueFalse);
    	boite.invalidate() ;
    	// Resize the box 's height
    	size.height = computeBoiteHeight() ;
    	boite.setSize(size);
    	boite.validate() ;
    	// TODO Prise en compte des autres panels
    	boite.repaint() ;
    }
    

    
    public void newPanel(int id,String titrePanel, TypePanel panelType) {
        MyPanel panel = new MyPanel(id,titrePanel, panelType);
        mapPanel.put(id,panel);
        conteneur.add(panel);
        conteneur.revalidate() ;
    }
    
    public void addText(int id,String text,Color c) {    
        MyPanel p = (MyPanel)this.mapPanel.get(id);
        p.addText(text,c);
        boite.repaint() ;
    }
    
    public int addImage(int id,URI image,String id_acteur,Dimension imageSize) {
        MyPanel p = (MyPanel)this.mapPanel.get(id);
        return p.addImage(image,id_acteur,imageSize);
       
    }
    
    public void collapsePanel(int id){
        JPanel panel = (JPanel)this.mapPanel.get(id);
        if (id == PANEL_CONTENEUR || id == PANEL_TITRE_ETENDU) {
            panel.setVisible(false);
        }else {
            MyPanel mypanel = (MyPanel)panel;
            mypanel.collapsePanel();
        }
    }
    
    public void expandPanel(int id) {
       
    	((MyPanel)mapPanel.get(id)).extendPanel();
    }
    
    public void setDimension(Dimension size) {
    	this.size = size ;
    	boite.invalidate() ;
        boite.setSize(this.size);
		boite.repaint() ;
		boite.validate() ;
    }
    
    public void removeItem(int id) {
        MyPanel p = (MyPanel)this.mapPanel.get(id);
        p.clean();
    }
    
    public void setBaseColor(Color c) {
        this.baseCouleur = new Color(c.getRed(), c.getGreen(), c.getBlue(), transparence);
        if (boite != null){
        	boite.repaint();      	
        }

    }
    
    public boolean isPanelVisible(int id){
        JPanel panel = (JPanel)this.mapPanel.get(id);
        if (id == PANEL_CONTENEUR || id == PANEL_TITRE_ETENDU) {
            return panel.isVisible();
        }
        return false;
    }

    public void setLocation(Point coordonnees) {
    	x = coordonnees.x ;
    	y = coordonnees.y ;
        boite.setLocation(coordonnees);
    }
        
    
    
////Classe priv�e pour la construction de l'image SVG
//    private class BlocImage{
//        
//        private int idActeur;
//        
//        public BlocImage() {
//            this.idActeur = 0;
//        }
//       //Contructeur, on passe en parametre le panel conteneur de la JFrame, l'URI de l'Image, un label et la taille de l'image
//        public BlocImage(JComponent box,URI image, String label,Dimension size) {
//            this();
//            JPanel panel = new JPanel (new BorderLayout());
//            panel.setPreferredSize(new Dimension(size.width,size.height+50));
//            JSVGCanvas svgCavenas = new JSVGCanvas();
//            svgCavenas.setPreferredSize(new Dimension(size.width,size.height));
//            svgCavenas.setURI(image.toString());
//            JLabel labid = new JLabel(label);
//            labid.setFont(new Font("TimesRoman",Font.BOLD,15));
//            panel.add(svgCavenas,BorderLayout.CENTER);
//            panel.add(labid,BorderLayout.SOUTH);
//            box.add(panel);
//            this.idActeur = newIdActeur();
//        }
//        
//        public int getIdActeur() {
//            return this.idActeur;
//        }
//        
//        private int newIdActeur() {
//            this.idActeur++;
//            return idActeur;
//        }
//    }
   //Classe priv�e pour la construction des panels
    private class MyPanel extends javax.swing.JPanel {
        
        /**
		 * 
		 */
		private static final long serialVersionUID = 2144405595447405222L;
		private int id; 
//        private Dimension dimReduit;
//        private Dimension dimEtendu;
        private JScrollPane scrollpaneConteneur; //Le scrollPane permet d'afficher une scrollbar si il y a trop d'information � afficher
        private JPanel panelTitre;
        private Box panelConteneur;
        private JLabel titre;
        private TypePanel panelType ;
//        private HashMap<Integer, JComponent> listComposant;
        
        public static final int TITRE_PANEL = 0;
        public static final int PANEL_CONTENEUR = 1;
        public static final int SCROLLPANE = 2;
        private static final int DEFAULT_PANEL_HEIGHT = 78;
        
        public MyPanel(int id, String titrePanel, TypePanel panelType){
            this.panelType = panelType ;
            this.setID(id);
//            this.listComposant = new HashMap<Integer, JComponent>();
            this.setLayout(new BorderLayout());
//            this.setPreferredSize(new Dimension(size.width, this.getHeight()));
            this.setSize(new Dimension( size.width, MARGE_PANEL));
//            this.setPreferredSize(size);
//            this.dimReduit = size;
//            this.dimEtendu = new Dimension(size.width,size.height+150);
            
            panelTitre = new JPanel(new BorderLayout());
            panelTitre.setBorder(new EmptyBorder(5, 5, 5, 5));
            panelConteneur = new Box(BoxLayout.Y_AXIS);
            panelConteneur.setBorder(new EmptyBorder(0, 8, 0, 0));
//            panelConteneur.setBorder(BorderFactory.createLineBorder(Color.black));
            panelConteneur.setMaximumSize(new Dimension(size.width, Integer.MAX_VALUE) );
            
            titre = new JLabel(titrePanel);
            titre.setHorizontalAlignment(JLabel.LEFT);
            titre.setForeground(new Color(Color.black.getRed(), Color.black.getGreen(), Color.black.getBlue(), transparence));
            titre.setFont(boiteFont) ;
            panelTitre.add(titre, BorderLayout.CENTER);
            panelTitre.setVisible(true);	// Par d�faut le titre est affich�
            
            scrollpaneConteneur = new JScrollPane(panelConteneur);
            scrollpaneConteneur.getViewport().setBackground(new Color(0, 0, 0, 0));
            scrollpaneConteneur.setBorder(new EmptyBorder(0, 0, 0, 0));
            scrollpaneConteneur.setBackground(new Color(0, 0, 0, 0));
            if (panelType == TypePanel.PANEL_TEXT) {
            	scrollpaneConteneur.setSize(size.width, (int) (boiteFont.getSize() * 1.5) * 4);
            }
            
            scrollpaneConteneur.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            scrollpaneConteneur.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
            scrollpaneConteneur.setVisible(false);
            
            add(panelTitre,BorderLayout.NORTH);
            add(scrollpaneConteneur,BorderLayout.CENTER);
        }

        public final int getID() {
            return this.id;
        }
        
        public final void setID(int id) {
            this.id = id;
        }
        
        public boolean isPanelExpanded () {
            return this.scrollpaneConteneur.isVisible();
        }
        
        public void addText(String text,Color c){
            JLabel lab = new JLabel(text);
            lab.setFont(boiteFont) ;
            lab.setHorizontalAlignment(JLabel.LEFT);
            lab.setForeground(new Color(c.getRed(), c.getGreen(), c.getBlue(), transparence));
            panelConteneur.add(lab);
            panelConteneur.revalidate() ;
            
//            scrollpaneConteneur.validate() ;
            
            boite.repaint() ;
//            boite.revalidate();
        }
        
        // TODO Revoir cette partie (on utilisera des BufferedImage, avec un raster small)
        public int addImage (URI image,String id_acteur,Dimension size){
//            BlocImage blocImage = new BlocImage(this.panelConteneur,image,id_acteur,size);
//            this.scrollpaneConteneur.setPreferredSize(size);
//            this.panelConteneur.setPreferredSize(size);
//            this.revalidate();
//            return blocImage.getIdActeur();
        	return -1 ;
        }
        
        public void extendPanel ()
        {
            scrollpaneConteneur.setVisible(true);
            size.height = computeBoiteHeight() ;
            boite.setSize(size);
            boite.repaint() ;
            boite.revalidate() ;
        }
        
        public void collapsePanel()
        {
        	scrollpaneConteneur.setVisible(false);
        	size.height = computeBoiteHeight() ;
            boite.setSize(size);
            boite.repaint() ;
            boite.revalidate() ;
        }
        
        public void clean() {
        	panelConteneur.removeAll() ;
//        	boite.repaint() ;
        	boite.revalidate() ;
        }
    }
    
    
	public Object getRepresentation() {
		return boite ;
	}
	
	public Object getPanelRepresentation(int id) {
		return mapPanel.get(id) ;
	}

	public void setVisible(boolean b) {
		size.height = computeBoiteHeight() ;
		boite.setSize(size);
		boite.setVisible(b);
		if (b) {		
			boite.revalidate();
			boite.repaint() ;
		}
	}




	public void displayPanel(int id) {
		if (!conteneur.isVisible()) {
			conteneur.setVisible(true);
		}
		mapPanel.get(id).setVisible(true);
		size.height = computeBoiteHeight() ;
		boite.setSize(size);
		boite.validate() ;
		
		boite.repaint() ;
	}


	public void hidePanel(int id) {
		mapPanel.get(id).setVisible(false);
		boolean someVisible = false ;
		for (Component singlePanel : conteneur.getComponents()) {
			someVisible |= singlePanel.isVisible() ;
		}
		if (!someVisible) {
			conteneur.setVisible(false);
		}
		size.height = computeBoiteHeight() ;
		boite.setSize(size);
		boite.validate() ;
		
		boite.repaint() ;
	}
}
