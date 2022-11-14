package libtest;


/**
 * stocke les methodes appelees dans les classes de test
 * possade des assertEquals et des methode de verification par introspection
 * 
 * @author vthomas
 *
 */
public class OutilTest {

	
	/**
	 * methode statique de comparaison sans chaine
	 */
	public static void assertEquals(Object attendu, Object obtenu) {
		// si on test des references null
		if (attendu == null) {
			if (obtenu != null)
				throw new LanceurTestException("Probleme [attendu=>" + attendu
						+ ", obtenu=>" + obtenu + "]");
		}
		// sinon on peut tester egalite
		else if (!attendu.equals(obtenu))
			throw new LanceurTestException("Probleme [attendu=>" + attendu
					+ ", obtenu=>" + obtenu + "]");
	}
	/**
	 * methode statique de comparaison
	 */
	public static void assertEquals(String erreur, Object attendu, Object obtenu) {
		// si on test des references null
		if (attendu == null) {
			if (obtenu != null)
				throw new LanceurTestException(erreur + " [attendu=>" + attendu
						+ ", obtenu=>" + obtenu + "]");
		}
		// sinon on peut tester egalite
		else if (!attendu.equals(obtenu))
			throw new LanceurTestException(erreur + " [attendu=>" + attendu
					+ ", obtenu=>" + obtenu + "]");
	}

	/**
	 * methode statique de test qui echoue
	 */
	public static void fail(String erreur) {
		throw new LanceurTestException(erreur);
	}

}
