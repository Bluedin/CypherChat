package fr.pcl.cypherchat.server;

public interface ClientListener {

	void onDeconnection(Client c);
	
	void onMessageReceived(Client c, String message);
	
}