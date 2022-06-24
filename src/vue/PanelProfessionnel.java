package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Professionnel;
import controleur.Tableau;
import modele.Modele;

public class PanelProfessionnel extends PanelDeBase implements ActionListener {
	
	private JLabel titre = new JLabel("Liste des clients professionnels");
	
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelTable = new JPanel();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");

	public PanelProfessionnel() {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(465, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.btRechercher.setBounds(1160, 10, 110, 20);
		this.add(this.btRechercher);
		
		this.txtMot.setBounds(1000, 10, 150, 20);
		this.add(txtMot);
		
		this.panelTable.setLayout(new GridLayout(7,1));
		this.panelTable.setBounds(10, 50, 1270, 2000);
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.setLayout(null);
		this.setVisible(false);
		
		String entetes[] = {"ID", "Nom", "Téléphone", "Email", "Statut", "Role", "Type"};
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
		ArrayList<Professionnel> lesProfessionnels = null;
		if (mot.equals("")) {
			lesProfessionnels = Modele.selectAllProfessionnels();
		} else {
			lesProfessionnels = Modele.selectLikeProfessionnel(mot);
		}
		Object[][] matrice = new Object[lesProfessionnels.size()][7];
		int i = 0;
		for (Professionnel unProfessionnel : lesProfessionnels) {
			matrice[i][0] = unProfessionnel.getIdclient();
			matrice[i][1] = unProfessionnel.getNom();
			matrice[i][2] = unProfessionnel.getTel();
			matrice[i][3] = unProfessionnel.getEmail();
			matrice[i][4] = unProfessionnel.getStatut();
			matrice[i][5] = unProfessionnel.getRole();
			matrice[i][6] = unProfessionnel.getType();
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
