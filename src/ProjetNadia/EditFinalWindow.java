package ProjetNadia;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EditFinalWindow extends JFrame{
	
	private JLabel infoTournoiLabel;
	private static JComboBox finalBox;
	private static JComboBox winnerBox;
	private int tournamentID;
	private int winnerID;
	private int finalistID;
	private JLabel messageLabel;
	
	public EditFinalWindow() {
		super("Editer infos");
		getContentPane().setBackground(new Color(40, 40, 40));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Editer les infos du tournoi :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 11, 564, 31);
		getContentPane().add(lblNewLabel);
		
		infoTournoiLabel = new JLabel("");
		infoTournoiLabel.setForeground(Color.WHITE);
		infoTournoiLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoTournoiLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		infoTournoiLabel.setBounds(10, 53, 564, 31);
		getContentPane().add(infoTournoiLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Vainqueur :");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 119, 205, 36);
		getContentPane().add(lblNewLabel_1);
		
		winnerBox = new JComboBox();
		winnerBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		winnerBox.setBounds(172, 119, 402, 36);
		getContentPane().add(winnerBox);
		
		JLabel lblNewLabel_1_1 = new JLabel("Finaliste :");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 179, 205, 36);
		getContentPane().add(lblNewLabel_1_1);
		
		finalBox = new JComboBox();
		finalBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		finalBox.setBounds(172, 179, 402, 36);
		getContentPane().add(finalBox);
		
		messageLabel = new JLabel("<html><p>Si le joueur n'apparait pas dans la liste,<br> ajoutez le d'abord dans l'onglet joueur</p></html>");
		messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		messageLabel.setForeground(Color.WHITE);
		messageLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		messageLabel.setBounds(10, 234, 564, 61);
		getContentPane().add(messageLabel);
		
		JButton cancelButton = new JButton("Annuler");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				closeWindow();
			}
		});
		cancelButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		cancelButton.setBounds(79, 306, 163, 44);
		getContentPane().add(cancelButton);
		
		JButton confirmButton = new JButton("Confirmer");
		confirmButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String finalist = (String) finalBox.getSelectedItem();
				String finalistArray[] = finalist.split(" ");
				String winner = (String) winnerBox.getSelectedItem();
				String winnerArray[] = winner.split(" ");
				String message = BddTournoi.updateTournamentInfo(tournamentID, finalistArray, winnerArray);
				messageLabel.setText(message);
				delayCloseWindow();
			}
		});
		confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		confirmButton.setBounds(324, 306, 163, 44);
		getContentPane().add(confirmButton);
		setPreferredSize(new Dimension(600, 400));
		setSize(new Dimension(600, 400));
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);	
	}
	/*=================== GETTERS / SETTERS =================================*/

	public static JComboBox getFinalBox() {
		return finalBox;
	}
	public static JComboBox getWinnerBox() {
		return winnerBox;
	}
	
	public int getTournamentID() {
		return tournamentID;
	}

	public void setTournamentID(int tournamentID) {
		this.tournamentID = tournamentID;
	}

	public int getWinnerID() {
		return winnerID;
	}

	public void setWinnerID(int winnerID) {
		this.winnerID = winnerID;
	}

	public int getFinalistID() {
		return finalistID;
	}

	public void setFinalistID(int finalistID) {
		this.finalistID = finalistID;
	}

	/*================= RECUPERER INFOS TOURNOIS =========================*/
	public void setInfoLabel(String name, int year, String sex) {
		if(sex.equals("H")) {
			sex = "homme";
		} else {
			sex = "femme";
		}
		this.infoTournoiLabel.setText(name + " " + year + ", épreuve " + sex);
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
