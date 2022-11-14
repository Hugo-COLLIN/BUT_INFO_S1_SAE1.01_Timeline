import static libtest.Lanceur.lancer;
import static libtest.OutilTest.assertEquals;
import libtest.*;

/**
 * Classe de test qui permet de verifier que la classe Carte
 * fonctionne correctement
 */
public class TestCarte {

	/**
	 * methode de lancement des tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		lancer(new TestCarte(), args);
	}

	/**
	 * test du constructeur qui prend une chaine ce caractere 
	 * et la transforme en un objet de type Carte.
	 */
	public void test1_constructeur_OK ()
	{
		Carte c = new Carte("L'invention du papier:-200");

		assertEquals("Doit contenir : L'invention du papier","L'invention du papier", c.getNom());
		assertEquals("Doit contenir : -200", -200, c.getDate());
		assertEquals("Doit valoir false", false, c.getVerso());
	}

	/**
	 * teste que la methode toString affiche la date de la carte si verso est vrai
	 */
	public void test2_toString_versoVrai ()
	{
		Carte c = new Carte("L'invention du papier:-200");

		c.setVerso(true);

		assertEquals("Doit contenir : -200 -> L'invention du papier", "-200 -> L'invention du papier", c.toString());
	}

	/**
	 * teste que la methode toString n'affiche pas la date de la carte si verso est faux
	 */
	public void test3_toString_versoFaux ()
	{
		Carte c = new Carte("L'invention du papier:-200");		

		assertEquals("Doit contenir : ??? -> L'invention du papier", "??? -> L'invention du papier", c.toString());
	}

	/**
	 * verifie que la methode setVerso permet de mettre l'attribut verso d'une carte a vrai.
	 */
	public void test4_setVerso_vrai ()
	{
		Carte c = new Carte("L'invention du papier:-200");

		c.setVerso(true);

		assertEquals("Doit valoir true", true, c.getVerso());
	}

	/**
	 * verifie que la methode setVerso permet de mettre l'attribut verso d'une carte a faux.
	 */
	public void test5_setVerso_faux ()
	{
		Carte c = new Carte("L'invention du papier:-200");

		c.setVerso(true);
		c.setVerso(false);

		assertEquals("Doit valoir false", false, c.getVerso());
	}
}