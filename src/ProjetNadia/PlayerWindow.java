package ProjetNadia;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class PlayerWindow extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField playerNameField;
	private JTextField playerFirstNameField;
	@SuppressWarnings("rawtypes")
	private JComboBox playerSexChoice;
	private JButton confirmButton;
	private JLabel messageLabel;
	private JLabel lblNewLabel_1;
	private JLabel nameLabel;
	private JLabel firstNameLabel;
	private JLabel sexLabel;
	private int ID;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PlayerWindow(String title) {
		super(title);
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

		messageLabel = new JLabel("");
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setBounds(10, 215, 564, 37);
		getContentPane().add(messageLabel);
		
		confirmButton = new JButton("Confirmer");		
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmButton.setBounds(338, 263, 205, 72);
		getContentPane().add(confirmButton);
		
		nameLabel = new JLabel("Nom");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLabel.setBounds(64, 54, 433, 34);
		getContentPane().add(nameLabel);
		
		firstNameLabel = new JLabel("Pr\u00E9nom");
		firstNameLabel.setForeground(Color.WHITE);
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		firstNameLabel.setBounds(64, 110, 408, 34);
		getContentPane().add(firstNameLabel);
		
		sexLabel = new JLabel("Sexe");
		sexLabel.setForeground(Color.WHITE);
		sexLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sexLabel.setBounds(64, 167, 389, 34);
		getContentPane().add(sexLabel);
		
		lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 11, 564, 34);
		getContentPane().add(lblNewLabel_1);			
	}
	
	public int getID() {
		return ID;
	}

	/* SETTER DE LA FENETRE -> RECUPERATION INFO JOUEUR LORS DE L'OUVERTURE */
	public void setId(int ID) {
		this.ID = ID;
	}
	
	public void setPlayerName(String name) {
		playerNameField.setText(name);
	}
	
	public void setWindow(String mode) {
		if(mode.equals("supprimer")) {
			nameLabel.setText("Nom : " + playerNameField.getText());
			playerNameField.setVisible(false);
			firstNameLabel.setText("Prenom : " + playerFirstNameField.getText());
			playerFirstNameField.setVisible(false);
			sexLabel.setText("Sexe : " + playerSexChoice.getSelectedItem());
			playerSexChoice.setVisible(false);
		}
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
	public void setButton(String mode) {
		if(mode.equals("ajouter")) {
			lblNewLabel_1.setText("Ajouter un joueur");
			this.confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = playerNameField.getText();
					String firstName = playerFirstNameField.getText();
					String sex = (String) playerSexChoice.getSelectedItem();				
					String message = BddPlayer.addPlayer(name, firstName, sex);
					messageLabel.setText(message);		
					ListeJoueur updatedListe = new ListeJoueur();
					String choice = Window.getChoice();
					updatedListe = BddPlayer.GetPlayers(choice);
					updatedListe.fillTab(Window.getTable());
					delayCloseWindow();
				}
			});
		}
		if(mode.equals("modifier")) {
			lblNewLabel_1.setText("Modifier le joueur selectionn\u00E9");
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = playerNameField.getText();
					String firstName = playerFirstNameField.getText();
					String sex = (String) playerSexChoice.getSelectedItem();
					String message = BddPlayer.updatePlayer(ID, name, firstName, sex);
					messageLabel.setText(message);	
					ListeJoueur updatedListe = new ListeJoueur();
					String choice = Window.getChoice();
					updatedListe = BddPlayer.GetPlayers(choice);
					updatedListe.fillTab(Window.getTable());
					delayCloseWindow();
				}
			});
		}
		if(mode.equals("supprimer")) {
			lblNewLabel_1.setText("Etes vous certain de vouloir supprimer ce joueur ?");
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String message = BddPlayer.removePlayer(ID);
					messageLabel.setText(message);	
					ListeJoueur updatedListe = new ListeJoueur();
					String choice = Window.getChoice();
					updatedListe = BddPlayer.GetPlayers(choice);
					updatedListe.fillTab(Window.getTable());
					delayCloseWindow();
				}
			});
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
		timer.schedule(task, 1500);
		
	}
}
