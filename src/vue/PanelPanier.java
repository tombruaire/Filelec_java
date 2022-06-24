package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Panier;
import controleur.Tableau;
import modele.Modele;

public class PanelPanier extends PanelDeBase {
	
	private JLabel titre = new JLabel("Gestion du panier");
	
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelTable = new JPanel();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;

	public PanelPanier() {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(505, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.panelTable.setLayout(new GridLayout(4,1));
		this.panelTable.setBounds(10, 50, 1270, 1000);
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.setLayout(null);
		this.setVisible(false);
		
		String entetes[] = {"Commande n°", "Produit n°", "Quantité", "Client n°"};
		Object donnees [][] = this.getLesDonnees();
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
	}
	
	public Object[][] getLesDonnees() {
		ArrayList<Panier> lesPaniers = Modele.selectAllPaniers();
		Object[][] matrice = new Object[lesPaniers.size()][4];
		int i = 0;
		for (Panier unPanier : lesPaniers) {
			matrice[i][0] = unPanier.getNumcommande();
			matrice[i][1] = unPanier.getIdproduit();
			matrice[i][2] = unPanier.getQuantite();
			matrice[i][3] = unPanier.getIdclient();
			i++;
		}
		return matrice;
	}

}
