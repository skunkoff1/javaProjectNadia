package ProjetNadia;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class ListeJoueur {
	
	public ArrayList<Joueur> ListeJoueur = new ArrayList<Joueur>();
	
	public ListeJoueur() {
		
	}
	
	public void addJoueur(Joueur joueur) {
		ListeJoueur.add(joueur);
	}
	
	public void showList() {
		for(int i=0; i<ListeJoueur.size(); i++) {
			System.out.println(ListeJoueur.get(i).getNom());
		}
	}
	public void fillTab(JTable table) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for( int i = model.getRowCount() - 1; i >= 0; i-- )
		{
		    model.removeRow(i);
		}
		for(Joueur n: ListeJoueur) {
			model.addRow(new Object[] {n.getID(),n.getNom(), n.getPrenom(), n.getSexe()});		
		}
	}
	
	public int getSize() {
		int size = ListeJoueur.size();
		return size;
	}
}
