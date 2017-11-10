package fr.pcl.cypherchat;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.UIManager;

import fr.pcl.cypherchat.client.ClientWindow;
import fr.pcl.cypherchat.client.Controller;
import fr.pcl.cypherchat.client.Model;
import fr.pcl.cypherchat.server.Server;

public class Main {

	public static void main(String[] args) throws IOException {
		Server srv = new Server(500);
		srv.start();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) { }
				ClientWindow view = new ClientWindow();
				Model model = new Model();
				Controller ctrl = new Controller(model, view);
				view.frmCypherchat.setVisible(true);
			}
		});
	}

}
