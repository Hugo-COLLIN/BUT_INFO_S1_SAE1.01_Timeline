package libtest;

/**
 * stocke les statistiques des logs de test
 * 
 * @author vthomas
 * 
 */
public class Stats {

	/**
	 * le nombre de tests
	 */
	int nbTests;

	/**
	 * le nombre de tests ok, echecs et erreurs
	 */
	int nbOk, nbEchec, nbErreur, nbTimeOut;

	/**
	 * retourne l'affichage des statistiques
	 */
	public String toString() {
		String r = "";
		r += "nb test:" + this.nbTests;
		r += "\nnb Ok:" + this.nbOk;
		r += "\nnb Echec:" + this.nbEchec;
		r += "\nnb erreur:" + this.nbErreur;
		r += "\nnb timeout:" + this.nbTimeOut;
		return (r);
	}
}
