/*
 * Copyright 2024 widad.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package tp04interface;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import tp04.metier.Action;
import tp04.metier.ActionSimple;
import tp04.metier.Portefeuille;

/**
 *
 * @author widad
 */
public class AchatInterface extends JFrame {

    private Portefeuille portefeuille;
    private DefaultListModel<Action> listModel;
    private JList<Action> actionList;
    private PortefeuilleInterface portefeuilleInterface;

    public AchatInterface(Portefeuille portefeuille, PortefeuilleInterface portefeuilleInterface) {
        this.portefeuille = portefeuille;
        this.portefeuilleInterface = portefeuilleInterface;

        setTitle("Interface d'achat");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Création d'un modèle de liste pour afficher les actions disponibles
        listModel = new DefaultListModel<>();
        listModel.addElement(new ActionSimple("Action 1"));
        listModel.addElement(new ActionSimple("Action 2"));
        listModel.addElement(new ActionSimple("Action 3"));

        // Création d'une liste avec le modèle de données
        actionList = new JList<>(listModel);
        // Ajout d'un JScrollPane pour permettre le défilement
        JScrollPane scrollPane = new JScrollPane(actionList);

        // Création d'un champ de texte pour entrer la quantité
        JTextField quantiteField = new JTextField(10);

        // Création d'un bouton pour ajouter l'action sélectionnée au portefeuille
        JButton ajouterButton = new JButton("Ajouter");
        ajouterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Action actionSelectionnee = actionList.getSelectedValue();
                if (actionSelectionnee != null) {
                    try {
                        int quantite = Integer.parseInt(quantiteField.getText());
                        portefeuille.acheter(actionSelectionnee, quantite);
                        JOptionPane.showMessageDialog(null, "Action ajoutée au portefeuille !");
                        // Rafraîchir l'affichage du portefeuille après l'achat
                        portefeuilleInterface.refreshTable();
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Veuillez entrer une quantité valide.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une action.");
                }
            }
        });

        // Création d'un conteneur pour les composants
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(quantiteField, BorderLayout.EAST);
        panel.add(ajouterButton, BorderLayout.SOUTH);

        // Ajout du conteneur au contenu de la fenêtre
        getContentPane().add(panel, BorderLayout.CENTER);

        // Création du bouton "Retour Accueil"
        JButton retourAccueilButton = new JButton("Retour Accueil");
        retourAccueilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ferme cette fenêtre
                dispose();

                // Ajoutez ici le code pour afficher l'écran d'accueil
                AccueilInterface accueil = new AccueilInterface(); // Crée une nouvelle instance de l'écran d'accueil
                accueil.setVisible(true); // Rend l'écran d'accueil visible
            }
        });

        // Ajout du bouton "Retour Accueil" en bas de la fenêtre
        getContentPane().add(retourAccueilButton, BorderLayout.SOUTH);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Création d'un portefeuille fictif pour tester l'interface
                Portefeuille portefeuille = new Portefeuille();
                PortefeuilleInterface portefeuilleInterface = new PortefeuilleInterface(portefeuille);


                // Création et affichage de l'interface d'achat
                AchatInterface achatInterface = new AchatInterface(portefeuille,portefeuilleInterface);
                achatInterface.setVisible(true);
                
                portefeuilleInterface.refreshTable();

            }
        });
    }
}
