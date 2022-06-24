package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Tableau;
import controleur.VCommentaire;
import modele.Modele;

public class PanelCommentaire extends PanelDeBase {
	
	private JLabel titre = new JLabel("Gestion des commentaires");
	
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelTable = new JPanel();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;

	public PanelCommentaire() {
		super(new Color(23, 25, 21));
		
		this.titre.setBounds(505, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.panelTable.setLayout(new GridLayout(5,1));
		this.panelTable.setBounds(10, 50, 1270, 1000);
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.setLayout(null);
		this.setVisible(false);
		
		String entetes[] = {"ID Com", "Produit", "Client", "Contenu", "DateHeure"};
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
		ArrayList<VCommentaire> lesCommentaires = Modele.selectAllVCommentaires();
		Object[][] matrice = new Object[lesCommentaires.size()][5];
		int i = 0;
		for (VCommentaire unCommentaire : lesCommentaires) {
			matrice[i][0] = unCommentaire.getIdcom();
			matrice[i][1] = unCommentaire.getProduit();
			matrice[i][2] = unCommentaire.getClient();
			matrice[i][3] = unCommentaire.getContenu();
			matrice[i][4] = unCommentaire.getDateheurepost();
			i++;
		}
		return matrice;
	}

}
