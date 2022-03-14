package ProjetNadia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface BddPlayer {	
	
	/*==================== METHODE BASE DE DONNEES TAB JOUEUR =========================*/
	public static ListeJoueur GetPlayers(String choice) {
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;
		ResultSet rs =null;
		ListeJoueur liste = new ListeJoueur();	
		if(choice == null) {
			choice = "";
		}
		try {
			
			if(choice.equals("homme")) {
				ps = cn.prepareStatement("SELECT * FROM joueur WHERE SEXE='H' ");
			}else if(choice.equals("femme")) {
				ps = cn.prepareStatement("SELECT * FROM joueur WHERE SEXE='F' ");
			} else {
				ps = cn.prepareStatement("SELECT * FROM joueur");
			}			
			rs = ps.executeQuery();
			while(rs.next()) {				
				Joueur joueur = new Joueur(rs.getInt("joueur.ID"), rs.getString("joueur.NOM"), rs.getString("joueur.PRENOM"), rs.getString("joueur.SEXE"));
				liste.addJoueur(joueur);				
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
		return liste;		
	}
	
	public static String addPlayer(String name, String firstName, String sex) {
		String message;
		String error;
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;		
		try {
			ps = cn.prepareStatement("INSERT INTO joueur(NOM, PRENOM, SEXE) VALUES(?,?,?)");	
			ps.setString(1, name);
			ps.setString(2, firstName);
			if(sex.equals("femme")) {
				sex = "F";
			} else {
				sex = "H";
			}
			ps.setString(3, sex);
			ps.executeUpdate();					
		}catch (SQLException e){
			e.printStackTrace();
			error = "Un probl�me est survenu" + e;
			return error;
		} finally {
			try {
				ps.close();					
			} catch (SQLException e) {
				e.printStackTrace();
				error = "Un probl�me est survenu" + e;
				return error;
			}	
		}
		message = "joueur ajout�, la fenetre va automatiquement se fermer";
		return message;
	}
	
	public static String updatePlayer(int ID, String name, String firstName, String sex) {
		String message;
		String error;
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;		
		try {
			ps = cn.prepareStatement("UPDATE joueur SET NOM=?, PRENOM=?, SEXE=? WHERE ID=?");	
			ps.setString(1, name);
			ps.setString(2, firstName);
			if(sex.equals("femme")) {
				sex = "F";
			} else {
				sex = "H";
			}
			ps.setString(3, sex);
			ps.setInt(4, ID);
			ps.executeUpdate();					
		}catch (SQLException e){
			e.printStackTrace();
			error = "Un probl�me est survenu" + e;
			return error;
		} finally {
			try {
				ps.close();					
			} catch (SQLException e) {
				e.printStackTrace();
				error = "Un probl�me est survenu" + e;
				return error;
			}	
		}
		message = "joueur modifi�, la fenetre va automatiquement se fermer";
		return message;
	}
	
	public static String removePlayer(int ID) {
		String message;
		String error;
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<Integer> IdMatch = new ArrayList<Integer>();
		try {
			// R�cup�ration de tous les matchs de la joueuse
			ps = cn.prepareStatement("SELECT ID FROM match_tennis WHERE ID_VAINQUEUR=? OR ID_FINALISTE=?");
			ps.setInt(1, ID);
			ps.setInt(2, ID);
			rs = ps.executeQuery();
			while(rs.next()) {
				IdMatch.add(rs.getInt("match_tennis.ID"));
			}
			ps.close();
			for(int i=0; i<IdMatch.size(); i++) {
				ps = cn.prepareStatement("DELETE FROM score_vainqueur WHERE ID=?");
				ps.setInt(1, IdMatch.get(i));
				ps.executeUpdate();
			}
			ps.close();	
			
			ps = cn.prepareStatement("DELETE FROM match_tennis WHERE ID_VAINQUEUR=? OR ID_FINALISTE=?");
			ps.setInt(1, ID);
			ps.setInt(2, ID);
			ps.executeUpdate();
			ps.close();
			
			ps = cn.prepareStatement("DELETE FROM joueur WHERE ID=?");	
			ps.setInt(1, ID);
			ps.executeUpdate();					
		}catch (SQLException e){
			e.printStackTrace();
			error = "Un probl�me est survenu" + e;
			return error;
		} finally {
			try {
				ps.close();					
			} catch (SQLException e) {
				e.printStackTrace();
				error = "Un probl�me est survenu" + e;
				return error;
			}	
		}
		message = "joueur supprim�, la fenetre va automatiquement se fermer";
		return message;
	}
	
	public static ListeJoueur searchPlayer(String search) {
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;	
		ResultSet rs =null;
		ListeJoueur liste = new ListeJoueur();
		try {
			ps = cn.prepareStatement("SELECT * FROM joueur WHERE NOM LIKE '%" + search +"%' OR PRENOM LIKE '%"+search+"%'");
			rs = ps.executeQuery();
			while(rs.next()) {				
				Joueur joueur = new Joueur(rs.getInt("joueur.ID"), rs.getString("joueur.NOM"), rs.getString("joueur.PRENOM"), rs.getString("joueur.SEXE"));
				liste.addJoueur(joueur);				
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
		return liste;
	}
	
	public static int getPlayerID(String name) {
		Connection cn = BddConnection.getCn();
		PreparedStatement ps = null;	
		ResultSet rs =null;
		int playerID = 0;
		try {
			ps = cn.prepareStatement("SELECT joueur.ID FROM joueur WHERE PRENOM='"+name+"'");
			rs = ps.executeQuery();
			while(rs.next()) {				
				playerID = rs.getInt("joueur.ID");	
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
		return playerID;
	}
}
