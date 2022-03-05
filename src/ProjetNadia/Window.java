package ProjetNadia;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class Window extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JTable table;
	private static String choice;
	private ListeJoueur liste = new ListeJoueur();
	
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
		getContentPane().add(container, "name_19341793489500");
				
		DefaultTableModel model = new DefaultTableModel(
	            new Object [][] {
	            },
	            new String [] {
	                "ID", "Nom", "Prenom", "Sexe"
        });
		
		String[] comboChoice = {"les deux" , "femme", "homme" };
		
		JPanel tab1 = new JPanel();		
		tab1.setBackground(Color.BLACK);
		container.addTab("Joueur", null, tab1, null);
		tab1.setLayout(null);
		
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
		tab1.add(errorLabel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(157, 133, 871, 600);
		tab1.add(scrollPane);
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
		tab1.add(comboBox);
				
		JLabel lblNewLabel = new JLabel("Choisir le sexe :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(157, 59, 145, 30);
		tab1.add(lblNewLabel);
		
		JButton addPlayerButton = new JButton("<html><p style='text-align:center'>Ajouter<br>un joueur</p></html>");
		addPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddPlayerWindow add = new AddPlayerWindow();
				add.setVisible(true);
			}
		});
		addPlayerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		addPlayerButton.setBounds(379, 66, 159, 56);
		tab1.add(addPlayerButton);
		
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
		tab1.add(editPlayerButton);
		
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
		tab1.add(deletePlayerButton);	
				
		JPanel tab2 = new JPanel();
		container.addTab("Match", null, tab2, null);
		tab2.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(47, 54, 269, 135);
		tab2.add(btnNewButton_1);
		
		JPanel tab3 = new JPanel();
		container.addTab("Chais Pas", null, tab3, null);
		
		// Remplissage par défaut du tableau
		liste = Bdd.GetPlayers("");
		liste.fillTab(table);		
		
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
