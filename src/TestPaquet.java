import static libtest.Lanceur.lancer;
import static libtest.OutilTest.assertEquals;
import libtest.*;


/**
 * Classe de test qui permet de verifier que la classe Paquet
 * fonctionne correctement
 */
public class TestPaquet {

	/**
	 * methode de lancement des tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		lancer(new TestPaquet(), args);
	}

	/**
	 * test du constructeur vide
	 */
	@Test
	public void test01_constructeurVide_OK() 
	{
		Paquet paquet = new Paquet();
		assertEquals("paquet devrait avoir 0 carte", 0, paquet.getNbCartes());
	}

	/**
	 * test du constructeur prenant un tableau de cartes pour en faire un paquet
	 */
	@Test
	public void test02_constructeurTabCartes_OK() 
	{
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'invention du telegraphe:1837");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("L'invention du morse:1838");
					
		Paquet paquet = new Paquet(tab);
		assertEquals("paquet devrait avoir 3 cartes", 3, paquet.getNbCartes());
	}

	/**
	 * test du constructeur prenant un fichier pour en faire un paquet
	 */
	public void test03_constructeurFichier_OK ()
	{
		Paquet p = new Paquet("../cartes/timeline.txt");


		assertEquals("Doit contenir : L'apparition de la ceramique","L'apparition de la ceramique", p.getCarte(0).getNom());
		assertEquals("Doit valoir : -9000", -9000, p.getCarte(0).getDate());
		assertEquals("Doit valoir false", false, p.getCarte(0).getVerso());
	}

	/**
	 * teste que getNbCartes retourne le nombre de cartes du paquet
	 */
	public void test04_getNbCartes_OK ()
	{
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'invention du telegraphe:1837");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("L'invention du morse:1838");

		Paquet paquet = new Paquet(tab);
		int res = paquet.getNbCartes();
		assertEquals("paquet devrait avoir 3 cartes", 3, res);
	}

    /**
	 * teste que getCarte renvoie la carte correspondant a l'indice
	 */
	@Test
	public void test05_getCarte_OK() 
	{
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'invention du telegraphe:1837");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("L'invention du morse:1838");
					
		Paquet paquet = new Paquet(tab);
        Carte c = paquet.getCarte(1);
		boolean res = c.toString().equals("??? -> L'invention du papier");
		assertEquals("Doit contenir : ??? -> L'invention du papier", true, res);
	}

	/**
	 * teste que getCarte renvoie null lorsque l'indice est hors tableau
	 */
	@Test
	public void test06_getCarte_horsTableau() 
	{
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'invention du telegraphe:1837");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("L'invention du morse:1838");
					
		Paquet paquet = new Paquet(tab);
		Carte c = paquet.getCarte(3);
		assertEquals("la carte 3 n'existe pas", null, c);
	}

	/**
	 * teste que ajouterCarteFin ajoute la carte en parametre e la fin du paquet
	 */
	@Test
	public void test07_ajouterCarteFin_OK () 
	{
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'invention du telegraphe:1837");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("L'invention du morse:1838");
					
		Paquet paquet = new Paquet(tab);
		paquet.ajouterCarteFin(new Carte("L'invention de l'imprimerie:1437"));

		assertEquals("paquet devrait avoir 4 cartes", 4, paquet.getNbCartes());

		Carte c = paquet.getCarte(3);
		assertEquals("la carte 3 a pour nom : L'invention de l'imprimerie", "L'invention de l'imprimerie", c.getNom());
		assertEquals("la carte 3 a pour date : 1437", 1437, c.getDate());
		assertEquals("la carte 3 a verso qui vaut : false", false, c.getVerso());
		
	}

	/**
	 * teste que ajouterCarteFin ne fait rien lorsque la carte en parametre vaut null
	 */
	public void test08_ajouterCarteFin_carteNull ()
	{
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'invention du telegraphe:1837");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("L'invention du morse:1838");
					
		Paquet paquet = new Paquet(tab);
		paquet.ajouterCarteFin(null);
		
		// tests
		assertEquals("paquet doit toujours contenir 3 cartes", 3, paquet.getNbCartes());
		assertEquals("premiere carte a pour nom : L'invention du telegraphe", "L'invention du telegraphe", paquet.getCarte(0).getNom());
		assertEquals("seconde carte a pour nom : L'invention du papier", "L'invention du papier", paquet.getCarte(1).getNom());
		assertEquals("troisieme carte a pour nom : L'invention du morse", "L'invention du morse", paquet.getCarte(2).getNom());
	}

