package ImplemHautLevel;

import java.net.*;
import java.util.Collection;
import java.util.concurrent.*;

public class Server{
	
	private ServerSocket s;
	private Socket client;
	private Connection c;
	private ExecutorService pool;
	public Server(){
			//create the socket
			try {
					s = new ServerSocket(5555);
					pool = Executors.newFixedThreadPool(3);	
			}
			catch (java.io.IOException e){
					System.out.println(e);
					System.exit(1);
			}
			
			// OK, now listen for connections
			System.out.println("Server is listening.");
			
			try{
				while (true){
					client = s.accept();
					//create a separate thread to service the request
					c = new Connection(client);
					//new Thread(c).start();
					pool.execute(c);
				}
			}
			catch (java.io.IOException e){
				System.out.println(e);
			}
	}
	
	public static void main(String[] args) {
		Server timeOfDayServer= new Server();
	
	}

}