package controleur;

public class VMessage {
	
	private int idmessage, id_exp;
	private String expediteur;
	private int id_dest;
	private String destinataire, date_envoi, contenu;
	private int lu;
	
	public VMessage(int idmessage, int id_exp, String expediteur, int id_dest, String destinataire, String date_envoi,
			String contenu, int lu) {
		this.idmessage = idmessage;
		this.id_exp = id_exp;
		this.expediteur = expediteur;
		this.id_dest = id_dest;
		this.destinataire = destinataire;
		this.date_envoi = date_envoi;
		this.contenu = contenu;
		this.lu = lu;
	}

	public int getIdmessage() {
		return idmessage;
	}

	public void setIdmessage(int idmessage) {
		this.idmessage = idmessage;
	}

	public int getId_exp() {
		return id_exp;
	}

	public void setId_exp(int id_exp) {
		this.id_exp = id_exp;
	}

	public String getExpediteur() {
		return expediteur;
	}

	public void setExpediteur(String expediteur) {
		this.expediteur = expediteur;
	}

	public int getId_dest() {
		return id_dest;
	}

	public void setId_dest(int id_dest) {
		this.id_dest = id_dest;
	}

	public String getDestinataire() {
		return destinataire;
	}

	public void setDestinataire(String destinataire) {
		this.destinataire = destinataire;
	}

	public String getDate_envoi() {
		return date_envoi;
	}

	public void setDate_envoi(String date_envoi) {
		this.date_envoi = date_envoi;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getLu() {
		return lu;
	}

	public void setLu(int lu) {
		this.lu = lu;
	}

}
