package libtest;

import java.util.ArrayList;

/**
 * constient une liste de Logs
 * 
 * @author vthomas
 * 
 */
public class Logs {

	/**
	 * la liste de logs
	 */
	ArrayList<Log> logs;

	/**
	 * nom de la classe testee
	 */
	String nomClasseTestee;

	/**
	 * constructeur par defaut
	 * 
	 * @param nomClasseTestee
	 *            nom de la classe testee
	 */
	public Logs(String nomClasseTestee) {
		this.nomClasseTestee = nomClasseTestee;
		this.logs = new ArrayList<Log>();
	}

	/**
	 * genere les statistiques des logs.
	 * 
	 * @return un objet contenant les statistiques generales sur tous
	 *         les tests
	 */
	public Stats genererStatistiques() {
		Stats statistiques = new Stats();
		// calculs log
		statistiques.nbTests = logs.size();
		statistiques.nbErreur = 0;
		statistiques.nbEchec = 0;
		statistiques.nbOk = 0;

		for (Log l : logs) {
			if (l.type.equals("Ok"))
				statistiques.nbOk++;
			if (l.type.equals("Erreur"))
				statistiques.nbErreur++;
			if (l.type.equals("Echec"))
				statistiques.nbEchec++;
			if (l.type.equals("TimeOut"))
				statistiques.nbTimeOut++;
		}
		return (statistiques);
	}

	public void ajouterLog(Log log) {
		this.logs.add(log);

	}

	/**
	 * permet d'ajouter une liste de logs
	 * 
	 * @param l
	 *            liste de logs a ajouter
	 */
	public void ajouterLogs(Logs ls) {
		for (Log log : ls.logs) {
			this.ajouterLog(log);
		}

	}

}
