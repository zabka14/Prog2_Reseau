package application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @author Bodet Cindy - Vianey Benjamin 
 * Cette classe est un serveur TCP capable de recevoir un message sur un port donné, et de le re-envoyé
 */
public class PingMachine {
	
	
	public static void main(String[] args) throws IOException {


		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(3333);
		
		while (true) 
		{
			Socket s = ss.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream(), "ASCII"));

			PrintStream ps = new PrintStream(s.getOutputStream(), true, "ASCII");

			String pending = br.readLine();
			
			ps.println(pending);
			s.close();

		}
	}
}
