package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controleur.Client;
import controleur.Produit;
import controleur.Tableau;
import controleur.Type;
import controleur.VClient;
import controleur.VProduit;
import modele.Modele;

public class PanelProduit extends PanelDeBase implements ActionListener, ListSelectionListener, ItemListener {
	
	private JPanel panelForm = new JPanel();
	private JPanel panelTable = new JPanel();
	
	private JLabel titre = new JLabel("Gestion des produits");
	
	Font fButton = new Font("Comic Sans MS", Font.BOLD, 18);
	Font fTitre = new Font("Comic Sans MS", Font.BOLD, 30);
	Font fImage = new Font("Arial", Font.BOLD, 10);
	
	private JButton btImage = new JButton("Sélectionner un image");
	private JButton btAjouter = new JButton("Ajouter");
	private JButton btAnnuler = new JButton("Annuler");
	
	private JTextField txtNomProduit = new JTextField();
	private JTextField txtImageProduit = new JTextField();
	private JTextField txtDescriptionProduit = new JTextField();
	private JTextField txtQteProduit = new JTextField();
	private JTextField txtPrixProduit = new JTextField();
	
	private JComboBox<String> comboType = new JComboBox<String>(); // Type du produit
	
	private JLabel lbNomProduit = new JLabel("Nom du produit : ", SwingConstants.RIGHT);
	private JLabel lbImageProduit = new JLabel("Image du produit : ", SwingConstants.RIGHT);
	private JLabel lbDescriptionProduit = new JLabel("Description du produit : ", SwingConstants.RIGHT);
	private JLabel lbQteProduit = new JLabel("Quantité du produit : ", SwingConstants.RIGHT);
	private JLabel lbPrixProduit = new JLabel("Prix du produit : ", SwingConstants.RIGHT);
	private JLabel lbTypeProduit = new JLabel("Type du produit : ", SwingConstants.RIGHT);
	
	private JTable uneTable = null;

	private static Tableau unTableau = null;
	
	private JTextField txtMot = new JTextField();
	private JButton btRechercher = new JButton("Rechercher");

