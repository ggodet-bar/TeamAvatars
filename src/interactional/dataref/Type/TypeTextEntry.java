package interactional.dataref.Type;

public class TypeTextEntry {
	private String name ;
	private String description ;
	private boolean isDeveloppee ;
	
	public TypeTextEntry(String name, String description) {
		this.setDescription(description) ;
		this.setName(name) ;
		this.isDeveloppee = false ;
	}
	
	public final String getDescription() {
		return description;
	}
	public final String getName() {
		return name;
	}
	public final void setDescription(String description) {
		this.description = description;
	}
	public final void setName(String name) {
		this.name = name;
	}

	public final boolean isDeveloppee() {
		return isDeveloppee;
	}

	public final void setDeveloppee(boolean isDeveloppee) {
		this.isDeveloppee = isDeveloppee;
	}
}
