package libtest;

import java.awt.Color;

public class LogEchec extends Log {

	/**
	 * la couleur du logEchec
	 */
	public static final Color COLOR=Color.orange;
	
	public LogEchec(Throwable exceptionInterne, String nomMethode) {

		// mise a jour du log
		this.type = "Echec";
		this.nomMethode = nomMethode;

		// indice dans le stacktrace
		int indiceLigne = 1;

		// faut voir si la methode appelee est verifier ou pas.
		StackTraceElement[] stackTrace = exceptionInterne
				.getStackTrace();
		if (stackTrace[1].getMethodName().equals("verifier")) {
			// c'est qu'il faut descendre d'un cran dans la stacktrace
			indiceLigne = 2;
		}
		this.classOuErreur = stackTrace[indiceLigne].getFileName();
		this.LigneErreur = "" + stackTrace[indiceLigne].getLineNumber();
		this.exception = exceptionInterne;
	}

	@Override
	public Color getColor() {
		return COLOR;
	}

	@Override
	public String getColorTexte() {
		return "orange";
	}

}
