package controleur;

public class VCommande {
	
	private int numcommande;
	private String nom, adresse, cp, ville, pays, mode_payement, etat;
	private float montantTotalHT, montantTotalTTC, TVA;
	private String datecommande, datelivraison;
	private String produit;
	
	public VCommande(int numcommande, String nom, String adresse, String cp, String ville, String pays,
			String mode_payement, String etat, float montantTotalHT, float montantTotalTTC, float TVA,
			String datecommande, String datelivraison, String produit) {
		this.numcommande = numcommande;
		this.nom = nom;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
		this.mode_payement = mode_payement;
		this.etat = etat;
		this.montantTotalHT = montantTotalHT;
		this.montantTotalTTC = montantTotalTTC;
		this.TVA = TVA;
		this.datecommande = datecommande;
		this.datelivraison = datelivraison;
		this.produit = produit;
	}

	public int getNumcommande() {
		return numcommande;
	}

	public void setNumcommande(int numcommande) {
		this.numcommande = numcommande;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
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

	public String getMode_payement() {
		return mode_payement;
	}

	public void setMode_payement(String mode_payement) {
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
		return TVA;
	}

	public void setTVA(float TVA) {
		this.TVA = TVA;
	}

	public String getDatecommande() {
		return datecommande;
	}

	public void setDatecommande(String datecommande) {
		this.datecommande = datecommande;
	}

	public String getDatelivraison() {
		return datelivraison;
	}

	public void setDatelivraison(String datelivraison) {
		this.datelivraison = datelivraison;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}
	
}
