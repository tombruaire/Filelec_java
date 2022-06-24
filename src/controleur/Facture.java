package controleur;

public class Facture {
	
	private int idfacture;
	private String datefacture;
	private int idclient, idproduit, numcommande, quantite;
	
	public Facture(int idfacture, String datefacture, int idclient, int idproduit, int numcommande, int quantite) {
		this.idfacture = idfacture;
		this.datefacture = datefacture;
		this.idclient = idclient;
		this.idproduit = idproduit;
		this.numcommande = numcommande;
		this.quantite = quantite;
	}

	public int getIdfacture() {
		return idfacture;
	}

	public void setIdfacture(int idfacture) {
		this.idfacture = idfacture;
	}

	public String getDatefacture() {
		return datefacture;
	}

	public void setDatefacture(String datefacture) {
		this.datefacture = datefacture;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public int getNumcommande() {
		return numcommande;
	}

	public void setNumcommande(int numcommande) {
		this.numcommande = numcommande;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
