/**
 * representer la frise chronologique contenant les cartes placees par le joueur.
 */
public class Frise{
    //Attribut
    /**
     * Attribut tableau de Carte
     */
    private Carte[] cartes;
    

    //Constructeur
    /**
     * Constructeur vide
     */
    public Frise ()
    {
        this.cartes = new Carte[0];
    }


    //Methodes
    /**
     * Ajouter la carte au bon endroit dans la frise
     * @param Carte la carte a ajouter dans le tableau
     */
    public void ajouterCarteTrie(Carte pCarte)
    {
        if (pCarte != null)
        {
            Carte[] Ctemp = new Carte[cartes.length+1];
            for (int i = 0; i < this.cartes.length; i++)
            {
                Ctemp[i] = cartes[i];
            }
            Ctemp[Ctemp.length-1] = pCarte;
            //tableau trie
            Carte temp;
            for (int j = 0 ; j < Ctemp.length-1; j++)
            {
                for (int k = j + 1; k < Ctemp.length; k++)
                {
                    if (Ctemp[j].getDate() > Ctemp[k].getDate())
                    {
                        temp = Ctemp[j];
                        Ctemp[j] = Ctemp[k];
                        Ctemp[k] = temp;
                    }
                }
            }
            for (int k = 0; k < Ctemp.length ; k++)
            {
                Ctemp[k].setVerso(true);
            }
            this.cartes = Ctemp;
        }
    }

    /**
     * verifier si la carte peut s'inserer derriere la place tout en respectant l'ordre chronologique de la frise
     * @param Carte la carte verifiee
     * @param indice la position que vous voulez verifier
     * @return si la carte est bien placee
     */
    public boolean verifierCarteApres(Carte c, int p)
    {
        boolean res = false;

        if (p >-2 && p<this.cartes.length && c != null)
        {
            if (p == this.cartes.length-1)
            {
                res = (c.getDate() >= this.cartes[this.cartes.length-1].getDate());
            }
            else if (p == -1)
            {
                res = (c.getDate() <= this.cartes[0].getDate());
            }
            else
            {
                res = (this.cartes[p].getDate() <= c.getDate() && this.cartes[p+1].getDate() >= c.getDate());
            }
        }

        return res;
    }

    /**
     * Insere la carte a la place p dans la frise si l’ordre chronologique est respecte.
     * @param Carte la carte inseree
     * @param indice la postion a laquelle vous voulez inserer la carte
     * @return si l'insertion a lieu
     */
    public boolean insererCarteApres(Carte c, int p)
    {
        boolean res = false;
        if (c != null)
        {
            if (this.cartes.length == 0)
            {
                Carte[] tempo = new Carte[1];
                tempo[0] = c;
                this.cartes = tempo;
                res = true;
            }
            else if (this.verifierCarteApres(c, p))
            {
                c.setVerso(true);
                Carte[] temp = new Carte[this.cartes.length+1];
                for (int i = 0; i < this.cartes.length; i++)
                {
                    temp[i] = this.cartes[i];
                }
                for (int j = p+1; j<temp.length-1;j++)
                {
                    temp[j+1] = this.cartes[j];
                }
                temp[p+1] = c;
                this.cartes = temp;
                res = true;
            }
        }
        return res;
    }

    /**
     * afficher la frise chronologique
     */
    public String toString()
    {
        String tb = "--------------";
        String middle = "";
        for (int i = 0; i < this.cartes.length;i++)
        {
            middle = middle + i + ". "+this.cartes[i].toString()+"\n";
        }
        return tb + "\n" +"frise" + "\n" + tb + "\n"+ middle + tb;

    }

    /**
     * getter d'une carte a l'indice specifie
     * @param i indice de la carte a laquelle vous voulez acceder
     * @return la carte a l'indice i
     */
    public Carte getCarte(int i){
        if (i > -1 && i < cartes.length)
        {
            return this.cartes[i];
        }
        else
        {
            return null;
        }
    }

    /**
     * getter du tableau Carte
     * @return retourner le tableau de Cartes
     */
    public Carte[] getCartes(){
        return this.cartes;
    }

    /**
     * verifie si 2 tableaux de cartes sont egaux
     * @param Cartes tableau de Cartes que vous voulez comparer avec le tableau de cartes de l'objet this
     * @return si les 2 tableaux sont egaux
     */
    public boolean equals_cartes(Carte[] c)
    {
        if (c.length == this.cartes.length)
        {
            boolean res = true;
            int i = 0;
            while (i<this.cartes.length && res)
            {
                if (this.cartes[i] != c[i])
                {
                    res = false;
                }
                i ++;
            }
            return res;
        }
        else
        {
            return false;
        }

        
    }
}