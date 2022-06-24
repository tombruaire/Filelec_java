package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Client;
import controleur.MesQuestions;
import controleur.Question;
import controleur.Reponse;
import controleur.Tableau;
import modele.Modele;

public class PanelReponse extends PanelDeBase implements ActionListener, ListSelectionListener, ItemListener {
	
	private JPanel panelForm = new JPanel();
	
	private JLabel titre = new JLabel("Gestion des réponses");
	
	Font fButton = new Font("Comic Sans MS", Font.BOLD, 18);
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelTable = new JPanel();
	
	private JComboBox<String> comboQuestion = new JComboBox<String>();
	private JTextField txtReponse = new JTextField();
	private JComboBox<String> comboClient = new JComboBox<String>();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btAjouter = new JButton("Ajouter");
	
	private JLabel lbQuestion = new JLabel("Question : ", SwingConstants.RIGHT);
	private JLabel lbReponse = new JLabel("Réponse : ", SwingConstants.RIGHT);
	private JLabel lbClient = new JLabel("Client : ", SwingConstants.RIGHT);
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");

	public PanelReponse() {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(465, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.btRechercher.setBounds(1160, 10, 110, 20);
		this.add(this.btRechercher);
		
		this.txtMot.setBounds(1000, 10, 150, 20);
		this.add(txtMot);
		
		this.panelForm.setLayout(new GridLayout(4,2));
		
		this.panelForm.add(lbQuestion);
		this.panelForm.add(this.comboQuestion);
		
		this.panelForm.add(lbReponse);
		this.panelForm.add(this.txtReponse);
		
		this.panelForm.add(lbClient);
		this.panelForm.add(this.comboClient);
		
		this.btAnnuler.setBackground(new Color(178, 34, 34));
		this.btAnnuler.setForeground(Color.WHITE);
		this.btAnnuler.setFont(fButton);
		this.panelForm.add(this.btAnnuler);
		
		this.btAjouter.setBackground(new Color(0, 128, 0));
		this.btAjouter.setForeground(Color.WHITE);
		this.btAjouter.setFont(fButton);
		this.panelForm.add(this.btAjouter);
		
		this.panelForm.setBounds(10, 150, 300, 150);
		this.add(this.panelForm);
		
		this.panelTable.setLayout(new GridLayout(5,1));
		this.panelTable.setBounds(400, 50, 850, 800);
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.setLayout(null);
		this.setVisible(false);
		
		String entetes[] = {"ID", "Question", "Réponse", "Client", "Email"};
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
		
		this.uneTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int nbclic = e.getClickCount();
				if (nbclic == 2) {
					int numLigne = uneTable.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette réponse à cette question ?",
							"Suppression d'une question",
							JOptionPane.YES_NO_OPTION);
					if (retour == 0) {
						int idreponse = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						Modele.deleteReponse(idreponse);
						unTableau.supprimerLigne(numLigne);
						txtReponse.setText("");
					}
					
				} else if (nbclic == 1) {
					int numLigne = uneTable.getSelectedRow();
					
					String idquestion = unTableau.getValueAt(numLigne, 5).toString();
					comboQuestion.setSelectedItem(idquestion);
					
					String reponse = unTableau.getValueAt(numLigne, 2).toString();
					txtReponse.setText(reponse);
					
					String idclient = unTableau.getValueAt(numLigne, 6).toString();
					comboClient.setSelectedItem(idclient);
					
					btAjouter.setText("Modifier");
				}
			}
		});
		
		this.remplirCBX();
		
		this.btAnnuler.addActionListener(this);
		this.btAjouter.addActionListener(this);
		this.btRechercher.addActionListener(this);
	}
	
	public void remplirCBX () {
		ArrayList<Question> lesQuestions = Modele.selectAllQuestions();
		for (Question uneQuestion : lesQuestions) {
			this.comboQuestion.addItem(uneQuestion.getEnonce());
		}
		ArrayList<Client> lesClients = Modele.selectAllClients();
		for (Client unClient : lesClients) {
			this.comboClient.addItem(unClient.getNom());
		}
	}
	
	public Object[][] getLesDonnees(String mot) {
		ArrayList<MesQuestions> lesQuestions = null;
		if (mot.equals("")) {
			lesQuestions = Modele.selectAllMesQuestions();
		} else {
			lesQuestions = Modele.selectLikeMesQuestions(mot);
		}
		Object[][] matrice = new Object[lesQuestions.size()][7];
		int i = 0;
		for (MesQuestions uneQuestion : lesQuestions) {
			matrice[i][0] = uneQuestion.getIdreponse();
			matrice[i][1] = uneQuestion.getEnonce();
			matrice[i][2] = uneQuestion.getReponse();
			matrice[i][3] = uneQuestion.getClient();
			matrice[i][4] = uneQuestion.getEmail();
			matrice[i][5] = uneQuestion.getIdquestion();
			matrice[i][6] = uneQuestion.getIdclient();
			i++;
		}
		return matrice;
	}
	
	public Reponse saisirReponse () {
		Reponse uneReponse = null;
		
		String tab[] = this.comboQuestion.getSelectedItem().toString().split("");
		int idquestion = Integer.parseInt(tab[0]);
		
		tab = this.comboClient.getSelectedItem().toString().split("");
		int idclient = Integer.parseInt(tab[0]);
		
		String reponse = this.txtReponse.getText();
		
		if (reponse.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez saisir une réponse");
			this.txtReponse.setBackground(Color.red);
		} else {
			this.txtReponse.setBackground(Color.white);
		}
		
		if (!reponse.equals("")) {
			uneReponse = new Reponse(idquestion, reponse, idclient);
		} else {
			return null;
		}
		
		return uneReponse;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btRechercher) {
			String mot = this.txtMot.getText();
			Object matrice [][] = this.getLesDonnees(mot);
			unTableau.setDonnees(matrice);
		} else if (e.getSource() == this.btAnnuler) {
			this.txtReponse.setText("");
		} else if (e.getSource() == this.btAjouter && e.getActionCommand().equals("Ajouter")) {
			Reponse uneReponse = this.saisirReponse();
			Modele.insertReponse(uneReponse);
			uneReponse = Modele.selectWhereReponseQuestion(uneReponse.getIdquestion());
			Object ligne[] = {uneReponse.getIdreponse(), uneReponse.getIdquestion(), uneReponse.getReponse(), uneReponse.getIdclient()};
			unTableau.ajouterLigne(ligne);
			JOptionPane.showMessageDialog(this, "Insertion de la réponse réussi !");
		} else if (e.getSource() == this.btAjouter && e.getActionCommand().equals("Modifier")) {
			Reponse uneReponse = this.saisirReponse();
			int numLigne = this.uneTable.getSelectedRow();
			int idreponse = Integer.parseInt(uneTable.getValueAt(numLigne, 0).toString());
			uneReponse.setIdreponse(idreponse);
			Modele.updateReponse(uneReponse);
			Object ligne[] = {uneReponse.getIdreponse(), uneReponse.getIdquestion(), uneReponse.getReponse(), uneReponse.getIdclient()};
			unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification de la réponse réussi !");
			this.txtReponse.setText("");
			this.btAjouter.setText("Ajouter");
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
