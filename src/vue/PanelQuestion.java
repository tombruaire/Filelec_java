package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import controleur.Client;
import controleur.Question;
import controleur.Tableau;
import controleur.VClient;
import modele.Modele;

public class PanelQuestion extends PanelDeBase implements ActionListener {
	
	private JPanel panelForm = new JPanel();
	
	private JLabel titre = new JLabel("Gestion des questions");
	
	Font fButton = new Font("Comic Sans MS", Font.BOLD, 18);
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	
	private JPanel panelTable = new JPanel();
	
	private JTextField txtEnonce = new JTextField();
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btAjouter = new JButton("Ajouter");
	
	private JLabel lbEnonce = new JLabel("Enoncé de la question : ", SwingConstants.RIGHT);
	
	private JTable uneTable = null;
	
	private static Tableau unTableau = null;
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");

	public PanelQuestion(Client unClient) {
		super(new Color(23, 25, 27));
		
		this.titre.setBounds(465, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.btRechercher.setBounds(1160, 10, 110, 20);
		this.add(this.btRechercher);
		
		this.txtMot.setBounds(1000, 10, 150, 20);
		this.add(txtMot);
		
		this.panelForm.setLayout(new GridLayout(2,2));
		
		this.panelForm.add(lbEnonce);
		this.panelForm.add(this.txtEnonce);
		
		this.btAnnuler.setBackground(new Color(178, 34, 34));
		this.btAnnuler.setForeground(Color.WHITE);
		this.btAnnuler.setFont(fButton);
		this.panelForm.add(this.btAnnuler);
		
		this.btAjouter.setBackground(new Color(0, 128, 0));
		this.btAjouter.setForeground(Color.WHITE);
		this.btAjouter.setFont(fButton);
		this.panelForm.add(this.btAjouter);
		
		this.panelForm.setBounds(10, 150, 300, 100);
		
		if (unClient.getRole().equals("admin")) {
			// Si le client est admin, on affiche le formulaire
			this.add(this.panelForm);
			
			this.panelTable.setLayout(new GridLayout(2,1));
			this.panelTable.setBounds(400, 50, 850, 800);
			this.panelTable.setBackground(new Color(23, 25, 51));
			this.setLayout(null);
			this.setVisible(false);
			
			String entetes[] = {"ID", "Enonce"};
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
		} else {
			// Modifier les dimensions du tableau d'affichage
		}
		
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
					int retour = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer cette question ?",
							"Suppression d'un énoncé",
							JOptionPane.YES_NO_OPTION);
					if (retour == 0) {
						int idquestion = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
						Modele.deleteQuestion(idquestion);
						unTableau.supprimerLigne(numLigne);
						txtEnonce.setText("");
					}
				} else if (nbclic == 1) {
					int numLigne = uneTable.getSelectedRow();
					String enonce = unTableau.getValueAt(numLigne, 1).toString();
					txtEnonce.setText(enonce);
					btAjouter.setText("Modifier");
				}
			}
		});
		
		this.btAnnuler.addActionListener(this);
		this.btAjouter.addActionListener(this);
		this.btRechercher.addActionListener(this);
	}
	
	public Object[][] getLesDonnees(String mot) {
		ArrayList<Question> lesQuestions = null;
		if (mot.equals("")) {
			lesQuestions = Modele.selectAllQuestions();
		} else {
			lesQuestions = Modele.selectLikeQuestion(mot);
		}
		Object[][] matrice = new Object[lesQuestions.size()][2];
		int i = 0;
		for (Question uneQuestion : lesQuestions) {
			matrice[i][0] = uneQuestion.getIdquestion();
			matrice[i][1] = uneQuestion.getEnonce();
			i++;
		}
		return matrice;
	}
	
	public Question saisirEnonce () {
		Question uneQuestion = null;
		
		String enonce = this.txtEnonce.getText();
		
		if (enonce.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez saisir un énoncé");
			this.txtEnonce.setBackground(Color.red);
		} else {
			this.txtEnonce.setBackground(Color.white);
		}
		
		if (!enonce.equals("")) {
			uneQuestion = new Question (enonce);
		} else {
			return null;
		}
		
		return uneQuestion;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btRechercher) {
			String mot = this.txtMot.getText();
			Object matrice [][] = this.getLesDonnees(mot);
			unTableau.setDonnees(matrice);
		} else if (e.getSource() == this.btAnnuler) {
			this.txtEnonce.setText("");
		} else if (e.getSource() == this.btAjouter && e.getActionCommand().equals("Ajouter")) {
			Question uneQuestion = this.saisirEnonce();
			Modele.insertQuestion(uneQuestion);
			uneQuestion = Modele.selectWhereQuestion(uneQuestion.getEnonce());
			Object ligne[] = {uneQuestion.getIdquestion(), uneQuestion.getEnonce()};
			unTableau.ajouterLigne(ligne);
			JOptionPane.showMessageDialog(this, "Insertion de l'énoncé réussi !");
		} else if (e.getSource() == this.btAjouter && e.getActionCommand().equals("Modifier")) {
			Question uneQuestion = this.saisirEnonce();
			int numLigne = this.uneTable.getSelectedRow();
			int idquestion = Integer.parseInt(uneTable.getValueAt(numLigne, 0).toString());
			uneQuestion.setIdquestion(idquestion);
			Modele.updateQuestion(uneQuestion);
			Object ligne[] = {uneQuestion.getIdquestion(), uneQuestion.getEnonce()};
			unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification de l'énoncé réussi !");
			this.txtEnonce.setText("");
			this.btAjouter.setText("Ajouter");
		}
	}

}
