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
	
	public ConnectionWindow() {
		super("Connection base de données");
		getContentPane().setBackground(new Color(0, 0, 0));
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
		urlField.setBounds(10, 84, 564, 29);
		getContentPane().add(urlField);
		urlField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Url de la base de donn\u00E9es");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(10, 54, 210, 29);
		getContentPane().add(lblNewLabel_1);
		
		nameField = new JTextField();
		nameField.setText(BddConnection.getLogin());
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		nameField.setColumns(10);
		nameField.setBounds(10, 154, 564, 29);
		getContentPane().add(nameField);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nom d'utilisateur");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(10, 124, 210, 29);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel passwordLabel = new JLabel("Mot de passe");
		passwordLabel.setForeground(Color.WHITE);
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordLabel.setBounds(10, 196, 210, 29);
		getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(10, 222, 564, 29);
		getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Connection");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BddConnection.setUrl(urlField.getText());
				BddConnection.setLogin(nameField.getText());
				BddConnection.setPassword(passwordField.getText());
				BddConnection.connectBdd();
				Window.getPlayers();
				closeWindow();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(218, 288, 124, 46);
		getContentPane().add(btnNewButton);
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
