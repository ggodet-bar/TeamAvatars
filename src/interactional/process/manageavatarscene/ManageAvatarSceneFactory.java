package interactional.process.manageavatarscene;

import org.sonata.framework.common.AbstractFactory;
import org.sonata.framework.common.TechnicalLayer;
import org.sonata.framework.common.process.AbstractProcessFactory;

public class ManageAvatarSceneFactory extends AbstractProcessFactory {

	public static AbstractFactory instance = new ManageAvatarSceneFactory() ;
	
	@Override
	public Object creerProcessus() {
		this.objectInstance = new ManageAvatarSceneImpl() ;
		try {
			((ManageAvatarSceneImpl) objectInstance).setTechnicalLayer((TechnicalLayer) this.getGraphics().newInstance()) ;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return objectInstance ;
	}

}
