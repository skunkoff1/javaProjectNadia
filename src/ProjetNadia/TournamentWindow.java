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
	
	public TournamentWindow(String title, String label) {
		super(title);
		getContentPane().setBackground(new Color(0, 0, 0));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel(label);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 11, 584, 32);
		getContentPane().add(lblNewLabel);
		
		
		yearField = new JTextField();
		yearField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		yearField.setColumns(10);
		yearField.setBounds(226, 120, 348, 37);
		getContentPane().add(yearField);
		
		String[] choice = {"femme", "homme"};
		
		String[] nameChoice = {"Australian Open", "Roland Garros", "Wimbledon", "US Open"};
		
		sexField = new JComboBox(choice);
		sexField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sexField.setBounds(226, 178, 152, 37);
		getContentPane().add(sexField);
		
		nameLabel = new JLabel("Choisir un tournoi :");
		nameLabel.setForeground(Color.WHITE);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameLabel.setBounds(23, 67, 501, 37);
		getContentPane().add(nameLabel);
		
		nameBox = new JComboBox(nameChoice);		
		nameBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		nameBox.setBounds(226, 67, 348, 37);
		getContentPane().add(nameBox);	
		
		
		yearLabel = new JLabel("Ann\u00E9e du tournoi :");
		yearLabel.setHorizontalAlignment(SwingConstants.LEFT);
		yearLabel.setForeground(Color.WHITE);
		yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		yearLabel.setBounds(23, 120, 501, 37);
		getContentPane().add(yearLabel);
		
		sexLabel = new JLabel("Type de l'\u00E9preuve :");
		sexLabel.setHorizontalAlignment(SwingConstants.LEFT);
		sexLabel.setForeground(Color.WHITE);
		sexLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sexLabel.setBounds(23, 178, 501, 37);
		getContentPane().add(sexLabel);
		
		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelButton.setBounds(70, 275, 190, 64);
		getContentPane().add(cancelButton);
		
		confirmButton = new JButton("Confirmer");
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmButton.setBounds(321, 275, 190, 64);
		getContentPane().add(confirmButton);
		
		messageLabel = new JLabel("");
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setBounds(70, 232, 441, 32);
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
	
	public void setWindow(String mode) {
		if(mode.equals("supprimer")) {
			nameLabel.setText("Nom du tournoi : " + nameBox.getSelectedItem());
			nameBox.setVisible(false);
			yearLabel.setText("Année du tournoi : " + yearField.getText());
			yearField.setVisible(false);
			sexLabel.setText("type d'épreuve : " + sexField.getSelectedItem());
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
