package ProjetNadia;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.EventObject;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.LineBorder;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTable tablePlayer;
	private static String choice;
	private static ListeJoueur liste = new ListeJoueur();
	private JTextField searchField;
	private JTextField textField;
	static JTable tableTournoi;
	
	public Window() {
		super("Projet Nadia");		
		this.setVisible(true);
		BddConnection.connectBdd();
		
		getContentPane().setBackground(new Color(0, 0, 0));
		setPreferredSize(new Dimension(1200, 800));
		setSize(new Dimension(1200, 800));
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new CardLayout(0, 0));
		JTabbedPane container = new JTabbedPane(JTabbedPane.TOP);
		container.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(container, "name_19108795320400");
				
		DefaultTableModel model = new DefaultTableModel(
	            new Object [][] {
	            },
	            new String [] {
	                "ID", "Nom", "Prenom", "Sexe"
        });
		
		DefaultTableModel modelTournoi = new DefaultTableModel(
	            new Object [][] {
	            },
	            new String [] {
	                "Année","Nom","Type", "ID"
        });
		
		String[] comboChoice = {"les deux" , "femme", "homme" };
		
		JPanel playerTab = new JPanel();				
		playerTab.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerTab.setBackground(Color.BLACK);
		container.addTab(" Joueur ", null, playerTab, null);
		playerTab.setLayout(null);
		
		tablePlayer = new JTable(model);
		tablePlayer.setFillsViewportHeight(true);
		tablePlayer.setForeground(Color.WHITE);
		tablePlayer.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tablePlayer.setBackground(Color.DARK_GRAY);		
		tablePlayer.setBounds(0, 0, 800, 400);
		tablePlayer.getColumn("ID").setCellEditor(new nullEditor(new JCheckBox()));
		tablePlayer.getColumn("Nom").setCellEditor(new nullEditor(new JCheckBox()));
		tablePlayer.getColumn("Prenom").setCellEditor(new nullEditor(new JCheckBox()));
		tablePlayer.getColumn("Sexe").setCellEditor(new nullEditor(new JCheckBox()));
					
		JLabel errorLabel = new JLabel();
		errorLabel.setOpaque(true);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBackground(new Color(255, 0, 0));
		errorLabel.setBounds(158, 11, 870, 37);
		errorLabel.setVisible(false);
		playerTab.add(errorLabel);
		
		JScrollPane scrollPane = new JScrollPane(tablePlayer);
		scrollPane.setBounds(158, 197, 870, 517);
		playerTab.add(scrollPane);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(comboChoice);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				choice = (String) comboBox.getSelectedItem();
				liste = BddPlayer.GetPlayers(choice);
				liste.fillTab(tablePlayer);
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setForeground(Color.BLACK);
		comboBox.setBounds(157, 100, 145, 22);
		playerTab.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Choisir le sexe :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(157, 59, 145, 30);
		playerTab.add(lblNewLabel);
		
		JButton addPlayerButton = new JButton("<html><p style='text-align:center'>Ajouter<br>un joueur</p></html>");
		addPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerWindow add = new PlayerWindow("Ajouter un joueur");
				add.setButton("ajouter");
				add.setVisible(true);
			}
		});
		addPlayerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addPlayerButton.setBounds(379, 66, 159, 56);
		playerTab.add(addPlayerButton);
		
		JButton editPlayerButton = new JButton("<html><p style='text-align:center'>Editer<br>un joueur</p></html>");
		editPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tablePlayer.getSelectedRow() == -1) {
					errorLabel.setText("Pour pouvoir modifier un joueur, veuillez en selectionner un");
					errorLabel.setVisible(true);					
				} else {
					errorLabel.setVisible(false);
					int ID = (int) model.getValueAt(tablePlayer.getSelectedRow(), 0); 
					String name = (String) model.getValueAt(tablePlayer.getSelectedRow(), 1);
					String firstName = (String) model.getValueAt(tablePlayer.getSelectedRow(), 2);
					String sex = (String) model.getValueAt(tablePlayer.getSelectedRow(), 3);
					PlayerWindow modify = new PlayerWindow("éditer un joueur");
					modify.setId(ID);
					modify.setButton("modifier");
					modify.setPlayerName(name);
					modify.setPlayerFirstName(firstName);
					modify.setPlayerSex(sex);
					modify.setVisible(true);
				}				
			}			
		});
		editPlayerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		editPlayerButton.setBounds(618, 66, 159, 56);
		playerTab.add(editPlayerButton);
		
		JButton deletePlayerButton = new JButton("<html><p style='text-align:center'>Supprimer<br>un joueur</p></html>");
		deletePlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				if(tablePlayer.getSelectedRow() == -1) {
					errorLabel.setText("Pour pouvoir supprimer un joueur, veuillez en selectionner un");
					errorLabel.setVisible(true);					
				} else {
					errorLabel.setVisible(false);
					int ID = (int) model.getValueAt(tablePlayer.getSelectedRow(), 0); 
					String name = (String) model.getValueAt(tablePlayer.getSelectedRow(), 1);
					String firstName = (String) model.getValueAt(tablePlayer.getSelectedRow(), 2);
					String sex = (String) model.getValueAt(tablePlayer.getSelectedRow(), 3);
					PlayerWindow remove = new PlayerWindow("supprimer un joueur");					
					remove.setButton("supprimer");
					remove.setId(ID);
					remove.setPlayerName(name);
					remove.setPlayerFirstName(firstName);
					remove.setPlayerSex(sex);
					remove.setWindow("supprimer");
					remove.setVisible(true);
				}
			}
		});
		deletePlayerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		deletePlayerButton.setBounds(869, 66, 159, 56);
		playerTab.add(deletePlayerButton);	
				
		searchField = new JTextField();
		searchField.setBackground(Color.WHITE);
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLabel.setVisible(false);
				String search = searchField.getText();
				liste = BddPlayer.searchPlayer(search);
				int size = liste.getSize();
				if(size ==0) {
					errorLabel.setVisible(true);
					errorLabel.setText("Aucun joueur trouvé");
				}
				liste.fillTab(tablePlayer);
			}
		});
		
		searchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		searchField.setForeground(Color.BLACK);
		searchField.setBounds(316, 144, 461, 30);
		playerTab.add(searchField);
		searchField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Rechercher :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(199, 144, 120, 30);
		playerTab.add(lblNewLabel_1);
		
		JPanel tournoiTab = new JPanel();
		tournoiTab.setBackground(Color.BLACK);
		tournoiTab.setForeground(Color.WHITE);
		container.addTab(" Tournoi ", null, tournoiTab, null);
		tournoiTab.setLayout(null);
		
		JLabel errorLabelTournoi = new JLabel("");
		errorLabelTournoi.setVisible(false);
		errorLabelTournoi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		errorLabelTournoi.setBackground(Color.RED);
		errorLabelTournoi.setForeground(Color.WHITE);
		errorLabelTournoi.setOpaque(true);
		errorLabelTournoi.setBounds(153, 11, 845, 46);
		tournoiTab.add(errorLabelTournoi);		
		
		tableTournoi = new JTable(modelTournoi);
		tableTournoi.setFillsViewportHeight(true);
		tableTournoi.setForeground(Color.WHITE);
		tableTournoi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		tableTournoi.setBackground(Color.DARK_GRAY);	
		tableTournoi.setBounds(0, 0, 800, 400);
		tableTournoi.getColumn("Nom").setCellEditor(new nullEditor(new JCheckBox()));
		tableTournoi.getColumnModel().getColumn(3).setMinWidth(0);
		tableTournoi.getColumnModel().getColumn(3).setMaxWidth(0);
		tableTournoi.getColumnModel().getColumn(3).setWidth(0);
		
		if(!BddConnection.isConnected) {
			ConnectionWindow cw = new ConnectionWindow();
			cw.setVisible(true);
		}
		else {
			liste = BddPlayer.GetPlayers("");
			liste.fillTab(tablePlayer);
			BddTournoi.getTournament(tableTournoi);
		}
		
		JButton editTournoiBtn = new JButton("<html><p style='text-align:center'>Editer <br> un tournoi</p></html>");
		editTournoiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableTournoi.getSelectedRow() == -1) {
					errorLabelTournoi.setText("Pour pouvoir modifier un tournoi, veuillez en selectionner un");
					errorLabelTournoi.setVisible(true);					
				} else {
					errorLabelTournoi.setVisible(false);
					int ID = (int) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),3 );
					int year = (int) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),0);
					String name = (String) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),1);
					String sex = (String) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),2);
					TournamentWindow add = new TournamentWindow("Editer un tournoi", "Editer un tournoi");
					add.setButton("modifier");
					add.setId(ID);
					add.setTournamentName(name);
					add.setTournamentSex(sex);
					add.setTournamentYear(year);
					add.setVisible(true);					
				}	
			}
		});
		editTournoiBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		editTournoiBtn.setBounds(572, 70, 199, 65);
		tournoiTab.add(editTournoiBtn);
		
		JButton removeTournoiBtn = new JButton("<html><p style='text-align:center'>Supprimer <br> un tournoi</p></html>");
		removeTournoiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableTournoi.getSelectedRow() == -1) {
					errorLabelTournoi.setText("Pour pouvoir supprimer un tournoi, veuillez en selectionner un");
					errorLabelTournoi.setVisible(true);					
				} else {
					errorLabelTournoi.setVisible(false);
					int ID = (int) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),3 );
					int year = (int) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),0);
					String name = (String) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),1);
					String sex = (String) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),2);
					TournamentWindow add = new TournamentWindow("Supprimer un tournoi", "Supprimer un tournoi");
					add.setButton("supprimer");					
					add.setId(ID);
					add.setTournamentName(name);
					add.setTournamentSex(sex);
					add.setTournamentYear(year);
					add.setWindow("supprimer");
					add.setVisible(true);
				}
			}
		});
		removeTournoiBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		removeTournoiBtn.setBounds(799, 69, 199, 66);
		tournoiTab.add(removeTournoiBtn);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textField.setBounds(362, 145, 636, 32);
		tournoiTab.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Rechercher :");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(153, 146, 199, 31);
		tournoiTab.add(lblNewLabel_6);		
		
		JButton addTournoiBtn = new JButton("<html><p style='text-align:center'>Ajouter <br> un tournoi</p></html>");
		addTournoiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TournamentWindow add = new TournamentWindow("Ajouter un tournoi", "Ajouter un tournoi");
				add.setButton("ajouter");
				add.setVisible(true);
			}
		});
		addTournoiBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addTournoiBtn.setBounds(358, 69, 188, 67);
		tournoiTab.add(addTournoiBtn);
		
		JScrollPane scrollPane_1 = new JScrollPane(tableTournoi);
		scrollPane_1.setForeground(Color.WHITE);
		scrollPane_1.setBackground(Color.DARK_GRAY);
		scrollPane_1.setBounds(153, 210, 845, 504);
		tournoiTab.add(scrollPane_1);
		
		JButton displayTournoiBtn = new JButton("<html><p style='text-align:center'>Afficher <br> les tournois</p></html>");
		displayTournoiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BddTournoi.getTournament(tableTournoi);
			}
		});
		displayTournoiBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		displayTournoiBtn.setBounds(153, 68, 188, 67);
		tournoiTab.add(displayTournoiBtn);
		
		JPanel matchTab = new JPanel();
		matchTab.setFont(new Font("Tahoma", Font.PLAIN, 20));
		container.addTab(" Match ", null, matchTab, null);
		matchTab.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(47, 54, 269, 135);
		matchTab.add(btnNewButton_1);
		
		JPanel optionTab = new JPanel();
		optionTab.setBackground(Color.BLACK);
		container.addTab(" Options ", null, optionTab, null);
		optionTab.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("  Options Graphiques");
		lblNewLabel_2.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblNewLabel_2.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2.setBounds(29, 11, 347, 41);
		optionTab.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Style de police");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(39, 166, 132, 35);
		optionTab.add(lblNewLabel_3);
		
		JLabel lblNewLabel_3_1 = new JLabel("Taille de police");
		lblNewLabel_3_1.setForeground(Color.WHITE);
		lblNewLabel_3_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(39, 106, 132, 35);
		optionTab.add(lblNewLabel_3_1);
		
		@SuppressWarnings("rawtypes")
		JComboBox fontSizeBox = new JComboBox();
		fontSizeBox.setBounds(249, 115, 127, 27);
		optionTab.add(fontSizeBox);
		
		@SuppressWarnings("rawtypes")
		JComboBox fontStyleBox = new JComboBox();
		fontStyleBox.setBounds(249, 175, 127, 27);
		optionTab.add(fontStyleBox);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setOpaque(true);
		lblNewLabel_4.setBounds(582, 0, 1, 733);
		optionTab.add(lblNewLabel_4);
		
		JLabel lblNewLabel_2_1 = new JLabel("  Options on verra");
		lblNewLabel_2_1.setForeground(Color.WHITE);
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_2_1.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		lblNewLabel_2_1.setAlignmentX(0.5f);
		lblNewLabel_2_1.setBounds(641, 11, 347, 41);
		optionTab.add(lblNewLabel_2_1);		
	}

	public void setListe(ListeJoueur liste) {
		this.liste = liste;
	}

	public static JTable getTable() {
		return tablePlayer;
	}
	
	public void setTable(JTable table) {
		this.tablePlayer = table;
	}        

	public static String getChoice() {
		return choice;
	}
	
	public static void getPlayers() {
		liste = BddPlayer.GetPlayers("");
		liste.fillTab(tablePlayer);
	}
	
	public void closeWindow() {
		this.dispose();
	}
	
	private class nullEditor extends DefaultCellEditor {
        /**
       * 
       */
      private static final long serialVersionUID = 1L;
      public nullEditor(JCheckBox checkBox) {
         super(checkBox);
        }
        @Override
        public boolean isCellEditable(EventObject anEvent) {
          return false;
        }
  }
}
