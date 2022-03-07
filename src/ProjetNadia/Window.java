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
	private static JTable table;
	private static String choice;
	private ListeJoueur liste = new ListeJoueur();
	private JTextField searchField;
	
	public Window() {
		super("Projet Nadia");
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
		
		String[] comboChoice = {"les deux" , "femme", "homme" };
		
		// Remplissage par défaut du tableau
		liste = Bdd.GetPlayers("");
		
		JPanel playerTab = new JPanel();		
		playerTab.setFont(new Font("Tahoma", Font.PLAIN, 18));
		playerTab.setBackground(Color.BLACK);
		container.addTab(" Joueur ", null, playerTab, null);
		playerTab.setLayout(null);
		
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		table.setForeground(Color.WHITE);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setBackground(Color.DARK_GRAY);		
		table.setBounds(0, 0, 800, 400);
		table.getColumn("ID").setCellEditor(new nullEditor(new JCheckBox()));
		table.getColumn("Nom").setCellEditor(new nullEditor(new JCheckBox()));
		table.getColumn("Prenom").setCellEditor(new nullEditor(new JCheckBox()));
		table.getColumn("Sexe").setCellEditor(new nullEditor(new JCheckBox()));
		
		JLabel errorLabel = new JLabel();
		errorLabel.setOpaque(true);
		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
		errorLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		errorLabel.setForeground(Color.WHITE);
		errorLabel.setBackground(new Color(255, 0, 0));
		errorLabel.setBounds(158, 11, 870, 37);
		errorLabel.setVisible(false);
		playerTab.add(errorLabel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(157, 197, 871, 600);
		playerTab.add(scrollPane);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		JComboBox comboBox = new JComboBox(comboChoice);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				choice = (String) comboBox.getSelectedItem();
				liste = Bdd.GetPlayers(choice);
				liste.fillTab(table);
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
				AddPlayerWindow add = new AddPlayerWindow();
				add.setVisible(true);
			}
		});
		addPlayerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addPlayerButton.setBounds(379, 66, 159, 56);
		playerTab.add(addPlayerButton);
		
		JButton editPlayerButton = new JButton("<html><p style='text-align:center'>Editer<br>un joueur</p></html>");
		editPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() == -1) {
					errorLabel.setText("Pour pouvoir modifier un joueur, veuillez en selectionner un");
					errorLabel.setVisible(true);					
				} else {
					errorLabel.setVisible(false);
					int ID = (int) model.getValueAt(table.getSelectedRow(), 0); 
					String name = (String) model.getValueAt(table.getSelectedRow(), 1);
					String firstName = (String) model.getValueAt(table.getSelectedRow(), 2);
					String sex = (String) model.getValueAt(table.getSelectedRow(), 3);
					ModifyPlayerWindow modify = new ModifyPlayerWindow();
					modify.setId(ID);
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
				if(table.getSelectedRow() == -1) {
					errorLabel.setText("Pour pouvoir supprimer un joueur, veuillez en selectionner un");
					errorLabel.setVisible(true);					
				} else {
					errorLabel.setVisible(false);
					int ID = (int) model.getValueAt(table.getSelectedRow(), 0); 
					String name = (String) model.getValueAt(table.getSelectedRow(), 1);
					String firstName = (String) model.getValueAt(table.getSelectedRow(), 2);
					String sex = (String) model.getValueAt(table.getSelectedRow(), 3);
					RemovePlayerWindow remove = new RemovePlayerWindow();
					remove.setId(ID);
					remove.setPlayerName(name);
					remove.setPlayerFirstName(firstName);
					remove.setPlayerSex(sex);
					remove.setVisible(true);
				}
			}
		});
		deletePlayerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		deletePlayerButton.setBounds(869, 66, 159, 56);
		playerTab.add(deletePlayerButton);	
		liste.fillTab(table);
		
		searchField = new JTextField();
		searchField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				errorLabel.setVisible(false);
				String search = searchField.getText();
				liste = Bdd.searchPlayer(search);
				int size = liste.getSize();
				if(size ==0) {
					errorLabel.setVisible(true);
					errorLabel.setText("Aucun joueur trouvé");
				}
				liste.fillTab(table);
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
		
		JPanel matchTab = new JPanel();
		matchTab.setFont(new Font("Tahoma", Font.PLAIN, 20));
		container.addTab(" Match ", null, matchTab, null);
		matchTab.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(47, 54, 269, 135);
		matchTab.add(btnNewButton_1);
		
		JPanel tab3 = new JPanel();
		container.addTab(" Chais Pas ", null, tab3, null);
		
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
		
		this.setVisible(true);
	}

	public void setListe(ListeJoueur liste) {
		this.liste = liste;
	}

	public static JTable getTable() {
		return table;
	}
	
	public void setTable(JTable table) {
		this.table = table;
	}        

	public static String getChoice() {
		return choice;
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
