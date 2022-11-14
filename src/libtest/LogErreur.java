package libtest;

import java.awt.Color;

public class LogErreur extends Log {

	/**
	 * la couleur du logEchec
	 */
	public static final Color COLOR=Color.red;
	
	public LogErreur(Throwable exceptionInterne, String nom) {
		// mise a jour du log
		type = "Erreur";
		nomMethode = nom;

		// indice dans le stacktrace
		int indiceLigne = 1;

		// faut voir si la methode appelee est verifier ou pas.
		StackTraceElement[] stackTrace = exceptionInterne.getStackTrace();
		if (stackTrace[1].getMethodName().equals("verifier")) {
			// c'est qu'il faut descendre d'un cran dans la stacktrace
			indiceLigne = 2;
		}
		classOuErreur = stackTrace[indiceLigne].getFileName();
		LigneErreur = "" + stackTrace[indiceLigne].getLineNumber();
		exception = exceptionInterne;
	}

	@Override
	public Color getColor() {
		return COLOR;
	}

	@Override
	public String getColorTexte() {
		return "red";
	}

}
