package controleur;

public class HistoCommande {
	
	private int numcommande, idclient;
	private String mode_payement, etat;
	private float montantTotalHT, montantTotalTTC, TVA;
	private String datecommande, datelivraison;
	private int idproduit;
	private String dateHeureAction, user, action;
	
	public HistoCommande(int numcommande, int idclient, String mode_payement, String etat, float montantTotalHT,
			float montantTotalTTC, float TVA, String datecommande, String datelivraison, int idproduit,
			String dateHeureAction, String user, String action) {
		this.numcommande = numcommande;
		this.idclient = idclient;
		this.mode_payement = mode_payement;
		this.etat = etat;
		this.montantTotalHT = montantTotalHT;
		this.montantTotalTTC = montantTotalTTC;
		this.TVA = TVA;
		this.datecommande = datecommande;
		this.datelivraison = datelivraison;
		this.idproduit = idproduit;
		this.dateHeureAction = dateHeureAction;
		this.user = user;
		this.action = action;
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

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
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
