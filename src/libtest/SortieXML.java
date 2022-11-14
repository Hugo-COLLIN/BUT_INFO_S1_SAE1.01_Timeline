package libtest;

public class SortieXML implements Sortie {

	@Override
	public void miseAjour(Logs ls) {

		// les statistiques
		Stats stats = ls.genererStatistiques();

		// affichage de la classe de test
		String resultat = "";
		resultat += "<xml>\n";
		resultat += "\n";
		resultat = ecrireStats(stats, resultat);
		resultat += "\n";
		resultat = ecrireTests(ls, resultat);
		resultat += "\n";
		resultat += "</xml>\n";
		System.out.println(resultat);
	}

	private String ecrireTests(Logs ls, String resultat) {
		resultat += "  </tests>\n";
		for (Log l : ls.logs) {
			resultat += "    <test>\n";
			resultat += ecrireTest(l);
			resultat += "    </test>\n";
		}
		resultat += "  <tests>\n";
		return resultat;
	}

	private String ecrireTest(Log l) {
		String res="";
		res+="        <type>"+l.type+"</type>\n";
		res+="        <methodeTest>"+l.nomMethode+"</methodeTest>\n";
		res+="        <classe>"+l.classOuErreur+"</classe>\n";
		res+="        <ligne>"+l.LigneErreur+"</ligne>\n";
		return res;
	}

	private String ecrireStats(Stats stats, String resultat) {
		resultat += "  <stats>\n";
		resultat += "    <nbTests>" + stats.nbTests + "</nbTests>\n";
		resultat += "    <nbOk>" + stats.nbOk + "</nbOk>\n";
		resultat += "    <nbEchec>" + stats.nbEchec + "</nbEchec>\n";
		resultat += "    <nbErreur>" + stats.nbErreur + "</nbErreur>\n";
		resultat += "  </stats>\n";
		return resultat;
	}

}
