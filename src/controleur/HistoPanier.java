package controleur;

public class HistoPanier {
	
	private int numcommande, idproduit, quantite, idclient;
	private String dateHeureAction, user, action;

	public HistoPanier(int numcommande, int idproduit, int quantite, int idclient,
			String dateHeureAction, String user, String action) {
		this.numcommande = numcommande;
		this.idproduit = idproduit;
		this.quantite = quantite;
		this.idclient = idclient;
		this.dateHeureAction = dateHeureAction;
		this.user = dateHeureAction;
		this.action = action;
	}

	public int getNumcommande() {
		return numcommande;
	}

	public void setNumcommande(int numcommande) {
		this.numcommande = numcommande;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
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
