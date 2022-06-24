package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import controleur.Tableau;
import controleur.VMessage;
import modele.Modele;

public class PanelMessage extends PanelDeBase {
	
	private JLabel titre = new JLabel("Gestion des messages");
	
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelTable = new JPanel();
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;

	public PanelMessage() {
		super(new Color(23, 25, 27));
		
		this.titre.setBounds(505, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.panelTable.setLayout(new GridLayout(5,1));
		this.panelTable.setBounds(10, 50, 1270, 2000);
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.setLayout(null);
		this.setVisible(false);
		
		String entetes[] = {"ID", "Expéditeur", "Destinataire", "Date envoi", "Contenu"};
		Object donnees [][] = this.getLesDonnees();
		unTableau = new Tableau(entetes, donnees);
		this.uneTable = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		this.panelTable.add(uneScroll);
		this.add(this.panelTable);
	}
	
	public Object[][] getLesDonnees() {
		ArrayList<VMessage> lesMessages = Modele.selectAllVMessages();
		Object[][] matrice = new Object[lesMessages.size()][5];
		int i = 0;
		for (VMessage unMessage : lesMessages) {
			matrice[i][0] = unMessage.getIdmessage();
			matrice[i][1] = unMessage.getExpediteur();
			matrice[i][2] = unMessage.getDestinataire();
			matrice[i][3] = unMessage.getDate_envoi();
			matrice[i][4] = unMessage.getContenu();
			i++;
		}
		return matrice;
	}

}
