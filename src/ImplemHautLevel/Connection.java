package ImplemHautLevel;

import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

/**
 * Classe permettant de faire les differentes connections du serveur
 * @author Benjamin Vianey & Cindy Bodet 
 *
 */
public class Connection implements Runnable{
	
	private Socket outputLine;
	private PrintStream ps; 
    private BufferedReader br; 
    private Server myServer;
    
    /**
     * Constructeur
     * @param s Le socket d'écoute
     * @param ss le serveur qui lance une connection.
     */
	public Connection(Socket s, Server ss){
		outputLine = s;
		myServer = ss;
	}
	
	/**
	 * Methode principale de la classe Connection
	 * Elle permet d'ecrire et de recevoir le message ecrit
	 * Possibilite de eteindre la connection en ecrivant /exit 
	 * Connection eteinte s'il y a un long moment d'inactivite
	 * 
	 */
	public void run() {
		try {
			br = new BufferedReader(new InputStreamReader(outputLine.getInputStream(), "ASCII"));
			ps = new PrintStream(outputLine.getOutputStream(), true, "ASCII");
			
			Timer t;
			TimerTask tt;
		
	    	ps.println("Utiliser /exit pour sortir");
	    	ps.println("Serveur ECHO, vous pouvez ecrire!");
	    	while (true){
	    		
	    		t = new Timer();
	     	    tt = new TimerTask() {
	     	    	
	     			public void run() {
	     				ps.println("Time out : vous allez etre deconnecte");
		     		    try {
		     		    	outputLine.close();
		     			} 
		     			    catch (IOException e) {
							System.out.println("Deconnection d'un client");
		     			}
	     			}
	     		};
	     		
	     		t.schedule(tt, myServer.getTimeout());  
				String pending = br.readLine();	
				t.cancel();
				
				if (pending.equals("/exit")) {
					ps.println("Vous allez etre deconnecte");
					outputLine.close();
				}
				ps.println(pending);
	    	}
	    }
	    catch (Exception e){ }
	    finally {
		    try {
		    	  outputLine.close();
		    }
		      catch (IOException e){ }
		}
	}
}