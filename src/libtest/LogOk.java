package libtest;

import java.awt.Color;

/**
 * gere un log qui s'est bien deroule 
 *
 */
public class LogOk extends Log{

	/**
	 * la couleur du logok
	 */
	public static final Color COLOR=Color.green;
	
	public LogOk(String nom) {
		this.type="Ok";
		this.nomMethode=nom;
	}

	@Override
	public Color getColor() {
		return COLOR;
	}

	@Override
	public String getColorTexte() {
		return "green";
	}

	
}
