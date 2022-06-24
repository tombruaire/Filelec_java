package controleur;

public class VCommentaire {
	
	private int idcom;
	private String produit, client, contenu;
	private int client_id;
	private String dateheurepost;
	
	public VCommentaire(int idcom, String produit, String client, String contenu, int client_id, String dateheurepost) {
		this.idcom = idcom;
		this.produit = produit;
		this.client = client;
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

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public String getClient() {
		return client;
	}

	public void setIdclient(String client) {
		this.client = client;
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
