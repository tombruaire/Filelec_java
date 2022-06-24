package controleur;

public class Message {
	
	private int idmessage, id_exp, id_dest;
	private String date_envoi, contenu;
	private int lu;
	
	public Message(int idmessage, int id_exp, int id_dest, String date_envoi, String contenu, int lu) {
		this.idmessage = idmessage;
		this.id_exp = id_exp;
		this.id_dest = id_dest;
		this.date_envoi = date_envoi;
		this.contenu = contenu;
		this.lu = lu;
	}

	public int getIdmessage() {
		return idmessage;
	}

	public void setIdmessage(int idmessage) {
		this.idmessage = idmessage;
	}

	public int getId_exp() {
		return id_exp;
	}

	public void setId_exp(int id_exp) {
		this.id_exp = id_exp;
	}

	public int getId_dest() {
		return id_dest;
	}

	public void setId_dest(int id_dest) {
		this.id_dest = id_dest;
	}

	public String getDate_envoi() {
		return date_envoi;
	}

	public void setDate_envoi(String date_envoi) {
		this.date_envoi = date_envoi;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public int getLu() {
		return lu;
	}

	public void setLu(int lu) {
		this.lu = lu;
	}

}
