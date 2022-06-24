package controleur;

public class HistoProduit {
	
	private int idproduit;
	private String nomproduit, imageProduit, descriptionproduit;
	private int qteproduit;
	private float prixproduit;
	private int idtype;
	private String date_ajout, dateHeureAction, user, action;
	
	public HistoProduit(int idproduit, String nomproduit, String imageProduit, String descriptionproduit,
			int qteproduit, float prixproduit, int idtype, String date_ajout, String dateHeureAction, String user,
			String action) {
		this.idproduit = idproduit;
		this.nomproduit = nomproduit;
		this.imageProduit = imageProduit;
		this.descriptionproduit = descriptionproduit;
		this.qteproduit = qteproduit;
		this.prixproduit = prixproduit;
		this.idtype = idtype;
		this.date_ajout = date_ajout;
		this.dateHeureAction = dateHeureAction;
		this.user = user;
		this.action = action;
	}

	public int getIdproduit() {
		return idproduit;
	}

	public void setIdproduit(int idproduit) {
		this.idproduit = idproduit;
	}

	public String getNomproduit() {
		return nomproduit;
	}

	public void setNomproduit(String nomproduit) {
		this.nomproduit = nomproduit;
	}

	public String getImageProduit() {
		return imageProduit;
	}

	public void setImageProduit(String imageProduit) {
		this.imageProduit = imageProduit;
	}

	public String getDescriptionproduit() {
		return descriptionproduit;
	}

	public void setDescriptionproduit(String descriptionproduit) {
		this.descriptionproduit = descriptionproduit;
	}

	public int getQteproduit() {
		return qteproduit;
	}

	public void setQteproduit(int qteproduit) {
		this.qteproduit = qteproduit;
	}

	public float getPrixproduit() {
		return prixproduit;
	}

	public void setPrixproduit(float prixproduit) {
		this.prixproduit = prixproduit;
	}

	public int getIdtype() {
		return idtype;
	}

	public void setIdtype(int idtype) {
		this.idtype = idtype;
	}

	public String getDate_ajout() {
		return date_ajout;
	}

	public void setDate_ajout(String date_ajout) {
		this.date_ajout = date_ajout;
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
