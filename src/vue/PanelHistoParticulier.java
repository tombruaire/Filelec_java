package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.HistoParticulier;
import controleur.Tableau;
import modele.Modele;

public class PanelHistoParticulier extends PanelDeBase implements ActionListener {
	
	private JLabel titre = new JLabel("Liste des particuliers archivés");
	
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelTable = new JPanel();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");

	public PanelHistoParticulier() {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(465, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.btRechercher.setBounds(1160, 10, 110, 20);
		this.add(this.btRechercher);
		
		this.txtMot.setBounds(1000, 10, 150, 20);
		this.add(txtMot);
		
		this.panelTable.setLayout(new GridLayout(6,1));
		this.panelTable.setBounds(10, 50, 1270, 2000);
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.setLayout(null);
		this.setVisible(false);
		
		String entetes[] = {"ID", "Nom", "Prénom", "Type", "Date action", "Action"};
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
		ArrayList<HistoParticulier> lesParticuliers = null;
		if (mot.equals("")) {
			lesParticuliers = Modele.selectAllHistoParticuliers();
		} else {
			lesParticuliers = Modele.selectLikeHistoParticulier(mot);
		}
		Object[][] matrice = new Object[lesParticuliers.size()][6];
		int i = 0;
		for (HistoParticulier unParticulier : lesParticuliers) {
			matrice[i][0] = unParticulier.getIdclient();
			matrice[i][1] = unParticulier.getNom();
			matrice[i][2] = unParticulier.getPrenom();
			matrice[i][3] = unParticulier.getType();
			matrice[i][4] = unParticulier.getDateHeureAction();
			matrice[i][5] = unParticulier.getAction();
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