	public PanelProduit(Client unClient) {
		super(new Color(23, 25, 51));
		
		this.titre.setBounds(5, -1, 500, 40);
		this.titre.setForeground(Color.WHITE);
		this.titre.setFont(fTitre);
		this.add(titre);
		
		this.txtMot.setBounds(980, 10, 150, 20);
		this.add(this.txtMot);
		
		this.btRechercher.setBounds(1145, 10, 110, 20);
		this.btRechercher.addActionListener(this);
		this.add(this.btRechercher);
		
		// Rendre le champ de type number
		txtQteProduit.addKeyListener(new KeyAdapter() { 
		    public void keyTyped(KeyEvent e) {
		        char c = e.getKeyChar();
		        if ( ((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE)) {
		            e.consume();  // Si ce n’est pas un nombre, ignorer l’événement
		        }
		     }
		});
		
		this.panelForm.setLayout(new GridLayout(7,2));
		
		this.panelForm.add(lbNomProduit);
		this.panelForm.add(this.txtNomProduit);
		
		this.panelForm.add(lbImageProduit);
		this.panelForm.add(this.txtImageProduit);
		
		this.panelForm.add(lbDescriptionProduit);
		this.panelForm.add(this.txtDescriptionProduit);
		
		this.panelForm.add(lbQteProduit);
		this.panelForm.add(this.txtQteProduit);
		
		this.panelForm.add(lbPrixProduit);
		this.panelForm.add(this.txtPrixProduit);
		
		this.panelForm.add(lbTypeProduit);
		this.panelForm.add(this.comboType);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btAjouter);
		
		this.panelForm.setBounds(10, 60, 300, 200);
		this.add(this.panelForm);
		
		// Bouton Image
		this.btImage.setBackground(Color.GRAY);
		this.btImage.setForeground(Color.WHITE);
		this.btImage.setFont(fImage);
		this.btImage.addActionListener(this);
		
		// Bouton Annuler
		this.btAnnuler.setBackground(new Color(178, 34, 34));
		this.btAnnuler.setForeground(Color.WHITE);
		this.btAnnuler.setFont(fButton);
		this.btAnnuler.addActionListener(this);
		
		// Bouton Ajouter
		this.btAjouter.setBackground(new Color(0, 128, 0));
		this.btAjouter.setForeground(Color.WHITE);
		this.btAjouter.setFont(fButton);
		this.btAjouter.addActionListener(this);
		
		this.panelTable.setBounds(330, 40, 945, 320); // Dimension du fond
		this.panelTable.setBackground(new Color(23, 25, 51));
		this.panelTable.setLayout(null);
		String entetes[] = { "ID", "Nom", "Image", "Quantité", "Prix", "Type", "Date ajout" };
		Object donnees[][] = this.getLesDonnees("");
		unTableau = new Tableau(entetes, donnees);
		this.uneTable = new JTable(unTableau);
		JScrollPane uneScroll = new JScrollPane(this.uneTable);
		uneScroll.setBounds(10, 10, 925, 250); // Dimension du tableau d'affichage
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
				if (unClient.getRole().equals("admin")) {
					int nbclic = e.getClickCount();
					if (nbclic == 2) {
						int numLigne = uneTable.getSelectedRow();
						int retour = JOptionPane.showConfirmDialog(null, 
								"Voulez-vous vraiment supprimer ce produit ?",
								"Suppression d'un produit",
								JOptionPane.YES_NO_OPTION);
						if (retour == 0) {
							int idproduit = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
							Modele.deleteProduit(idproduit);
							unTableau.supprimerLigne(numLigne);
							viderChamps();
						}
					} else if (nbclic == 1) {
						int numLigne = uneTable.getSelectedRow();
						
						String nomproduit = unTableau.getValueAt(numLigne, 1).toString();
						txtNomProduit.setText(nomproduit);
						
						String imageproduit = unTableau.getValueAt(numLigne, 2).toString();
						txtImageProduit.setText(imageproduit);
						
						String qteproduit = unTableau.getValueAt(numLigne, 3).toString();
						txtQteProduit.setText(qteproduit);
						
						String prixproduit = unTableau.getValueAt(numLigne, 4).toString();
						txtPrixProduit.setText(prixproduit);
						
						String libelle = unTableau.getValueAt(numLigne, 5).toString();
						
						Produit unProduit = Modele.selectWhereNomProduit(nomproduit);
						txtDescriptionProduit.setText(unProduit.getDescriptionproduit());
						
						Type unType = Modele.selectWhereType(libelle);
						comboType.setSelectedItem(unType.getIdtype()+" - "+unType.getLibelle());
						
						btAjouter.setText("Modifier");
					}
				}
			}
		});
		
		this.remplirComboBox();
	}
	
	public void remplirComboBox () {
		ArrayList<Type> lesTypes = Modele.selectAllTypes();
		for (Type unType : lesTypes) {
			this.comboType.addItem(unType.getIdtype()+" - "+unType.getLibelle());
		}
	}
	
	public Object[][] getLesDonnees(String mot) {
		ArrayList<VProduit> lesVProduits = null;
		if (mot.equals("")) {
			lesVProduits = Modele.selectAllVProduits();
		} else {
			lesVProduits = Modele.selectLikeVProduit(mot);
		}
		Object[][] matrice = new Object[lesVProduits.size()][7];
		int i = 0;
		for (VProduit unProduit : lesVProduits) {
			matrice[i][0] = unProduit.getIdproduit();
			matrice[i][1] = unProduit.getNomproduit();
			matrice[i][2] = unProduit.getImageproduit();
			matrice[i][3] = unProduit.getQteproduit();
			matrice[i][4] = unProduit.getPrixproduit() + " €";
			matrice[i][5] = unProduit.getLibelle();
			matrice[i][6] = unProduit.getDate_ajout();
			i++;
		}
		return matrice;
	}
	
	public void viderChamps() {
		this.txtNomProduit.setText("");
		this.txtImageProduit.setText("");
		this.txtDescriptionProduit.setText("");
		this.txtQteProduit.setText("");
		this.txtPrixProduit.setText("");
		this.comboType.setSelectedIndex(0);
		this.btAjouter.setText("Ajouter");
	}
	
	public Produit saisirProduit () {
		Produit unProduit = null;
		
		String nomproduit = this.txtNomProduit.getText();
		String imageproduit = this.txtImageProduit.getText();
		String descriptionproduit = this.txtDescriptionProduit.getText();
		int qteproduit = 0;
		int prixproduit = 0;
		
		String tab[] = this.comboType.getSelectedItem().toString().split(" - ");
		int idtype = Integer.parseInt(tab[0]);
		
		if (nomproduit.equals("")) {
			this.txtNomProduit.setBackground(Color.red);
		} else {
			this.txtNomProduit.setBackground(Color.white);
		}
		
		if (imageproduit.equals("")) {
			this.txtImageProduit.setBackground(Color.red);
		} else {
			this.txtImageProduit.setBackground(Color.white);
		}
		
		if (descriptionproduit.equals("")) {
			this.txtDescriptionProduit.setBackground(Color.red);
		} else {
			this.txtDescriptionProduit.setBackground(Color.white);
		}
		
		try {
			qteproduit = Integer.parseInt(this.txtQteProduit.getText());
		} catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this, "Attention au format du nombre.");
			this.txtQteProduit.setBackground(Color.red);
		}
		
		try {
			prixproduit = Integer.parseInt(this.txtPrixProduit.getText());
		} catch (NumberFormatException exp) {
			JOptionPane.showMessageDialog(this, "Attention au format du nombre.");
			this.txtPrixProduit.setBackground(Color.red);
		}
		
		if (!nomproduit.equals("") && !imageproduit.equals("") && !descriptionproduit.equals("") && qteproduit > 0 && prixproduit > 0) {
			unProduit = new Produit (nomproduit, imageproduit, descriptionproduit, qteproduit, prixproduit, idtype);
		} else {
			return null;
		}
		
		return unProduit;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == this.btAjouter && e.getActionCommand().equalsIgnoreCase("Ajouter")) {
			Produit unProduit = this.saisirProduit();
			if (unProduit != null) {
				Modele.insertProduit(unProduit);
				unProduit = Modele.selectWhereNomProduit(unProduit.getNomproduit());
				
				VProduit unVProduit = Modele.selectWhereVProduit(unProduit.getNomproduit());
				
				Object ligne[] = {
					unVProduit.getIdproduit(),
					unVProduit.getNomproduit(),
					unVProduit.getImageproduit(),
					unVProduit.getQteproduit(),
					unVProduit.getPrixproduit(),
					unVProduit.getLibelle(),
					unVProduit.getDate_ajout()
				};
				unTableau.ajouterLigne(ligne);
				unTableau.fireTableDataChanged();
				
				JOptionPane.showMessageDialog(this, "Insertion du produit réussi !");
				this.viderChamps();
			} else {
				JOptionPane.showMessageDialog(this, "Echec de l'insertion !");
			}
		} else if (e.getSource() == this.btAjouter && e.getActionCommand().equalsIgnoreCase("Modifier")) {
			Produit unProduit = this.saisirProduit();
			int numLigne = this.uneTable.getSelectedRow();
			int idproduit = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
			unProduit.setIdproduit(idproduit);
			Modele.updateProduit(unProduit);
			
			VProduit unVProduit = Modele.selectWhereVProduit(unProduit.getNomproduit());
			
			Object ligne[] = {
				unVProduit.getIdproduit(),
				unVProduit.getNomproduit(),
				unVProduit.getImageproduit(),
				unVProduit.getQteproduit(),
				unVProduit.getPrixproduit(),
				unVProduit.getLibelle(),
				unVProduit.getDate_ajout()
			};
			unTableau.modifierLigne(numLigne, ligne);
			JOptionPane.showMessageDialog(this, "Modification du produit effectuée !");
			this.viderChamps();
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
