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
	 * @param maxCo d�termine le nombre de connection maximum
	 * @param port d�termine le port d'�coute
	 * @param timeout d�termine le temps de connection 
	 */
	public Server(int maxCo, int port, int timeout) {
		this.maxCo = maxCo;
		this.port = port;
		this.timeout = timeout;
	}
	
	/**
	 * M�thode qui permet l'implementation de haut niveau
	 */
	public void runServer(){
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
				//Cr�ation d'un nouveau Thread
				c = new Connection(client, this);
				//d�but du thread
				pool.execute(c);
			}
		}
		catch (java.io.IOException e){
			System.out.println(e);
		}
	}
	
	public int getTimeout()
	{
		return this.timeout;
	}
}