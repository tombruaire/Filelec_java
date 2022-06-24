package controleur;

public class Reponse {
	
	private int idreponse, idquestion;
	private String reponse;
	private int idclient;
	
	public Reponse(int idreponse, int idquestion, String reponse, int idclient) {
		this.idreponse = idreponse;
		this.idquestion = idquestion;
		this.reponse = reponse;
		this.idclient = idclient;
	}
	
	public Reponse(int idquestion, String reponse, int idclient) {
		this.idreponse = 0;
		this.idquestion = idquestion;
		this.reponse = reponse;
		this.idclient = idclient;
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

}
