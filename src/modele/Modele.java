package modele;

import java.sql.*;
import java.io.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Client;
import controleur.Commande;
import controleur.Commentaire;
import controleur.Facture;
import controleur.HistoClient;
import controleur.HistoCommande;
import controleur.HistoCommentaire;
import controleur.HistoFacture;
import controleur.HistoMessage;
import controleur.HistoPanier;
import controleur.HistoParticulier;
import controleur.HistoProduit;
import controleur.HistoProfessionnel;
import controleur.HistoQuestion;
import controleur.HistoReponse;
import controleur.MesQuestions;
import controleur.Message;
import controleur.Panier;
import controleur.Particulier;
import controleur.Produit;
import controleur.Professionnel;
import controleur.Question;
import controleur.Reponse;
import controleur.Type;
import controleur.VClient;
import controleur.VCommande;
import controleur.VCommentaire;
import controleur.VFacture;
import controleur.VMessage;
import controleur.VPanier;
import controleur.VProduit;
import controleur.Vstatsproduits;

public class Modele {
	
	private static Bdd uneBdd = new Bdd ("localhost", "filelec", "root", "");
	
	// private static Bdd uneBdd = new Bdd ("172.20.111.118", "filelec", "tom", "tom");
	
	/*** GESTION DES CLIENTS ***/
	
