package ProjetNadia;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.ActionEvent;

public class RemovePlayerWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JLabel nameLabel;
	private JLabel firstNameLabel;
	private JLabel sexLabel;
	private int ID;

	public RemovePlayerWindow() {
		super("Ajouter un joueur");
		getContentPane().setBackground(new Color(0, 0, 0));
		getContentPane().setLayout(null);
		setPreferredSize(new Dimension(600, 400));
		setSize(new Dimension(600, 400));
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		JLabel lblNewLabel = new JLabel("Etes vous certain de vouloir supprimer ce joueur ?");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(10, 11, 564, 38);
		getContentPane().add(lblNewLabel);
		
		nameLabel = new JLabel("Nom : ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setBounds(10, 59, 564, 38);
		getContentPane().add(nameLabel);
		
		firstNameLabel = new JLabel("Pr\u00E9nom : ");
		firstNameLabel.setForeground(Color.WHITE);
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		firstNameLabel.setBounds(10, 109, 564, 38);
		getContentPane().add(firstNameLabel);
		
		sexLabel = new JLabel("Sexe  :  ");
		sexLabel.setForeground(Color.WHITE);
		sexLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sexLabel.setBounds(10, 158, 564, 38);
		getContentPane().add(sexLabel);
		
		JLabel messageLabel = new JLabel("");
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		messageLabel.setBounds(10, 220, 564, 39);
		getContentPane().add(messageLabel);
		
		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelButton.setBounds(60, 270, 198, 68);
		getContentPane().add(cancelButton);
		
		JButton confirmButton = new JButton("Confirmer");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String message = Bdd.removePlayer(ID);
				messageLabel.setText(message);	
				ListeJoueur updatedListe = new ListeJoueur();
				String choice = Window.getChoice();
				updatedListe = Bdd.GetPlayers(choice);
				updatedListe.fillTab(Window.getTable());
				delayCloseWindow();
			}
		});
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmButton.setBounds(308, 270, 198, 68);
		getContentPane().add(confirmButton);
		
		
		
	}
	public void setId(int ID) {
		this.ID = ID;
	}
	
	public void setPlayerName(String name) {
		nameLabel.setText("Nom : " + name);
	}
	
	public void setPlayerFirstName(String firstName) {
		firstNameLabel.setText("Prénom : " + firstName);
	}
	
	public void setPlayerSex(String sex) {
		if(sex.equals("F")) {
			sexLabel.setText("Sexe : femme");
		} else {
			sexLabel.setText("Sexe : homme");
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
