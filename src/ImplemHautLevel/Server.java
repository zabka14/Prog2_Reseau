package ImplemHautLevel;

import java.net.*;
import java.util.Collection;
import java.util.concurrent.*;

public class Server{
	
	private ServerSocket s;
	private Socket client;
	private Connection c;
	private ExecutorService pool;
	
	private int maxCo;
	private int port;
	private int timeout;
		
	/**
	 *Constructeur
	 * @param maxCo détermine le nombre de connection maximum
	 * @param port détermine le port d'écoute
	 * @param timeout détermine le temps de connection 
	 */
	public Server(int maxCo, int port, int timeout) {
		this.maxCo = maxCo;
		this.port = port;
		this.timeout = timeout;
	}

	public Server(){
		try {
			s = new ServerSocket(port);
			pool = Executors.newFixedThreadPool(maxCo);	
		}
		catch (java.io.IOException e){
			System.out.println(e);
			System.exit(1);
		}
		System.out.println("Server is listening.");
			
		try{
			while (true){
				client = s.accept();
				//Création d'un nouveau Thread
				c = new Connection(client);
				//début du thread
				pool.execute(c);
			}
		}
		catch (java.io.IOException e){
			System.out.println(e);
		}
	}
	
	/**
	 * méthode permettant l'implémentation du haut niveau
	 */
	public void runServer() {
		Server timeOfDayServer= new Server();
	}

}