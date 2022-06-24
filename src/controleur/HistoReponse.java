package controleur;

public class HistoReponse {
	
	private int idreponse, idquestion;
	private String reponse;
	private int idclient;
	private String dateHeureAction, user, action;
	
	public HistoReponse(int idreponse, int idquestion, String reponse, int idclient, String dateHeureAction,
			String user, String action) {
		this.idreponse = idreponse;
		this.idquestion = idquestion;
		this.reponse = reponse;
		this.idclient = idclient;
		this.dateHeureAction = dateHeureAction;
		this.user = user;
		this.action = action;
	}

	public int getIdreponse() {
		return idreponse;
	}

	public void setIdreponse(int idreponse) {
		this.idreponse = idreponse;
	}

	public int getIdquestion() {
		return idquestion;
	}

	public void setIdquestion(int idquestion) {
		this.idquestion = idquestion;
	}

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
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
