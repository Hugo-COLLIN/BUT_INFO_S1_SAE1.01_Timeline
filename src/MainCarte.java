/**
 * lit le fichier contenant les cartes puis affiche ces cartes
 */
public class MainCarte
{
    public static void main(String[] args)
    {
        LectureFichier fichier = new LectureFichier("../cartes/timeline.txt");
        String[] NomCartes;
        NomCartes = fichier.lireFichier();
        for (int i = 0;i < NomCartes.length;i++)
        {
            Carte temp = new Carte(NomCartes[i]);
            System.out.println(temp);
            temp.setVerso(true);
            System.out.println(temp);
            
        }
        
    }
}

