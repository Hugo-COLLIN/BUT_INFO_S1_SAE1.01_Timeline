package libtest;

import java.awt.Color;

public abstract class Log {
	
	/**
	 * le nom de la methode testee
	 */
	public String nomMethode;

	/**
	 * le resultat du Log
	 */
	public String type;

	/**
	 * la classe testee
	 */
	public String classOuErreur;

	/**
	 * la ligne contenant l'echec
	 */
	public String LigneErreur;

	/**
	 * L'exception generee
	 */
	public Throwable exception;

	/**
	 * par defaut il n'y a pas d'erreur
	 */
	public Log() {
		this.type = "Ok";
	}

	/**
	 * affiche un log d'erreur
	 */
	public String toString() {
		String resultat = ("***** Test " + nomMethode + "  **********************************\n");

		// si il y eu une erreur
		if (exception != null) {
			resultat += ("  - Type: " + type + "\n");
			resultat += ("  - Classe: " + classOuErreur);
			resultat += (", Ligne: " + LigneErreur + "\n");
			resultat += ("  - Message: " + exception.getMessage() + "\n");

			// affiche exception
			StackTraceElement[] traces = exception.getStackTrace();
			for (StackTraceElement trace : traces) {
				// on arrete de faire la pile des qu'on arrive a
				// l'invocation de lanceur
				if (trace.getClassName().equals(
						"sun.reflect.NativeMethodAccessorImpl"))
					break;

				// sinon on remonte la pile d'appel
				resultat += "       -> " + trace.getClassName() + " at "
						+ trace.getFileName() + "(" + trace.getLineNumber()
						+ ")\n";
			}

		} else {
			resultat += "  - OK\n";
		}

		resultat += ("***** fin " + nomMethode + "  **********************************\n\n");

		return (resultat);
	}

	/**
	 * @return couleur associee au type de log
	 */
	public abstract Color getColor();

	/**
	 * utile pour l'ecriture en HTML dans la fenetre
	 * 
	 * @return couleur en version texte pour HTML
	 */
	public abstract String getColorTexte();

}