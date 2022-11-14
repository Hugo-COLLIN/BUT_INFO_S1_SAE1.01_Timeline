import static libtest.Lanceur.lancer;
import static libtest.OutilTest.assertEquals;
import libtest.*;

/**
 * Classe de test qui permet de verifier que la classe Frise
 * fonctionne correctement
 */
public class TestFrise {

	/**
	 * methode de lancement des tests
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		lancer(new TestFrise(), args);
	}
	/**
	 * test ajouterCarteTrie
	 */
	@Test
	public void test01_constructeurVide_OK() 
	{
		Frise frise = new Frise();
		assertEquals("cartes devrait avoir 0 carte", 0, frise.getCartes().length);
	}

	/**
	 * teste que la methode ajouterCarteTrie ajoute bien les carte dans la frise de 
	 * telle sorte qu'elles soient toujours triees dans l'ordre chronologique.
	 */
	public void test02_ajouterCarteTrie_OK() {
		//Initialisation
		Frise f = new Frise();
		Carte c1 = new Carte("a:2");
		Carte c2 = new Carte("b:-4");
		Carte c3 = new Carte("c:10");
		c1.setVerso(true);
		c2.setVerso(true);
		c3.setVerso(true);
		Carte[] cartes = new Carte[3];
		cartes[0] = c2;
		cartes[1] = c1;
		cartes[2] = c3;
		f.ajouterCarteTrie(c1);
		f.ajouterCarteTrie(c3);
		f.ajouterCarteTrie(c2);
		assertEquals("Frise.cartes devrait etre bien trie",true,f.equals_cartes(cartes));
	}

	/**
	 * teste que la methode ajouterCarteTrie ne fait rien si on ajoute une carte qui vaut null
	 */
	public void test03_ajouterCarteTrie_carteNull() {
		//Initialisation
		Frise f = new Frise();
		f.ajouterCarteTrie(null);

		//Test
		assertEquals("f est toujours vide",0,f.getCartes().length);
	}

	/**
	 * teste que la methode verifierCarteApres renvoie vrai lorsque 
	 * > la place est situee entre -1 et la longueur du tableau de cartes,
	 * > la carte ne vaut pas null,
	 * > on veut placer la carte au milieu de la frise,
	 * > on respecte l'ordre chronologique de la frise.
	 */
	public void test04_verifierCarteApres_milieuOrdreRespecte(){
		Frise f = new Frise();
		Carte a = new Carte("a:4");
		Carte b = new Carte("b:10");
		f.ajouterCarteTrie(a);
		f.ajouterCarteTrie(b);
		Carte test1 = new Carte("test1:5");
		assertEquals("Test1 4_[5]_10 p:0 True",true,f.verifierCarteApres(test1, 0));
	}

	
	/**
	 * teste que la methode verifierCarteApres renvoie faux lorsque 
	 * > la place est situee entre -1 et la longueur du tableau de cartes,
	 * > la carte ne vaut pas null,
	 * > on veut placer la carte au milieu de la frise,
	 * > mais la date de la carte a placer est inferieure a celle de la carte qui va la preceder.
	 */
	public void test05_verifierCarteApres_milieuDateInfPredecesseur(){
		Frise f = new Frise();
		Carte a = new Carte("a:4");
		Carte b = new Carte("b:10");
		f.ajouterCarteTrie(a);
		f.ajouterCarteTrie(b);
		Carte test1 = new Carte("test1:3");
		assertEquals("Test2 4_10_[3] p:1 false",false,f.verifierCarteApres(test1, 1));
	}

