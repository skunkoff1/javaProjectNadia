package ProjetNadia;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class AddPlayerWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTextField playerNameField;
	private JTextField playerFirstNameField;
	@SuppressWarnings("rawtypes")
	private JComboBox playerSexChoice;
	private int ID;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public AddPlayerWindow() {
		super("Ajouter un joueur");
		getContentPane().setBackground(new Color(0, 0, 0));
		getContentPane().setLayout(null);
		setPreferredSize(new Dimension(600, 400));
		setSize(new Dimension(600, 400));
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);	
		
		playerNameField = new JTextField();
		playerNameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerNameField.setBounds(250, 54, 293, 34);
		getContentPane().add(playerNameField);
		playerNameField.setColumns(10);
		
		playerFirstNameField = new JTextField();
		playerFirstNameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerFirstNameField.setBounds(250, 110, 293, 34);
		getContentPane().add(playerFirstNameField);
		playerFirstNameField.setColumns(10);
		
		String[] choice = {"femme", "homme"};
		playerSexChoice = new JComboBox(choice);
		playerSexChoice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerSexChoice.setBounds(250, 167, 293, 34);
		getContentPane().add(playerSexChoice);
		
		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelButton.setBounds(64, 263, 197, 72);
		getContentPane().add(cancelButton);
		
		JLabel messageLabel = new JLabel("");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setBounds(10, 214, 564, 38);
		getContentPane().add(messageLabel);
		
		JButton confirmButton = new JButton("Ajouter");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = playerNameField.getText();
				String firstName = playerFirstNameField.getText();
				String sex = (String) playerSexChoice.getSelectedItem();				
				String message = Bdd.addPlayer(name, firstName, sex);
				messageLabel.setText(message);		
				ListeJoueur updatedListe = new ListeJoueur();
				String choice = Window.getChoice();
				updatedListe = Bdd.GetPlayers(choice);
				updatedListe.fillTab(Window.getTable());
				delayCloseWindow();
			}
		});
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmButton.setBounds(338, 263, 205, 72);
		getContentPane().add(confirmButton);
		
		JLabel lblNewLabel = new JLabel("Nom");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(64, 54, 141, 34);
		getContentPane().add(lblNewLabel);
		
		JLabel lblPrnom = new JLabel("Pr\u00E9nom");
		lblPrnom.setForeground(Color.WHITE);
		lblPrnom.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPrnom.setBounds(64, 110, 141, 34);
		getContentPane().add(lblPrnom);
		
		JLabel lblNewLabel_1_1 = new JLabel("Sexe");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(64, 167, 141, 34);
		getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1 = new JLabel("Ajouter un joueur");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 11, 564, 34);
		getContentPane().add(lblNewLabel_1);		
	}
	
	/* SETTER DE LA FENETRE -> RECUPERATION INFO JOUEUR LORS DE L'OUVERTURE */
	public void setId(int ID) {
		this.ID = ID;
		System.out.println(this.ID);
	}
	
	public void setPlayerName(String name) {
		playerNameField.setText(name);
	}
	
	public void setPlayerFirstName(String firstName) {
		playerFirstNameField.setText(firstName);
	}
	
	public void setPlayerSex(String sex) {
		if(sex.equals("F")) {
			playerSexChoice.setSelectedIndex(0);
		} else {
			playerSexChoice.setSelectedIndex(1);
		}
	}
	
	/* METHODE DE LA FENETRE */
	public void closeWindow() {
		this.dispose();
	}
	
	public void delayCloseWindow() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
		      public void run()
		      {
		        closeWindow();
		      }
		    };
		timer.schedule(task, 2000);
		
	}
}


