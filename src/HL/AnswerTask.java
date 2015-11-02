package HL;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Callable;

public class AnswerTask implements Callable<String> {

	private int port;
	
	
	
	
	public AnswerTask(int port) {
		super();
		this.port = port;
	}




	@Override
	public String call() throws Exception {
		@SuppressWarnings("resource")
		ServerSocket ss = new ServerSocket(port);
		
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
