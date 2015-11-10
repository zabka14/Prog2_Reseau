package HL;


import java.net.*;
import java.io.*;


class MyThread implements Runnable
{
  private Thread _t; 
  private Socket _s; 
  private PrintStream ps; 
  private BufferedReader br;  

  
  MyThread(Socket s)
  {
    _s=s;
    try
    {
    	br = new BufferedReader(new InputStreamReader(_s.getInputStream(), "ASCII"));
		ps = new PrintStream(_s.getOutputStream(), true, "ASCII");
    }
    catch (IOException e){ }

    _t = new Thread(this);
    _t.start();
  }


  public void run()
  {
    try
    {
    	ps.println("Utiliser /exit pour sortir");
    	ps.println("Serveur ECHO, dites bonjour !");
    	while (true)
    	{
			String pending = br.readLine();	
			if (pending.equals("/exit"))
			{
				ps.println("Vous allez etre deconnecte");
				_s.close();
			}
			ps.println(pending);
    	}
    }
    catch (Exception e){ }
    finally 
    {
      try
      {
        _s.close();
      }
      catch (IOException e){ }
    }
  }
}
