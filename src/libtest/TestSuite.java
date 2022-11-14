package libtest;

import java.util.ArrayList;

/**
 * lancer plusieurs tests
 * 
 * @author vthomas
 *
 */
public class TestSuite {

	public static void lancerTests(ArrayList<Object> tests, String[] args) {
		// liste des logs
		ArrayList<Logs> logs = new ArrayList<Logs>();

		// on lance chaque test
		for (Object test : tests) {
			Lanceur l = new Lanceur(test);
			logs.add(l.genereLog());
		}

		// union des logs
		Logs finLogs = new Logs("Suite");
		for (Logs l : logs) {
			finLogs.ajouterLogs(l);
		}
		
		System.out.println(finLogs);
		SortieGUI sortie=new SortieGUI(null);
		sortie.miseAjour(finLogs);

	}

}