	/**
	 * teste que la methode verifierCarteApres renvoie faux lorsque 
	 * > la place est situee entre -1 et la longueur du tableau de cartes,
	 * > la carte ne vaut pas null,
	 * > on veut placer la carte au milieu de la frise,
	 * > mais la date de la carte a placer est superieure a celle de la carte qui va la succeder.
	 */
	public void test06_verifierCarteApres_milieuDateSupSuccesseur(){
		Frise f = new Frise();
		Carte a = new Carte("a:4");
		Carte b = new Carte("b:10");
		f.ajouterCarteTrie(a);
		f.ajouterCarteTrie(b);
		Carte test1 = new Carte("test1:11");
		assertEquals("Test2 4_[11]_10 p:0 false",false,f.verifierCarteApres(test1, 0));
	}

	/**
	 * teste que la methode verifierCarteApres renvoie faux lorsque 
	 * > la place est inferieure a -1
	 */
	public void test07_verifierCarteApres_placeInfTableau(){
		Frise f = new Frise();
		Carte a = new Carte("a:4");
		f.ajouterCarteTrie(a);
		Carte test1 = new Carte("test1:3");
		assertEquals("Test2 [3]_4 p:-10 false",false,f.verifierCarteApres(test1, -2));
	}

	/**
	 * teste que la methode verifierCarteApres renvoie faux lorsque 
	 * > la place est superieure a la longueur du tableau de cartes
	 */
	public void test08_verifierCarteApres_placeSupTableau(){
		Frise f = new Frise();
		Carte a = new Carte("a:4");
		f.ajouterCarteTrie(a);
		Carte test1 = new Carte("test1:3");
		assertEquals("Test1 4_[3] p:10 false",false,f.verifierCarteApres(test1, 1));
	}

	/**
	 * teste que la methode verifierCarteApres renvoie faux lorsque 
	 * > la carte vaut null,
	 */
	public void test09_verifierCarteApres_carteNull(){
		Frise f = new Frise();
		Carte a = new Carte("a:4");
		f.ajouterCarteTrie(a);
		Carte test1 = null;
		assertEquals("Test1 ?_4_? p:0 false",false,f.verifierCarteApres(test1, 0));
		assertEquals("Test1 ?_4_? p:1 false",false,f.verifierCarteApres(test1, 1));
	}

	/**
	 * teste que la methode verifierCarteApres renvoie vrai lorsque 
	 * > la place est situee entre -1 et la longueur du tableau de cartes,
	 * > la carte ne vaut pas null,
	 * > on veut placer la carte au debut de la frise,
	 * > la date de la carte a placer est inferieure a la premiere carte de la frise.
	 */
	public void test10_verifierCarteApres_debutDateOK(){
		Carte a = new Carte("a:1");
		Carte b = new Carte("b:2");
		Frise f = new Frise();
		f.ajouterCarteTrie(a);
		f.ajouterCarteTrie(b);
		Carte test1 = new Carte("test1:0");
		assertEquals("Test1 [0]_1_2 p:-1 true",true,f.verifierCarteApres(test1, -1));
	}
	
	/**
	 * teste que la methode verifierCarteApres renvoie faux lorsque 
	 * > la place est situee entre -1 et la longueur du tableau de cartes,
	 * > la carte ne vaut pas null,
	 * > on veut placer la carte au debut de la frise,
	 * > mais la date de la carte a placer est superieure a la premiere carte de la frise.
	 */
	public void test11_verifierCarteApres_debutDateSup(){
		Carte a = new Carte("a:1");
		Carte b = new Carte("b:2");
		Frise f = new Frise();
		f.ajouterCarteTrie(a);
		f.ajouterCarteTrie(b);
		Carte test1 = new Carte("test1:3");
		assertEquals("Test1 [3]_1_2 p:-1 true",false,f.verifierCarteApres(test1, -1));;
	}

	/**
	 * teste que la methode verifierCarteApres renvoie vrai lorsque 
	 * > la place est situee entre -1 et la longueur du tableau de cartes,
	 * > la carte ne vaut pas null,
	 * > on veut placer la carte au debut de la frise,
	 * > la date de la carte a placer est superieure a la derniere carte de la frise.
	 */
	public void test12_verifierCarteApres_finDateOK(){
		Carte a = new Carte("a:1");
		Carte b = new Carte("b:2");
		Frise f = new Frise();
		f.ajouterCarteTrie(a);
		f.ajouterCarteTrie(b);
		Carte test2 = new Carte("test2:3");
		assertEquals("Test2 1_2_[3] p:1 true",true,f.verifierCarteApres(test2, 1));
	}