	/**
	 * teste que retirerCarte enleve une carte dans le tableau e la place en parametre
	 */
	@Test
	public void test09_retirerCarte_OK() 
	{
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'invention du telegraphe:1837");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("L'invention du morse:1838");
					
		Paquet paquet = new Paquet(tab);
		Carte c = paquet.retirerCarte(1);
		
		// test paquet
		assertEquals("paquet devrait avoir 2 cartes", 2, paquet.getNbCartes());
		assertEquals("premiere carte a pour nom : L'invention du telegraphe", "L'invention du telegraphe", paquet.getCarte(0).getNom());
		assertEquals("seconde carte a pour nom : L'invention du morse", "L'invention du morse", paquet.getCarte(1).getNom());
			
		// test carte retournee
		assertEquals("carte retiree a pour nom : L'invention du papier", "L'invention du papier", c.getNom());
		assertEquals("carte retiree a pour date : -200", -200, c.getDate());
		assertEquals("carte retiree a son verso qui vaut : false", false, c.getVerso());

	}

	/**
	 * teste que retirerCarte ne fait rien lorsque la place en parametre est en 
	 * dehors du tableau (prend en compte le cas ou le paquet est vide)
	 */
	public void test10_retirerCarte_horsTableau() 
	{
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'invention du telegraphe:1837");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("L'invention du morse:1838");
					
		Paquet paquet = new Paquet(tab);
		Carte c = paquet.retirerCarte(9);
		
		// test paquet
		assertEquals("paquet doit toujours contenir 3 cartes", 3, paquet.getNbCartes());
		assertEquals("c vaut null", null, c);
		assertEquals("premiere carte a pour nom : L'invention du telegraphe", "L'invention du telegraphe", paquet.getCarte(0).getNom());
		assertEquals("seconde carte a pour nom : L'invention du papier", "L'invention du papier", paquet.getCarte(1).getNom());
		assertEquals("troisieme carte a pour nom : L'invention du morse", "L'invention du morse", paquet.getCarte(2).getNom());

	}

	/**  
	 * teste que piocherHasard renvoie une carte lorsque le paquet contient des cartes
	 */
    public void test11_piocherHasard_paquetContientCartes ()
	{
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'invention du telegraphe:1837");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("L'invention du morse:1838");

		Paquet paquet = new Paquet(tab);
		assertEquals("Le paquet a 3 cartes", 3, paquet.getNbCartes());

        Carte c = paquet.piocherHasard();

		assertEquals("La carte ne vaut pas null", true, c != null);
		assertEquals("Le paquet a 2 cartes (1 carte en moins)", 2, paquet.getNbCartes());
    }
    
    
    /**  
	 * teste que piocherHasard renvoie null et ne touche pas au paquet lorsqu'il est vide
	 */
    public void test12_piocherHasard_paquetVide ()
	{
        Paquet paquet = new Paquet();
		assertEquals("Le paquet a 0 cartes", 0, paquet.getNbCartes());

        Carte c = paquet.piocherHasard();

		assertEquals("La carte doit valoir null", null, c);
		assertEquals("Le paquet a toujours 0 cartes", 0, paquet.getNbCartes());
    } 
    
	
    /** 
	 * teste que toString affiche correctement le paquet de cartes
	 */
    public void test13_toString_OK ()
	{
		Carte[] tab = new Carte[3];
		tab[0] = new Carte("L'invention du telegraphe:1837");
		tab[1] = new Carte("L'invention du papier:-200");
		tab[2] = new Carte("L'invention du morse:1838");
					
		Paquet paquet = new Paquet(tab);

		boolean res = paquet.toString().equals("-------------------------\n0. ??? -> L'invention du telegraphe\n1. ??? -> L'invention du papier\n2. ??? -> L'invention du morse\n-------------------------");
		assertEquals("doit afficher le paquet",true,res);
    }
}
