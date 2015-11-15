/**
 * 
 */
package LL;

import java.net.*;
import java.io.*;

public class Connection implements Runnable{
	private Socket outputLine;
	private PrintStream ps; 
    private BufferedReader br; 
	public Connection(Socket s){
		outputLine = s;
	}
	
	public void run() {
		try{
			//create a PrintWriter object with automatic flushing
			// This allows ordinary file IO over the socket
			br = new BufferedReader(new InputStreamReader(outputLine.getInputStream(), "ASCII"));
			ps = new PrintStream(outputLine.getOutputStream(), true, "ASCII");
		
    	ps.println("Utiliser /exit pour sortir");
    	ps.println("Serveur ECHO, dites bonjour !");
    	while (true)
    	{
			String pending = br.readLine();	
			if (pending.equals("/exit"))
			{
				ps.println("Vous allez etre deconnecte");
				outputLine.close();
			}
			ps.println(pending);
    	}
    }
    catch (Exception e){ System.out.println("oh oh");}
    finally 
    {
      try
      {
    	  outputLine.close();
      }
      catch (IOException e){ }
    }
  }
	}