package ProjetNadia;

public class Joueur {
	
	/*=============== CELA N'ETAIT PAS UTILE DE FAIRE UN OBJET JOUEUR =================*/
	/*==================== MAIS CA POURRAIT SERVIR A TERME ============================*/
	private int ID;
	private String nom;
	private String prenom;
	private String sexe;
	
	public Joueur(int ID, String nom, String prenom, String sexe) {
		this.ID = ID;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
}
