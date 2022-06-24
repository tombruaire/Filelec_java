package controleur;

public class Commande {
	
	private int numcommande, idclient;
	private String mode_payement, etat;
	private float montantTotalHT, montantTotalTTC, tva;
	private String datecommande, datelivraison;
	private int idproduit;
	
	public Commande(int numcommande, int idclient, String mode_payement, String etat,
			float montantTotalHT, float montantTotalTTC, float tva, String datecommande,
			String datelivraison, int idproduit) {
		this.numcommande = numcommande;
		this.idclient = idclient;
		this.mode_payement = mode_payement;
		this.etat = etat;
		this.montantTotalHT = montantTotalHT;
		this.montantTotalTTC = montantTotalTTC;
		this.tva = tva;
		this.datecommande = datecommande;
		this.datelivraison = datelivraison;
		this.idproduit = idproduit;
	}

	public int getNumcommande() {
		return numcommande;
	}

	public void setNumcommande(int numcommande) {
		this.numcommande = numcommande;
	}
	
	public int getIdclient() {
		return idclient;
	}
	
	public void setIdclient(int idclient) {
		this.idclient = idclient;
	}
	
	public String getModePayement() {
		return mode_payement;
	}
	
	public void setModePayement(String mode_payement) {
		this.mode_payement = mode_payement;
	}
	
	public String getEtat() {
		return etat;
	}
	
	public void setEtat(String etat) {
		this.etat = etat;
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
	
	public float getTVA() {
		return tva;
	}
	
	public void setTVA(float tva) {
		this.tva = tva;
	}
	
	public String getDatecommande() {
		return datecommande;
	}
	
	public void setDateCommande(String datecommande) {
		this.datecommande = datecommande;
	}
	
	public String getDatelivraison() {
		return datelivraison;
	}
	
	public void setDateLivraison(String datelivraison) {
		this.datelivraison = datelivraison;
	}
	
	public int getIdproduit() {
		return idproduit;
	}
	
	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

}
