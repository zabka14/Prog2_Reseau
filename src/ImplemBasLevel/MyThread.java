package ImplemBasLevel;


import java.net.*;
import java.io.*;

/**
 * Classe définissant les threads utilisés par le serveur (Server.java) pour chaque client
 * Ces threads implémentent une fonction echo basique (ils renvoient une copie de tout ce qui leur est envoyé) 
 * @author Benjamin Vianey & Cindy Bodet
 *
 */
class MyThread implements Runnable
{
  private Thread myThread; 
  private Socket mySocket; 
  private PrintStream ps; 
  private BufferedReader br;  

  /**
   * Constructeur du thread
   * On récupère le socket, on bind deux buffer dessus : un reader et un writer. 
   * @param s Le socket reliant le client au serveur.
   */
  MyThread(Socket s)
  {
    mySocket=s;
    try
    {
    	br = new BufferedReader(new InputStreamReader(mySocket.getInputStream(), "ASCII"));
		ps = new PrintStream(mySocket.getOutputStream(), true, "ASCII");
    }
    catch (IOException e){ }

    myThread = new Thread(this);
    myThread.start();
  }

  /**
   * Corps du thread
   * Cette méthode définit le comportement du thread, en l'occurence, renvoyé tout ce qu'il ressoit
   * Pour pouvoir fermer le thread, une commande /exit est disponible
   */
  public void run()
  {
    try
    {
    	ps.println("Utiliser /exit pour sortir");
    	ps.println("Serveur ECHO, dites bonjour !");
    	while (true)
    	{
			String pending = br.readLine();	
			if (pending.equals("/exit"))
			{
				ps.println("Vous allez etre deconnecte");
				mySocket.close();
			}
			ps.println(pending);
    	}
    }
    catch (Exception e){ }
    finally 
    {
      try
      {
        mySocket.close();
      }
      catch (IOException e){ }
    }
  }
}
