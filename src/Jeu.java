import java.util.Scanner;

/**
 * realiser des tours de jeu, et verifier l'etat de la partie
 * @author Hugo COLLIN, Xin ZHANG
 */
public class Jeu 
{
    //Attributs
    /**
     * Creation de 2 attributs Paquet correspondant a la main du joueur et a la pioche.
     */
    private Paquet mainJ, pioche;

    /* 
     * Creation d'un attribut de type Frise correspondant a la frise chronologique.
     */
    private Frise frise;


    //Constructeur
    /**
     * Constructeur permettant de creer une partie de jeu.
     * @param tailleMain la taille de la main du joueur
     * @param fichier le ficher contenant le descriptif des cartes pour cette partie
     */
    public Jeu (int tailleMain, String fichier)
    {
        this.frise = new Frise();
        this.pioche = new Paquet(fichier);
        this.mainJ = new Paquet();

        for (int i = 0 ; i < tailleMain ; i ++)
        {
            this.mainJ.ajouterCarteFin(this.pioche.piocherHasard());
        }  
    }


    //Methodes
    /**
     * la methode tour lance un nouveau tour de jeu
     */
    public void tour ()
    {
        Scanner sc = new Scanner(System.in);
        int nCarteJ;
        int nPlacerApres = 0;

        System.out.println("" + this.frise + "--------------\nMain du joueur\n" + this.mainJ);
        do
        {
            System.out.println("Quelle carte de votre main ? (Parmi [0;"+(this.mainJ.getNbCartes()-1)+"])");
            nCarteJ = sc.nextInt();
        }
        while (nCarteJ < 0 || nCarteJ >= this.mainJ.getNbCartes());

        Carte addCarte = this.mainJ.getCarte(nCarteJ);
        System.out.println(addCarte);

        if (this.frise.getCartes().length > 0)
        {
            do
            {
                System.out.println("Derriere quelle carte de la frise ? (Parmi [-1;"+(this.frise.getCartes().length-1)+"])");
                nPlacerApres = sc.nextInt();
            } 
            while (nPlacerApres < -1 || nPlacerApres >= this.frise.getCartes().length);

            if(nPlacerApres == -1){
                System.out.println("Placer avant: "+this.frise.getCarte(0));
            }
            else if (nPlacerApres == this.frise.getCartes().length-1)
            {
                System.out.println("Placer apres: "+this.frise.getCarte(this.frise.getCartes().length-1));
            }
            else
            {
                System.out.println("Entre :");
                for (int i = nCarteJ ; i <= nCarteJ + 1 ; i ++)
                {
                    System.out.println("   - " + this.frise.getCarte(i));
                }
            }
        }
        addCarte.setVerso(true);
        System.out.println("- Carte jouee :"+ addCarte);

        if (this.frise.insererCarteApres(addCarte, nPlacerApres))
        {
            System.out.println("Une carte de placee !!!\n");
        }
        else
        {
            System.out.println("Echoue...");
            this.mainJ.ajouterCarteFin(this.pioche.piocherHasard());
        }
        
        this.mainJ.retirerCarte(nCarteJ);
        System.out.println("\n");
    }

    /**
     * determiner l'etat de la partie de jeu
     * @return un entier res qui determine si la partie est en cours (0), gagnee (1) ou perdue (2)
     */
    public int resultatPartie ()
    {
        int res = 0;
        if (this.mainJ.getNbCartes() <= 0)
        {
            res = 1;
            System.out.println("Gagne ! Vous n'avez plus de cartes !");
        }
        else if (this.pioche.getNbCartes() <= 0)
        {
            res = 2;
            System.out.println("Perdu ! La pioche est vide !");
        }
        return res;
    }
}
