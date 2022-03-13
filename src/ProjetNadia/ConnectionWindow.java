package ProjetNadia;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConnectionWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField urlField;
	private JTextField nameField;
	private JPasswordField passwordField;
	private JLabel messageLabel;
	
	public ConnectionWindow() {
		super("Connection base de données");
		getContentPane().setBackground(new Color(40, 40, 40));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Param\u00E8tres de connection");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 564, 36);
		getContentPane().add(lblNewLabel);
		
		urlField = new JTextField();
		urlField.setText(BddConnection.getUrl());
		urlField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		urlField.setBounds(10, 110, 564, 29);
		getContentPane().add(urlField);
		urlField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Url de la base de donn\u00E9es");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 80, 210, 29);
		getContentPane().add(lblNewLabel_1);
		
		nameField = new JTextField();
		nameField.setText(BddConnection.getLogin());
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBounds(10, 180, 564, 29);
		getContentPane().add(nameField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nom d'utilisateur");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 150, 210, 29);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel passwordLabel = new JLabel("Mot de passe");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordLabel.setBounds(10, 222, 210, 29);
		getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 248, 564, 29);
		passwordField.setText(BddConnection.getPassword());
		getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Connection");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BddConnection.setUrl(urlField.getText());
				BddConnection.setLogin(nameField.getText());
				BddConnection.setPassword(passwordField.getText());
				BddConnection.connectBdd();
				Window.getDatas();
				closeWindow();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(215, 304, 124, 46);
		getContentPane().add(btnNewButton);
		
		messageLabel = new JLabel("La connection \u00E0 la base de donn\u00E9es a \u00E9chou\u00E9");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setForeground(Color.RED);
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		messageLabel.setBounds(10, 43, 564, 29);
		if(BddConnection.isConnected) {
			messageLabel.setVisible(false);
		} else {
			messageLabel.setVisible(true);
		}
		
		
		getContentPane().add(messageLabel);
		setPreferredSize(new Dimension(600, 400));
		setSize(new Dimension(600, 400));
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
	}
	

	/* METHODE DE LA FENETRE */
	public void closeWindow() {
		this.dispose();
	}
}
