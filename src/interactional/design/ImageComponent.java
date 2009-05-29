package interactional.design;

import java.awt.AlphaComposite;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

public class ImageComponent extends Component {
	private static final long serialVersionUID = 0L;

	private BufferedImage image;

	private Dimension taille;
	
	private float transparency = 1.0f ;
	
	private AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparency) ;
	
	

	public void paintComponent(Graphics graphics) {
		
	}
	
	public void paint(Graphics graphics) {

		Graphics2D g = (Graphics2D) graphics.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
				RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		
		g.setComposite(ac) ;
        g.drawImage(image, 0, 0, taille.width, taille.height, this);
		g.dispose();
	}

	public Dimension getMinimumSize() {
		return new Dimension(taille);
	}

	public Dimension getPreferredSize() {
		return new Dimension(taille);
	}

	public void definirTaille(Dimension taille) {
		this.taille = taille;
	}

	public void setImage(Image image) {
		this.image = ((BufferedImage) image).getSubimage(0, 0, image.getWidth(null), image.getHeight(null));
	}
	
	public void setTransparency(float transparency) {
		 this.transparency = transparency ;
		 ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, this.transparency) ;
		 repaint() ;
	}
}
