package ProjetNadia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public interface BddTournoi {

		public static void getTournament(JTable table) {
			Connection cn = BddConnection.getCn();
			PreparedStatement ps = null;
			ResultSet rs =null;
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for( int i = model.getRowCount() - 1; i >= 0; i-- )	{
			    model.removeRow(i);
			}			
			try {
				ps = cn.prepareStatement("SELECT epreuve.ANNEE, epreuve.TYPE_EPREUVE, tournoi.NOM, epreuve.ID "
										+ "FROM tennis.epreuve, tennis.tournoi "
										+ "WHERE epreuve.ID_TOURNOI = tournoi.ID");			
				rs = ps.executeQuery();
				while(rs.next()) {	
					model.addRow(new Object[] {rs.getInt("epreuve.ANNEE"), rs.getString("tournoi.NOM"), rs.getString("epreuve.TYPE_EPREUVE"), rs.getInt("epreuve.ID")});
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
		
		public static String addTournament(String name, String year, String sex) {				
			String message;
			String error;
			if(sex.equals("femme")) {
				sex = "F";
			} else {
				sex = "H";
			}
			int newYear = Integer.parseInt(year);
			int ID=0;
			Connection cn = BddConnection.getCn();
			PreparedStatement ps = null;	
			ResultSet rs = null;
			try {
				ps = cn.prepareStatement("SELECT ID FROM tournoi WHERE NOM = ?");	
				ps.setString(1, name);
				rs = ps.executeQuery();
				ps.close();
				boolean empty = true;
				while( rs.next() ) {
				    //ResultSet processing here
				    empty = false;
				    ID = rs.getInt(1);
				}

				if( empty ) {
				    //Empty result set
					System.out.println("pas de résultat");
				}
				else {
					ps = cn.prepareStatement("INSERT INTO epreuve(ANNEE, TYPE_EPREUVE, ID_TOURNOI) VALUES(?,?,?)");
					ps.setInt(1,newYear);
					ps.setString(2, sex);
					ps.setInt(3,ID);
					ps.executeUpdate();
					ps.close();
					
				}						
			}catch (SQLException e){
				e.printStackTrace();
				error = "Un problème est survenu" + e;
				return error;
			} finally {
				try {
					ps.close();					
				} catch (SQLException e) {
					e.printStackTrace();
					error = "Un problème est survenu" + e;
					return error;
				}	
			}
			message = "tournoi ajouté, la fenetre va automatiquement se fermer";
			return message;
		}
		
		public static String updateTournament(int ID, String name, String year, String sex) {			
			String message;
			String error;
			int newYear = Integer.parseInt(year);
			int newId = 0;
			Connection cn = BddConnection.getCn();
			PreparedStatement ps = null;	
			ResultSet rs;
			try {
				ps = cn.prepareStatement("SELECT ID FROM tournoi WHERE NOM = ?");	
				ps.setString(1, name);
				rs = ps.executeQuery();
				ps.close();
				boolean empty = true;
				while( rs.next() ) {
				    //ResultSet processing here
				    empty = false;
				    newId = rs.getInt(1);
				}

				if( empty ) {
				    //Empty result set
					System.out.println("pas de résultat");
				}
				else {
					ps = cn.prepareStatement("UPDATE epreuve SET ANNEE=?, TYPE_EPREUVE=?, ID_TOURNOI=? WHERE ID=?");	
					ps.setInt(1, newYear);
					if(sex.equals("femme")) {
						sex = "F";
					} else {
						sex = "H";
					}
					ps.setString(2, sex);					
					ps.setInt(4, ID);
					ps.setInt(3, newId);
					ps.executeUpdate();
				}					
			}catch (SQLException e){
				e.printStackTrace();
				error = "Un problème est survenu" + e;
				return error;
			} finally {
				try {
					ps.close();					
				} catch (SQLException e) {
					e.printStackTrace();
					error = "Un problème est survenu" + e;
					return error;
				}	
			}
			message = "tournoi modifié, la fenetre va automatiquement se fermer";
			return message;
		}
		
		public static String removeTournament(int ID) {
			String message;
			String error;
			Connection cn = BddConnection.getCn();
			PreparedStatement ps = null;	
			try {
				ps = cn.prepareStatement("DELETE FROM epreuve WHERE ID = ?");	
				ps.setInt(1, ID);
				ps.executeUpdate();						
			}catch (SQLException e){
				e.printStackTrace();
				error = "Un problème est survenu" + e;
				return error;
			} finally {
				try {
					ps.close();					
				} catch (SQLException e) {
					e.printStackTrace();
					error = "Un problème est survenu" + e;
					return error;
				}	
			}
			message = "tournoi supprimé, la fenetre va automatiquement se fermer";
			return message;
		}
		
		public static void searchTournament(String search, JTable table) {
			Connection cn = BddConnection.getCn();
			PreparedStatement ps = null;	
			ResultSet rs =null;
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			for( int i = model.getRowCount() - 1; i >= 0; i-- ) {
			    model.removeRow(i);
			}
			try {
				ps = cn.prepareStatement("SELECT epreuve.ANNEE, epreuve.TYPE_EPREUVE, tournoi.NOM, epreuve.ID "
										+ "FROM tennis.epreuve, tennis.tournoi"
										+ " WHERE epreuve.ID_TOURNOI = tournoi.ID AND epreuve.ANNEE "
										+ "LIKE '%"+search+"%' OR tournoi.NOM LIKE '%"+search+"%'");
				rs = ps.executeQuery();
				while(rs.next()) {				
					model.addRow(new Object[] {rs.getInt("epreuve.ANNEE"), rs.getString("tournoi.NOM"), rs.getString("epreuve.TYPE_EPREUVE"), rs.getInt("epreuve.ID")});			
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
		
		public static void getPlayers(int ID, JLabel winner, JLabel finalist) {
			Connection cn = BddConnection.getCn();
			PreparedStatement ps = null;	
			ResultSet rs =null;
			try {
				ps = cn.prepareStatement("SELECT joueur.nom, joueur.prenom "
										+ "FROM joueur, match_tennis "
										+ "WHERE match_tennis.ID_EPREUVE ='"+ID+"'"
										+ " AND joueur.ID = match_tennis.ID_VAINQUEUR;");
				rs = ps.executeQuery();
				boolean empty = true;
				while(rs.next()) {	
					empty = false;
					winner.setText(rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"));
				}	
				if(empty == true) {
					winner.setText("pas de données");
				}
				ps.close();				
				rs = null;
				ps = cn.prepareStatement("SELECT joueur.nom, joueur.prenom "
										+ "FROM joueur, match_tennis "
										+ "WHERE match_tennis.ID_EPREUVE ='"+ID+"'"
										+ " AND joueur.ID = match_tennis.ID_FINALISTE;");
				rs = ps.executeQuery();
				empty=true;
				while(rs.next()) {		
					empty = false;
					finalist.setText(rs.getString(2) + " " + rs.getString(1));
				}
				if(empty == true) {
					finalist.setText("pas de données");
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
		
		public static void fillBox(JComboBox winner, JComboBox finalist, String sex) {
			Connection cn = BddConnection.getCn();
			PreparedStatement ps = null;	
			ResultSet rs =null;
			try {
				ps = cn.prepareStatement("SELECT joueur.NOM, joueur.PRENOM FROM joueur WHERE SEXE='"+sex+"'");
				rs = ps.executeQuery();
				while(rs.next()) {				
					winner.addItem(rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"));
					finalist.addItem(rs.getString("joueur.PRENOM") + " " + rs.getString("joueur.NOM"));
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
		
		public static String updateTournamentInfo(int tournamentID, String[] finalist, String[] winner) {
			Connection cn = BddConnection.getCn();
			PreparedStatement ps = null;	
			String message;
			String error;
			int finalistID = BddPlayer.getPlayerID(finalist[0]);
			int winnerID = BddPlayer.getPlayerID(winner[0]);
			try {
				ps = cn.prepareStatement("UPDATE match_tennis "
										+ "SET ID_VAINQUEUR='"+winnerID+"', "
										+ "ID_FINALISTE='"+finalistID+"' "
										+ "WHERE ID_EPREUVE='"+tournamentID+"'");				
				ps.executeUpdate();					
			}catch (SQLException e){
				e.printStackTrace();
				error = "Un problème est survenu" + e;
				return error;
			} finally {
				try {
					ps.close();					
				} catch (SQLException e) {
					e.printStackTrace();
					error = "Un problème est survenu" + e;
					return error;
				}	
			}
			message = "infos modifiées, la fenetre va automatiquement se fermer";
			return message;
		}
		
	}

		
	