	// Sélection d'un client en fonction de son email
	public static Client selectWhereEmailClient (String email) {
		Client unClient = null;
		String requete = "SELECT * FROM client WHERE email = '"+email+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unClient = new Client (
						unResultat.getInt("idclient"),
						unResultat.getString("nom"),
						unResultat.getString("tel"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("adresse"),
						unResultat.getString("cp"),
						unResultat.getString("ville"),
						unResultat.getString("pays"),
						unResultat.getString("etat"),
						unResultat.getString("role"),
						unResultat.getString("type"),
						unResultat.getInt("nbTentatives"),
						unResultat.getInt("bloque"),
						unResultat.getInt("nbConnexion"),
						unResultat.getString("date_creation_mdp"),
						unResultat.getString("date_dernier_changement_mdp"),
						unResultat.getString("date_creation_compte"),
						unResultat.getString("connexion"),
						unResultat.getString("deconnexion")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unClient;
	}
	
	// Sélection d'un client en fonction de son email et de son mdp
	public static Client selectWhereClient (String email, String mdp) {
		Client unClient = null;
		String requete = "SELECT * FROM client WHERE email = '"+email+"' and mdp = '"+mdp+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unClient = new Client (
						unResultat.getInt("idclient"),
						unResultat.getString("nom"),
						unResultat.getString("tel"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("adresse"),
						unResultat.getString("cp"),
						unResultat.getString("ville"),
						unResultat.getString("pays"),
						unResultat.getString("etat"),
						unResultat.getString("role"),
						unResultat.getString("type"),
						unResultat.getInt("nbTentatives"),
						unResultat.getInt("bloque"),
						unResultat.getInt("nbConnexion"),
						unResultat.getString("date_creation_mdp"),
						unResultat.getString("date_dernier_changement_mdp"),
						unResultat.getString("date_creation_compte"),
						unResultat.getString("connexion"),
						unResultat.getString("deconnexion")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unClient;
	}
	
	// Mise à jour du nombre de tentatives du client (nbTentatives + 1)
	public static void updateNbTentatives (Client unClient) {
		String requete = "UPDATE client SET nbTentatives = "
				+ (unClient.getNbTentatives()+1) + " WHERE email = '"+unClient.getEmail()+"' ;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	// Remise à 0 du nombre de tentatives du client
	public static void resetNbTentatives (Client unClient) {
		String requete = "UPDATE client SET nbTentatives = 0 WHERE email = '"+unClient.getEmail()+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	// Bloquage du compte du client
	public static void bloquerCompte (Client unClient) {
		String requete = "UPDATE client SET bloque = 1 WHERE email = '"+unClient.getEmail()+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	// Mise à jour de la date de dernière connexion du client
	public static void updateConnexion (Client unClient) {
		String requete = "UPDATE client SET connexion = NOW() WHERE email = '"+unClient.getEmail()+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	// Mise à jour du nombre de connexion du client
	public static void updateNbConnexion (Client unClient) {
		String requete = "UPDATE client SET nbConnexion = nbConnexion + 1 WHERE email = '"+unClient.getEmail()+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requete : " + requete);
		}
	}
	
	// Sélection de tous les clients
	public static ArrayList<Client> selectAllClients () {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		String requete = "SELECT * FROM client;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Client unClient = new Client (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("connexion"),
						desResultats.getString("deconnexion")
						);
				lesClients.add(unClient);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesClients;
	}
	
	// Recherche d'un client
	public static ArrayList<Client> selectLikeClient(String mot) {
		ArrayList<Client> lesClients = new ArrayList<Client>();
		String requete = "SELECT * FROM client WHERE "
				+ "nom LIKE '%"+mot+"%' OR "
				+ "tel LIKE '%"+mot+"%' OR "
				+ "email LIKE '%"+mot+"%' OR "
				+ "etat LIKE '%"+mot+"%' OR "
				+ "role LIKE '%"+mot+"%' OR "
				+ "type LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Client unClient = new Client (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("connexion"),
						desResultats.getString("deconnexion")
						);
				lesClients.add(unClient);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesClients;
	}
	
	// Sélection de tous les clients de la view 'vclient'
	public static ArrayList<VClient> selectAllVClients () {
		ArrayList<VClient> lesClients = new ArrayList<VClient>();
		String requete = "SELECT * FROM vclient;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VClient unClient = new VClient (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("connexion"),
						desResultats.getString("deconnexion")
						);
				lesClients.add(unClient);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesClients;
	}
	
	// Recherche d'un client à partir de la view 'vclient'
	public static ArrayList<VClient> selectLikeVClient(String mot) {
		ArrayList<VClient> lesClients = new ArrayList<VClient>();
		String requete = "SELECT * FROM vclient WHERE "
				+ "nom LIKE '%"+mot+"%' OR "
				+ "tel LIKE '%"+mot+"%' OR "
				+ "email LIKE '%"+mot+"%' OR "
				+ "etat LIKE '%"+mot+"%' OR "
				+ "role LIKE '%"+mot+"%' OR "
				+ "type LIKE '%"+mot+"%' OR "
				+ "connexion LIKE '%"+mot+"%' OR "
				+ "deconnexion LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VClient unClient = new VClient (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("connexion"),
						desResultats.getString("deconnexion")
						);
				lesClients.add(unClient);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesClients;
	}
	
	// Sélection d'un client en fonction de son email à partir de la view 'vclient'
	public static VClient selectWhereEmailVClient (String email) {
		VClient unClient = null;
		String requete = "SELECT * FROM vclient WHERE email = '"+email+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unClient = new VClient (
						unResultat.getInt("idclient"),
						unResultat.getString("nom"),
						unResultat.getString("tel"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("adresse"),
						unResultat.getString("cp"),
						unResultat.getString("ville"),
						unResultat.getString("pays"),
						unResultat.getString("etat"),
						unResultat.getString("role"),
						unResultat.getString("type"),
						unResultat.getInt("nbTentatives"),
						unResultat.getInt("bloque"),
						unResultat.getInt("nbConnexion"),
						unResultat.getString("date_creation_mdp"),
						unResultat.getString("date_dernier_changement_mdp"),
						unResultat.getString("date_creation_compte"),
						unResultat.getString("connexion"),
						unResultat.getString("deconnexion")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unClient;
	}
	
	// Sélection de tous les clients archivés
	public static ArrayList<HistoClient> selectAllHistoClients () {
		ArrayList<HistoClient> lesClients = new ArrayList<HistoClient>();
		String requete = "SELECT * FROM histoClient;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoClient unClient = new HistoClient (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("type"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("connexion"),
						desResultats.getString("deconnexion"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesClients.add(unClient);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesClients;
	}
	
	// Recherche d'un client archivé
	public static ArrayList<HistoClient> selectLikeHistoClient(String mot) {
		ArrayList<HistoClient> lesClients = new ArrayList<HistoClient>();
		String requete = "SELECT * FROM histoClient WHERE "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "nom LIKE '%"+mot+"%' OR "
				+ "type LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoClient unClient = new HistoClient (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("type"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("connexion"),
						desResultats.getString("deconnexion"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesClients.add(unClient);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesClients;
	}
	
	// Sélection des 3 dernières connexion d'un client
	public static ArrayList<VClient> selectLastConnexionClients () {
		ArrayList<VClient> lesClients = new ArrayList<VClient>();
		String requete = "SELECT * FROM vclient ORDER BY connexion DESC LIMIT 3;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VClient unClient = new VClient (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("connexion"),
						desResultats.getString("deconnexion")
						);
				lesClients.add(unClient);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesClients;
	}
	
	// Nombre de clients
	public static int countClients () {
		int nbclient = 0;
		String requete = "SELECT count(*) as nb FROM client;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbclient = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbclient;
	}
	
	/*** GESTION DES PARTICULIERS ***/
	
	// Insertion d'un particulier
	public static void insertParticulier (Particulier unParticulier) {
		String requete = "call insertParticulier('"
				+ unParticulier.getNom() + "', '"
				+ unParticulier.getPrenom() + "', '"
				+ unParticulier.getTel() + "', '"
				+ unParticulier.getEmail() + "', '"
				+ unParticulier.getMdp() + "', '"
				+ unParticulier.getAdresse() + "', '"
				+ unParticulier.getCp() + "', '"
				+ unParticulier.getVille() + "', '"
				+ unParticulier.getPays() + "', '"
				+ unParticulier.getEtat() + "', '"
				+ unParticulier.getRole() + "'); ";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Sélection de tous les particuliers
	public static ArrayList<Particulier> selectAllParticuliers () {
		ArrayList<Particulier> lesParticuliers = new ArrayList<Particulier>();
		String requete = "SELECT * FROM particulier;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Particulier unParticulier = new Particulier (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getString("prenom")
						);
				lesParticuliers.add(unParticulier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesParticuliers;
	}
	
	// Recherche d'un particulier
	public static ArrayList<Particulier> selectLikeParticulier(String mot) {
		ArrayList<Particulier> lesParticuliers = new ArrayList<Particulier>();
		String requete = "SELECT * FROM particulier WHERE "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "nom LIKE '%"+mot+"%' OR "
				+ "tel LIKE '%"+mot+"%' OR "
				+ "email LIKE '%"+mot+"%' OR "
				+ "adresse LIKE '%"+mot+"%' OR "
				+ "cp LIKE '%"+mot+"%' OR "
				+ "ville LIKE '%"+mot+"%' OR "
				+ "pays LIKE '%"+mot+"%' OR "
				+ "etat LIKE '%"+mot+"%' OR "
				+ "role LIKE '%"+mot+"%' OR "
				+ "type LIKE '%"+mot+"%' OR "
				+ "prenom LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Particulier unParticulier = new Particulier (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getString("prenom")
						);
				lesParticuliers.add(unParticulier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesParticuliers;
	}
	
	// Sélection d'un particulier
	public static Particulier selectWhereParticulier (String tel, String email) {
		Particulier unParticulier = null;
		String requete = "SELECT * FROM particulier WHERE tel = '"+tel+"' AND email = '"+email+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unParticulier = new Particulier (
						unResultat.getInt("idclient"),
						unResultat.getString("nom"),
						unResultat.getString("tel"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("adresse"),
						unResultat.getString("cp"),
						unResultat.getString("ville"),
						unResultat.getString("pays"),
						unResultat.getString("etat"),
						unResultat.getString("role"),
						unResultat.getString("type"),
						unResultat.getString("prenom")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unParticulier;
	}
	
	// Edition d'un particulier
	public static void updateParticulier (Particulier unParticulier) {
		String requete = "call updateParticulier('"
				+ unParticulier.getNom() + "', '"
				+ unParticulier.getPrenom() + "', '"
				+ unParticulier.getTel() + "', '"
				+ unParticulier.getEmail() + "', '"
				+ unParticulier.getMdp() + "', '"
				+ unParticulier.getAdresse() + "', '"
				+ unParticulier.getCp() + "', '"
				+ unParticulier.getVille() + "', '"
				+ unParticulier.getPays() + "', '"
				+ unParticulier.getEtat() + "', '"
				+ unParticulier.getRole() + "', 0, 0, sysdate());";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Suppression d'un particulier
	public static void deleteParticulier (String tel, String email) {
		String requete = "call deleteParticulier('"+tel+"', '"+email+"');";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Sélection de tous les particuliers archivés
	public static ArrayList<HistoParticulier> selectAllHistoParticuliers () {
		ArrayList<HistoParticulier> lesParticuliers = new ArrayList<HistoParticulier>();
		String requete = "SELECT * FROM histoParticulier;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoParticulier unParticulier = new HistoParticulier (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("type"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesParticuliers.add(unParticulier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesParticuliers;
	}
	
	// Recherche d'un particulier archivé
	public static ArrayList<HistoParticulier> selectLikeHistoParticulier(String mot) {
		ArrayList<HistoParticulier> lesParticuliers = new ArrayList<HistoParticulier>();
		String requete = "SELECT * FROM histoParticulier WHERE "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "nom LIKE '%"+mot+"%' OR "
				+ "type LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoParticulier unParticulier = new HistoParticulier (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("prenom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("type"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesParticuliers.add(unParticulier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesParticuliers;
	}
	
	// Nombre de particuliers
	public static int countParticuliers () {
		int nbparticulier = 0;
		String requete = "SELECT count(*) as nb FROM particulier;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbparticulier = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbparticulier;
	}
	
	/*** GESTION DES PROFESSIONNELS ***/
	
	// Insertion d'un professionnel
	public static void insertProfessionnel (Professionnel unProfessionnel) {
		String requete = "call insertProfessionnel('"
				+ unProfessionnel.getNom() + "', '"
				+ unProfessionnel.getTel() + "', '"
				+ unProfessionnel.getEmail() + "', '"
				+ unProfessionnel.getMdp() + "', '"
				+ unProfessionnel.getAdresse() + "', '"
				+ unProfessionnel.getCp() + "', '"
				+ unProfessionnel.getVille() + "', '"
				+ unProfessionnel.getPays() + "', '"
				+ unProfessionnel.getNumSITRET() + "', '"
				+ unProfessionnel.getStatut() + "', '"
				+ unProfessionnel.getEtat() + "', '"
				+ unProfessionnel.getRole() + "'); ";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Sélection de tous les professionnels
	public static ArrayList<Professionnel> selectAllProfessionnels () {
		ArrayList<Professionnel> lesProfessionnels = new ArrayList<Professionnel>();
		String requete = "SELECT * FROM professionnel;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Professionnel unProfessionnel = new Professionnel (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getString("numSIRET"),
						desResultats.getString("statut")
						);
				lesProfessionnels.add(unProfessionnel);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProfessionnels;
	}
	
	// Recherche d'un professionnel
	public static ArrayList<Professionnel> selectLikeProfessionnel(String mot) {
		ArrayList<Professionnel> lesProfessionnels = new ArrayList<Professionnel>();
		String requete = "SELECT * FROM professionnel WHERE "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "nom LIKE '%"+mot+"%' OR "
				+ "tel LIKE '%"+mot+"%' OR "
				+ "email LIKE '%"+mot+"%' OR "
				+ "adresse LIKE '%"+mot+"%' OR "
				+ "cp LIKE '%"+mot+"%' OR "
				+ "ville LIKE '%"+mot+"%' OR "
				+ "pays LIKE '%"+mot+"%' OR "
				+ "numSIRET LIKE '%"+mot+"%' OR "
				+ "statut LIKE '%"+mot+"%' OR "
				+ "etat LIKE '%"+mot+"%' OR "
				+ "role LIKE '%"+mot+"%' OR "
				+ "type LIKE '%"+mot+"%'; ";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Professionnel unProfessionnel = new Professionnel (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getString("type"),
						desResultats.getString("numSIRET"),
						desResultats.getString("statut")
						);
				lesProfessionnels.add(unProfessionnel);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProfessionnels;
	}
	
	// Sélection d'un professionnel
	public static Professionnel selectWhereProfessionnel (String tel, String email) {
		Professionnel unProfessionnel = null;
		String requete = "SELECT * FROM professionnel WHERE tel = '"+tel+"' AND email = '"+email+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unProfessionnel = new Professionnel (
						unResultat.getInt("idclient"),
						unResultat.getString("nom"),
						unResultat.getString("tel"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("adresse"),
						unResultat.getString("cp"),
						unResultat.getString("ville"),
						unResultat.getString("pays"),
						unResultat.getString("etat"),
						unResultat.getString("role"),
						unResultat.getString("type"),
						unResultat.getString("numSIRET"),
						unResultat.getString("statut")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unProfessionnel;
	}
	
	// Edition d'un professionnel
	public static void updateProfessionnel (Professionnel unProfessionnel) {
		String requete = "call updateProfessionnel('"
				+ unProfessionnel.getNom() + "', '"
				+ unProfessionnel.getTel() + "', '"
				+ unProfessionnel.getEmail() + "', '"
				+ unProfessionnel.getMdp() + "', '"
				+ unProfessionnel.getAdresse() + "', '"
				+ unProfessionnel.getCp() + "', '"
				+ unProfessionnel.getVille() + "', '"
				+ unProfessionnel.getPays() + "', '"
				+ unProfessionnel.getStatut() + "', '"
				+ unProfessionnel.getEtat() + "', '"
				+ unProfessionnel.getRole() + "', 0, 0, sysdate());";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Suppression d'un professionnel
	public static void deleteProfessionnel (String tel, String email) {
		String requete = "call deleteProfessionnel('"+tel+"', '"+email+"');";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Sélection de tous les professionnels archivés
	public static ArrayList<HistoProfessionnel> selectAllHistoProfessionnels () {
		ArrayList<HistoProfessionnel> lesProfessionnels = new ArrayList<HistoProfessionnel>();
		String requete = "SELECT * FROM histoProfessionnel;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoProfessionnel unProfessionnel = new HistoProfessionnel (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("numSIRET"),
						desResultats.getString("statut"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("type"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesProfessionnels.add(unProfessionnel);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProfessionnels;
	}
	
	// Recherche d'un professionnel archivé
	public static ArrayList<HistoProfessionnel> selectLikeHistoProfessionnel(String mot) {
		ArrayList<HistoProfessionnel> lesProfessionnels = new ArrayList<HistoProfessionnel>();
		String requete = "SELECT * FROM histoProfessionnel WHERE "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "nom LIKE '%"+mot+"%' OR "
				+ "type LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoProfessionnel unProfessionnel = new HistoProfessionnel (
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("tel"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("numSIRET"),
						desResultats.getString("statut"),
						desResultats.getString("etat"),
						desResultats.getString("role"),
						desResultats.getInt("nbTentatives"),
						desResultats.getInt("bloque"),
						desResultats.getInt("nbConnexion"),
						desResultats.getString("type"),
						desResultats.getString("date_creation_mdp"),
						desResultats.getString("date_dernier_changement_mdp"),
						desResultats.getString("date_creation_compte"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesProfessionnels.add(unProfessionnel);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProfessionnels;
	}
	
	// Nombre de professionnels
	public static int countProfessionnels () {
		int nbprofessionnel = 0;
		String requete = "SELECT count(*) as nb FROM professionnel;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbprofessionnel = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbprofessionnel;
	}
	
	/*** GESTION DES TYPES ***/
	
	// Insertion d'un type
	public static void insertType (Type unType) {
		String requete = "INSERT INTO type VALUES (null, '"+unType.getLibelle()+"');";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Sélection de tous les types
	public static ArrayList<Type> selectAllTypes () {
		ArrayList<Type> lesTypes = new ArrayList<Type>();
		String requete = "SELECT * FROM type ORDER BY idtype;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Type unType = new Type (
						desResultats.getInt("idtype"),
						desResultats.getString("libelle")
						);
				lesTypes.add(unType);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesTypes;
	}
	
	// Sélection d'un type en fonction de son libellé
	public static Type selectWhereType (String libelle) {
		Type unType = null;
		String requete = "SELECT * FROM type WHERE libelle = '"+libelle+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unType = new Type (
						unResultat.getInt("idtype"),
						unResultat.getString("libelle")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unType;
	}
	
	// Édition d'un type
	public static void updateType (Type unType) {
		String requete = "UPDATE type SET libelle = '"+unType.getLibelle()+"' WHERE idtype = "+unType.getIdtype()+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Suppression d'un type
	public static void deleteType (int idtype) {
		String requete = "DELETE FROM type WHERE idtype = "+idtype+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Recherche d'un type
	public static ArrayList<Type> selectLikeType(String mot) {
		ArrayList<Type> lesTypes = new ArrayList<Type>();
		String requete = "SELECT * FROM type WHERE "
				+ "libelle LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Type unType = new Type (
						desResultats.getInt("idtype"),
						desResultats.getString("libelle")
						);
				lesTypes.add(unType);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesTypes;
	}
	
	// Nombre de types
	public static int countTypes () {
		int nbtype = 0;
		String requete = "SELECT count(*) as nb FROM type;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbtype = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbtype;
	}
	
	/*** GESTION DES PRODUITS ***/
	
	// Séléction de tous les produits
	public static ArrayList<Produit> selectAllProduits () {
		ArrayList<Produit> lesProduits = new ArrayList<Produit>();
		String requete = "SELECT * FROM produit;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Produit unProduit = new Produit (
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getString("imageproduit"),
						desResultats.getString("descriptionproduit"),
						desResultats.getInt("qteproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getInt("idtype"),
						desResultats.getString("date_ajout")
						);
				lesProduits.add(unProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProduits;
	}
	
	// Séléction de la view vproduit
	public static ArrayList<VProduit> selectAllVProduits () {
		ArrayList<VProduit> lesVProduits = new ArrayList<VProduit>();
		String requete = "SELECT * FROM vproduit;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VProduit unVProduit = new VProduit (
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getString("imageproduit"),
						desResultats.getString("descriptionproduit"),
						desResultats.getInt("qteproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getString("libelle"),
						desResultats.getString("date_ajout")
						);
				lesVProduits.add(unVProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesVProduits;
	}
	
	// Recherche d'un produit
	public static ArrayList<Produit> selectLikeProduit(String mot) {
		ArrayList<Produit> lesProduits = new ArrayList<Produit>();
		String requete = "SELECT * FROM produit WHERE "
				+ "idproduit LIKE '%"+mot+"%' OR "
				+ "nomproduit LIKE '%"+mot+"%' OR "
				+ "imageproduit LIKE '%"+mot+"%' OR "
				+ "qteproduit LIKE '%"+mot+"%' OR "
				+ "prixproduit LIKE '%"+mot+"%' OR "
				+ "idtype LIKE '%"+mot+"%' OR "
				+ "date_ajout LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Produit unProduit = new Produit (
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getString("imageproduit"),
						desResultats.getString("descriptionproduit"),
						desResultats.getInt("qteproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getInt("idtype"),
						desResultats.getString("date_ajout")
						);
				lesProduits.add(unProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProduits;
	}
	
	// Sélection d'un produit à partir de la view 'vproduit' en fonction de son id
	public static VProduit selectWhereVProduit (int idproduit) {
		VProduit unProduit = null;
		String requete = "SELECT * FROM vproduit WHERE idproduit = "+idproduit+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unProduit = new VProduit (
						unResultat.getInt("idproduit"),
						unResultat.getString("nomproduit"),
						unResultat.getString("imageproduit"),
						unResultat.getString("descriptionproduit"),
						unResultat.getInt("qteproduit"),
						unResultat.getFloat("prixproduit"),
						unResultat.getString("libelle"),
						unResultat.getString("date_ajout")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unProduit;
	}
	
	// Sélection d'un produit à partir de la view 'vproduit' en fonction de son nom
	public static VProduit selectWhereVProduit (String nomproduit) {
		VProduit unProduit = null;
		String requete = "SELECT * FROM vproduit WHERE nomproduit = '"+nomproduit+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unProduit = new VProduit (
						unResultat.getInt("idproduit"),
						unResultat.getString("nomproduit"),
						unResultat.getString("imageproduit"),
						unResultat.getString("descriptionproduit"),
						unResultat.getInt("qteproduit"),
						unResultat.getFloat("prixproduit"),
						unResultat.getString("libelle"),
						unResultat.getString("date_ajout")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unProduit;
	}
	
	// Recherche d'un produit à partir de la view 'vproduit'
	public static ArrayList<VProduit> selectLikeVProduit(String mot) {
		ArrayList<VProduit> lesProduits = new ArrayList<VProduit>();
		String requete = "SELECT * FROM vproduit WHERE "
				+ "nomproduit LIKE '%"+mot+"%' OR "
				+ "imageproduit LIKE '%"+mot+"%' OR "
				+ "qteproduit LIKE '%"+mot+"%' OR "
				+ "prixproduit LIKE '%"+mot+"%' OR "
				+ "libelle LIKE '%"+mot+"%' OR "
				+ "date_ajout LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VProduit unProduit = new VProduit (
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getString("imageproduit"),
						desResultats.getString("descriptionproduit"),
						desResultats.getInt("qteproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getString("libelle"),
						desResultats.getString("date_ajout")
						);
				lesProduits.add(unProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProduits;
	}
	
	// Sélection de tous les produits archivés
	public static ArrayList<HistoProduit> selectAllHistoProduits () {
		ArrayList<HistoProduit> lesProduits = new ArrayList<HistoProduit>();
		String requete = "SELECT * FROM histoProduit;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoProduit unProduit = new HistoProduit (
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getString("imageproduit"),
						desResultats.getString("descriptionproduit"),
						desResultats.getInt("qteproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getInt("idtype"),
						desResultats.getString("date_ajout"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesProduits.add(unProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProduits;
	}
	
	// Recherche d'un produit archivé
	public static ArrayList<HistoProduit> selectLikeHistoProduit(String mot) {
		ArrayList<HistoProduit> lesProduits = new ArrayList<HistoProduit>();
		String requete = "SELECT * FROM histoProduit WHERE "
				+ "idproduit LIKE '%"+mot+"%' OR "
				+ "nomproduit LIKE '%"+mot+"%' OR "
				+ "imageproduit LIKE '%"+mot+"%' OR "
				+ "descriptionproduit LIKE '%"+mot+"%' OR "
				+ "qteproduit LIKE '%"+mot+"%' OR "
				+ "prixproduit LIKE '%"+mot+"%' OR "
				+ "idtype LIKE '%"+mot+"%' OR "
				+ "date_ajout LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "user LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoProduit unProduit = new HistoProduit (
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getString("imageproduit"),
						desResultats.getString("descriptionproduit"),
						desResultats.getInt("qteproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getInt("idtype"),
						desResultats.getString("date_ajout"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesProduits.add(unProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesProduits;
	}
	
	// Sélection d'un produit en fonction de son id
	public static Produit selectWhereProduit (int idproduit) {
		Produit unProduit = null;
		String requete = "SELECT * FROM produit WHERE idproduit = '"+idproduit+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unProduit = new Produit (
						unResultat.getInt("idproduit"),
						unResultat.getString("nomproduit"),
						unResultat.getString("imageproduit"),
						unResultat.getString("descriptionproduit"),
						unResultat.getInt("qteproduit"),
						unResultat.getFloat("prixproduit"),
						unResultat.getInt("idtype"),
						unResultat.getString("date_ajout")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unProduit;
	}
	
	// Sélection d'un produit en fonction de son nom
	public static Produit selectWhereNomProduit (String nomproduit) {
		Produit unProduit = null;
		String requete = "SELECT * FROM produit WHERE nomproduit = '"+nomproduit+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unProduit = new Produit (
						unResultat.getInt("idproduit"),
						unResultat.getString("nomproduit"),
						unResultat.getString("imageproduit"),
						unResultat.getString("descriptionproduit"),
						unResultat.getInt("qteproduit"),
						unResultat.getFloat("prixproduit"),
						unResultat.getInt("idtype"),
						unResultat.getString("date_ajout")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unProduit;
	}
	
	// Insertion d'un produit
	public static void insertProduit (Produit unProduit) {
		String requete = "INSERT INTO produit VALUES (null, '" 
				+ unProduit.getNomproduit() + "', '"
				+ unProduit.getImageproduit() + "', '"
				+ unProduit.getDescriptionproduit() + "', "
				+ unProduit.getQteproduit() + ", "
				+ unProduit.getPrixproduit() + ", "
				+ unProduit.getIdtype() + ", sysdate());";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Édition d'un produit
	public static void updateProduit (Produit unProduit) {
		String requete = "UPDATE produit SET nomproduit = '"
				+ unProduit.getNomproduit() + "', imageproduit = '" 
				+ unProduit.getImageproduit() + "', descriptionproduit = '"
				+ unProduit.getDescriptionproduit() + "', qteproduit = "
				+ unProduit.getQteproduit() + ", prixproduit = '"
				+ unProduit.getPrixproduit() + "', idtype = "
				+ unProduit.getIdtype() + " WHERE idproduit = "+unProduit.getIdproduit()+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Suppression d'un produit
	public static void deleteProduit (int idproduit) {
		String requete = "DELETE FROM produit WHERE idproduit = "+idproduit+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}

	// Nombre de produits
	public static int countProduits () {
		int nbproduit = 0;
		String requete = "SELECT count(*) as nb FROM produit;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbproduit = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbproduit;
	}
	
	/*** GESTION DES COMMANDES ***/
	
	// Sélection de toutes les commandes
	public static ArrayList<Commande> selectAllCommandes () {
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		String requete = "SELECT * FROM commande;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Commande uneCommande = new Commande (
						desResultats.getInt("numcommande"),
						desResultats.getInt("idclient"),
						desResultats.getString("mode_payement"),
						desResultats.getString("etat"),
						desResultats.getFloat("montantTotalHT"),
						desResultats.getFloat("montantTotalTTC"),
						desResultats.getFloat("tva"),
						desResultats.getString("datecommande"),
						desResultats.getString("datelivraison"),
						desResultats.getInt("idproduit")
						);
				lesCommandes.add(uneCommande);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesCommandes;
	}
	
	// Sélection d'une commande d'un client
	public static Commande selectWhereCommandeClient (int idclient) {
		Commande uneCommande = null;
		String requete = "SELECT * FROM commande WHERE idclient = "+idclient+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				uneCommande = new Commande (
						unResultat.getInt("numcommande"),
						unResultat.getInt("idclient"),
						unResultat.getString("mode_payement"),
						unResultat.getString("etat"),
						unResultat.getFloat("montantTotalHT"),
						unResultat.getFloat("montantTotalTTC"),
						unResultat.getFloat("tva"),
						unResultat.getString("datecommande"),
						unResultat.getString("datelivraison"),
						unResultat.getInt("idproduit")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return uneCommande;
	}
	
	// Recherche d'une commande
	public static ArrayList<Commande> selectLikeCommande(String mot) {
		ArrayList<Commande> lesCommandes = new ArrayList<Commande>();
		String requete = "SELECT * FROM commande WHERE "
				+ "numcommande LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "mode_payement LIKE '%"+mot+"%' OR "
				+ "etat LIKE '%"+mot+"%' OR "
				+ "montantTotalHT LIKE '%"+mot+"%' OR "
				+ "montantTotalTTC LIKE '%"+mot+"%' OR "
				+ "TVA LIKE '%"+mot+"%' OR "
				+ "datecommande LIKE '%"+mot+"%' OR "
				+ "datelivraison LIKE '%"+mot+"%' OR "
				+ "idproduit LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Commande uneCommande = new Commande (
						desResultats.getInt("numcommande"),
						desResultats.getInt("idclient"),
						desResultats.getString("mode_payement"),
						desResultats.getString("etat"),
						desResultats.getFloat("montantTotalHT"),
						desResultats.getFloat("montantTotalTTC"),
						desResultats.getFloat("tva"),
						desResultats.getString("datecommande"),
						desResultats.getString("datelivraison"),
						desResultats.getInt("idproduit")
						);
				lesCommandes.add(uneCommande);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesCommandes;
	}
	
	// Sélection de toutes les commandes de la view 'vcommande'
	public static ArrayList<VCommande> selectAllVCommandes () {
		ArrayList<VCommande> lesVCommandes = new ArrayList<VCommande>();
		String requete = "SELECT * FROM vcommande;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VCommande uneVCommande = new VCommande (
						desResultats.getInt("numcommande"),
						desResultats.getString("nom"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("mode_payement"),
						desResultats.getString("etat"),
						desResultats.getFloat("montantTotalHT"),
						desResultats.getFloat("montantTotalTTC"),
						desResultats.getFloat("TVA"),
						desResultats.getString("datecommande"),
						desResultats.getString("datelivraison"),
						desResultats.getString("produit")
						);
				lesVCommandes.add(uneVCommande);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesVCommandes;
	}
	
	// Recherche d'une commande à partir de la view 'vcommande'
	public static ArrayList<VCommande> selectLikeVCommande(String mot) {
		ArrayList<VCommande> lesCommandes = new ArrayList<VCommande>();
		String requete = "SELECT * FROM vcommande WHERE "
				+ "numcommande LIKE '%"+mot+"%' OR "
				+ "nom LIKE '%"+mot+"%' OR "
				+ "adresse LIKE '%"+mot+"%' OR "
				+ "cp LIKE '%"+mot+"%' OR "
				+ "ville LIKE '%"+mot+"%' OR "
				+ "pays LIKE '%"+mot+"%' OR "
				+ "mode_payement LIKE '%"+mot+"%' OR "
				+ "etat LIKE '%"+mot+"%' OR "
				+ "montantTotalHT LIKE '%"+mot+"%' OR "
				+ "montantTotalTTC LIKE '%"+mot+"%' OR "
				+ "TVA LIKE '%"+mot+"%' OR "
				+ "datecommande LIKE '%"+mot+"%' OR "
				+ "datelivraison LIKE '%"+mot+"%' OR "
				+ "produit LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VCommande uneCommande = new VCommande (
						desResultats.getInt("numcommande"),
						desResultats.getString("nom"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getString("mode_payement"),
						desResultats.getString("etat"),
						desResultats.getFloat("montantTotalHT"),
						desResultats.getFloat("montantTotalTTC"),
						desResultats.getFloat("TVA"),
						desResultats.getString("datecommande"),
						desResultats.getString("datelivraison"),
						desResultats.getString("produit")
						);
				lesCommandes.add(uneCommande);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesCommandes;
	}
	
	// Sélection de toutes les commandes archivées
	public static ArrayList<HistoCommande> selectAllHistoCommandes () {
		ArrayList<HistoCommande> lesCommandes = new ArrayList<HistoCommande>();
		String requete = "SELECT * FROM histoCommande;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoCommande uneCommande = new HistoCommande (
						desResultats.getInt("numcommande"),
						desResultats.getInt("idclient"),
						desResultats.getString("mode_payement"),
						desResultats.getString("etat"),
						desResultats.getFloat("montantTotalHT"),
						desResultats.getFloat("montantTotalTTC"),
						desResultats.getFloat("TVA"),
						desResultats.getString("datecommande"),
						desResultats.getString("datelivraison"),
						desResultats.getInt("idproduit"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesCommandes.add(uneCommande);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesCommandes;
	}
	
	// Recherche d'une commande archivée
	public static ArrayList<HistoCommande> selectLikeHistoCommande(String mot) {
		ArrayList<HistoCommande> lesCommandes = new ArrayList<HistoCommande>();
		String requete = "SELECT * FROM histoCommande WHERE "
				+ "numcommande LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "mode_payement LIKE '%"+mot+"%' OR "
				+ "etat LIKE '%"+mot+"%' OR "
				+ "montantTotalHT LIKE '%"+mot+"%' OR "
				+ "montantTotalTTC LIKE '%"+mot+"%' OR "
				+ "TVA LIKE '%"+mot+"%' OR "
				+ "datecommande LIKE '%"+mot+"%' OR "
				+ "datelivraison LIKE '%"+mot+"%' OR "
				+ "idproduit LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoCommande uneCommande = new HistoCommande (
						desResultats.getInt("numcommande"),
						desResultats.getInt("idclient"),
						desResultats.getString("mode_payement"),
						desResultats.getString("etat"),
						desResultats.getFloat("montantTotalHT"),
						desResultats.getFloat("montantTotalTTC"),
						desResultats.getFloat("TVA"),
						desResultats.getString("datecommande"),
						desResultats.getString("datelivraison"),
						desResultats.getInt("idproduit"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesCommandes.add(uneCommande);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesCommandes;
	}
	
	// Nombre de commandes
	public static int countCommandes () {
		int nbcommande = 0;
		String requete = "SELECT count(*) as nb FROM commande;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbcommande = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbcommande;
	}
	
	/*** GESTION DU PANIER ***/
	
	// Sélection de toutes les commandes du panier
	public static ArrayList<Panier> selectAllPaniers () {
		ArrayList<Panier> lesPaniers = new ArrayList<Panier>();
		String requete = "SELECT * FROM panier;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Panier unPanier = new Panier (
						desResultats.getInt("numcommande"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("quantite"),
						desResultats.getInt("idclient")
						
						);
				lesPaniers.add(unPanier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesPaniers;
	}
	
	// Recherche d'une commande d'un panier
	public static ArrayList<Panier> selectLikePanier(String mot) {
		ArrayList<Panier> lesPaniers = new ArrayList<Panier>();
		String requete = "SELECT * FROM panier WHERE "
				+ "numcommande LIKE '%"+mot+"%' OR "
				+ "idproduit LIKE '%"+mot+"%' OR "
				+ "quantite LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Panier unPanier = new Panier (
						desResultats.getInt("numcommande"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("quantite"),
						desResultats.getInt("idclient")
						);
				lesPaniers.add(unPanier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesPaniers;
	}
	
	// Sélection de toutes les commandes de la view 'vpanier'
	public static ArrayList<VPanier> selectAllVPanier () {
		ArrayList<VPanier> lesVPaniers = new ArrayList<VPanier>();
		String requete = "SELECT * FROM vpanier;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VPanier uneVPanier = new VPanier (
						desResultats.getInt("numcommande"),
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getInt("quantite"),
						desResultats.getFloat("montantTotalHT"),
						desResultats.getFloat("montantTotalTTC"),
						desResultats.getString("nomclient"),
						desResultats.getInt("idclient")
						);
				lesVPaniers.add(uneVPanier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesVPaniers;
	}
	
	// Recherche d'une commande d'un panier à partir de la view 'vpanier'
	public static ArrayList<VPanier> selectLikeVPanier(String mot) {
		ArrayList<VPanier> lesPaniers = new ArrayList<VPanier>();
		String requete = "SELECT * FROM panier WHERE "
				+ "numcommande LIKE '%"+mot+"%' OR "
				+ "idproduit LIKE '%"+mot+"%' OR "
				+ "nomproduit LIKE '%"+mot+"%' OR "
				+ "prixproduit LIKE '%"+mot+"%' OR "
				+ "quantite LIKE '%"+mot+"%' OR "
				+ "montantTotalHT LIKE '%"+mot+"%' OR "
				+ "montantTotalTTC LIKE '%"+mot+"%' OR "
				+ "nomclient LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VPanier unPanier = new VPanier (
						desResultats.getInt("numcommande"),
						desResultats.getInt("idproduit"),
						desResultats.getString("nomproduit"),
						desResultats.getFloat("prixproduit"),
						desResultats.getInt("quantite"),
						desResultats.getFloat("montantTotalHT"),
						desResultats.getFloat("montantTotalTTC"),
						desResultats.getString("nomclient"),
						desResultats.getInt("idclient")
						);
				lesPaniers.add(unPanier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesPaniers;
	}
	
	// Sélection de toutes les commandes du panier archivées
	public static ArrayList<HistoPanier> selectAllHistoPaniers () {
		ArrayList<HistoPanier> lesPaniers = new ArrayList<HistoPanier>();
		String requete = "SELECT * FROM histoPanier;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoPanier unPanier = new HistoPanier (
						desResultats.getInt("numcommande"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("quantite"),
						desResultats.getInt("idclient"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesPaniers.add(unPanier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesPaniers;
	}
	
	// Recherche d'une commande d'un panier archivée
	public static ArrayList<HistoPanier> selectLikeHistoPanier(String mot) {
		ArrayList<HistoPanier> lesPaniers = new ArrayList<HistoPanier>();
		String requete = "SELECT * FROM histoPanier WHERE "
				+ "numcommande LIKE '%"+mot+"%' OR "
				+ "idproduit LIKE '%"+mot+"%' OR "
				+ "quantite LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "user LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoPanier unPanier = new HistoPanier (
						desResultats.getInt("numcommande"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("quantite"),
						desResultats.getInt("idclient"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesPaniers.add(unPanier);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesPaniers;
	}
	
	// Nombre de commandes dans le panier
	public static int countPaniers () {
		int nbpanier = 0;
		String requete = "SELECT count(*) as nb FROM panier;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbpanier = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbpanier;
	}
	
	/*** GESTION DES FACTURES ***/
	
	// Sélection de toutes les factures
	public static ArrayList<Facture> selectAllFactures () {
		ArrayList<Facture> lesFactures = new ArrayList<Facture>();
		String requete = "SELECT * FROM facture;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Facture uneFacture = new Facture (
						desResultats.getInt("idfacture"),
						desResultats.getString("datefacture"),
						desResultats.getInt("idclient"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("numcommande"),
						desResultats.getInt("quantite")
						);
				lesFactures.add(uneFacture);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesFactures;
	}
	
	// Sélection d'une facture d'un client
	public static Facture selectWhereFactureClient (int idclient) {
		Facture uneFacture = null;
		String requete = "SELECT * FROM facture WHERE idclient = "+idclient+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				uneFacture = new Facture (
						unResultat.getInt("idfacture"),
						unResultat.getString("datefacture"),
						unResultat.getInt("idclient"),
						unResultat.getInt("idproduit"),
						unResultat.getInt("numcommande"),
						unResultat.getInt("quantite")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return uneFacture;
	}
	
	// Sélection d'une facture d'un client et d'un produit
	public static Facture selectWhereFactureClientProduit (int idclient, int idproduit) {
		Facture uneFacture = null;
		String requete = "SELECT * FROM facture WHERE idclient = "+idclient+" and idproduit = "+idproduit+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				uneFacture = new Facture (
						unResultat.getInt("idfacture"),
						unResultat.getString("datefacture"),
						unResultat.getInt("idclient"),
						unResultat.getInt("idproduit"),
						unResultat.getInt("numcommande"),
						unResultat.getInt("quantite")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return uneFacture;
	}
	
	// Recherche d'une facture
	public static ArrayList<Facture> selectLikeFacture(String mot) {
		ArrayList<Facture> lesFactures = new ArrayList<Facture>();
		String requete = "SELECT * FROM facture WHERE "
				+ "idfacture LIKE '%"+mot+"%' OR "
				+ "datefacture LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "idproduit LIKE '%"+mot+"%' OR "
				+ "numcommande LIKE '%"+mot+"%' OR "
				+ "quantite LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Facture uneFacture = new Facture (
						desResultats.getInt("idfacture"),
						desResultats.getString("datefacture"),
						desResultats.getInt("idclient"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("numcommande"),
						desResultats.getInt("quantite")
						);
				lesFactures.add(uneFacture);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesFactures;
	}
	
	// Sélection de toutes les factures de la view 'vfacture'
	public static ArrayList<VFacture> selectAllVFactures () {
		ArrayList<VFacture> lesVFactures = new ArrayList<VFacture>();
		String requete = "SELECT * FROM vfacture;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VFacture uneVFacture = new VFacture (
						desResultats.getInt("idfacture"),
						desResultats.getString("datefacture"),
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("email"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getInt("idproduit"),
						desResultats.getString("produit"),
						desResultats.getFloat("prix"),
						desResultats.getFloat("montantTotalHT"),
						desResultats.getFloat("montantTotalTTC"),
						desResultats.getFloat("TVA"),
						desResultats.getString("datecommande"),
						desResultats.getString("datelivraison"),
						desResultats.getInt("numcommande"),
						desResultats.getString("mode_payement"),
						desResultats.getString("etat"),
						desResultats.getInt("quantite")
						);
				lesVFactures.add(uneVFacture);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesVFactures;
	}
	
	// Recherche d'une facture dans la view 'vfacture'
	public static ArrayList<VFacture> selectLikeVFacture(String mot) {
		ArrayList<VFacture> lesFactures = new ArrayList<VFacture>();
		String requete = "SELECT * FROM vfacture WHERE "
				+ "idfacture LIKE '%"+mot+"%' OR "
				+ "datefacture LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "nom LIKE '%"+mot+"%' OR "
				+ "email LIKE '%"+mot+"%' OR "
				+ "adresse LIKE '%"+mot+"%' OR "
				+ "cp LIKE '%"+mot+"%' OR "
				+ "ville LIKE '%"+mot+"%' OR "
				+ "pays LIKE '%"+mot+"%' OR "
				+ "idproduit LIKE '%"+mot+"%' OR "
				+ "produit LIKE '%"+mot+"%' OR "
				+ "prix LIKE '%"+mot+"%' OR "
				+ "montantTotalHT LIKE '%"+mot+"%' OR "
				+ "montantTotalTTC LIKE '%"+mot+"%' OR "
				+ "TVA LIKE '%"+mot+"%' OR "
				+ "datecommande LIKE '%"+mot+"%' OR "
				+ "datelivraison LIKE '%"+mot+"%' OR "
				+ "numcommande LIKE '%"+mot+"%' OR "
				+ "mode_payement LIKE '%"+mot+"%' OR "
				+ "etat LIKE '%"+mot+"%' OR "
				+ "quantite LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VFacture uneFacture = new VFacture (
						desResultats.getInt("idfacture"),
						desResultats.getString("datefacture"),
						desResultats.getInt("idclient"),
						desResultats.getString("nom"),
						desResultats.getString("email"),
						desResultats.getString("adresse"),
						desResultats.getString("cp"),
						desResultats.getString("ville"),
						desResultats.getString("pays"),
						desResultats.getInt("idproduit"),
						desResultats.getString("produit"),
						desResultats.getFloat("prix"),
						desResultats.getFloat("montantTotalHT"),
						desResultats.getFloat("montantTotalTTC"),
						desResultats.getFloat("tva"),
						desResultats.getString("datecommande"),
						desResultats.getString("datelivraison"),
						desResultats.getInt("numcommande"),
						desResultats.getString("mode_payement"),
						desResultats.getString("etat"),
						desResultats.getInt("quantite")
						);
				lesFactures.add(uneFacture);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesFactures;
	}
	
	// Sélection de toutes les factures archivées
	public static ArrayList<HistoFacture> selectAllHistoFactures () {
		ArrayList<HistoFacture> lesFactures = new ArrayList<HistoFacture>();
		String requete = "SELECT * FROM histoFacture;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoFacture uneFacture = new HistoFacture (
						desResultats.getInt("idfacture"),
						desResultats.getString("datefacture"),
						desResultats.getInt("idclient"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("numcommande"),
						desResultats.getInt("quantite"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesFactures.add(uneFacture);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesFactures;
	}
	
	// Recherche d'une facture archivée
	public static ArrayList<HistoFacture> selectLikeHistoFacture(String mot) {
		ArrayList<HistoFacture> lesFactures = new ArrayList<HistoFacture>();
		String requete = "SELECT * FROM histoFacture WHERE "
				+ "idfacture LIKE '%"+mot+"%' OR "
				+ "datefacture LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "idproduit LIKE '%"+mot+"%' OR "
				+ "numcommande LIKE '%"+mot+"%' OR "
				+ "quantite LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "user LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoFacture uneFacture = new HistoFacture (
						desResultats.getInt("idfacture"),
						desResultats.getString("datefacture"),
						desResultats.getInt("idclient"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("numcommande"),
						desResultats.getInt("quantite"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesFactures.add(uneFacture);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesFactures;
	}
	
	// Nombre de factures (totales)
	public static int countFactures () {
		int nbfacture = 0;
		String requete = "SELECT count(*) as nb FROM facture;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbfacture = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbfacture;
	}
	
	/*** GESTION DES MESSAGES ***/
	
	// Sélection de tous les messages
	public static ArrayList<Message> selectAllMessages () {
		ArrayList<Message> lesMessages = new ArrayList<Message>();
		String requete = "SELECT * FROM message;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Message unMessage = new Message (
						desResultats.getInt("idmessage"),
						desResultats.getInt("id_exp"),
						desResultats.getInt("id_dest"),
						desResultats.getString("date_envoi"),
						desResultats.getString("contenu"),
						desResultats.getInt("lu")
						);
				lesMessages.add(unMessage);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesMessages;
	}
	
	// Sélection des messages 'lu' (1) ou 'non lu' (0)
	public static Message selectWhereMessageLu (int lu) {
		Message unMessage = null;
		String requete = "SELECT * FROM message WHERE lu = "+lu+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unMessage = new Message (
						unResultat.getInt("idmessage"),
						unResultat.getInt("id_exp"),
						unResultat.getInt("id_dest"),
						unResultat.getString("date_envoi"),
						unResultat.getString("contenu"),
						unResultat.getInt("lu")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unMessage;
	}
	
	// Sélection des messages en fonction de la date d'envoi
	public static Message selectWhereMessageDate (String date_envoi) {
		Message unMessage = null;
		String requete = "SELECT * FROM message WHERE date_envoi = '"+date_envoi+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				unMessage = new Message (
						unResultat.getInt("idmessage"),
						unResultat.getInt("id_exp"),
						unResultat.getInt("id_dest"),
						unResultat.getString("date_envoi"),
						unResultat.getString("contenu"),
						unResultat.getInt("lu")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return unMessage;
	}
	
	// Recherche d'un message
	public static ArrayList<Message> selectLikeMessage(String mot) {
		ArrayList<Message> lesMessages = new ArrayList<Message>();
		String requete = "SELECT * FROM message WHERE "
				+ "idmessage LIKE '%"+mot+"%' OR "
				+ "id_exp LIKE '%"+mot+"%' OR "
				+ "id_dest LIKE '%"+mot+"%' OR "
				+ "date_envoi LIKE '%"+mot+"%' OR "
				+ "contenu LIKE '%"+mot+"%' OR "
				+ "lu LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Message unMessage = new Message (
						desResultats.getInt("idmessage"),
						desResultats.getInt("id_exp"),
						desResultats.getInt("id_dest"),
						desResultats.getString("date_envoi"),
						desResultats.getString("contenu"),
						desResultats.getInt("lu")
						);
				lesMessages.add(unMessage);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesMessages;
	}
	
	// Sélection de tous les messages de la view 'vmessage'
	public static ArrayList<VMessage> selectAllVMessages () {
		ArrayList<VMessage> lesVMessages = new ArrayList<VMessage>();
		String requete = "SELECT * FROM vmessage;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VMessage uneVMessage = new VMessage (
						desResultats.getInt("idmessage"),
						desResultats.getInt("id_exp"),
						desResultats.getString("expediteur"),
						desResultats.getInt("id_dest"),
						desResultats.getString("destinataire"),
						desResultats.getString("date_envoi"),
						desResultats.getString("contenu"),
						desResultats.getInt("lu")
						);
				lesVMessages.add(uneVMessage);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesVMessages;
	}
	
	// Recherche d'un message à partir de la view 'vmessage'
	public static ArrayList<VMessage> selectLikeVMessage(String mot) {
		ArrayList<VMessage> lesMessages = new ArrayList<VMessage>();
		String requete = "SELECT * FROM vmessage WHERE "
				+ "idmessage LIKE '%"+mot+"%' OR "
				+ "expediteur LIKE '%"+mot+"%' OR "
				+ "destinataire LIKE '%"+mot+"%' OR "
				+ "date_envoi LIKE '%"+mot+"%' OR "
				+ "contenu LIKE '%"+mot+"%' OR "
				+ "lu LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VMessage unMessage = new VMessage (
						desResultats.getInt("idmessage"),
						desResultats.getInt("id_exp"),
						desResultats.getString("expediteur"),
						desResultats.getInt("id_dest"),
						desResultats.getString("destinataire"),
						desResultats.getString("date_envoi"),
						desResultats.getString("contenu"),
						desResultats.getInt("lu")
						);
				lesMessages.add(unMessage);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesMessages;
	}
	
	// Sélection de tous les messages archivés
	public static ArrayList<HistoMessage> selectAllHistoMessages () {
		ArrayList<HistoMessage> lesMessages = new ArrayList<HistoMessage>();
		String requete = "SELECT * FROM histoMessage;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoMessage unMessage = new HistoMessage (
						desResultats.getInt("idmessage"),
						desResultats.getInt("id_exp"),
						desResultats.getInt("id_dest"),
						desResultats.getString("date_envoi"),
						desResultats.getString("contenu"),
						desResultats.getInt("lu"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesMessages.add(unMessage);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesMessages;
	}
	
	// Recherche d'un message archivé
	public static ArrayList<HistoMessage> selectLikeHistoMessage(String mot) {
		ArrayList<HistoMessage> lesMessages = new ArrayList<HistoMessage>();
		String requete = "SELECT * FROM histoMessage WHERE "
				+ "idmessage LIKE '%"+mot+"%' OR "
				+ "id_exp LIKE '%"+mot+"%' OR "
				+ "id_dest LIKE '%"+mot+"%' OR "
				+ "date_envoi LIKE '%"+mot+"%' OR "
				+ "contenu LIKE '%"+mot+"%' OR "
				+ "lu LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "user LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoMessage unMessage = new HistoMessage (
						desResultats.getInt("idmessage"),
						desResultats.getInt("id_exp"),
						desResultats.getInt("id_dest"),
						desResultats.getString("date_envoi"),
						desResultats.getString("contenu"),
						desResultats.getInt("lu"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesMessages.add(unMessage);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesMessages;
	}
	
	// Nombre de messages
	public static int countMessages () {
		int nbmessage = 0;
		String requete = "SELECT count(*) as nb FROM message;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbmessage = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbmessage;
	}
	
	// Nombre de messages envoyés d'un client
	public static int countMessagesEnvoyeClient (int idclient) {
		int nbmessage = 0;
		String requete = "SELECT count(*) as nb FROM message WHERE id_exp  = "+idclient+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbmessage = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbmessage;
	}
	
	// Nombre de messages reçu d'un client
	public static int countMessagesRecuClient (int idclient) {
		int nbmessage = 0;
		String requete = "SELECT count(*) as nb FROM message WHERE id_dest  = "+idclient+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbmessage = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbmessage;
	}
	
	/*** GESTION DES COMMENTAIRES ***/
	
	// Sélection de tous les commentaires
	public static ArrayList<Commentaire> selectAllCommentaires () {
		ArrayList<Commentaire> lesCommentaires = new ArrayList<Commentaire>();
		String requete = "SELECT * FROM commentaire;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Commentaire unCommentaire = new Commentaire (
						desResultats.getInt("idcom"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("idclient"),
						desResultats.getString("contenu"),
						desResultats.getInt("client_id"),
						desResultats.getString("dateheurepost")
						);
				lesCommentaires.add(unCommentaire);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesCommentaires;
	}
	
	// Recherche d'un commentaire
	public static ArrayList<Commentaire> selectLikeCommentaire(String mot) {
		ArrayList<Commentaire> lesCommentaires = new ArrayList<Commentaire>();
		String requete = "SELECT * FROM commentaire WHERE "
				+ "idcom LIKE '%"+mot+"%' OR "
				+ "idproduit LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "contenu LIKE '%"+mot+"%' OR "
				+ "client_id LIKE '%"+mot+"%' OR "
				+ "dateheurepost LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Commentaire unCommentaire = new Commentaire (
						desResultats.getInt("idcom"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("idclient"),
						desResultats.getString("contenu"),
						desResultats.getInt("client_id"),
						desResultats.getString("dateheurepost")
						);
				lesCommentaires.add(unCommentaire);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesCommentaires;
	}
	
	// Sélection de tous les commentaires de la view 'vcommentaire'
	public static ArrayList<VCommentaire> selectAllVCommentaires () {
		ArrayList<VCommentaire> lesVCommentaires = new ArrayList<VCommentaire>();
		String requete = "SELECT * FROM vcommentaire;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VCommentaire uneVCommentaire = new VCommentaire (
						desResultats.getInt("idcom"),
						desResultats.getString("produit"),
						desResultats.getString("client"),
						desResultats.getString("contenu"),
						desResultats.getInt("client_id"),
						desResultats.getString("dateheurepost")
						);
				lesVCommentaires.add(uneVCommentaire);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesVCommentaires;
	}
	
	// Recherche d'un commentaire à partir de la view 'vcommentaire'
	public static ArrayList<VCommentaire> selectLikeVCommentaire(String mot) {
		ArrayList<VCommentaire> lesCommentaires = new ArrayList<VCommentaire>();
		String requete = "SELECT * FROM vcommentaire WHERE "
				+ "idcom LIKE '%"+mot+"%' OR "
				+ "produit LIKE '%"+mot+"%' OR "
				+ "client LIKE '%"+mot+"%' OR "
				+ "contenu LIKE '%"+mot+"%' OR "
				+ "client_id LIKE '%"+mot+"%' OR "
				+ "dateheurepost LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				VCommentaire unCommentaire = new VCommentaire (
						desResultats.getInt("idcom"),
						desResultats.getString("produit"),
						desResultats.getString("client"),
						desResultats.getString("contenu"),
						desResultats.getInt("client_id"),
						desResultats.getString("dateheurepost")
						);
				lesCommentaires.add(unCommentaire);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesCommentaires;
	}
	
	// Sélection de tous les commentaires archivés
	public static ArrayList<HistoCommentaire> selectAllHistoCommentaires () {
		ArrayList<HistoCommentaire> lesCommentaires = new ArrayList<HistoCommentaire>();
		String requete = "SELECT * FROM histoCommentaire;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoCommentaire unCommentaire = new HistoCommentaire (
						desResultats.getInt("idcom"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("idclient"),
						desResultats.getString("contenu"),
						desResultats.getInt("client_id"),
						desResultats.getString("dateheurepost"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesCommentaires.add(unCommentaire);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesCommentaires;
	}
	
	// Recherche d'un commentaire archivé
	public static ArrayList<HistoCommentaire> selectLikeHistoCommentaire(String mot) {
		ArrayList<HistoCommentaire> lesCommentaires = new ArrayList<HistoCommentaire>();
		String requete = "SELECT * FROM histoCommentaire WHERE "
				+ "idmessage LIKE '%"+mot+"%' OR "
				+ "id_exp LIKE '%"+mot+"%' OR "
				+ "id_dest LIKE '%"+mot+"%' OR "
				+ "date_envoi LIKE '%"+mot+"%' OR "
				+ "contenu LIKE '%"+mot+"%' OR "
				+ "lu LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "user LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoCommentaire unCommentaire = new HistoCommentaire (
						desResultats.getInt("idcom"),
						desResultats.getInt("idproduit"),
						desResultats.getInt("idclient"),
						desResultats.getString("contenu"),
						desResultats.getInt("client_id"),
						desResultats.getString("dateheurepost"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesCommentaires.add(unCommentaire);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesCommentaires;
	}
	
	// Nombre de commentaires
	public static int countCommentaires () {
		int nbcommentaire = 0;
		String requete = "SELECT count(*) as nb FROM commentaire;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbcommentaire = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbcommentaire;
	}
	
	// Nombre de commentaires d'un client
	public static int countCommentairesClient (int idclient) {
		int nbcommentaire = 0;
		String requete = "SELECT count(*) as nb FROM commentaire WHERE idclient = "+idclient+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbcommentaire = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbcommentaire;
	}
	
	/*** GESTION DES QUESTIONS ***/
	
	// Insertion d'une question
	public static void insertQuestion (Question uneQuestion) {
		String requete = "INSERT INTO question VALUES (null, '" + uneQuestion.getEnonce() + "'); ";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Édition d'une question
	public static void updateQuestion (Question uneQuestion) {
		String requete = "UPDATE question SET enonce = '"+uneQuestion.getEnonce()+"' WHERE idquestion = "+uneQuestion.getIdquestion()+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Suppression d'une question
	public static void deleteQuestion (int idquestion) {
		String requete = "DELETE FROM question WHERE idquestion = "+idquestion+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Sélection de toutes les questions
	public static ArrayList<Question> selectAllQuestions () {
		ArrayList<Question> lesQuestions = new ArrayList<Question>();
		String requete = "SELECT * FROM question;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Question uneQuestion = new Question (
						desResultats.getInt("idquestion"),
						desResultats.getString("enonce")
						);
				lesQuestions.add(uneQuestion);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesQuestions;
	}
	
	// Sélection d'une question en fonction de son énonce
	public static Question selectWhereQuestion (String enonce) {
		Question uneQuestion = null;
		String requete = "SELECT * FROM question WHERE enonce = '"+enonce+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				uneQuestion = new Question (
						unResultat.getInt("idquestion"),
						unResultat.getString("enonce")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return uneQuestion;
	}
	
	// Recherche d'une question
	public static ArrayList<Question> selectLikeQuestion(String mot) {
		ArrayList<Question> lesQuestions = new ArrayList<Question>();
		String requete = "SELECT * FROM question WHERE "
				+ "idquestion LIKE '%"+mot+"%' OR "
				+ "enonce LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Question uneQuestion = new Question (
						desResultats.getInt("idquestion"),
						desResultats.getString("enonce")
						);
				lesQuestions.add(uneQuestion);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesQuestions;
	}
	
	// Sélection de toutes les questions archivées
	public static ArrayList<HistoQuestion> selectAllHistoQuestions () {
		ArrayList<HistoQuestion> lesQuestions = new ArrayList<HistoQuestion>();
		String requete = "SELECT * FROM histoQuestion;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoQuestion uneQuestion = new HistoQuestion (
						desResultats.getInt("idquestion"),
						desResultats.getString("enonce"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesQuestions.add(uneQuestion);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesQuestions;
	}
	
	// Recherche d'une question archivée
	public static ArrayList<HistoQuestion> selectLikeHistoQuestion(String mot) {
		ArrayList<HistoQuestion> lesQuestions = new ArrayList<HistoQuestion>();
		String requete = "SELECT * FROM histoQuestion WHERE "
				+ "idquestion LIKE '%"+mot+"%' OR "
				+ "enonce LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "user LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoQuestion uneQuestion = new HistoQuestion (
						desResultats.getInt("idquestion"),
						desResultats.getString("enonce"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesQuestions.add(uneQuestion);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesQuestions;
	}
	
	// Nombre de questions
	public static int countQuestions () {
		int nbquestion = 0;
		String requete = "SELECT count(*) as nb FROM question;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbquestion = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbquestion;
	}
	
	/*** GESTION DES REPONSES ***/
	
	// Insertion d'une réponse
	public static void insertReponse (Reponse uneReponse) {
		String requete = "INSERT INTO reponse VALUES (null, " 
				+ uneReponse.getIdquestion() + ", '"
				+ uneReponse.getReponse() + "', "
				+ uneReponse.getIdclient() + "); '";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Édition d'une réponse
	public static void updateReponse (Reponse uneReponse) {
		String requete = "UPDATE reponse SET idquestion = "+uneReponse.getIdquestion()+", reponse = '"+uneReponse.getReponse()+"', client = "+uneReponse.getIdclient()+" WHERE idreponse = "+uneReponse.getIdreponse()+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Suppression d'une réponse
	public static void deleteReponse (int idreponse) {
		String requete = "DELETE FROM reponse WHERE idreponse = "+idreponse+";";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			unStatement.execute(requete);
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
	}
	
	// Sélection de toutes les réponses
	public static ArrayList<Reponse> selectAllReponse () {
		ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();
		String requete = "SELECT * FROM reponse;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Reponse uneReponse = new Reponse (
						desResultats.getInt("idresponse"),
						desResultats.getInt("idreponse"),
						desResultats.getString("reponse"),
						desResultats.getInt("idclient")
						);
				lesReponses.add(uneReponse);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesReponses;
	}
	
	// Sélection d'une réponse en fonction de sa question
	public static Reponse selectWhereReponseQuestion (int idquestion) {
		Reponse uneReponse = null;
		String requete = "SELECT * FROM reponse WHERE idquestion = '"+idquestion+"';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				uneReponse = new Reponse (
						unResultat.getInt("idreponse"),
						unResultat.getInt("idquestion"),
						unResultat.getString("reponse"),
						unResultat.getInt("idclient")
						);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return uneReponse;
	}
	
	// Recherche d'une réponse
	public static ArrayList<Reponse> selectLikeReponse(String mot) {
		ArrayList<Reponse> lesReponses = new ArrayList<Reponse>();
		String requete = "SELECT * FROM response WHERE "
				+ "idreponse LIKE '%"+mot+"%' OR "
				+ "idquestion LIKE '%"+mot+"%' OR "
				+ "reponse LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Reponse uneReponse = new Reponse (
						desResultats.getInt("idresponse"),
						desResultats.getInt("idreponse"),
						desResultats.getString("reponse"),
						desResultats.getInt("idclient")
						);
				lesReponses.add(uneReponse);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesReponses;
	}
	
	// Sélection de toutes les réponses archivées
	public static ArrayList<HistoReponse> selectAllHistoReponse () {
		ArrayList<HistoReponse> lesReponses = new ArrayList<HistoReponse>();
		String requete = "SELECT * FROM histoReponse;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoReponse uneReponse = new HistoReponse (
						desResultats.getInt("idreponse"),
						desResultats.getInt("idquestion"),
						desResultats.getString("reponse"),
						desResultats.getInt("idclient"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesReponses.add(uneReponse);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesReponses;
	}
		
	// Recherche d'une reponse archivée
	public static ArrayList<HistoReponse> selectLikeHistoReponse(String mot) {
		ArrayList<HistoReponse> lesReponses = new ArrayList<HistoReponse>();
		String requete = "SELECT * FROM histoReponse WHERE "
				+ "idreponse LIKE '%"+mot+"%' OR "
				+ "idquestion LIKE '%"+mot+"%' OR "
				+ "reponse LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "dateHeureAction LIKE '%"+mot+"%' OR "
				+ "user LIKE '%"+mot+"%' OR "
				+ "action LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				HistoReponse uneReponse = new HistoReponse (
						desResultats.getInt("idreponse"),
						desResultats.getInt("idquestion"),
						desResultats.getString("reponse"),
						desResultats.getInt("idclient"),
						desResultats.getString("dateHeureAction"),
						desResultats.getString("user"),
						desResultats.getString("action")
						);
				lesReponses.add(uneReponse);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesReponses;
	}
	
	// Nombre de réponses
	public static int countReponses () {
		int nbreponse = 0;
		String requete = "SELECT count(*) as nb FROM reponse;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStatement.executeQuery(requete);
			if (unResultat.next()) {
				nbreponse = unResultat.getInt("nb");
			}
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return nbreponse;
	}
	
	/*** GESTION DE LA VIEW 'vstatsproduits' ***/
	
	// Séléction de la view 'vstatsproduits'
	public static ArrayList<Vstatsproduits> selectAllVstatsproduits () {
		ArrayList<Vstatsproduits> lesStatsProduits = new ArrayList<Vstatsproduits>();
		String requete = "SELECT * FROM vstatsproduits;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				Vstatsproduits unStatProduit = new Vstatsproduits (
						desResultats.getString("nomproduit"),
						desResultats.getString("autoradio"),
						desResultats.getString("gps"),
						desResultats.getString("aide_a_la_conduite"),
						desResultats.getString("haut_parleurs"),
						desResultats.getString("kit_mains_libre"),
						desResultats.getString("amplificateurs")
						);
				lesStatsProduits.add(unStatProduit);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesStatsProduits;
	}
	
	/*** GESTION DE LA VIEW 'mesQuestions' ***/
	
	// Séléction de la view 'mesQuestions'
	public static ArrayList<MesQuestions> selectAllMesQuestions () {
		ArrayList<MesQuestions> lesQuestions = new ArrayList<MesQuestions>();
		String requete = "SELECT * FROM mesQuestions;";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				MesQuestions uneQuestion = new MesQuestions (
						desResultats.getInt("idreponse"),
						desResultats.getInt("idquestion"),
						desResultats.getString("enonce"),
						desResultats.getString("reponse"),
						desResultats.getInt("idclient"),
						desResultats.getString("client"),
						desResultats.getString("email")
						);
				lesQuestions.add(uneQuestion);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesQuestions;
	}
	
	// Recherche d'une reponse dans la view 'mesQuestions'
	public static ArrayList<MesQuestions> selectLikeMesQuestions(String mot) {
		ArrayList<MesQuestions> lesQuestions = new ArrayList<MesQuestions>();
		String requete = "SELECT * FROM mesQuestions WHERE "
				+ "idreponse LIKE '%"+mot+"%' OR "
				+ "idquestion LIKE '%"+mot+"%' OR "
				+ "enonce LIKE '%"+mot+"%' OR "
				+ "reponse LIKE '%"+mot+"%' OR "
				+ "idclient LIKE '%"+mot+"%' OR "
				+ "client LIKE '%"+mot+"%' OR "
				+ "email LIKE '%"+mot+"%';";
		try {
			uneBdd.seConnecter();
			Statement unStatement = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStatement.executeQuery(requete);
			while (desResultats.next()) {
				MesQuestions uneQuestion = new MesQuestions (
						desResultats.getInt("idreponse"),
						desResultats.getInt("idquestion"),
						desResultats.getString("enonce"),
						desResultats.getString("reponse"),
						desResultats.getInt("idclient"),
						desResultats.getString("client"),
						desResultats.getString("email")
						);
				lesQuestions.add(uneQuestion);
			}
			unStatement.close();
			uneBdd.seDeconnecter();
		} catch (SQLException exp) {
			System.out.println("Erreur de requête : " + requete);
		}
		return lesQuestions;
	}
	
}
