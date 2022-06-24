package controleur;

public class VFacture {
	
	private int idfacture;
	private String datefacture;
	private int idclient;
	private String nom, email, adresse, cp, ville, pays;
	private int idproduit;
	private String produit;
	private float prix, montantTotalHT, montantTotalTTC, tva;
	private String datecommande, datelivraison;
	private int numcommande;
	private String mode_payement, etat;
	private int quantite;
	
	public VFacture(int idfacture, String datefacture, int idclient, String nom, String email, String adresse,
			String cp, String ville, String pays, int idproduit, String produit, float prix, float montantTotalHT,
			float montantTotalTTC, float tva, String datecommande, String datelivraison, int numcommande,
			String mode_payement, String etat, int quantite) {
		this.idfacture = idfacture;
		this.datefacture = datefacture;
		this.idclient = idclient;
		this.nom = nom;
		this.email = email;
		this.adresse = adresse;
		this.cp = cp;
		this.ville = ville;
		this.pays = pays;
		this.idproduit = idproduit;
		this.produit = produit;
		this.prix = prix;
		this.montantTotalHT = montantTotalHT;
		this.montantTotalTTC = montantTotalTTC;
		this.tva = tva;
		this.datecommande = datecommande;
		this.datelivraison = datelivraison;
		this.numcommande = numcommande;
		this.mode_payement = mode_payement;
		this.etat = etat;
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

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public String getProduit() {
		return produit;
	}

	public void setProduit(String produit) {
		this.produit = produit;
	}

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
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

	public void setTVA(float TVA) {
		this.tva = tva;
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

	public int getNumcommande() {
		return numcommande;
	}

	public void setNumcommande(int numcommande) {
		this.numcommande = numcommande;
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

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

}
