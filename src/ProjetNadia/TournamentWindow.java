package ProjetNadia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;

public class TournamentWindow extends JFrame{
	
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("rawtypes")
	private JComboBox sexField;
	private JComboBox nameBox;
	private int ID;
	private JTextField yearField;
	private JLabel nameLabel;
	private JLabel yearLabel;
	private JLabel sexLabel;
	private JButton confirmButton;
	private JLabel messageLabel;
	private JLabel tournamentTitle;
	
	public TournamentWindow(String title, String label) {
		super(title);
		getContentPane().setBackground(new Color(40, 40, 40));
		getContentPane().setLayout(null);
		
		tournamentTitle = new JLabel(label);
		tournamentTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));
		tournamentTitle.setForeground(Color.WHITE);
		tournamentTitle.setHorizontalAlignment(SwingConstants.CENTER);
		tournamentTitle.setBounds(0, 11, 584, 45);
		getContentPane().add(tournamentTitle);
		
		
		yearField = new JTextField();
		yearField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		yearField.setColumns(10);
		yearField.setBounds(226, 144, 348, 37);
		getContentPane().add(yearField);
		
		String[] choice = {"femme", "homme"};
		
		String[] nameChoice = {"Australian Open", "Roland Garros", "Wimbledon", "US Open"};
		
		sexField = new JComboBox(choice);
		sexField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sexField.setBounds(226, 192, 152, 37);
		getContentPane().add(sexField);
		
		nameLabel = new JLabel("Choisir un tournoi :");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setBounds(23, 91, 501, 37);
		getContentPane().add(nameLabel);
		
		nameBox = new JComboBox(nameChoice);		
		nameBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameBox.setBounds(226, 91, 348, 37);
		getContentPane().add(nameBox);	
		
		
		yearLabel = new JLabel("Ann\u00E9e du tournoi :");
		yearLabel.setHorizontalAlignment(SwingConstants.LEFT);
		yearLabel.setForeground(Color.WHITE);
		yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		yearLabel.setBounds(23, 144, 501, 37);
		getContentPane().add(yearLabel);
		
		sexLabel = new JLabel("Type de l'\u00E9preuve :");
		sexLabel.setHorizontalAlignment(SwingConstants.LEFT);
		sexLabel.setForeground(Color.WHITE);
		sexLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sexLabel.setBounds(23, 192, 501, 37);
		getContentPane().add(sexLabel);
		
		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelButton.setBounds(93, 287, 163, 52);
		getContentPane().add(cancelButton);
		
		confirmButton = new JButton("Confirmer");
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmButton.setBounds(321, 287, 163, 52);
		getContentPane().add(confirmButton);
		
		messageLabel = new JLabel("");
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setBounds(70, 244, 441, 32);
		getContentPane().add(messageLabel);
		setPreferredSize(new Dimension(600, 400));
		setSize(new Dimension(600, 400));
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);	
	}
	/* ================== GETTER SETTER ============================*/
	public void setId(int ID) {
		this.ID = ID;
	}
	
	public void setTournamentName(String name) {
		nameBox.setSelectedItem(name);
	}	
	
	public void setTournamentSex(String sex) {
		if(sex.equals("F")) {
			sexField.setSelectedIndex(0);
		} else {
			sexField.setSelectedIndex(1);
		}
	}
	
	public void setTournamentYear(int year) {
		yearField.setText(String.valueOf(year));
	}
	
	/*============== REGLAGE DE LA FENETRE EN FONCTION DU MODE ===================*/
	public void setWindow(String mode) {
		if(mode.equals("supprimer")) {
			nameLabel.setText("Nom du tournoi : " + nameBox.getSelectedItem());
			nameBox.setVisible(false);
			yearLabel.setText("Ann?e du tournoi : " + yearField.getText());
			yearField.setVisible(false);
			sexLabel.setText("type d'?preuve : " + sexField.getSelectedItem());
			sexField.setVisible(false);
		}
	}
	
	public void setButton(String mode) {
		if(mode.equals("ajouter")) {
			this.confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					String name = (String) nameBox.getSelectedItem();	
					String year = yearField.getText();
					String sex = (String) sexField.getSelectedItem();
					String message = BddTournoi.addTournament(name, year, sex);
					messageLabel.setText(message);	
					BddTournoi.getTournament(Window.tableTournoi);
					delayCloseWindow();
				}
			});
		}
		if(mode.equals("modifier")) {
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String name = (String) nameBox.getSelectedItem();	
					String year = yearField.getText();
					String sex = (String) sexField.getSelectedItem();
					String message = BddTournoi.updateTournament(ID, name, year, sex);
					messageLabel.setText(message);
					BddTournoi.getTournament(Window.tableTournoi);
					delayCloseWindow();
				}
			});
		}
		if(mode.equals("supprimer")) {
			confirmButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String message = BddTournoi.removeTournament(ID);
					messageLabel.setText(message);	
					BddTournoi.getTournament(Window.tableTournoi);
					delayCloseWindow();
				}
			});
		}
	}
	/*================== SET COLOR =======================*/
	public void setColor(Color background, Color text) {
		getContentPane().setBackground(background);
		tournamentTitle.setForeground(text);
		nameLabel.setForeground(text);
		yearLabel.setForeground(text);
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
