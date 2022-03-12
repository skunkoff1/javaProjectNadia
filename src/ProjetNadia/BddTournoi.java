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
					model.addRow(new Object[] {rs.getInt(1), rs.getString(3), rs.getString(2), rs.getInt(4)});
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
			message = "tournoi supprimer, la fenetre va automatiquement se fermer";
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
					model.addRow(new Object[] {rs.getInt(1), rs.getString(3), rs.getString(2), rs.getInt(4)});			
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


