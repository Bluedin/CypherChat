package fr.pcl.cypherchat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable, ClientListener{

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
				Client c = new Client(/*this, */s);
				c.startPollingThread();
				synchronized (this.connectedClients) {
					this.connectedClients.add(c);
				}
				c.addClientListener(this);
			} catch (IOException e) {
				System.err.println("[Server] Client initialization error");
				e.printStackTrace();
			}
		}
	}
	
	public void onClientDeconnection(Client c) {
		System.out.println("[Server]{" + c.getSocket().getInetAddress() + "] Client has been disconnected");
		synchronized(this.connectedClients) {
			this.connectedClients.remove(c);
		}
	}
	
	public void onMessageReceived(Client c, String message) {
		System.out.println("[Server][" + c.getSocket().getInetAddress() + "] Received message: " + message);
		broadcastMessage(c, message);
	}

	private void broadcastMessage(Client c, String message) {
		String data = "MSG;";
		data += c.getNickname();
		data += ";";
		data += (long) System.currentTimeMillis() / 1000;
		data += ";";
		data += c.getSocket().getInetAddress();
		data += ";";
		data += message;
		
		broadcast(data);
	}
	
	private void broadcast(String message) {
		
		ArrayList<Client> copy;
		synchronized(this.connectedClients) {
			copy = new ArrayList<>(this.connectedClients);
		}
		
		for(Client client : copy) {
			client.sendMessage(message);
		}
	}
	
}
