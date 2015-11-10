package HL;


import java.net.*;

/**
 * Classe principale, l'objet Server créera les threads à la demande et permet de les gérer. 
 * @author Benjamin Vianey & Cindy Baudet
 *
 */
public class Server
{

  /**
   * Classe principale du serveur
   * C'est cette classe qui sert de point d'entrée pour l'implémentation HL
   * @param args args[0] doit contenir le port (sinon utilisation par défaut du port 3333
   */
  @SuppressWarnings("resource")
public static void main(String args[])
  {
    try
    {
      Integer port; 
      if(args.length<=0) port=new Integer("3334"); 
      else port = new Integer(args[0]);

      

      ServerSocket ss = new ServerSocket(port); 
      messageHandler(port);
     
      while (true)
      {
        new MyThread(ss.accept());
      }
    }
    catch (Exception e) { }
  }

  /**
   * Un handler pour afficher un message au lancement du serveur avec l'information du port utilisé
   * @param port le port sur lequel le serveur écoute
   */
  static private void messageHandler(Integer port)
  {
    System.out.println("--------");
    System.out.println("Implementation Serveur Echo HL");
    System.out.println("Port utilisé : "+port);
    System.out.println("--------");
  }

}
