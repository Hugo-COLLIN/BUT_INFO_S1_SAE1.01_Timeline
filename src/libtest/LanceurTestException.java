package libtest;

/**
 * cree une classe d'Exception qui remonte sans verifier le try catch
 * 
 * @author vthomas
 */
class LanceurTestException extends RuntimeException {


	/**
	 * constructeur avec un message
	 * 
	 * @param s
	 *            message d'erreur
	 */
	public LanceurTestException(String s) {
		super(s);
	}

}