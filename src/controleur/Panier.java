package controleur;

public class Panier {
	
	private int numcommande, idproduit, quantite, idclient;

	public Panier(int numcommande, int idproduit, int quantite, int idclient) {
		this.numcommande = numcommande;
		this.idproduit = idproduit;
		this.quantite = quantite;
		this.idclient = idclient;
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

}
