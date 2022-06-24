package controleur;

public class VPanier {
	
	private int numcommande, idproduit;
	private String nomproduit;
	private float prixproduit;
	private int quantite;
	private float montantTotalHT, montantTotalTTC;
	private String nomclient;
	private int idclient;
	
	public VPanier(int numcommande, int idproduit, String nomproduit, float prixproduit, int quantite, float montantTotalHT,
			float montantTotalTTC, String nomclient, int idclient) {
		this.numcommande = numcommande;
		this.idproduit = idproduit;
		this.nomproduit = nomproduit;
		this.prixproduit = prixproduit;
		this.quantite = quantite;
		this.montantTotalHT = montantTotalHT;
		this.montantTotalTTC = montantTotalTTC;
		this.nomclient = nomclient;
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
	
	public String getNomProduit() {
		return nomproduit;
	}

	public void setNomProduit(String nomproduit) {
		this.nomproduit = nomproduit;
	}

	public float getPrixproduit() {
		return prixproduit;
	}

	public void setPrixproduit(float prixproduit) {
		this.prixproduit = prixproduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public float getMontantTotalHT() {
		return montantTotalHT;
	}

	public void setMontantTotalHT(float montantTotalHT) {
		this.montantTotalHT = montantTotalHT;
	}

	public float getMontantTotalTTC() {
		return montantTotalTTC;
	}

	public void setMontantTotalTTC(float montantTotalTTC) {
		this.montantTotalTTC = montantTotalTTC;
	}

	public String getNomclient() {
		return nomclient;
	}

	public void setNomclient(String nomclient) {
		this.nomclient = nomclient;
	}

	public int getIdclient() {
		return idclient;
	}

	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}

}
