import java.io.*;
import java.util.ArrayList;

/**
 * classe EcritureFichier en charge d'ecrire dans un fichier
 */
public class EcritureFichier {

    /**
     * nom du fichier dans lequel ecrire
     */
    String nom;

    /**
     * objet en chareg d'ecrire
     */
    FileWriter fw;

    /**
     * constructeur de l'objet en charge d'ecrire dans fichier
     * 
     * @param nom nom du fichier dans lequel ecrire
     */
    public EcritureFichier(String nom) {
        this.nom = nom;
    }

    /**
     * ouvre le fichier en ecriture (ecrase le ficher existant)
     */
    public void ouvrirFichier(){
       try{
         File f = new File(this.nom);
         this.fw = new FileWriter(f);
       }
       catch(IOException ioe){
         throw new Error("probleme a l'ouverture du fichier "+this.nom);	
       }
    }

    /**
     * ecrit dans le fichier
     *
     * @param ligne chaine a ajouter dans le fichier
     */
    public void ecrireFichier(String ligne) {
       if (this.fw == null)
         throw new Error("il faut d'abord ouvrir le fichier");

       try{
         this.fw.write(ligne+"\n");
       }
       catch(IOException ioe){
          throw new Error("probleme pour ecrire dans le fichier "+this.nom);
       }
    }

    /**
     * ferme et sauve le fichier
     */
    public void fermerFichier() {
       try{
         this.fw.close();
       }
       catch(IOException ioe){
         throw new Error("Probleme a la fermeture du fichier");
       }
       this.fw = null;
    }


    /** 
     * exemple d'utilisation
     */
    public static void main(String []args){
      // il faut un argument au lancement du programme = nom du fichier
      if (args.length != 1) {
          throw new Error("le programme attend en argument un nom de fichier");
      }

      // creation de l'objet pour ecrire
      EcritureFichier fichier = new EcritureFichier(args[0]);
      
      // ouverture du ficheir
      fichier.ouvrirFichier();
      // ecriture de 10 lignes       
      for (int i = 0; i < 10; i++){
           fichier.ecrireFichier("Bonjour, ligne" + i);
      }
      // fermeture et sauvegarde
      fichier.fermerFichier();
    }

}
