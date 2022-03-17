package ProjetNadia;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.EventObject;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.border.LineBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/* ====================== ATTRIBUTS ==========================*/	
	private static String choice;
	private static ListeJoueur liste = new ListeJoueur();
	
	/*========= TAB PLAYER ==========*/
	private JPanel playerTab;
	private static JTable tablePlayer;
	private JLabel playerSexLabel;
	private JLabel playerSearchLabel;	
	private JButton addPlayerButton;
	private JButton editPlayerButton;
	private JButton deletePlayerButton;
	private JLabel errorLabel;
	private static JComboBox comboBox;
	private JTextField searchField;
	
	/*========= TAB TOURNOI =========*/
	private JPanel tournoiTab;
	public static JTable tableTournoi;
	private JLabel finalText;
	private JLabel winnerText;
	private JLabel winnerLabel;
	private JLabel finalLabel;
	private JLabel infoLabel;
	private JLabel tournoiSearchLabel;
	private JButton addTournoiBtn;
	private JButton editTournoiBtn;
	private JButton removeTournoiBtn;
	private JTextField searchTournoi;
	private JButton editInfoBtn;
	private JLabel errorLabelTournoi;
	
	/*========== TAB EPREUVE ==========*/
	private JPanel epreuveTab;
	static JTable tableEpreuve;
	private JLabel epreuveYearLabel;
	private JLabel epreuveSexLabel;
	private static JComboBox yearBox;
	private static JComboBox sexBoxEpreuve;
	private JButton searchEpreuveBtn;
	
	/*=========== TAB MATCH ==============*/
	private JPanel matchTab;
	private static JTable tableMatch;
	private JRadioButton finalRadioBtn;
	private JRadioButton winnerRadioBtn;
	private JRadioButton allRadioBtn;
	private JLabel searchMatchLabel;
	private JButton validateBtn;
	
	/*=========== LABELS OPTION TAB =============*/
	private JPanel optionTab;
	private JLabel optionTitle1;
	private JLabel optionTitle2;
	private JLabel fontSizeLabel;
	private JLabel fontStyleLabel;
	private JLabel colorThemeLabel;
	private JLabel connectionLabel;
	private static JComboBox fontSizeBox;
	private static JComboBox fontStyleBox;
	private static JComboBox colorThemeBox;
	private JButton connectionBtn;
	private JButton applyStyleBtn;
	
	/*=============== THEME COULEUR / PAR DEFAUT -> THEME SOMBRE ==================*/
	private Color backGroundColor = new Color(40,40,40);
	private Color backGroundTab = new Color(70,70,70);
	private Color textColor1 = new Color(0,0,0);
	private Color textColor2 = new Color(255,255,255);	
	
	/*======================== TAILLE ET STYLE POLICE =============================*/
	private Font font1;
	private Font font2;
	private Font font3;
	private JTextField searchMatch;
	
	public Window() {
		
		/* ================== PROPRIETES GENERALES DE LA FENETRE ======================*/
		super("Projet Nadia");		
		this.setVisible(true);				
		getContentPane().setBackground(backGroundColor);
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
		
		DefaultTableModel modelMatch = new DefaultTableModel(
	            new Object [][] {
	            },
	            new String [] {
	                "Année", "Tournoi", "Nom","Statut"
        });		
		
		/*======================= CHOIX POUR LES COMBOBOX =======================*/
		
		String[] comboChoice = {"les deux" , "femme", "homme" };		
		String[] typeChoice = {"femme", "homme"};
		
		/*============================ TAB JOUEUR ===============================*/	
		
		playerTab = new JPanel();				
		playerTab.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerTab.setBackground(backGroundColor);
		container.addTab(" Joueur ", null, playerTab, null);
		playerTab.setLayout(null);
		
		tablePlayer = new JTable(model);
		tablePlayer.setFillsViewportHeight(true);
		tablePlayer.setForeground(textColor2);
		tablePlayer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tablePlayer.setBackground(backGroundTab);		
		tablePlayer.setBounds(0, 0, 800, 400);
		tablePlayer.getColumnModel().getColumn(0).setWidth(0);	
		tablePlayer.getColumnModel().getColumn(0).setMinWidth(0);	
		tablePlayer.getColumnModel().getColumn(0).setMaxWidth(0);	
		tablePlayer.getColumnModel().getColumn(3).setWidth(50);	
		tablePlayer.getColumnModel().getColumn(3).setMinWidth(50);	
		tablePlayer.getColumnModel().getColumn(3).setMaxWidth(50);	
		renderTable(tablePlayer);
		tablePlayer.setRowHeight(20);
					
		errorLabel = new JLabel();
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
		comboBox = new JComboBox(comboChoice);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				choice = (String) comboBox.getSelectedItem();
				liste = BddPlayer.GetPlayers(choice);
				liste.fillTab(tablePlayer);
			}
		});
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		comboBox.setForeground(textColor1);
		comboBox.setBounds(157, 100, 145, 22);
		playerTab.add(comboBox);
		
		playerSexLabel = new JLabel("Choisir le sexe :");
		playerSexLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		playerSexLabel.setForeground(textColor2);
		playerSexLabel.setBounds(157, 59, 145, 30);
		playerTab.add(playerSexLabel);
		
		addPlayerButton = new JButton("<html><p style='text-align:center'>Ajouter<br>un joueur</p></html>");
		addPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PlayerWindow add = new PlayerWindow("Ajouter un joueur");
				add.setButton("ajouter");
				add.setColor(backGroundColor, textColor2);
				add.setVisible(true);
			}
		});
		addPlayerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addPlayerButton.setBounds(379, 66, 159, 56);
		playerTab.add(addPlayerButton);
		
		editPlayerButton = new JButton("<html><p style='text-align:center'>Editer<br>un joueur</p></html>");
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
					modify.setColor(backGroundColor, textColor2);
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
		
		deletePlayerButton = new JButton("<html><p style='text-align:center'>Supprimer<br>un joueur</p></html>");
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
					remove.setColor(backGroundColor, textColor2);
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
		searchField.setBounds(415, 144, 613, 30);
		playerTab.add(searchField);
		searchField.setColumns(10);
		
		playerSearchLabel = new JLabel("Rechercher un joueur :");
		playerSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerSearchLabel.setForeground(Color.WHITE);
		playerSearchLabel.setBounds(158, 144, 247, 30);
		playerTab.add(playerSearchLabel);
		
		/*=========================== TAB TOUNROI ==================================*/
		
		tournoiTab = new JPanel();
		tournoiTab.setBackground(backGroundColor);
		tournoiTab.setForeground(Color.WHITE);
		container.addTab(" Tournoi ", null, tournoiTab, null);
		tournoiTab.setLayout(null);
		
		errorLabelTournoi = new JLabel("");
		errorLabelTournoi.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabelTournoi.setVisible(false);
		errorLabelTournoi.setFont(new Font("Tahoma", Font.PLAIN, 24));
		errorLabelTournoi.setBackground(Color.RED);
		errorLabelTournoi.setForeground(Color.WHITE);
		errorLabelTournoi.setOpaque(true);
		errorLabelTournoi.setBounds(153, 11, 845, 46);
		tournoiTab.add(errorLabelTournoi);		

		winnerText = new JLabel("");
		winnerText.setForeground(textColor2);
		winnerText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		winnerText.setBounds(849, 344, 270, 46);
		tournoiTab.add(winnerText);
		
		finalText = new JLabel("");
		finalText.setForeground(textColor2);
		finalText.setFont(new Font("Tahoma", Font.PLAIN, 18));
		finalText.setBounds(849, 458, 270, 46);
		tournoiTab.add(finalText);
		
		tableTournoi = new JTable(modelTournoi);		
		tableTournoi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode() == 40 || e.getKeyCode() == 38 || e.getKeyCode() == 10) {
					int ID = (int)modelTournoi.getValueAt(tableTournoi.getSelectedRow(),3);
					BddTournoi.getPlayers(ID, winnerText, finalText);					
				}
			}
		});
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
		tableTournoi.getColumnModel().getColumn(3).setMinWidth(0);
		tableTournoi.getColumnModel().getColumn(3).setMaxWidth(0);
		tableTournoi.getColumnModel().getColumn(3).setWidth(0);
		tableTournoi.getColumnModel().getColumn(0).setMinWidth(120);
		tableTournoi.getColumnModel().getColumn(0).setMaxWidth(120);
		tableTournoi.getColumnModel().getColumn(0).setWidth(120);	
		tableTournoi.getColumnModel().getColumn(2).setMinWidth(120);
		tableTournoi.getColumnModel().getColumn(2).setMaxWidth(120);
		tableTournoi.getColumnModel().getColumn(2).setWidth(120);	
		renderTable(tableTournoi);
		tableTournoi.setRowHeight(20);
		
		addTournoiBtn = new JButton("<html><p style='text-align:center'>Ajouter <br> un tournoi</p></html>");
		addTournoiBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TournamentWindow add = new TournamentWindow("Ajouter un tournoi", "Ajouter un tournoi");
				add.setColor(backGroundColor, textColor2);
				add.setButton("ajouter");
				add.setVisible(true);
			}
		});
		addTournoiBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addTournoiBtn.setBounds(153, 68, 166, 58);
		tournoiTab.add(addTournoiBtn);
		
		editTournoiBtn = new JButton("<html><p style='text-align:center'>Editer <br> un tournoi</p></html>");
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
					TournamentWindow modify = new TournamentWindow("Editer un tournoi", "Editer un tournoi");
					modify.setColor(backGroundColor, textColor2);
					modify.setButton("modifier");
					modify.setId(ID);
					modify.setTournamentName(name);
					modify.setTournamentSex(sex);
					modify.setTournamentYear(year);
					modify.setVisible(true);					
				}	
			}
		});
		editTournoiBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		editTournoiBtn.setBounds(489, 68, 166, 58);
		tournoiTab.add(editTournoiBtn);
		
		removeTournoiBtn = new JButton("<html><p style='text-align:center'>Supprimer <br> un tournoi</p></html>");
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
					TournamentWindow remove = new TournamentWindow("Supprimer un tournoi", "Supprimer un tournoi");
					remove.setColor(backGroundColor, textColor2);
					remove.setButton("supprimer");					
					remove.setId(ID);
					remove.setTournamentName(name);
					remove.setTournamentSex(sex);
					remove.setTournamentYear(year);
					remove.setWindow("supprimer");
					remove.setVisible(true);
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
		searchTournoi.setBounds(409, 156, 589, 32);
		tournoiTab.add(searchTournoi);
		searchTournoi.setColumns(10);
		
		tournoiSearchLabel = new JLabel("Rechercher un tournoi :");
		tournoiSearchLabel.setHorizontalAlignment(SwingConstants.LEFT);
		tournoiSearchLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tournoiSearchLabel.setForeground(Color.WHITE);
		tournoiSearchLabel.setBounds(153, 157, 246, 31);
		tournoiTab.add(tournoiSearchLabel);		
				
		JScrollPane scrollPane_1 = new JScrollPane(tableTournoi);
		scrollPane_1.setForeground(Color.WHITE);
		scrollPane_1.setBackground(Color.DARK_GRAY);
		scrollPane_1.setBounds(153, 210, 638, 504);
		tournoiTab.add(scrollPane_1);
		
		winnerLabel = new JLabel("Vainqueur : ");
		winnerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		winnerLabel.setForeground(Color.WHITE);
		winnerLabel.setBounds(813, 296, 208, 37);
		tournoiTab.add(winnerLabel);
		
		finalLabel = new JLabel("Finaliste :");
		finalLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		finalLabel.setForeground(Color.WHITE);
		finalLabel.setBounds(813, 401, 208, 46);
		tournoiTab.add(finalLabel);
				
		infoLabel = new JLabel("<html><p>Cliquer sur un tournoi<br>ou parcourez le tableau<br> pour afficher les infos</p></html>");
		infoLabel.setForeground(Color.WHITE);
		infoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		infoLabel.setBounds(813, 210, 306, 75);
		tournoiTab.add(infoLabel);
		
		editInfoBtn = new JButton("Editer");
		editInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tableTournoi.getSelectedRow() == -1) {
					errorLabelTournoi.setText("Pour pouvoir éditer les infos, veuillez selectionner un tournoi");
					errorLabelTournoi.setVisible(true);		 			
				} else {
				int ID = (int) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),3);
				int year = (int) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),0);
				String name = (String) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),1);
				String sex = (String) modelTournoi.getValueAt(tableTournoi.getSelectedRow(),2);				
				EditFinalWindow efw = new EditFinalWindow();
				BddTournoi.fillBox(EditFinalWindow.getFinalBox(), EditFinalWindow.getWinnerBox(), sex );
				efw.setInfoLabel(name, year, sex);
				efw.setTournamentID(ID);
				efw.setVisible(true);
				}
			}
		});
		editInfoBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		editInfoBtn.setBounds(867, 543, 154, 58);
		tournoiTab.add(editInfoBtn);

		
		/*======================== TAB EPREUVE ==============================================*/
		
		epreuveTab = new JPanel();
		epreuveTab.setBackground(backGroundColor);
		container.addTab(" Epreuve ", null, epreuveTab, null);
		epreuveTab.setLayout(null);
		
		epreuveYearLabel = new JLabel("Ann\u00E9e :");
		epreuveYearLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		epreuveYearLabel.setForeground(Color.WHITE);
		epreuveYearLabel.setBounds(131, 26, 213, 32);
		epreuveTab.add(epreuveYearLabel);
		
		yearBox = new JComboBox();
		yearBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		yearBox.setBounds(132, 80, 170, 32);
		epreuveTab.add(yearBox);
		
		epreuveSexLabel = new JLabel("Type d'\u00E9preuve :");
		epreuveSexLabel.setHorizontalAlignment(SwingConstants.CENTER);
		epreuveSexLabel.setForeground(Color.WHITE);
		epreuveSexLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		epreuveSexLabel.setBounds(383, 26, 219, 32);
		epreuveTab.add(epreuveSexLabel);
		
		sexBoxEpreuve = new JComboBox(typeChoice);
		sexBoxEpreuve.setFont(new Font("Tahoma", Font.PLAIN, 18));
		sexBoxEpreuve.setBounds(432, 80, 170, 32);
		epreuveTab.add(sexBoxEpreuve);
		
		searchEpreuveBtn = new JButton("Rechercher");
		searchEpreuveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String year = (String) yearBox.getSelectedItem();
				String sex = (String) sexBoxEpreuve.getSelectedItem();
				BddEpreuve.getPlayers(year, sex, tableEpreuve);
			}
		});
		searchEpreuveBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		searchEpreuveBtn.setBounds(901, 53, 177, 32);
		epreuveTab.add(searchEpreuveBtn);

		tableEpreuve = new JTable(modelEpreuve);
		tableEpreuve.setFont(new Font("Tahoma", Font.PLAIN, 14));
		tableEpreuve.setForeground(Color.WHITE);
		tableEpreuve.setBackground(Color.DARK_GRAY);
		tableEpreuve.setFillsViewportHeight(true);
		renderTable(tableEpreuve);
		tableEpreuve.setRowHeight(20);
		
		JScrollPane scrollPane_2 = new JScrollPane(tableEpreuve);
		scrollPane_2.setBounds(131, 141, 947, 573);
		epreuveTab.add(scrollPane_2);
				
		/*=============================== TAB MATCH ======================================*/
		
		matchTab = new JPanel();
		matchTab.setBackground(backGroundColor);
		matchTab.setFont(new Font("Tahoma", Font.PLAIN, 20));
		container.addTab(" Match ", null, matchTab, null);
		matchTab.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(156, 225, 891, 476);
		matchTab.add(scrollPane_3);
		
		tableMatch = new JTable(modelMatch);
		tableMatch.setFillsViewportHeight(true);
		tableMatch.setForeground(Color.WHITE);
		tableMatch.setBackground(Color.DARK_GRAY);
		tableMatch.getColumnModel().getColumn(0).setMinWidth(120);
		tableMatch.getColumnModel().getColumn(0).setMaxWidth(120);
		tableMatch.getColumnModel().getColumn(0).setWidth(120);
		renderTable(tableMatch);
		scrollPane_3.setViewportView(tableMatch);		

		searchMatch = new JTextField();
		searchMatch.setFont(new Font("Tahoma", Font.PLAIN, 18));
		searchMatch.setBounds(468, 117, 373, 33);
		matchTab.add(searchMatch);
		searchMatch.setColumns(10);
		
		
		ButtonGroup buttonGroup = new ButtonGroup();
		finalRadioBtn = new JRadioButton("Finaliste");		
		finalRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(finalRadioBtn.getSelectedObjects() != null) {
					String search = searchMatch.getText();
					BddMatch.getPlayers(tableMatch, search, "final");
				}
			}
		});
		finalRadioBtn.setBackground(backGroundColor);
		finalRadioBtn.setForeground(Color.WHITE);
		finalRadioBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		finalRadioBtn.setBounds(156, 130, 251, 23);
		matchTab.add(finalRadioBtn);
		buttonGroup.add(finalRadioBtn);
		
		winnerRadioBtn = new JRadioButton("Vainqueur");
		winnerRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(winnerRadioBtn.getSelectedObjects() != null) {
					String search = searchMatch.getText();
					BddMatch.getPlayers(tableMatch, search, "winner");
				}
			}
		});
		winnerRadioBtn.setBackground(backGroundColor);
		winnerRadioBtn.setForeground(Color.WHITE);
		winnerRadioBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		winnerRadioBtn.setBounds(156, 86, 251, 23);
		matchTab.add(winnerRadioBtn);
		buttonGroup.add(winnerRadioBtn);
		
		allRadioBtn = new JRadioButton("Les deux");
		allRadioBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(allRadioBtn.getSelectedObjects() != null) {
					String search = searchMatch.getText();
					BddMatch.getPlayers(tableMatch, search, "all");
				}
			}
		});
		allRadioBtn.setBackground(backGroundColor);
		allRadioBtn.setForeground(Color.WHITE);
		allRadioBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		allRadioBtn.setBounds(156, 41, 251, 23);
		matchTab.add(allRadioBtn);
		buttonGroup.add(allRadioBtn);
		allRadioBtn.setSelected(true);
		
		searchMatchLabel = new JLabel("Rechercher :");
		searchMatchLabel.setForeground(Color.WHITE);
		searchMatchLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		searchMatchLabel.setBounds(468, 61, 239, 45);
		matchTab.add(searchMatchLabel);
		
		validateBtn = new JButton("Valider");
		validateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String search = searchMatch.getText();
				String mode = "";
				if(finalRadioBtn.getSelectedObjects() != null) {
					mode = "final";
				}
				if(winnerRadioBtn.getSelectedObjects() != null) {
					mode = "winner";
				}
				if(allRadioBtn.getSelectedObjects() != null) {
					mode = "all";
				}
				BddMatch.getPlayers(tableMatch, search, mode);
			}
		});
		validateBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		validateBtn.setBounds(914, 117, 133, 33);
		matchTab.add(validateBtn);
				
		/*=========================== TAB OPTION ========================================*/
		
		String[] fontSizeOptions = {"petit", "moyen", "grand"};
		String[] fontStyleOptions = {"Arial","Baskerville Old Face","Calibri","DialogInput","Cambria","Tahoma"};
		String[] colorThemeOptions = {"clair", "sombre"};
		
		optionTab = new JPanel();
		optionTab.setBackground(new Color(40, 40, 40));
		container.addTab(" Options ", null, optionTab, null);
		optionTab.setLayout(null);
		
		optionTitle1 = new JLabel(" Options Graphiques");
		optionTitle1.setHorizontalAlignment(SwingConstants.CENTER);
		optionTitle1.setAlignmentX(Component.CENTER_ALIGNMENT);
		optionTitle1.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		optionTitle1.setForeground(Color.WHITE);
		optionTitle1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		optionTitle1.setBounds(93, 11, 347, 41);
		optionTab.add(optionTitle1);
		
		fontStyleLabel = new JLabel("Style de police :");
		fontStyleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fontStyleLabel.setForeground(Color.WHITE);
		fontStyleLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fontStyleLabel.setBounds(50, 166, 253, 35);
		optionTab.add(fontStyleLabel);
		
		fontSizeLabel = new JLabel("Taille de police :");
		fontSizeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		fontSizeLabel.setForeground(Color.WHITE);
		fontSizeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fontSizeLabel.setBounds(50, 108, 253, 35);
		optionTab.add(fontSizeLabel);
		
		fontSizeBox = new JComboBox(fontSizeOptions);
		fontSizeBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fontSizeBox.setBounds(313, 108, 180, 34);
		optionTab.add(fontSizeBox);
		
		fontStyleBox = new JComboBox(fontStyleOptions);
		fontStyleBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fontStyleBox.setBounds(313, 167, 180, 35);
		optionTab.add(fontStyleBox);
				
		optionTitle2 = new JLabel("Options logicielles");
		optionTitle2.setHorizontalAlignment(SwingConstants.CENTER);
		optionTitle2.setForeground(Color.WHITE);
		optionTitle2.setFont(new Font("Tahoma", Font.PLAIN, 24));
		optionTitle2.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		optionTitle2.setAlignmentX(0.5f);
		optionTitle2.setBounds(696, 11, 347, 41);
		optionTab.add(optionTitle2);
		
		colorThemeLabel = new JLabel("Th\u00E8me de couleur :");
		colorThemeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		colorThemeLabel.setForeground(Color.WHITE);
		colorThemeLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		colorThemeLabel.setBounds(50, 229, 253, 35);
		optionTab.add(colorThemeLabel);
		
		colorThemeBox = new JComboBox(colorThemeOptions);
		colorThemeBox.setFont(new Font("Tahoma", Font.PLAIN, 18));
		colorThemeBox.setBounds(313, 230, 180, 35);
		optionTab.add(colorThemeBox);
		
		connectionLabel = new JLabel("Informations de connexion");
		connectionLabel.setForeground(Color.WHITE);
		connectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		connectionLabel.setBounds(762, 103, 370, 35);
		optionTab.add(connectionLabel);
		
		applyStyleBtn = new JButton("Appliquer");
		applyStyleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String size = (String) fontSizeBox.getSelectedItem();
				String style = (String) fontStyleBox.getSelectedItem();
				String color = (String) colorThemeBox.getSelectedItem();
				changeGraphics(size, style, color);
			}
		});
		applyStyleBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		applyStyleBtn.setBounds(217, 304, 162, 41);
		optionTab.add(applyStyleBtn);
		
		connectionBtn = new JButton("Ouvrir");
		connectionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ConnectionWindow cw = new ConnectionWindow();
				cw.setVisible(true);
			}
		});
		connectionBtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		connectionBtn.setBounds(762, 152, 218, 49);
		optionTab.add(connectionBtn);
				
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

	/*============= GETTER ET SETTER ====================*/
	public static JTable getTable() {
		return tablePlayer;
	}
	
	public static String getChoice() {
		return choice;
	}
	
	public static void getDatas() {		
		liste = BddPlayer.GetPlayers("");
		liste.fillTab(tablePlayer);
		BddTournoi.getTournament(tableTournoi);
		BddEpreuve.getYear(yearBox);
		BddMatch.getPlayers(tableMatch, "", "all");
	}
	
	/*======== FONCTION POUR LE RENDU DU TABLEAU + CELLULES NON EDITABLES ========*/
	public void renderTable(JTable table) {
		
		/*= MODELE DE RENDU DES CELLULES DU TABLEAU =*/	
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();		
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		TableModel model = table.getModel();
		
		for(int i=0; i<model.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setCellEditor(new nullEditor(new JCheckBox()));
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);			
		}
		
	}	
	
	/*================== FONCTION OPTIONS GRAPHIQUES ===============================*/
	public void changeGraphics(String size, String style, String color) {
		/*=========================== COLOR ===============================*/
		if(color.equals("clair")) {
			backGroundColor = new Color(177,194,222);
			backGroundTab = new Color(193,215,222);
			textColor1 = new Color(255,255,255);
			textColor2 = new Color(0,0,0);			
		} else if(color.equals("sombre")) {
			backGroundColor = new Color(40,40,40);
			backGroundTab = new Color(70,70,70);
			textColor1 = new Color(0,0,0);
			textColor2 = new Color(255,255,255);
		}
		/*======= PANELS ======*/
		playerTab.setBackground(backGroundColor);
		tournoiTab.setBackground(backGroundColor);
		epreuveTab.setBackground(backGroundColor);
		matchTab.setBackground(backGroundColor);
		optionTab.setBackground(backGroundColor);
		
		/*========= TABLES ======*/
		tablePlayer.setBackground(backGroundTab);
		tablePlayer.setForeground(textColor2);
		tableTournoi.setBackground(backGroundTab);
		tableTournoi.setForeground(textColor2);
		tableEpreuve.setBackground(backGroundTab);
		tableEpreuve.setForeground(textColor2);
		tableMatch.setBackground(backGroundTab);
		tableMatch.setForeground(textColor2);
		
		/*======== LABELS ========*/
		playerSexLabel.setForeground(textColor2);
		playerSearchLabel.setForeground(textColor2);
		finalText.setForeground(textColor2);
		winnerText.setForeground(textColor2);
		winnerLabel.setForeground(textColor2);
		finalLabel.setForeground(textColor2);
		infoLabel.setForeground(textColor2);
		tournoiSearchLabel.setForeground(textColor2);
		epreuveYearLabel.setForeground(textColor2);
		epreuveSexLabel.setForeground(textColor2);
		searchMatchLabel.setForeground(textColor2);
		optionTitle1.setForeground(textColor2);
		optionTitle2.setForeground(textColor2);
		fontSizeLabel.setForeground(textColor2);
		fontStyleLabel.setForeground(textColor2);
		colorThemeLabel.setForeground(textColor2);
		connectionLabel.setForeground(textColor2);
		
		/*========== RADIO BUTTON ============*/
		finalRadioBtn.setBackground(backGroundColor);
		finalRadioBtn.setForeground(textColor2);
		winnerRadioBtn.setBackground(backGroundColor);
		winnerRadioBtn.setForeground(textColor2);
		allRadioBtn.setBackground(backGroundColor);
		allRadioBtn.setForeground(textColor2);
		
		/*========= OPTION TITLE ============*/
		optionTitle1.setBorder(new LineBorder((textColor2),2));
		optionTitle2.setBorder(new LineBorder((textColor2),2));
		
		/*=========================== POLICE ==============================*/
		if(size.equals("petit")) {
			font1 = new Font(style, Font.PLAIN, 11);	
			font2 = new Font(style, Font.PLAIN, 14);	
			font3 = new Font(style, Font.PLAIN, 18);	
			tablePlayer.setRowHeight(19);
			tableTournoi.setRowHeight(19);
			tableEpreuve.setRowHeight(19);
			tableMatch.setRowHeight(19);
		} else if(size.equals("moyen")) {
			font1 = new Font(style, Font.PLAIN, 14);	
			font2 = new Font(style, Font.PLAIN, 18);	
			font3 = new Font(style, Font.PLAIN, 24);	
			tablePlayer.setRowHeight(22);
			tableTournoi.setRowHeight(22);
			tableEpreuve.setRowHeight(22);
			tableMatch.setRowHeight(22);
		} else if(size.equals("grand")) {
			font1 = new Font(style, Font.PLAIN, 16);	
			font2 = new Font(style, Font.PLAIN, 22);	
			font3 = new Font(style, Font.PLAIN, 28);	
			tablePlayer.setRowHeight(30);
			tableTournoi.setRowHeight(30);
			tableEpreuve.setRowHeight(30);
			tableMatch.setRowHeight(30);
		}
		tablePlayer.setFont(font1);		
		tableTournoi.setFont(font1);
		tableEpreuve.setFont(font1);
		tableMatch.setFont(font1);
		
		errorLabel.setFont(font3);
		errorLabelTournoi.setFont(font3);
		optionTitle1.setFont(font3);
		optionTitle2.setFont(font3);
		
		/*======= TAB PLAYER ========*/
		comboBox.setFont(font2);
		playerSexLabel.setFont(font2);
		addPlayerButton.setFont(font2);
		editPlayerButton.setFont(font2);
		deletePlayerButton.setFont(font2);
		searchField.setFont(font2);
		playerSearchLabel.setFont(font2);
		
		/*======= TAB TOURNOI ========*/
		winnerText.setFont(font2);
		finalText.setFont(font2);
		addTournoiBtn.setFont(font2);
		editTournoiBtn.setFont(font2);
		removeTournoiBtn.setFont(font2);
		searchTournoi.setFont(font2);
		tournoiSearchLabel.setFont(font2);
		winnerLabel.setFont(font2);
		finalLabel.setFont(font2);
		infoLabel.setFont(font2);
		editInfoBtn.setFont(font2);
		
		/*======= TAB EPREUVE ========*/
		epreuveSexLabel.setFont(font2);
		epreuveYearLabel.setFont(font2);
		yearBox.setFont(font2);
		sexBoxEpreuve.setFont(font2);
		searchEpreuveBtn.setFont(font2);
		
		/*======= TAB MATCH ==========*/
		finalRadioBtn.setFont(font2);
		winnerRadioBtn.setFont(font2);
		allRadioBtn.setFont(font2);
		searchMatchLabel.setFont(font2);
		searchMatch.setFont(font2);
		validateBtn.setFont(font2);
		
		/*======= TAB OPTION ========*/
		fontStyleLabel.setFont(font2);
		fontSizeLabel.setFont(font2);
		fontSizeBox.setFont(font2);
		fontStyleBox.setFont(font2);
		colorThemeLabel.setFont(font2);
		colorThemeBox.setFont(font2);
		applyStyleBtn.setFont(font2);
		connectionLabel.setFont(font2);
		connectionBtn.setFont(font2);
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
