package libtest;

public class SortieTxtSimple implements Sortie {

	@Override
	public void miseAjour(Logs ls) {

		// les statistiques
		Stats stats = ls.genererStatistiques();

		// affichage de la classe de test
		// TODO System.out.println(this.classedetest.getClass());

		// affiche le resultat global
		System.out.println("nombre de tests lances " + stats.nbTests);

		if (stats.nbOk == stats.nbTests) {
			System.out.println("Tout les tests sont corrects");
		} else {
			// System.out.println("Il y a des erreurs");
			if (stats.nbEchec > 0 && stats.nbErreur == 0)
				System.out.println("il y a " + stats.nbEchec + " echecs");
			if (stats.nbErreur > 0 && stats.nbEchec == 0)
				System.out.println("il y a " + stats.nbErreur + " erreurs");

			if (stats.nbErreur > 0 && stats.nbEchec > 0) {
				String string = "il y a " + stats.nbEchec + " echecs" + "et" + stats.nbErreur
						+ " erreurs";
				System.out.println(string);
			}

			// affiche les logs qui echouent
			int num = 0;
			for (Log l : ls.logs) {
				if ((l.type.equals("Erreur")) || (l.type.equals("Echec"))) {
					num++;
					String r = num + " - ";
					r += "classe " + l.classOuErreur;
					r += " a la ligne " + l.LigneErreur;
					r += ": ";
					r += l.exception.getMessage();
					System.out.println(r);
				}
			}

		}
	}

}
