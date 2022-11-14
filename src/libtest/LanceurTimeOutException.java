package libtest;

/**
 * cree une classe d'Exception qui remonte sans verifier le try catch
 * 
 * @author vthomas
 */
class LanceurTimeOutException extends Exception {


	/**
	 * constructeur avec un message
	 * 
	 * @param s
	 *            message d'erreur
	 */
	public LanceurTimeOutException(String s) {
		super(s);
	}

}