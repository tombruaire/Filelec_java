package controleur;

public class HistoCommentaire {
	
	private int idcom, idproduit, idclient;
	private String contenu;
	private int client_id;
	private String dateheurepost, dateHeureAction, user, action;
	
	public HistoCommentaire(int idcom, int idproduit, int idclient, String contenu, int client_id, String dateheurepost,
			String dateHeureAction, String user, String action) {
		this.idcom = idcom;
		this.idproduit = idproduit;
		this.idclient = idclient;
		this.contenu = contenu;
		this.client_id = client_id;
		this.dateheurepost = dateheurepost;
		this.dateHeureAction = dateHeureAction;
		this.user = user;
		this.action = action;
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
