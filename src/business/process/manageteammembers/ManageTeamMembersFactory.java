package business.process.manageteammembers;

import org.sonata.framework.common.AbstractFactory;
import org.sonata.framework.common.process.AbstractProcessFactory;

public class ManageTeamMembersFactory extends AbstractProcessFactory {

	public static final AbstractFactory instance = new ManageTeamMembersFactory() ;
	
	@Override
	public Object creerProcessus() {
		objectInstance = new ManageTeamMembersImpl() ;
		try {
			((ManageTeamMembersImpl)objectInstance).setPersistence((Parser) this.getPersistence().newInstance()) ;
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
