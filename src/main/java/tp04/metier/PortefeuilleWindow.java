/*
 * Copyright 2024 Imane.
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
package tp04.metier;

/**
 *
 * @author Imane
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

public class PortefeuilleWindow extends JFrame {

    private DefaultTableModel tableModel;
    private JTable tableView;
    private Portefeuille portefeuille;

    public PortefeuilleWindow(Portefeuille portefeuille) {
        this.portefeuille = portefeuille;

        // Création du modèle de table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Libellé");
        tableModel.addColumn("Quantité");

        // Ajout des lignes au modèle de table à partir des données du portefeuille
        for (Map.Entry<Action, Portefeuille.LignePortefeuille> entry : portefeuille.getMapLignes().entrySet()) {
            Action action = entry.getKey();
            Portefeuille.LignePortefeuille lignePortefeuille = entry.getValue();
            tableModel.addRow(new Object[]{action.getLibelle(), lignePortefeuille.getQte()});
        }

        // Création de la table avec le modèle
        tableView = new JTable(tableModel);

        // Création du bouton "Vendre"
        JButton vendreButton = new JButton("Vendre");
        vendreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableView.getSelectedRow();
                if (selectedRow != -1) {
                    Action actionSelectionnee = portefeuille.getMapLignes().keySet().toArray(new Action[0])[selectedRow];
                    int quantiteTotale = portefeuille.getQuantite(actionSelectionnee);

                    // Demander à l'utilisateur de saisir la quantité à vendre
                    String quantiteStr = JOptionPane.showInputDialog("Entrez la quantité à vendre : ");
                    if (quantiteStr != null && !quantiteStr.isEmpty()) {
                        int quantiteAVendre = Integer.parseInt(quantiteStr);
                        if (quantiteAVendre > 0 && quantiteAVendre <= quantiteTotale) {
                            portefeuille.vendre(actionSelectionnee, quantiteAVendre);
                            updateTableModel();
                        } else {
                            JOptionPane.showMessageDialog(null, "La quantité à vendre est invalide ou dépasse la quantité disponible.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Veuillez saisir une quantité valide.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Aucune action sélectionnée.");
                }
            }
        });

        // Configuration de la fenêtre
        setTitle("Portefeuille du client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer uniquement cette fenêtre
        setLayout(new BorderLayout());

        // Ajout de la table dans un JScrollPane
        JScrollPane scrollPane = new JScrollPane(tableView);
        add(scrollPane, BorderLayout.CENTER);

        // Ajout du bouton "Vendre"
        add(vendreButton, BorderLayout.SOUTH);

        pack(); // Redimensionner la fenêtre selon le contenu
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
    }

    // Méthode pour mettre à jour le modèle de la table avec les données du portefeuille
    private void updateTableModel() {
        tableModel.setRowCount(0); // Effacer les lignes existantes de la table
        for (Map.Entry<Action, Portefeuille.LignePortefeuille> entry : portefeuille.getMapLignes().entrySet()) {
            Action action = entry.getKey();
            Portefeuille.LignePortefeuille lignePortefeuille = entry.getValue();
            tableModel.addRow(new Object[]{action.getLibelle(), lignePortefeuille.getQte()});
        }
    }
}
