package controleur;

public class Question {
	
	private int idquestion;
	private String enonce;
	
	public Question(int idquestion, String enonce) {
		this.idquestion = idquestion;
		this.enonce = enonce;
	}
	
	public Question(String enonce) {
		this.idquestion = 0;
		this.enonce = enonce;
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

}
