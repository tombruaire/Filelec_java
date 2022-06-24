package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Client;
import controleur.Tableau;
import controleur.VClient;
import modele.Modele;

public class PanelConnexion extends PanelDeBase implements ActionListener {
	
	private JLabel titre = new JLabel("Liste des dernières connexions");
	
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelTable = new JPanel();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;

	public PanelConnexion() {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(505, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.panelTable.setLayout(new GridLayout(4,1));
		this.panelTable.setBounds(10, 50, 1270, 4000);
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.setLayout(null);
		this.setVisible(false);
		
		String entetes[] = {"ID", "Nom", "Email", "Connexion", "Déconnexion"};
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
	}
	
	public Object[][] getLesDonnees(String mot) {
		ArrayList<VClient> lesClients = Modele.selectLastConnexionClients();
		Object[][] matrice = new Object[lesClients.size()][5];
		int i = 0;
		for (VClient unClient : lesClients) {
			matrice[i][0] = unClient.getIdclient();
			matrice[i][1] = unClient.getNom();
			matrice[i][2] = unClient.getEmail();
			matrice[i][3] = unClient.getConnexion();
			matrice[i][4] = unClient.getDeconnexion();
			i++;
		}
		return matrice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// 
	}

}
