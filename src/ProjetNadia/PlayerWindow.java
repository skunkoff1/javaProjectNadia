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
	private JLabel playerTitle;
	private JLabel nameLabel;
	private JLabel firstNameLabel;
	private JLabel sexLabel;
	private int ID;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public PlayerWindow(String title) {
		super(title);
		getContentPane().setBackground(new Color(40, 40, 40));
		getContentPane().setLayout(null);
		setPreferredSize(new Dimension(600, 400));
		setSize(new Dimension(600, 400));
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);	
		
		playerNameField = new JTextField();
		playerNameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerNameField.setBounds(248, 75, 293, 34);
		getContentPane().add(playerNameField);
		playerNameField.setColumns(10);
		
		playerFirstNameField = new JTextField();
		playerFirstNameField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerFirstNameField.setBounds(248, 131, 293, 34);
		getContentPane().add(playerFirstNameField);
		playerFirstNameField.setColumns(10);
		
		String[] choice = {"femme", "homme"};
		playerSexChoice = new JComboBox(choice);
		playerSexChoice.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerSexChoice.setBounds(248, 188, 293, 34);
		getContentPane().add(playerSexChoice);
		
		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelButton.setBounds(98, 284, 163, 51);
		getContentPane().add(cancelButton);

		messageLabel = new JLabel("");
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setBounds(10, 236, 564, 37);
		getContentPane().add(messageLabel);
		
		confirmButton = new JButton("Confirmer");		
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmButton.setBounds(338, 284, 163, 51);
		getContentPane().add(confirmButton);
		
		nameLabel = new JLabel("Nom");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLabel.setBounds(62, 75, 433, 34);
		getContentPane().add(nameLabel);
		
		firstNameLabel = new JLabel("Pr\u00E9nom");
		firstNameLabel.setForeground(Color.WHITE);
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		firstNameLabel.setBounds(62, 131, 408, 34);
		getContentPane().add(firstNameLabel);
		
		sexLabel = new JLabel("Sexe");
		sexLabel.setForeground(Color.WHITE);
		sexLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sexLabel.setBounds(62, 188, 389, 34);
		getContentPane().add(sexLabel);
		
		playerTitle = new JLabel();
		playerTitle.setHorizontalAlignment(SwingConstants.CENTER);
		playerTitle.setForeground(Color.WHITE);
		playerTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		playerTitle.setBounds(10, 11, 564, 53);
		getContentPane().add(playerTitle);			
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
	
	/*============== REGLAGE DE LA FENETRE EN FONCTION DU MODE ===================*/
	public void setButton(String mode) {
		if(mode.equals("ajouter")) {
			playerTitle.setText("Ajouter un joueur");
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
			playerTitle.setText("Modifier le joueur selectionn\u00E9");
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
			playerTitle.setText("Etes vous certain de vouloir supprimer ce joueur ?");
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

	/*================= SET COLOR ====================*/
	public void setColor(Color background, Color text) {
		getContentPane().setBackground(background);
		playerTitle.setForeground(text);
		nameLabel.setForeground(text);
		firstNameLabel.setForeground(text);
		sexLabel.setForeground(text);
		messageLabel.setForeground(text);
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
