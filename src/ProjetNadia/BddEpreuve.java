package ProjetNadia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BddEpreuve {
	public static void getYear(JComboBox box) {
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;
		ResultSet rs =null;
		Set<String> yearList = new TreeSet<>(); 
		try {
			ps = cn.prepareStatement("SELECT ANNEE FROM epreuve");			
			rs = ps.executeQuery();
			while(rs.next()) {	
				yearList.add(rs.getString("epreuve.ANNEE"));
				}			
			}catch (SQLException e){
				e.printStackTrace();
			} finally {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		for(String s : yearList) {
			box.addItem(s);
		}
	}
	
	public static void getPlayers(String year, String sex, JTable table) {
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;
		ResultSet rs =null; 
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for( int i = model.getRowCount() - 1; i >= 0; i-- )	{
		    model.removeRow(i);
		}	
		if(sex.equals("femme")) {
			sex = "F";
		} else {
			sex = "H";
		}
		try {
			ps = cn.prepareStatement("SELECT joueur.NOM,joueur.PRENOM,tournoi.NOM "
									+ "FROM tennis.epreuve, tennis.tournoi, tennis.joueur, tennis.match_tennis "
									+ "WHERE joueur.ID = match_tennis.ID_FINALISTE "
									+ "AND epreuve.ANNEE='"+year+"' "
									+ "AND epreuve.TYPE_EPREUVE ='"+sex+"'"
									+ "AND epreuve.ID=match_tennis.ID_EPREUVE "
									+ "AND epreuve.ID_TOURNOI = tournoi.ID;");			
			rs = ps.executeQuery();
			while(rs.next()) {	
				model.addRow(new Object[] { rs.getString("joueur.NOM"), rs.getString("joueur.PRENOM"), rs.getString("tournoi.NOM"), "finaliste"});
				}	
			ps.close();
			rs = null;
			ps = cn.prepareStatement("SELECT joueur.NOM,joueur.PRENOM,tournoi.NOM "
									+ "FROM tennis.epreuve, tennis.tournoi, tennis.joueur, tennis.match_tennis "
									+ "WHERE joueur.ID = match_tennis.ID_VAINQUEUR "
									+ "AND epreuve.ANNEE='"+year+"' "
									+ "AND epreuve.TYPE_EPREUVE ='"+sex+"'"
									+ "AND epreuve.ID=match_tennis.ID_EPREUVE "
									+ "AND epreuve.ID_TOURNOI = tournoi.ID;");			
			rs = ps.executeQuery();
			while(rs.next()) {	
			model.addRow(new Object[] { rs.getString("joueur.NOM"), rs.getString("joueur.PRENOM"), rs.getString("tournoi.NOM"), "vainqueur"});
			}	
		}catch (SQLException e){
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
	}
}
