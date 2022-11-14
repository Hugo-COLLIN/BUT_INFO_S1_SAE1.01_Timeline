package libtest;

import java.awt.Color;

public class LogTimeOut extends Log {

	/**
	 * la couleur du logTimeOut
	 */
	public static final Color COLOR = Color.blue;

	public LogTimeOut(Throwable exceptionInterne, String nomM) {
		// mise a jour du log
		this.type = "TimeOut";
		this.nomMethode = nomM;

		// indice dans le stacktrace
		int indiceLigne = 1;

		// faut voir si la methode appelee est verifier ou pas.
		StackTraceElement[] stackTrace;
		stackTrace = exceptionInterne.getStackTrace();
				
		
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
		return "blue";
	}

}
