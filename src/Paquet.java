import java.util.Random;

/**
 * Represente un paquet contenant des cartes
 */
public class Paquet
{
    //Attribut
    /**
     * Le paquet est represente par un tableau de cartes.
     */
    private Carte[] cartes;
    
    /**
     * Constructeur par defaut : cree un tableau de cartes vide
     */
    public Paquet()
    {
        this.cartes = new Carte[0];
    }


    //Constructeurs
    /**
     * Constructeur a partir d'un tableau de cartes
     * @param pCarte copie la reference au tableau pCarte dans l'attribut cartes de l'objet Paquet.
     */
    public Paquet(Carte[] pCarte)
    {
        this.cartes = pCarte;
    }

    /**
     * Constructeur a partir d'un fichier contenant les cartes
     * @param NomFichier le chemin de fichier Ex:../cartes/timeline.txt
     */
    public Paquet(String NomFichier)
    {
        LectureFichier fichier = new LectureFichier(NomFichier);
        String[] NomCartes;
        NomCartes = fichier.lireFichier();
        this.cartes = new Carte[NomCartes.length];
        for(int i = 0; i < NomCartes.length;i++)
        {
            Carte temp = new Carte(NomCartes[i]);
            this.cartes[i] = temp;
        }
    }


    //Methodes
    /**
     * Getter du nombre de cartes
     * @return la longueur du tableau de cartes de l'objet
     */
    public int getNbCartes()
    {
        return this.cartes.length;
    }

    /**
     * Getter de la carte a l'indice place dans le tableau de cartes de l'objet Paquet.
     * @param place l'indice de la carte dans le tableau de cartes
     * @return l'objet Carte correspondant
     */
    public Carte getCarte(int place)
    {
        if (place > -1 && place < this.cartes.length)
        {
            return this.cartes[place];
        }
        else
        {
            return null;
        }

    }

    /**
     * Ajouter la carte a la fin de tableau de cartes du Paquet
     * @param Carte la Carte que vous voulez ajouter
     */
    public void ajouterCarteFin(Carte pCarte)
    {
        if (pCarte != null)
        {
            Carte [] newCartes = new Carte[this.cartes.length+1];
            for (int i = 0; i < this.cartes.length; i++)
            {
                newCartes[i] = this.cartes[i];
            }
            newCartes[this.cartes.length] = pCarte;
            this.cartes = newCartes; 
        }
        
    }

    /**
     * Retirer la carte selectionne du Paquet.
     * @param place la position de la carte que vous voulez supprimer
     * @return la carte qui est supprimee
     */
    public Carte retirerCarte(int place)
    {
        if (place > -1 && place < this.cartes.length)
        {
            Carte [] temp = new Carte[1];
            temp[0] = this.cartes[place];
            Carte [] newCartes = new Carte[this.cartes.length - 1];
            for (int i = place; i < this.cartes.length-1; i++)
            {
                this.cartes[i] = this.cartes[i+1];
            }
            for (int j = 0 ; j < newCartes.length; j++)
            {
                newCartes[j] = this.cartes[j];
            }
            this.cartes = newCartes;
            return temp[0]; 
        }
        else
        {
            return null;
        }
    }

    /**
     * Afficher le tableau de cartes
     */
    public String toString()
    {
        String tb = "-------------------------";
        String middle = "";
        for (int i = 0; i < this.cartes.length;i++)
        {
            middle = middle + i + ". "+this.cartes[i].toString()+"\n";
        }
        return tb + "\n"+ middle + tb;

    }

   /**
    * Piocher une carte dans le paquet et la supprimer ensuite
    * @return la carte qui est piochee
    */
    public Carte piocherHasard()
    {
        Carte res = null;
        if (this.cartes.length != 0)
        {
            Random random = new Random();
            int i = random.nextInt(cartes.length);
            res = this.retirerCarte(i);
        }
        return res;
    }
}