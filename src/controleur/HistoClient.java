package controleur;

public class HistoClient {
	
	private int idclient;
	private String nom, tel, email, mdp, adresse, cp, ville, pays, etat, role;
	private int nbTentatives, bloque, nbConnexion;
	private String type, date_creation_mdp, date_dernier_changement_mdp, date_creation_compte, connexion, deconnexion, 
	dateHeureAction, user, action;
	
	public HistoClient(int idclient, String nom, String tel, String email, String mdp, String adresse, String cp,
			String ville, String pays, String etat, String role, int nbTentatives, int bloque, int nbConnexion,
			String type, String date_creation_mdp, String date_dernier_changement_mdp, String date_creation_compte,
			String connexion, String deconnexion, String dateHeureAction, String user, String action) {
		this.idclient = idclient;
		this.nom = nom;
		this.tel = tel;
		this.email = email;
		this.mdp = mdp;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
		this.etat = etat;
		this.role = role;
		this.nbTentatives = nbTentatives;
		this.bloque = bloque;
		this.nbConnexion = nbConnexion;
		this.type = type;
		this.date_creation_mdp = date_creation_mdp;
		this.date_dernier_changement_mdp = date_dernier_changement_mdp;
		this.date_creation_compte = date_creation_compte;
		this.connexion = connexion;
		this.deconnexion = deconnexion;
		this.dateHeureAction = dateHeureAction;
		this.user = user;
		this.action = action;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getEtat() {
		return etat;
	}

	public void setEtat(String etat) {
		this.etat = etat;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getNbTentatives() {
		return nbTentatives;
	}

	public void setNbTentatives(int nbTentatives) {
		this.nbTentatives = nbTentatives;
	}

	public int getBloque() {
		return bloque;
	}

	public void setBloque(int bloque) {
		this.bloque = bloque;
	}

	public int getNbConnexion() {
		return nbConnexion;
	}

	public void setNbConnexion(int nbConnexion) {
		this.nbConnexion = nbConnexion;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDate_creation_mdp() {
		return date_creation_mdp;
	}

	public void setDate_creation_mdp(String date_creation_mdp) {
		this.date_creation_mdp = date_creation_mdp;
	}

	public String getDate_dernier_changement_mdp() {
		return date_dernier_changement_mdp;
	}

	public void setDate_dernier_changement_mdp(String date_dernier_changement_mdp) {
		this.date_dernier_changement_mdp = date_dernier_changement_mdp;
	}

	public String getDate_creation_compte() {
		return date_creation_compte;
	}

	public void setDate_creation_compte(String date_creation_compte) {
		this.date_creation_compte = date_creation_compte;
	}

	public String getConnexion() {
		return connexion;
	}

	public void setConnexion(String connexion) {
		this.connexion = connexion;
	}

	public String getDeconnexion() {
		return deconnexion;
	}

	public void setDeconnexion(String deconnexion) {
		this.deconnexion = deconnexion;
	}

	public String getDateHeureAction() {
		return dateHeureAction;
	}

	public void setDateHeureAction(String dateHeureAction) {
		this.dateHeureAction = dateHeureAction;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
