package libtest;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * classe qui permet d'afficher la barre horizontale
 * 
 * @author vthomas
 * 
 */
class SortieGUIBarre extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * attributs pour affichage des statistiques
	 */
	Stats stats = new Stats();

	/**
	 * attributs pour gerer taille de la barre
	 */
	int tailleX = 400;
	int TailleY = 80;
	// decalage de la barre par rapport a jframe
	public static final int decalX = 20;
	public static final int decalY = 10;
	// taille de la barre en Y
	public static final int BarreY = 40;

	/**
	 * met a jour la barre de status
	 * 
	 * @param PnbOk
	 *            nombre de ok
	 * @param PnbErreur
	 *            nombre erreurs
	 * @param PnbEchec
	 *            nombre echec
	 */
	public void miseAJour(Stats statistiques) {
		this.stats = statistiques;
		this.repaint();
	}

	/**
	 * permet de generer la barre a la bonne taille
	 */
	public SortieGUIBarre() {
		super();
		setPreferredSize(new Dimension(tailleX, TailleY));
	}

	/**
	 * surcharge de la methode paint pour dessiner la barre
	 */
	public void paint(Graphics g) {
		super.paint(g);

		// s'il n'y a pas de tests, on ne fait rien
		int nbTests = this.stats.nbTests;
		if (nbTests == 0)
			return;

		// met a jour l'affichage de la barre
		int tailleXBarre = this.tailleX - 2 * decalX;
		int decalage = decalX;

		// le OK
		int nbOk = this.stats.nbOk;
		int finOK = (tailleXBarre * nbOk) / nbTests;
		g.setColor(LogOk.COLOR);
		g.fillRect(decalage, decalY, finOK, BarreY);
		decalage += finOK;

		// le echec
		g.setColor(LogEchec.COLOR);
		int nbEchec = this.stats.nbEchec;
		int finEchec = (tailleXBarre * nbEchec) / nbTests;
		g.fillRect(decalage, decalY, finEchec, BarreY);
		decalage += finEchec;

		// le erreur
		g.setColor(LogErreur.COLOR);
		int nbErreur = this.stats.nbErreur;
		int finErreur = (tailleXBarre * nbErreur) / nbTests;
		g.fillRect(decalage, decalY, finErreur, BarreY);
		decalage += finErreur;

		// le timeout
		g.setColor(LogTimeOut.COLOR);
		int nbTimeOut = this.stats.nbTimeOut;
		int finTimeOut = (tailleXBarre * nbTimeOut) / nbTests;
		g.fillRect(decalage, decalY, finTimeOut, BarreY);
		decalage += finTimeOut;

		// dessin rectangle
		g.setColor(Color.black);
		g.drawRect((tailleX - tailleXBarre) / 2, decalY, tailleXBarre, BarreY);

		g.drawString("Tests: " + nbTests, 0, TailleY - 10);
		g.drawString("Ok: " + nbOk, 100, TailleY - 10);
		g.drawString("Echec: " + nbEchec, 200, TailleY - 10);
		g.drawString("Erreur: " + nbErreur, 300, TailleY - 10);
		g.dispose();

	}

}