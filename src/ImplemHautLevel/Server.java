package ImplemHautLevel;

import java.net.*;
import java.util.Collection;
import java.util.concurrent.*;
/**
 * Serveur de haut niveau.
 * @author Benjamin Vianey & Cindy Bodet 
 *
 */
public class Server{
	
	private ServerSocket s;
	private Socket client;
	private Connection c;
	private ExecutorService pool;
	
	private int maxCo;
	private int port;
	private int timeout;
	
	
	//Getter et setter
	public ServerSocket getS() {
		return s;
	}
	public void setS(ServerSocket s) {
		this.s = s;
	}
	public Socket getClient() {
		return client;
	}
	public void setClient(Socket client) {
		this.client = client;
	}
	public Connection getC() {
		return c;
	}
	public void setC(Connection c) {
		this.c = c;
	}
	public ExecutorService getPool() {
		return pool;
	}
	public void setPool(ExecutorService pool) {
		this.pool = pool;
	}
	public int getMaxCo() {
		return maxCo;
	}
	public void setMaxCo(int maxCo) {
		this.maxCo = maxCo;
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

	/**
	 *Constructeur
	 * @param maxCo determine le nombre de connection maximum
	 * @param port determine le port d'ecoute
	 * @param timeout determine le temps de connection 
	 */
	public Server(int maxCo, int port, int timeout) {
		this.maxCo = maxCo;
		this.port = port;
		this.timeout = timeout;
	}
	
	/**
	 * Methode qui permet l'implementation de haut niveau
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
}