/**
 * 
 */
package ImplemHautLevel;

import java.net.*;
import java.util.Timer;
import java.util.TimerTask;
import java.io.*;

public class Connection implements Runnable
{
	private Socket outputLine;
	private PrintStream ps; 
    private BufferedReader br; 
    private Server myServer;
    
    
	public Connection(Socket s, Server ss)
	{
		outputLine = s;
		myServer = ss;
	}
	
	public void run() {
	try{
			//create a PrintWriter object with automatic flushing
			// This allows ordinary file IO over the socket
			br = new BufferedReader(new InputStreamReader(outputLine.getInputStream(), "ASCII"));
			ps = new PrintStream(outputLine.getOutputStream(), true, "ASCII");
			
			Timer t;
			TimerTask tt;
		
	    	ps.println("Utiliser /exit pour sortir");
	    	ps.println("Serveur ECHO, dites bonjour !");
	    	while (true)
	    	{
	    		t = new Timer();
	     	    tt = new TimerTask() 
	     	    {
	     	    	public void run()
	     	    	{
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
	     	   
				if (pending.equals("/exit"))
				{
					ps.println("Vous allez etre deconnecte");
					outputLine.close();
				}
				
				ps.println(pending);
	     	    
	    	}
	    }
	    catch (Exception e){ 
	    	System.out.println("Un problème est survenu lors de l'execution");
	    	}
	    finally 
	    {
	      try
	      {
	    	  outputLine.close();
	      }
	      catch (IOException e){
	    	  
	      }
	    }
 
	}
}