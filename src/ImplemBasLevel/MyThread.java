package ImplemBasLevel;


import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

/**
 * Classe definissant les threads utilises par le serveur (Server.java) pour chaque client
 * Ces threads implementent une fonction echo basique (ils renvoient une copie de tout ce qui leur est envoye) 
 * @author Benjamin Vianey & Cindy Bodet
 *
 */
class MyThread implements Runnable
{
  private Thread myThread; 
  private Socket mySocket; 
  private Server myServer;
  private PrintStream ps; 
  private BufferedReader br;  

  /**
   * Constructeur de la l'objet MyThread
   * @param s Le socket d'ecoute du thread sur laquelle la connexion a ete effectuee
   * @param _myServer L'instance du serveur qui a lance le thread (afin de recuperer diverses donnees)
   * @throws SocketException Retourne en cas d'erreur lie au socket
   */
  MyThread(Socket s, Server _myServer) throws SocketException
  {
    mySocket=s;  
    myServer=_myServer;
    try
    {
    	br = new BufferedReader(new InputStreamReader(mySocket.getInputStream(), "ASCII"));
		ps = new PrintStream(mySocket.getOutputStream(), true, "ASCII");
    }
    catch (IOException e){ 
    	System.out.println("A problem occured");;
    }

    myThread = new Thread(this);
    myThread.start();
  }

  /**
   * Corps du thread
   * Cette methode definit le comportement du thread, en l'occurence, renvoye tout ce qu'il ressoit
   * Pour pouvoir fermer le thread, une commande /exit est disponible
   * Le timeout a ete implemente sous la forme d'un objet Timer et d'une tache TimerTask associe. 
   */
  public void run()
  {
	  
	Timer t;
	TimerTask tt;
		
    try
    {
    	ps.println("Utiliser /exit pour sortir");
    	ps.println("Serveur ECHO, dites bonjour !");
    	while (true)
    	{    		
    		t = new Timer();
     	    tt = new TimerTask() {
     			public void run() 
     			{
     			  ps.println("Time out : vous allez etre deconnecte");
     			  try {
					mySocket.close();
     			  } 
     			  catch (IOException e) {
					System.out.println("Deconnection d'un client");
     			  }
     			}
     		};
     		
     		
     		t.schedule(tt, myServer.getTimeout());            
			String pending = br.readLine();	
			t.cancel();
			
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
    	myServer.setNbrCo(myServer.getNbrCo()-1);
        mySocket.close();
      }
      catch (IOException e){ }
    }
  }
}
