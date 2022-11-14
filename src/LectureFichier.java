import java.io.*;
import java.util.ArrayList;

/**
 * classe LectureFichier en charge de lire un fichier
 */
public class LectureFichier {

    /**
     * nom du fichier a charger
     */
    String nom;

    /**
     * constructeur de lecteur de fichier
     * 
     * @param nom nom du fichier a charger
     */
    public LectureFichier(String nom) {
        this.nom = nom;
	
        // verifie fichier existe
        File f = new File(nom);
        FileReader fr = null;

        // ouvre et teste existence du fichier
        try {
            fr = new FileReader(f);
            fr.close();
        } catch (FileNotFoundException e) {
            // erreur fichier inexistant
            throw new Error("Fichier "+nom+" inexistant");
        } catch (IOException e) {
            // erreur fichier inexistant
            throw new Error("Fichier "+nom+" erreur entree sortie");
        }
    }


    /**
     * lit le fichier ligne par ligne a partir du nom donne au constructeur
     *
     * @return tableau de chaines de caracteres: une case du tableau = une ligne du fichier
     */
    public String[] lireFichier() {
        File f = new File(nom);
        FileReader fr = null;

        // ouvre et teste existence du fichier
        try {
            fr = new FileReader(f);
        } catch (FileNotFoundException e) {
            // erreur fichier inexistant
            throw new Error("Fichier "+nom+" inexistant");
        }
        BufferedReader bf = new BufferedReader(fr);

        // lecture des lignes du fichier (stocke dans une liste)
        ArrayList<String> liste = new ArrayList<>();
        String line = null;
        try {
            line = bf.readLine();
            while ( line != null){
                liste.add(line);
                line = bf.readLine();
            }
            bf.close();
        } catch (IOException e) {
            // erreur lors de la lecture du fichier  
            throw new Error("Erreur I/O de lecture du Fichier "+nom);
        }
        
        // transformation de la liste en tableau de chaines
        String[] lignes = liste.toArray(new String[0]);
        return lignes;
    }

    /** 
     * exemple d'utilisation
     */
    public static void main(String []args){
      // il faut un argument au lancement du programme = nom du fichier
      if (args.length != 1) {
            throw new Error("le programme attend en argument un nom de fichier");
      }

      // creation du lecteur de fichier
      LectureFichier lf = new LectureFichier(args[0]);
      
      // recupere et affiche contenu du fichier
      String[] lignes = lf.lireFichier();
      for (int i=0; i < lignes.length; i++)
           System.out.println(lignes[i]); 
      
    }

}
