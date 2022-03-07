package ProjetNadia;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public interface Bdd {	
	
	public static ListeJoueur GetPlayers(String choice) {
		String url = BddConnection.getUrl();
		String login = BddConnection.getLogin();
		String password = BddConnection.getPassword();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		ListeJoueur liste = new ListeJoueur();	
		if(choice == null) {
			choice = "";
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, password);
			if(choice.equals("homme")) {
				ps = cn.prepareStatement("SELECT * FROM joueur WHERE SEXE='H' ");
			}else if(choice.equals("femme")) {
				ps = cn.prepareStatement("SELECT * FROM joueur WHERE SEXE='F' ");
			} else {
				ps = cn.prepareStatement("SELECT * FROM joueur");
			}			
			rs = ps.executeQuery();
			while(rs.next()) {				
				Joueur joueur = new Joueur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				liste.addJoueur(joueur);				
				}			
			}catch (SQLException e){
				e.printStackTrace();
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				try {
					cn.close();
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return liste;		
	}
	
	public static String addPlayer(String name, String firstName, String sex) {
		String url = BddConnection.getUrl();
		String login = BddConnection.getLogin();
		String password = BddConnection.getPassword();		
		String message;
		String error;
		Connection cn = null;
		PreparedStatement ps = null;		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, password);
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
				error = "Un problème est survenu" + e;
				return error;
			}
				catch (ClassNotFoundException e) {
				e.printStackTrace();
				error = "Un problème est survenu" + e;
				return error;
			}
			finally {
				try {
					cn.close();
					ps.close();					
				} catch (SQLException e) {
					e.printStackTrace();
					error = "Un problème est survenu" + e;
					return error;
				}	
			}
		message = "joueur ajouté, la fenetre va automatiquement se fermer";
		return message;
	}
	
	public static String updatePlayer(int ID, String name, String firstName, String sex) {
		String url = BddConnection.getUrl();
		String login = BddConnection.getLogin();
		String password = BddConnection.getPassword();	
		String message;
		String error;
		Connection cn = null;
		PreparedStatement ps = null;		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, password);
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
				error = "Un problème est survenu" + e;
				return error;
			}
				catch (ClassNotFoundException e) {
				e.printStackTrace();
				error = "Un problème est survenu" + e;
				return error;
			}
			finally {
				try {
					cn.close();
					ps.close();					
				} catch (SQLException e) {
					e.printStackTrace();
					error = "Un problème est survenu" + e;
					return error;
				}	
			}
		message = "joueur modifié, la fenetre va automatiquement se fermer";
		return message;
	}
	
	public static String removePlayer(int ID) {
		String url = BddConnection.getUrl();
		String login = BddConnection.getLogin();
		String password = BddConnection.getPassword();	
		String message;
		String error;
		Connection cn = null;
		PreparedStatement ps = null;		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, password);
			ps = cn.prepareStatement("DELETE FROM joueur WHERE ID=?");	
			ps.setInt(1, ID);
			ps.executeUpdate();
					
			}catch (SQLException e){
				e.printStackTrace();
				error = "Un problème est survenu" + e;
				return error;
			}
				catch (ClassNotFoundException e) {
				e.printStackTrace();
				error = "Un problème est survenu" + e;
				return error;
			}
			finally {
				try {
					cn.close();
					ps.close();					
				} catch (SQLException e) {
					e.printStackTrace();
					error = "Un problème est survenu" + e;
					return error;
				}	
			}
		message = "joueur supprimé, la fenetre va automatiquement se fermer";
		return message;
	}
	
	public static ListeJoueur searchPlayer(String search) {
		String url = BddConnection.getUrl();
		String login = BddConnection.getLogin();
		String password = BddConnection.getPassword();	
		Connection cn = null;
		PreparedStatement ps = null;	
		ResultSet rs =null;
		ListeJoueur liste = new ListeJoueur();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn = DriverManager.getConnection(url, login, password);
			ps = cn.prepareStatement("SELECT * FROM joueur WHERE NOM LIKE '%" + search +"%' OR PRENOM LIKE '%"+search+"%'");
			rs = ps.executeQuery();
			while(rs.next()) {				
				Joueur joueur = new Joueur(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				liste.addJoueur(joueur);				
				}	
					
			}catch (SQLException e){
				e.printStackTrace();
			}
				catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			finally {
				try {
					cn.close();
					ps.close();					
				} catch (SQLException e) {
					e.printStackTrace();
				}	
			}
		return liste;
	}
}
