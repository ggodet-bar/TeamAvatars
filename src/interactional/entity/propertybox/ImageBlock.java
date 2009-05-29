package interactional.entity.propertybox;

import java.net.URI;

public class ImageBlock {

	private URI image;

	private String label;

	private int idActeur;

	public ImageBlock() {
		this.image = null;
		this.label = null;
		this.idActeur = 0;
	}

	public ImageBlock(URI image, String label) {
		this.image = image;
		this.label = label;
		this.idActeur = newIdActeur();
	}

	public int getIdActeur() {
		return this.idActeur;
	}

	public String getLabelActeur() {
		return this.label;
	}

	private int newIdActeur() {
		this.idActeur++;
		return idActeur;
	}

}
