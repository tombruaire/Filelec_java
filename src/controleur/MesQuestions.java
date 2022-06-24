package controleur;

public class MesQuestions {
	
	private int idreponse, idquestion;
	private String enonce, reponse;
	private int idclient;
	private String client, email;
	
	public MesQuestions(int idreponse, int idquestion, String enonce, String reponse, int idclient, String client, String email) {
		this.idreponse = idreponse;
		this.idquestion = idquestion;
		this.enonce = enonce;
		this.reponse = reponse;
		this.idclient = idclient;
		this.client = client;
		this.email = email;
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

	public String getEnonce() {
		return enonce;
	}

	public void setEnonce(String enonce) {
		this.enonce = enonce;
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
	
	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
