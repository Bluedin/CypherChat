package fr.pcl.cypherchat.server;

public interface ClientListener {

	void onClientDeconnection(Client c);
	
	void onClientRawDataReceived(Client c, String message);
	
}
