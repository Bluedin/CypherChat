package fr.exia.cypherchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable{

	private int port;
	private ServerSocket socket;
	private Thread acceptThread;
	
	private List<Client> connectedClients;
	
	public Server(int port) {
		if(port <1 || port > 65535) {
			throw new IllegalArgumentException("Invalid port");
		}
		this.port = port;
		this.connectedClients = new ArrayList<>();
	}
	
	public void start() throws IOException{
		this.socket = new ServerSocket(this.port);
		
		this.acceptThread = new Thread(this);
		this.acceptThread.start();
		System.out.println("[Server] Listening at port " + this.port);
	}

	@Override
	public void run() {
		while(true) {
			Socket s;
			try {
				s = socket.accept();
			System.out.println("[Server] Connection received from " 
					+ s.getInetAddress());
			Client c = new Client(s);
			this.connectedClients.add(c);
			} catch (IOException e) {
				System.out.println("[Server] Accept error");
				e.printStackTrace();
			}
		}
	}
	
}
