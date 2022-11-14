/**
 * Classe permettant de lancer une partie de jeu
 */
public class ProgJeu
{
    /**
     * methode qui lance la partie de jeu
     * @param args le 1er argument est la taille de la main du joueur, 
     * le 2eme est le chemin du fichier contenant les cartes.
     */
    public static void main (String [] args)
    {
        Jeu partie = new Jeu(Integer.parseInt(args[0]),args[1]);
        int fini = 0;
        while (fini == 0)
        {
            partie.tour();
            fini = partie.resultatPartie();
        }
    }   
}