	/**
	 * teste que la methode verifierCarteApres renvoie faux lorsque 
	 * > la place est situee entre -1 et la longueur du tableau de cartes,
	 * > la carte ne vaut pas null,
	 * > on veut placer la carte au debut de la frise,
	 * > la date de la carte a placer est inferieure a la derniere carte de la frise.
	 */
	public void test13_verifierCarteApres_finDateInf(){
		Carte a = new Carte("a:1");
		Carte b = new Carte("b:2");
		Frise f = new Frise();
		f.ajouterCarteTrie(a);
		f.ajouterCarteTrie(b);
		Carte test2 = new Carte("test2:-1");
		assertEquals("Test2 1_2_[-1] p:1 true",false,f.verifierCarteApres(test2, 1));
	}

	/**
	 * teste que la methode insererCarteApres ne fait rien lorsque la carte a inserer vaut null.
	 */
	public void test14_insererCarteApres_carteNull(){
		//Initialisation
		Frise f = new Frise();
		Carte a = new Carte("a:1");
		f.ajouterCarteTrie(a);
		assertEquals("la liste a une longueur de 1", 1, f.getCartes().length);

		//Tests
		Carte b = null;
		boolean res = f.insererCarteApres(b, 0);
		assertEquals("la carte n'est pas inseree",false,res);
		assertEquals("la liste a toujours une longueur de 1", 1, f.getCartes().length);
	}

	/**
	 * teste que la methode insererCarteApres insere une carte dans la frise lorsque 
	 * la carte a inserer ne vaut pas null et que la liste de cartes de la frise est vide.
	 */
	public void test15_insererCarteApres_listeVide ()
	{
		//Initialisation
		Frise f = new Frise();
		Carte a = new Carte("a:1");

		//Tests
		boolean res = f.insererCarteApres(a, 0);
		assertEquals("la carte est inseree",true,res);
		assertEquals("la liste a une longueur de 1", 1, f.getCartes().length);
	}

	/**
	 * teste que la methode insererCarteApres insere une carte dans la frise lorsque 
	 * la carte a inserer ne vaut pas null, que la liste de cartes de la frise n'est 
	 * pas vide et que la carte peut etre placee sur la frise.
	 */
	public void test16_insererCarteApres_listePasVidePlacerCarteApresVrai ()
	{
		//Initialisation
		Frise f = new Frise();
		Carte a = new Carte("a:1");
		f.ajouterCarteTrie(a);
		assertEquals("la liste a une longueur de 1", 1, f.getCartes().length);
		
		//Tests
		Carte b = new Carte("b:3");
		boolean res = f.insererCarteApres(b, 0);
		assertEquals("la carte est inseree",true,res);
		assertEquals("la liste a une longueur de 2", 2, f.getCartes().length);
	}

	/**
	 * teste que la methode insererCarteApres ne fait rien lorsque la carte a inserer 
	 * ne vaut pas null, que la liste de cartes de la frise n'est pas vide et que la 
	 * carte ne peut pas etre placee sur la frise.
	 */
	public void test17_insererCarteApres_pasVidePlacerCarteApresFaux ()
	{
		//Initialisation
		Frise f = new Frise();
		Carte a = new Carte("a:6");
		f.ajouterCarteTrie(a);
		assertEquals("la liste a une longueur de 1", 1, f.getCartes().length);
		
		//Tests
		Carte b = new Carte("b:3");
		boolean res = f.insererCarteApres(b, 0);
		assertEquals("la carte est inseree",false,res);
		assertEquals("la liste a toujours une longueur de 1", 1, f.getCartes().length);
	}
}
