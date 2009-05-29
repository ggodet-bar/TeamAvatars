package technical.graphics;

import interactional.process.manageavatarscene.AvatarSceneGraphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.sun.jna.examples.WindowUtils;

public class SwingSceneGraphics implements AvatarSceneGraphics {

	private JFrame frame;
	private GraphicsZone zone ;
	private Dimension dimensionZone = new Dimension(800, 800);
	private Color bgColor = Color.white ;
	private ConvolveOp	convolve ;
	private List<Shape> disks ;
	private List<Color>	diskColors ;
	
	public SwingSceneGraphics() {
		disks = new ArrayList<Shape>() ;
		diskColors = new ArrayList<Color>() ;
	}
	
	@Override
	public boolean initializeScene() {
		frame = new JFrame() ;
		frame.setPreferredSize(dimensionZone);
	
		zone = new GraphicsZone() ;
		zone.setLayout(null);
		frame.getContentPane().add(zone);
//		glassPane = (JPanel) frame.getGlassPane();
//		glassPane.setLayout(null);
//		glassPane.setVisible(true);
		
		frame.setUndecorated(true) ;
		frame.pack() ;
		

        
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		return true;
	}

	private class GraphicsZone extends JComponent {

		private static final long serialVersionUID = 0L;


		
		protected void paintComponent(Graphics graphics) {
			super.paintComponents(graphics) ;
			paintGraphicsZone(graphics) ;
		}
		
		protected void paintGraphicsZone(Graphics graphics) {
            Graphics2D g = (Graphics2D)graphics.create();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                               RenderingHints.VALUE_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, 
                               RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setRenderingHint(RenderingHints.KEY_RENDERING,
                               RenderingHints.VALUE_RENDER_QUALITY);

//			if (plan != null && ((Displayable)plan).isVisible()) {
				g.setColor(bgColor);
				g.fill(new RoundRectangle2D.Float(0, 0, dimensionZone.width,
						dimensionZone.height, 20f, 20f));

				g.setColor(Color.darkGray);
				g.setStroke(new BasicStroke(2f));
				g.draw(new RoundRectangle2D.Float(0, 0, dimensionZone.width,
						dimensionZone.height, 20f, 20f));
				
				drawCircles(g) ;
				
//				g.drawImage((Image)((Displayable)plan).obtenirRepresentation(), 0, 0, null) ;
//			}
			
            g.dispose() ;
		}

		private void drawCircles(Graphics2D g) {
			BufferedImage img = new BufferedImage(dimensionZone.width, dimensionZone.height, BufferedImage.TYPE_INT_RGB) ;
			Graphics2D g2 = (Graphics2D) img.getGraphics() ;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
					RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
			g2.setRenderingHint(RenderingHints.KEY_RENDERING,
					RenderingHints.VALUE_RENDER_QUALITY);
			g2.setColor(bgColor) ;
			g2.fillRect(0, 0, dimensionZone.width, dimensionZone.height) ;
			
			
			// We first draw the circles' shadows
			if (!disks.isEmpty()) {
				g2.setColor(Color.black) ;
				for (Shape aDisk: disks) {
					g2.fill(aDisk);
				}
			}
			
			if (convolve != null) {
				img = convolve.filter(img,null);
			}
			
			// The shadows need to have a slight offset for correct rendering
			g.drawImage(img, 2, 2, null);
			
			// Then we draw the actual circles
			if (!disks.isEmpty()) {
				for (int i=0; i<disks.size(); i++) {
					g.setColor(diskColors.get(i)) ;
					g.fill(disks.get(i)) ;
				}
			}
		}
		
		
	}

	@Override
	public void setBlur(int shadowBlur) {
		float[] convolveKernel = new float[shadowBlur * shadowBlur] ;
		float fraction = 1.0f / ((float)shadowBlur * (float)shadowBlur) ;
		for (int i=0; i< shadowBlur * shadowBlur; i++) {
			convolveKernel[i] = fraction ;
		}
		convolve = new ConvolveOp(new Kernel(shadowBlur,shadowBlur, convolveKernel), ConvolveOp.EDGE_NO_OP, null);
		if (zone !=null){
			zone.repaint() ;
		}
	}

	@Override
	public void setSceneSize(Dimension sceneSize) {
		dimensionZone = sceneSize;
        Shape mask = new Area(new RoundRectangle2D.Float(0, 0, dimensionZone.width, dimensionZone.height,20f,20f));
        WindowUtils.setWindowMask(frame,mask);
		frame.setSize(dimensionZone) ;

		frame.repaint() ;
		
	}

	@Override
	public void addDisk(int diskID, int internalDiameter, int externalDiameter,
			Color diskColor) {
		int midH = dimensionZone.height / 2 ;
		int midW = dimensionZone.width / 2 ;
		
		Ellipse2D diskOut = new Ellipse2D.Double(midW - externalDiameter / 2, midH - externalDiameter / 2, externalDiameter, externalDiameter) ;
		Ellipse2D diskIn ; 
		if (internalDiameter == 0) {
			disks.add(diskID, diskOut) ;
		} else {
			diskIn = new Ellipse2D.Double(midW - internalDiameter / 2, midH - internalDiameter / 2, internalDiameter, internalDiameter) ;
			Area result = new Area(diskOut) ;
			result.subtract(new Area(diskIn)) ;
			disks.add(diskID, result) ;
		}

		diskColors.add(diskID, diskColor) ;
		
		zone.repaint() ;
		
	}
}
