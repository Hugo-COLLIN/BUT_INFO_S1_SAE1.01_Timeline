package libtest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Un thread permettant de lancer l'execution d'un test
 * 
 * @author vthomas
 * 
 */
public class ThreadExecutionTest extends Thread {

	/**
	 * la duree max du thread en ms
	 */
	public static long DUREEMAX = 1000;

	/**
	 * Endroit ou stocker les logs
	 */
	// TODO a supprimer et a remplacer par un attribut log simple
	Logs res;

	/**
	 * methode a tester
	 */
	Method methodeATester;
	Object classDeTest;

	/**
	 * demande execution d'un thread
	 * 
	 * @param methode
	 *            methode que le thread doit lancer et tester
	 */
	public ThreadExecutionTest(Object classTest, Method methode, Logs logs) {
		this.classDeTest = classTest;
		this.methodeATester = methode;
		this.res = logs;
	}

	/**
	 * methode d'execution du thread
	 */
	public void run() {
		// nom de la methode
		String nom = methodeATester.getName();

		// on invoque la methode
		try {
			methodeATester.invoke(this.classDeTest, new Object[0]);
			res.ajouterLog(new LogOk(nom));
		} catch (InvocationTargetException e) {
			// vient a cause d'un test echoue

			// voir l'exception interne
			Throwable exceptionInterne = e.getCause();

			// afin d'eviter les problemes dans le nouveau
			// rechargement de classes
			// (les classes ne sont pas les memes et instance of ne
			// fonctionne donc pas)
			
			String nomException;
			nomException = exceptionInterne.getClass().getSimpleName();
			
			//construit le log correspondant
			if (estTimeOut(nomException)) {
				// c'est une exception due a un test echoue
				res.ajouterLog(new LogTimeOut(exceptionInterne, nom));
			} else if (estEchec(nomException)) {
				// c'est une exception due a un test echoue
				res.ajouterLog(new LogEchec(exceptionInterne, nom));
			} else {
				res.ajouterLog(new LogErreur(exceptionInterne, nom));
			}

		} catch (IllegalAccessException e) {
			System.out.println("erreur importante de l'appli de test!!!");
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			System.out.println("erreur importante de l'appli de test!!!");
			e.printStackTrace();
		}

	}

	/**
	 * verifie si l'erreur est due a un echec
	 * 
	 * @param nomException
	 *            l'exception recuperee
	 * @return true si c'est un echec
	 */
	private boolean estEchec(String nomException) {
		return nomException.equals(LanceurTestException.class.getSimpleName());
	}

	/**
	 * verifie si l'erreur est du a un timeout
	 * 
	 * @param nomException
	 *            exception recuperee
	 * @return true si c'est un timeOut
	 */
	private boolean estTimeOut(String nomException) {
		return nomException.equals(LanceurTimeOutException.class
				.getSimpleName());
	}

}
