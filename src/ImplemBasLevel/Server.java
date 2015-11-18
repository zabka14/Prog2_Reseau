package ImplemBasLevel;


import java.net.*;

/**
 * Classe principale de l'implementation de haut niveau.
 * C'est l'objet Server qui permet d'ecouter sur un port donne, et de lancer un thread pour chaque connexion. 
 * C'est cette classe qui gerera la limitation en nombre de thread simultanes, et le port d'ecoute. 
 * @author Benjamin Vianey & Cindy Bodet
 */
public class Server
{
	private int nbrCo;
	
	private int maxCo;
	private int port;
	private int timeout;
		
	
/**
 * Constructeur unique de la classe.
 * Il n'y a pas de constructeur sans parametres (=valeurs par defaut), c'est le point d'entree principale qui se charge des valeurs par defauts.
 * @param maxCo Le nombre maximum de thread (et donc de client) lance simultanement
 * @param port Le port d'ecoute du serveur
 * @param timeout Le temps, en milliseconde, qui sera transmis au thread pour sa valeur de timeout
 */
  public Server(int maxCo, int port, int timeout) {
		this.maxCo = maxCo;
		this.nbrCo = 0;
		this.port = port;
		this.timeout = timeout;
	}

/**
 * Classe principale du serveur.
 * C'est cette classe qui cree un thread a chaque connexion d'utilisateur, en instanciant un objet MyThread.
 */
public void runServer()
  {
    try
    {    
    	
      @SuppressWarnings("resource")
      ServerSocket ss = new ServerSocket(this.port);
      messageHandler(this.port);
     
      while (true)
      {
    	if(this.nbrCo < this.maxCo){
    		try{
    			new MyThread(ss.accept(), this);
    			this.nbrCo++;
    		}
    		catch(SocketTimeoutException e)
    		{
    			System.out.println(e);
    		}
    	}
      }
    }
    catch (Exception e) { }
  }

  /**
   * Un handler pour afficher un message au lancement du serveur avec l'information du port utilise
   * @param port le port sur lequel le serveur ecoute
   */
  static private void messageHandler(Integer port)
  {
    System.out.println("--------");
    System.out.println("Implementation Serveur Echo Bas Level");
    System.out.println("Port utilisé : "+port);
    System.out.println("--------");
  }

  // Pas de JavaDoc pour les getters et setters
	public int getMaxCo() {
		return maxCo;
	}
	
	public void setMaxCo(int maxCo) {
		this.maxCo = maxCo;
	}
	
	public int getNbrCo() {
		return nbrCo;
	}
	
	public void setNbrCo(int nbrCo) {
		this.nbrCo = nbrCo;
	}
	
	public int getPort() {
		return port;
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getTimeout() {
		return timeout;
	}
	
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	
	  
  
  

}
