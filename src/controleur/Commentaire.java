package controleur;

public class Commentaire {
	
	private int idcom, idproduit, idclient;
	private String contenu;
	private int client_id;
	private String dateheurepost;
	
	public Commentaire(int idcom, int idproduit, int idclient, String contenu, int client_id, String dateheurepost) {
		this.idcom = idcom;
		this.idproduit = idproduit;
		this.idclient = idclient;
		this.contenu = contenu;
		this.client_id = client_id;
		this.dateheurepost = dateheurepost;
	}

	public int getIdcom() {
		return idcom;
	}

	public void setIdcom(int idcom) {
		this.idcom = idcom;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getClient_id() {
		return client_id;
	}

	public void setClient_id(int client_id) {
		this.client_id = client_id;
	}

	public String getDateheurepost() {
		return dateheurepost;
	}

	public void setDateheurepost(String dateheurepost) {
		this.dateheurepost = dateheurepost;
	}

}
