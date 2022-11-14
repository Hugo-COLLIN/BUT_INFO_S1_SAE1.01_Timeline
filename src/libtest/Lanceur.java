package libtest;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Lanceur de Tests
 * version 1.2 - gestion des null
 * version 1.3 - lancement graphique ou textuel
 * version 1.4 - correction bug de mise a jour avec bouton
 * version 1.5 - onglet guide exceptions 
 * version 2.0 - lien Junit
 * version 2.1 - gestion timeOut (deprecated en java8)
 *            
 */

/**
 * lanceur de test unitaire
 * 
 * @author vthomas
 * 
 */
public class Lanceur {

	/**
	 * le nombre de lancement
	 */
	int numLancer = 0;

	/**
	 * la classe qui est a tester contient les tests
	 */
	Object classDeTest;

	/**
	 * L'interface graphique
	 */
	Sortie interfaceGraphique;

	/**
	 * on construit un Lanceur possedant les test
	 * 
	 * @param classedeTest
	 *            la classe a tester
	 */
	public Lanceur(Object classedeTest) {
		this.interfaceGraphique = null;
		this.classDeTest = classedeTest;
	}

	/**
	 * lance les test dans la classedetest en attribut et on evoie les
	 * resultats
	 * dans l'interface
	 */
	public void lance(Sortie sortie) {
		// creer l'interface graphique
		this.interfaceGraphique = sortie;
		// execute les tests
		executeTests();
	}

	public void executeTests() {
		// regenere les logs
		Logs logs = genereLog();

		// mise a jour interface
		interfaceGraphique.miseAjour(logs);
	}

	/**
	 * genere les logs en lancant les tests
	 * 
	 * @param test
	 *            classe de test à lancer
	 * 
	 * @return liste de logs obtenus
	 */
	public Logs genereLog() {

		Logs res = new Logs("" + this.classDeTest.getClass());

		// recharge a chaud les classes avec un nouveau classLoader
		rechargeAChaud();

		// inxcrementer le nombre de lancer
		numLancer++;

		// recupere les methodes de test
		Method[] methodes = this.classDeTest.getClass().getMethods();

		// les garde si elles contiennent test
		ArrayList<Method> listeMethodes;
		listeMethodes = filtreMethodes(methodes);

		// on trie les methodes
		trieLesMethodesParNom(listeMethodes);
		// System.out.println(listeMethodes);

		// lance tous les tests
		for (Method methodeATester : listeMethodes) {
			testeUneMethode(methodeATester, res);
		}
		return res;
	}

	/**
	 * permet de recharger les classes a chaud recharge la classe de
	 * test avec
	 * un classLoader (en forcant avec l'URL).
	 */
	public void rechargeAChaud() {
		URL u, u2 = null;
		try {
			u = new File("").toURI().toURL();
			u2 = new File("bin").toURI().toURL();
			URL[] url = { u, u2 };
			URLClassLoader ucl = new URLClassLoader(url, null);
			Class classAn = ucl.loadClass(classDeTest.getClass().getName());
			classDeTest = classAn.newInstance();

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * permet de tester une methode
	 * 
	 * deux cas se produisent - soit elle renvoie une
	 * InvocationException et il
	 * y a une erreur dans l'appel - si c'est un cause par un
	 * TestException ==
	 * failure - sinon c'est une exception interne - soit elle ne
	 * renvoie rien
	 * et c'est ok
	 * 
	 * @param methodeATester
	 * @param res
	 *            l'ensemble des logs
	 */
	public void testeUneMethode(Method methodeATester, Logs res) {

		// creation du Thread
		ThreadExecutionTest thread;
		thread = new ThreadExecutionTest(this.classDeTest, methodeATester, res);

		// lancement du thread
		thread.start();

		// attente de la fin du
		boolean tempsDepasse = false;
		long arrivee = System.currentTimeMillis() + ThreadExecutionTest.DUREEMAX;

		// tant que le thread est vivant et a encore du temps
		while (thread.isAlive() && !tempsDepasse) {
			tempsDepasse = (System.currentTimeMillis() > arrivee);
		}

		// TODO ajouter un log de depassement
		if (tempsDepasse) {
			thread.interrupt();
			String nomMethode = nomMethode(methodeATester);
			Log log = new LogTimeOut(new LanceurTimeOutException("TimeOut"), nomMethode);
			res.ajouterLog(log);

		}

	}

	private String nomMethode(Method methodeATester) {
		return methodeATester.getName();
	}

	/**
	 * filtre les methodes et ne garde que les methodes commencant par
	 * test
	 * 
	 * @param methodes
	 *            liste des methodes
	 * @return liste des methodes avec test dans leur nom
	 */
	public ArrayList<Method> filtreMethodes(Method[] methodes) {
		ArrayList<Method> listeMethodes;
		listeMethodes = new ArrayList<Method>();
		for (Method m : methodes) {
			// si son nom commence par test, on l'ajoute
			if (nomMethode(m).substring(0, 4).equals("test"))
				listeMethodes.add(m);
		}
		return listeMethodes;
	}

	/**
	 * trie les methodes selon leur numero
	 * 
	 * @param listeMethodes
	 */
	public void trieLesMethodesParNom(ArrayList<Method> listeMethodes) {
		// on trie les methodes par numero
		Comparator<Method> compMethode = new Comparator<Method>() {

			@Override
			public int compare(Method methode0, Method methode1) {
				return (methode0.getName().compareTo(methode1.getName()));
			}

			/**
			 * calcule le numero de la methode
			 * 
			 * @param methode0
			 *            methode dont on cherche le numero
			 * @return numero de methode
			 */
			public int getNumeroMethode(Method methode0) {
				try {
					String[] tab = nomMethode(methode0).split("_");
					if (tab.length > 1)
						return Integer.parseInt(tab[1]);
				} catch (NumberFormatException e) {
				}
				return (-1);
			}
		};
		Collections.sort(listeMethodes, compMethode);
	}

	/**
	 * permet de lancer le Test
	 * 
	 * @param classedeTest
	 *            la classe de test a lancer
	 * @param args
	 *            les arguments qui viennent du main
	 */
	public static void lancer(Object classedeTest, String[] args) {
		Lanceur l = new Lanceur(classedeTest);

		if ((args.length == 1) && (args[0].equals("-text"))) {
			l.lance(new SortieTexte());
		} else if ((args.length == 1) && (args[0].equals("-xml"))) {
			l.lance(new SortieXML());
		} else if ((args.length == 1) && (args[0].equals("-short"))) {
			l.lance(new SortieTxtSimple());
		} else
			l.lance(new SortieGUI(l));
	}

}
