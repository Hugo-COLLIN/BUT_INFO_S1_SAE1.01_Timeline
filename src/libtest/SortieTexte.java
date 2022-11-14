package libtest;

public class SortieTexte implements Sortie {

	@Override
	public void miseAjour(Logs ls) {
		
		// les statistiques
		Stats stats = ls.genererStatistiques();

		// affichage de la classe de test
		// TODO System.out.println(this.classedetest.getClass());
		
		// affiche le resultat global
		System.out.println(stats);
		System.out.println("\n");

		System.out.println("#########################################");
		System.out.println("# Echecs et erreurs ####################");
		System.out.println("######################################### \n");

		// affiche les logs qui echouent
		for (Log l : ls.logs) {
			if ((l.type.equals("Erreur")) || (l.type.equals("Echec")))
				System.out.println(l);
		}

		System.out.println("#########################################");
		System.out.println("# Ensemble des resultats ####################");
		System.out.println("######################################### \n");

		// afiche logs
		for (Log l : ls.logs) {
			System.out.println(l);
		}

		System.out.println("\n");
		System.out
				.println("************************************************************\n");
	}

}
