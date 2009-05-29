

package interactional.entity.propertybox;

import interactional.dataref.Exceptions.ErreurEtatApplication;
import interactional.dataref.Type.TypePanel;

import java.awt.Color;
import java.awt.Dimension;
import java.net.URI;

/**
 * Cette interface d�crit l'ensemble des services propos�s par l'Objet Interactionnel Entit� BoitePropriete.
 * 
 * @see PropertyBoxFactory BoiteProprieteFactory, pour la m�canique de construction des entit�s BoitePropriete
 * @see common.entity.AbstractEntityFactory AbstractEntityFactory pour la m�canique g�n�rique d'instanciation des 
 * Objets Entit�
 * @see common.entity.EntityObject EntityObject, pour les services g�n�riques des Objets Entit�
 * @author godetg
 *
 */
public interface PropertyBox {
       

        /**
         * TODO Fonction non contr�l�e, susceptible d'�voluer !
         * @param panelNumber
         * @param image
         * @param Label
         * @param imageSize
         * @throws ErreurEtatApplication
         */
        void ajouterImage (int panelNumber ,URI image, String Label,Dimension imageSize) throws ErreurEtatApplication;
        
        
        /*----------------------------------------------------*
         * 
         * 			GESTION GLOBALE DE LA BOITEPROPRIETE
         * 
         *----------------------------------------------------*/      
        
        /**
         * D�finit le titre de la bo�te de propri�t�s, ainsi que la couleur
         * sous laquelle il doit appara�tre. Le titre appara�t dans toutes les
         * repr�sentations de la BoitePropriete (bo�te d�velopp�e, r�duite, panels
         * affich�s, cach�s etc.)
         * @param titre le titre de la BoitePropriete
         * @param color la couleur du titre
         */
        void definirTitre (String titre,Color color);
        
        /**
         * Renvoie le titre de la BoitePropriete
         * @return la cha�ne de caract�res du titre
         */
        String obtenirTitre() ;
        
        /**
         * D�finit le titre �tendu de la BoitePropriete, ainsi que la couleur
         * sous laquelle il doit appara�tre. Le titre �tendu appara�t
         * lorsque la BoitePropriete est dans l'�tat d�velopp�e.
         * @param titre le titre �tendu de la BoitePropriete
         * @param color la couleur du titre �tendu
         */
        void definirTitreEtendu (String titre,Color color);
        
        /**
         * Renvoie le titre �tendu de la BoitePropriete
         * @return la cha�ne de caract�res du titre �tendu
         */
        String obtenirTitreEtendu() ;
        
        /**
         * D�finit la couleur de fond de la BoitePropriete. Cette derni�re
         * est utilis�e comme base pour ensuite d�limiter les panels (ces
         * derniers sont par d�faut g�n�r�s avec une couleur plus fonc�e
         * que la couleur de base de la BoitePropriete).
         * @param couleur la couleur de la BoitePropriete.
         */
        void definirBaseCouleur (Color couleur);
        
        /**
         * Ajoute un panel de type <code>typePanel</code> � la BoitePropriete. Les panels apparaissent dans
         * l'ordre chronologique de leur ajout. Le panel ajout� porte le titre <code>titre</code>. Si
         * la cr�ation du panel est r�alis�e, la m�thode renvoie l'identifiant de ce dernier, -1 sinon.
         * @param typePanel le type du panel parmi {TEXTE, IMAGE}
         * @param titre le titre du panel
         * @return l'identifiant du panel cr�� (-1 sinon)
         */
        int ajouterPanel(TypePanel typePanel,String titre);
        
        
        /*----------------------------------------------------*
         * 
         * 			GESTION DES PANELS
         * 
         *----------------------------------------------------*/
      
        /**
         * Ajoute le texte <code>text</code> au panel d'identifiant <code>panelNumber</code>. Le texte
         * est formatt� dans la couleur <code>couleurText</code>
         * @param panelNumber l'identifiant du panel
         * 
         */
        void ajouterTexte (int panelNumber, String text, Color couleurText) throws ErreurEtatApplication;
        
        /**
         * le panel d'identifiant <code>panelNumber</code> est affich� : son contenu et son en-t�te
         * apparaissent.
         * @param panelNumber l'identifiant du panel
         */
        void afficherPanel(int panelNumber) ;
        
        /**
         * Le panel d'identifiant <code>panelNumber</code> est cach� : son contenu et son en-t�te
         * ne sont plus affich�s.
         * @param panelNumber l'identifiant du panel
         */
        void cacherPanel(int panelNumber);
        
        /**
         * Tous les panels sont affich�s dans leur �tat (�tendu/r�duit) pr�c�dent leur disparition.
         */
        void afficherTousLesPanels() ;
        
        /**
         * Cache tous les panels de la BoitePropriete (y compris les en-t�tes des panels).
         * Seuls le titre et le titre �tendu de la BoitePropriete restent visibles.
         */
        void cacherTousLesPanels() ;
        
        /**
         * Supprime le contenu du panel d'identifiant <code>panelNumber</code>. Le panel lui-m�me n'est pas
         * supprim�.
         * @param panelNumber l'identifiant du panel
         * @throws ErreurEtatApplication si le panel n'existe pas
         */
        void nettoyerPanel (int panelNumber) throws ErreurEtatApplication;
        
		/**
		 * Renvoie <code>true</code> si le panel d'identifiant <code>idPanel</code> est �tendu (i.e., les propri�t�s
		 * contenues dans le panel sont visibles)
		 * @param idPanel l'identifiant du panel
		 * @return la valeur bool�enne
		 */
		boolean estPanelEtendu(int idPanel);
        
		/**
		 * Affiche les propri�t�s (i.e., le contenu) du panel d'identifiant <code>panelNumber</code>
		 * (en plus du titre du panel).
		 * @param panelNumber l'identifiant du panel
		 * @throws ErreurEtatApplication si le panel <code>panelNumber</code> n'existe pas.
		 */
       	void etendreProprietes(int panelNumber) throws ErreurEtatApplication;
        
       	
		/**
		 * Cache les propri�t�s (i.e., le contenu) du panel d'identifiant <code>panelNumber</code>.
		 * Le titre (en-t�te) du panel reste visible.
		 * @param panelNumber l'identifiant du panel
		 * @throws ErreurEtatApplication si le panel <code>panelNumber</code> n'existe pas.
		 */
        void reduireProprietes (int panelNumber) throws ErreurEtatApplication;
        
        /**
         * Affiche le titre �tendu ainsi que les panels, dans leur position pr�c�dente
         * (d�velopp�e ou r�duite, par d�faut r�duite)
         *
         */
		void developperBoite() ;
		
		/**
		 * Cache les panels pour n'afficher plus que le titre de la bo�te
		 *
		 */
		void reduireBoite() ;


		void definirTaillePolice(int taillePolice);
}
