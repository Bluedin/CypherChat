package exia.fr.cypherchat;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.JComboBox;

public class ClientWindow {

	private JFrame frmCypherchat;
	private JPanel panelLeft;
	private JPanel panelBottom;
	private JPanel panelCenter;
	private JTextField messageField;
	private JTextField txtNickname;
	private JScrollPane scrollPaneUser;
	private JList<?> UserList;
	private JComboBox<?> CypherComboBox;
	private JScrollPane scrollPaneChat;
	private JTextArea chatTextArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
					window.frmCypherchat.setVisible(true);
					window.messageField.grabFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCypherchat = new JFrame();
		frmCypherchat.setTitle("CypherChat");
		frmCypherchat.setBounds(100, 100, 674, 470);
		frmCypherchat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panelLeft = new JPanel();
		panelLeft.setBackground(Color.WHITE);
		panelLeft.setForeground(Color.BLACK);
		
		panelBottom = new JPanel();
		panelBottom.setBackground(Color.WHITE);
		
		panelCenter = new JPanel();
		panelCenter.setBackground(Color.WHITE);
		GroupLayout groupLayout = new GroupLayout(frmCypherchat.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelBottom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelLeft, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelCenter, GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)))
					.addGap(2))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panelLeft, GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelCenter, GroupLayout.DEFAULT_SIZE, 371, Short.MAX_VALUE)
							.addGap(1)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelBottom, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		txtNickname = new JTextField();
		txtNickname.setBackground(null);
		txtNickname.setForeground(Color.BLACK);
		txtNickname.setHorizontalAlignment(SwingConstants.LEFT);
		txtNickname.setFont(new Font("Tahoma", Font.BOLD, 14));
		txtNickname.setText("Bob");
		txtNickname.setColumns(10);
		txtNickname.setBorder(null);
		
		scrollPaneUser = new JScrollPane();
		
		CypherComboBox = new JComboBox<Object>();
		GroupLayout gl_panelLeft = new GroupLayout(panelLeft);
		gl_panelLeft.setHorizontalGroup(
			gl_panelLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panelLeft.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelLeft.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPaneUser, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(txtNickname, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(CypherComboBox, 0, 139, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelLeft.setVerticalGroup(
			gl_panelLeft.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelLeft.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtNickname, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPaneUser, GroupLayout.DEFAULT_SIZE, 258, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(CypherComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		
		UserList = new JList<String>();
		scrollPaneUser.setViewportView(UserList);
		panelLeft.setLayout(gl_panelLeft);
		
		messageField = new JTextField();
		messageField.setColumns(10);
		GroupLayout gl_panelBottom = new GroupLayout(panelBottom);
		gl_panelBottom.setHorizontalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addComponent(messageField, GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
		);
		gl_panelBottom.setVerticalGroup(
			gl_panelBottom.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelBottom.createSequentialGroup()
					.addComponent(messageField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panelBottom.setLayout(gl_panelBottom);
		panelCenter.setLayout(new CardLayout(0, 0));
		
		scrollPaneChat = new JScrollPane();
		scrollPaneChat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelCenter.add(scrollPaneChat, "name_157438100952586");
		
		chatTextArea = new JTextArea();
		chatTextArea.setEditable(false);
		scrollPaneChat.setViewportView(chatTextArea);
		frmCypherchat.getContentPane().setLayout(groupLayout);
	}
}
