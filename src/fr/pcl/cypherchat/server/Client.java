package fr.pcl.cypherchat.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Client implements Runnable {
	
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private Thread thread;
	private List<ClientListener> list = new ArrayList<>();
	private Server server;
	
	public Client(Server server, Socket socket) throws IOException {
		this.server = server;
		this.socket = socket;
		out = new PrintWriter(socket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}
	
	public void addClientListener(ClientListener listener) {
		this.list.add(listener);
	}
	
	public void removeClientListener(ClientListener listener) {
		this.list.remove(listener);
	}
	
	public Socket getSocket() {
		return this.socket;
	}

	public void startPollingThread() {
		this.thread = new Thread(this);
		this.thread.start();
	}

	@Override
	public void run() {
		String message;
		while(true) {
			try {
				message = this.in.readLine();
				if(message == null) {
					//this.server.onDeconnection(this);
					notifyDeconnection();
					close();
					// TODO prévenir la classe serveur
					return;
				}
				//this.server.onMessageReceived(this, message);
				notifyMessage(message);
				//TODO Temporaire
				sendMessage("ECHO => " + message);
			} catch (IOException e) {
				System.out.println("[Server][" + socket.getInetAddress() + "] Error while receiving message");
			}
		}
	}
	
	public boolean sendMessage(String data) {
		try{
			this.out.println(data);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	
	public boolean close() {
		thread.interrupt();
		try {
			this.in.close();
			this.out.close();
			this.socket.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void notifyDeconnection() {
		for(ClientListener listener : list) {
			listener.onDeconnection(this);
		}
	}
	
	public void notifyMessage(String message) {
		for(ClientListener listener : list) {
			listener.onMessageReceived(this, message);
		}
	}

}
