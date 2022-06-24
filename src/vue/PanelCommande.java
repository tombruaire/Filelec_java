package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Client;
import controleur.Commande;
import controleur.Tableau;
import controleur.VClient;
import modele.Modele;

public class PanelCommande extends PanelDeBase implements ActionListener {
	
	private JLabel titre = new JLabel("Liste des commandes");
	
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelTable = new JPanel();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");

	public PanelCommande(Client unClient) {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(505, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.btRechercher.setBounds(1160, 10, 110, 20);
		this.add(this.btRechercher);
		
		this.txtMot.setBounds(1000, 10, 150, 20);
		this.add(txtMot);
		
		this.panelTable.setLayout(new GridLayout(10,1));
		this.panelTable.setBounds(10, 50, 1270, 4000);
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.setLayout(null);
		this.setVisible(false);
		
		String entetes[] = {"Commande n°", "Client n°", "Payement", "Etat", "MTHT", "MTTTC", "TVA", "Effectué le", "Livraison le", "Produit n°"};
		Object donnees [][] = this.getLesDonnees("");
		unTableau = new Tableau(entetes, donnees);
		this.uneTable = new JTable(unTableau);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0; i<uneTable.getColumnCount(); i++) {
			uneTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		this.panelTable.add(uneScroll);
		this.add(this.panelTable);
		
		this.btRechercher.addActionListener(this);
	}
	
	public Object[][] getLesDonnees(String mot) {
		ArrayList<Commande> lesCommandes = null;
		if (mot.equals("")) {
			lesCommandes = Modele.selectAllCommandes();
		} else {
			lesCommandes = Modele.selectLikeCommande(mot);
		}
		Object[][] matrice = new Object[lesCommandes.size()][10];
		int i = 0;
		for (Commande uneCommande : lesCommandes) {
			matrice[i][0] = uneCommande.getNumcommande();
			matrice[i][1] = uneCommande.getIdclient();
			matrice[i][2] = uneCommande.getModePayement();
			matrice[i][3] = uneCommande.getEtat();
			matrice[i][4] = uneCommande.getMontantTotalHT() + " €";
			matrice[i][5] = uneCommande.getMontantTotalTTC() + " €";
			matrice[i][6] = uneCommande.getTVA() + " %";
			matrice[i][7] = uneCommande.getDatecommande();
			matrice[i][8] = uneCommande.getDatelivraison();
			matrice[i][9] = uneCommande.getIdproduit();
			i++;
		}
		return matrice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btRechercher) {
			String mot = this.txtMot.getText();
			Object matrice [][] = this.getLesDonnees(mot);
			unTableau.setDonnees(matrice);
		}
	}

}
