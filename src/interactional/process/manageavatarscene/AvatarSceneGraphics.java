package interactional.process.manageavatarscene;

import java.awt.Color;
import java.awt.Dimension;

import org.sonata.framework.common.TechnicalLayer;

public interface AvatarSceneGraphics extends TechnicalLayer {

	boolean initializeScene() ;

	void setBlur(int shadowBlur);

	void setSceneSize(Dimension sceneSize);

	void addDisk(int diskID, int internalDiameter, int externalDiameter,
			Color diskColor);
}
