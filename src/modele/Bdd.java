package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Bdd {
	
	private String serveur, bdd, user, mdp;
	private Connection maConnexion;
	
	public Bdd (String serveur, String bdd, String user, String mdp) {
		this.serveur = serveur;
		this.bdd = bdd;
		this.user = user;
		this.mdp = mdp;
		this.maConnexion = null;
	}
	
	public void seConnecter () {
		String url = "jdbc:mysql://"+this.serveur+"/"+this.bdd+"?verifyServerCertificate=false&useSSL=false";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.maConnexion = DriverManager.getConnection(url, this.user, this.mdp);
		} catch (ClassNotFoundException exp) {
			System.out.println("Absence du pilote jdbc");
		} catch (SQLException exp) {
			System.out.println("Erreur de connexion � : " + url);
			exp.printStackTrace();
		}
	}
	
	public void seDeconnecter () {
		try {
			if (this.maConnexion != null) {
				this.maConnexion.close();
			}
		} catch (SQLException exp) {
			System.out.println("Erreur de d�connexion au serveur !");
		}
	}
	
	public Connection getMaConnexion () {
		return this.maConnexion; 
	}

}
