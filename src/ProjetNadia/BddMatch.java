package ProjetNadia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BddMatch {

	public static void getPlayers(JTable table, String search, String mode) {
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;
		ResultSet rs =null;
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		for( int i = model.getRowCount() - 1; i >= 0; i-- )	{
		    model.removeRow(i);
		}			
		if(search.equals("")) {
			if(mode.equals("winner") || mode.equals("all")) {
				try {
					ps = cn.prepareStatement("SELECT tournoi.NOM, epreuve.ANNEE, joueur.NOM, joueur.PRENOM "
											+ "FROM joueur, epreuve, tournoi, match_tennis "
											+ "WHERE match_tennis.ID_VAINQUEUR = joueur.ID "
											+ "AND match_tennis.ID_EPREUVE = epreuve.ID "
											+ "AND epreuve.ID_TOURNOI = tournoi.ID");
					rs = ps.executeQuery();
					while(rs.next()) {
						model.addRow(new Object[] {rs.getInt("epreuve.ANNEE"), rs.getString("tournoi.NOM"), rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"), "vainqueur"});
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
			} 
			if(mode.equals("final") || mode.equals("all")) {
				try {
					ps = cn.prepareStatement("SELECT tournoi.NOM, epreuve.ANNEE, joueur.NOM, joueur.PRENOM "
											+ "FROM joueur, epreuve, tournoi, match_tennis "
											+ "WHERE match_tennis.ID_FINALISTE = joueur.ID "
											+ "AND match_tennis.ID_EPREUVE = epreuve.ID "
											+ "AND epreuve.ID_TOURNOI = tournoi.ID");
					rs = ps.executeQuery();
					while(rs.next()) {
						model.addRow(new Object[] {rs.getInt("epreuve.ANNEE"), rs.getString("tournoi.NOM"), rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"), "finaliste"});
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
			} 
		} else {
			if(mode.equals("winner") || mode.equals("all")) {
				try {
					ps = cn.prepareStatement("SELECT tournoi.NOM, epreuve.ANNEE, joueur.NOM, joueur.PRENOM "
											+ "FROM joueur, epreuve, tournoi, match_tennis "
											+ "WHERE match_tennis.ID_VAINQUEUR = joueur.ID "
											+ "AND match_tennis.ID_EPREUVE = epreuve.ID "
											+ "AND epreuve.ID_TOURNOI = tournoi.ID "
											+ "AND (joueur.NOM LIKE '%" + search +"%' OR joueur.PRENOM LIKE '%" + search + "%')");
					rs = ps.executeQuery();
					while(rs.next()) {
						model.addRow(new Object[] {rs.getInt("epreuve.ANNEE"), rs.getString("tournoi.NOM"), rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"), "vainqueur"});
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}	
			} 
			if(mode.equals("final") || mode.equals("all")) {
				try {
					ps = cn.prepareStatement("SELECT tournoi.NOM, epreuve.ANNEE, joueur.NOM, joueur.PRENOM "
											+ "FROM joueur, epreuve, tournoi, match_tennis "
											+ "WHERE match_tennis.ID_FINALISTE = joueur.ID "
											+ "AND match_tennis.ID_EPREUVE = epreuve.ID "
											+ "AND epreuve.ID_TOURNOI = tournoi.ID "
											+ "AND (joueur.NOM LIKE '%"+search+"%' OR joueur.PRENOM LIKE '%"+search+"%')");
					rs = ps.executeQuery();
					while(rs.next()) {
						model.addRow(new Object[] {rs.getInt("epreuve.ANNEE"), rs.getString("tournoi.NOM"), rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"), "finaliste"});
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
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
		
				
	}
}
