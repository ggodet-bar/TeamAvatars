package interactional.entity.sprite;

import java.awt.Component;
import java.awt.Dimension;
import java.net.URI;

import org.sonata.framework.common.TechnicalLayer;

public interface SpriteFactoryTechnicalLayer extends TechnicalLayer {

	public Component rasterToImage(URI uri, Dimension taillSprites);
	
}
