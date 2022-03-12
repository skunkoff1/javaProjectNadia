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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* ====================== ATTRIBUTS ==========================*/
	private static JTable tablePlayer;
	private static String choice;
	private static ListeJoueur liste = new ListeJoueur();
	private JTextField searchField;
	private JTextField searchTournoi;
	public static JTable tableTournoi;
	private static JComboBox yearBox;
	static JTable tableEpreuve;
	private JLabel finalText;
	private JLabel winnerText;
	private JLabel winnerLabel;
	private JLabel finalLabel;
	
	public Window() {
		
		/* ================== PROPRIETES GENERALES DE LA FENETRE ======================*/
		super("Projet Nadia");		
		this.setVisible(true);
				
		getContentPane().setBackground(new Color(40, 40, 40));
		setPreferredSize(new Dimension(1200, 800));
		setSize(new Dimension(1200, 800));
		setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		getContentPane().setLayout(new CardLayout(0, 0));
		JTabbedPane container = new JTabbedPane(JTabbedPane.TOP);
		container.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(container, "name_19108795320400");
		
		/*================ MODELES DES TABLEAUX ===========================*/
 				
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
	                "Année","Nom","Type d'épreuve", "ID"
        });
		
		DefaultTableModel modelEpreuve = new DefaultTableModel(
	            new Object [][] {
	            },
	            new String [] {
	                "Nom","Prénom", "Tournoi", "Statut"
        });
		
		/*======================= CHOIX POUR LES COMBOBOX =======================*/
		String[] comboChoice = {"les deux" , "femme", "homme" };		
		String[] typeChoice = {"femme", "homme"};
		
		/*============================ TAB JOUEUR ===============================*/		
		JPanel playerTab = new JPanel();				
		playerTab.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerTab.setBackground(new Color(40, 40, 40));
		container.addTab(" Joueur ", null, playerTab, null);
		playerTab.setLayout(null);
		
		tablePlayer = new JTable(model);
		tablePlayer.setFillsViewportHeight(true);
		tablePlayer.setForeground(Color.WHITE);
		tablePlayer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablePlayer.setBackground(Color.DARK_GRAY);		
		tablePlayer.setBounds(0, 0, 800, 400);
		tablePlayer.getColumn("ID").setCellEditor(new nullEditor(new JCheckBox()));
		tablePlayer.getColumn("Nom").setCellEditor(new nullEditor(new JCheckBox()));
		tablePlayer.getColumn("Prenom").setCellEditor(new nullEditor(new JCheckBox()));
		tablePlayer.getColumn("Sexe").setCellEditor(new nullEditor(new JCheckBox()));
		tablePlayer.getColumnModel().getColumn(0).setWidth(50);	
		tablePlayer.getColumnModel().getColumn(0).setMinWidth(50);	
		tablePlayer.getColumnModel().getColumn(0).setMaxWidth(50);	
		tablePlayer.getColumnModel().getColumn(3).setWidth(50);	
		tablePlayer.getColumnModel().getColumn(3).setMinWidth(50);	
		tablePlayer.getColumnModel().getColumn(3).setMaxWidth(50);	
		tablePlayer.setRowHeight(20);
					
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
		searchField.setBounds(367, 144, 661, 30);
		playerTab.add(searchField);
		searchField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Rechercher un joueur :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(158, 144, 220, 30);
		playerTab.add(lblNewLabel_1);
		
		/*=========================== TAB TOUNROI ==================================*/
		JPanel tournoiTab = new JPanel();
		tournoiTab.setBackground(new Color(40, 40, 40));
		tournoiTab.setForeground(Color.WHITE);
		container.addTab(" Tournoi ", null, tournoiTab, null);
		tournoiTab.setLayout(null);
		
		JLabel errorLabelTournoi = new JLabel("");
		errorLabelTournoi.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabelTournoi.setVisible(false);
		errorLabelTournoi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		errorLabelTournoi.setBackground(Color.RED);
		errorLabelTournoi.setForeground(Color.WHITE);
		errorLabelTournoi.setOpaque(true);
		errorLabelTournoi.setBounds(153, 11, 845, 46);
		tournoiTab.add(errorLabelTournoi);		

		winnerText = new JLabel("");
		winnerText.setForeground(Color.WHITE);
		winnerText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		winnerText.setBounds(849, 344, 270, 46);
		tournoiTab.add(winnerText);
		
		finalText = new JLabel("");
		finalText.setForeground(Color.WHITE);
		finalText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		finalText.setBounds(849, 458, 270, 46);
		tournoiTab.add(finalText);
		
		tableTournoi = new JTable(modelTournoi);
		tableTournoi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int ID = (int)modelTournoi.getValueAt(tableTournoi.getSelectedRow(),3);
				BddTournoi.getPlayers(ID, winnerText, finalText);
			}
		});
		tableTournoi.setFillsViewportHeight(true);
		tableTournoi.setForeground(Color.WHITE);
		tableTournoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableTournoi.setBackground(Color.DARK_GRAY);	
		tableTournoi.setBounds(0, 0, 800, 400);
		tableTournoi.getColumn("Nom").setCellEditor(new nullEditor(new JCheckBox()));
		tableTournoi.getColumn("Année").setCellEditor(new nullEditor(new JCheckBox()));
		tableTournoi.getColumn("Type d'épreuve").setCellEditor(new nullEditor(new JCheckBox()));
		tableTournoi.getColumnModel().getColumn(3).setMinWidth(0);
		tableTournoi.getColumnModel().getColumn(3).setMaxWidth(0);
		tableTournoi.getColumnModel().getColumn(3).setWidth(0);
		tableTournoi.getColumnModel().getColumn(0).setMinWidth(120);
		tableTournoi.getColumnModel().getColumn(0).setMaxWidth(120);
		tableTournoi.getColumnModel().getColumn(0).setWidth(120);	
		tableTournoi.getColumnModel().getColumn(2).setMinWidth(120);
		tableTournoi.getColumnModel().getColumn(2).setMaxWidth(120);
		tableTournoi.getColumnModel().getColumn(2).setWidth(120);	
		tableTournoi.setRowHeight(20);
		
		JButton addTournoiBtn = new JButton("<html><p style='text-align:center'>Ajouter <br> un tournoi</p></html>");
		addTournoiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TournamentWindow add = new TournamentWindow("Ajouter un tournoi", "Ajouter un tournoi");
				add.setButton("ajouter");
				add.setVisible(true);
			}
		});
		addTournoiBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addTournoiBtn.setBounds(153, 68, 166, 58);
		tournoiTab.add(addTournoiBtn);
		
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
		editTournoiBtn.setBounds(489, 68, 166, 58);
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
		removeTournoiBtn.setBounds(832, 69, 166, 58);
		tournoiTab.add(removeTournoiBtn);
		
		searchTournoi = new JTextField();
		searchTournoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("wesh");
				errorLabelTournoi.setVisible(false);
				String search = searchTournoi.getText();
				BddTournoi.searchTournament(search, tableTournoi);
			}
		});
		searchTournoi.setFont(new Font("Tahoma", Font.PLAIN, 18));
		searchTournoi.setBounds(362, 156, 636, 32);
		tournoiTab.add(searchTournoi);
		searchTournoi.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Rechercher un tournoi :");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setBounds(153, 157, 199, 31);
		tournoiTab.add(lblNewLabel_6);		
				
		JScrollPane scrollPane_1 = new JScrollPane(tableTournoi);
		scrollPane_1.setForeground(Color.WHITE);
		scrollPane_1.setBackground(Color.DARK_GRAY);
		scrollPane_1.setBounds(153, 210, 638, 504);
		tournoiTab.add(scrollPane_1);
		
		winnerLabel = new JLabel("Vainqueur : ");
		winnerLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		winnerLabel.setForeground(Color.WHITE);
		winnerLabel.setBounds(813, 296, 208, 37);
		tournoiTab.add(winnerLabel);
		
		finalLabel = new JLabel("Finaliste :");
		finalLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		finalLabel.setForeground(Color.WHITE);
		finalLabel.setBounds(813, 401, 208, 46);
		tournoiTab.add(finalLabel);
				
		JLabel lblNewLabel_7 = new JLabel("<html><p>Cliquer sur un tournoi<br> pour afficher les infos</p></html>");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_7.setBounds(813, 210, 199, 75);
		tournoiTab.add(lblNewLabel_7);

		
		/*======================== TAB EPREUVE ==============================================*/
		JPanel epreuveTab = new JPanel();
		epreuveTab.setBackground(new Color(40, 40, 40));
		container.addTab(" Epreuve ", null, epreuveTab, null);
		epreuveTab.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("Ann\u00E9e :");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setBounds(131, 53, 84, 32);
		epreuveTab.add(lblNewLabel_5);
		
		yearBox = new JComboBox();
		yearBox.setBounds(238, 53, 170, 32);
		epreuveTab.add(yearBox);
		
		JLabel lblNewLabel_5_2 = new JLabel("Type d'\u00E9preuve :");
		lblNewLabel_5_2.setForeground(Color.WHITE);
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_5_2.setBounds(472, 53, 163, 32);
		epreuveTab.add(lblNewLabel_5_2);
		
		JComboBox sexBoxEpreuve = new JComboBox(typeChoice);
		sexBoxEpreuve.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sexBoxEpreuve.setBounds(658, 53, 170, 32);
		epreuveTab.add(sexBoxEpreuve);
		
		JButton btnNewButton = new JButton("Rechercher");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String year = (String) yearBox.getSelectedItem();
				String sex = (String) sexBoxEpreuve.getSelectedItem();
				BddEpreuve.getPlayers(year, sex, tableEpreuve);
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(901, 53, 177, 32);
		epreuveTab.add(btnNewButton);

		tableEpreuve = new JTable(modelEpreuve);
		tableEpreuve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableEpreuve.setForeground(Color.WHITE);
		tableEpreuve.setBackground(Color.DARK_GRAY);
		tableEpreuve.setFillsViewportHeight(true);
		tableEpreuve.getColumn("Nom").setCellEditor(new nullEditor(new JCheckBox()));
		tableEpreuve.getColumn("Prénom").setCellEditor(new nullEditor(new JCheckBox()));
		tableEpreuve.getColumn("Tournoi").setCellEditor(new nullEditor(new JCheckBox()));
		tableEpreuve.setRowHeight(20);
		
		JScrollPane scrollPane_2 = new JScrollPane(tableEpreuve);
		scrollPane_2.setBounds(131, 141, 947, 573);
		epreuveTab.add(scrollPane_2);
				
		/*=============================== TAB MATCH ======================================*/
		JPanel matchTab = new JPanel();
		matchTab.setFont(new Font("Tahoma", Font.PLAIN, 20));
		container.addTab(" Match ", null, matchTab, null);
		matchTab.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(47, 54, 269, 135);
		matchTab.add(btnNewButton_1);
		
		/*=========================== TAB OPTION ========================================*/
		JPanel optionTab = new JPanel();
		optionTab.setBackground(new Color(40, 40, 40));
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
		
		/* ====================== TEST CONNECTION BASE DE DONNEES ==============================*/
		BddConnection.connectBdd();
		// Si connection invalide, ouverture de la fenetre de connection
		if(!BddConnection.isConnected) {
			ConnectionWindow cw = new ConnectionWindow();
			cw.setVisible(true);
		}
		// Sinon, remplissage des tableaux, des box
		else {
			getDatas();
		}
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
	
	public static void getDatas() {		
		liste = BddPlayer.GetPlayers("");
		liste.fillTab(tablePlayer);
		BddTournoi.getTournament(tableTournoi);
		BddEpreuve.getYear(yearBox);
	}
	
	/*======================= FONCTION CLOSE WINDOW =====================*/
	public void closeWindow() {
		this.dispose();
	}
	
	/* ========================= CLASS POUR RENDRE LES CELLULES DU TABLEAU NON EDITABLES ==============*/
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
