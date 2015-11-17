package ImplemBasLevel;


import java.net.*;

/**
 * Classe principale, l'objet Server créera les threads à la demande et permet de les gérer. 
 * @author Benjamin Vianey & Cindy Baudet
 *
 */
public class Server
{
	private int nbrCo;
	
	private int maxCo;
	private int port;
	private int timeout;
		
	

  public Server(int maxCo, int port, int timeout) {
		this.maxCo = maxCo;
		this.nbrCo = 0;
		this.port = port;
		this.timeout = timeout;
	}

/**
   * Classe principale du serveur
   * C'est cette classe qui sert de point d'entrée pour l'implémentation HL
   * 
   */
  @SuppressWarnings("resource")
public void runServer()
  {
    try
    {    
    	
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
   * Un handler pour afficher un message au lancement du serveur avec l'information du port utilisé
   * @param port le port sur lequel le serveur écoute
   */
  static private void messageHandler(Integer port)
  {
    System.out.println("--------");
    System.out.println("Implementation Serveur Echo Bas Level");
    System.out.println("Port utilisé : "+port);
    System.out.println("--------");
  }

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
