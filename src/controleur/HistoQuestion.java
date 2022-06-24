package controleur;

public class HistoQuestion {
	
	private int idquestion;
	private String enonce, dateHeureAction, user, action;
	
	public HistoQuestion(int idquestion, String enonce, String dateHeureAction, String user, String action) {
		this.idquestion = idquestion;
		this.enonce = enonce;
		this.dateHeureAction = dateHeureAction;
		this.user = user;
		this.action = action;
	}

	public int getIdquestion() {
		return idquestion;
	}

	public void setIdquestion(int idquestion) {
		this.idquestion = idquestion;
	}

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
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
