package fr.pcl.cypherchat.server;

public interface ClientListener {

	void onClientDeconnection(Client c);
	
	void onMessageReceived(Client c, String message);
	
}
