/**
 * Represente une carte de jeu ayant un nom, une date et une face visible.
 */
class Carte{
  //Attributs
  /** 
   * nom, date et face de la carte
   */
  private String nom;
  private int date;
  private boolean verso;


  //Constructeur
  /**
   * constructeur de carte
   * @param carte nom et date de la carte
   */
  public Carte (String carte){
    String eventCarte = "";
    String dateCarte = "";
    int i=0;

    while (carte.charAt(i) != ':')
    {
      eventCarte += carte.charAt(i);
      i++;
    }

    i++;

    while (i < carte.length())
    {
      dateCarte += carte.charAt(i);
      i++;
    }

    this.nom = eventCarte;
    this.date = Integer.parseInt(dateCarte);
    this.verso = false;
  }

  
  //Methodes
  /**afficher les attributs de carte*/
  public String toString(){
    if (this.verso == true)
    {
      return this.date + " -> " + this.nom;
    }
    else
    {
      return "??? -> " + this.nom;
    }    
  }

  
  /**setter de verso*/
  public void setVerso(boolean b)
  {
    this.verso = b;
  }

  

  /**getter de nom*/
  public String getNom () 
  {
    return this.nom;
  }

  /**getter de date*/
  public int getDate ()
  {
    return this.date;
  }

  /**getter de verso*/
  public boolean getVerso ()
  {
    return this.verso;
  }

}
