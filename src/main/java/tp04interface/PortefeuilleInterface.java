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

/**
 *
 * @author widad
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import tp04.metier.ActionSimple;
import tp04.metier.Portefeuille;
import java.util.Map.Entry;

public class PortefeuilleInterface extends JFrame {

    private Portefeuille portefeuille;

    public PortefeuilleInterface(Portefeuille portefeuille) {
        this.portefeuille = portefeuille;

        setTitle("Consultation du portefeuille");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Création d'un modèle de tableau pour afficher les actions du portefeuille
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Action");
        tableModel.addColumn("Quantité");

        // Ajout des lignes de données au tableau
        for (var entry : portefeuille.getMapLignes().entrySet()) {
            tp04.metier.Action action = entry.getKey();
            int quantite = entry.getValue().getQte();
            tableModel.addRow(new Object[]{action.getLibelle(), quantite});
        }
        // Création du tableau avec le modèle de données
        JTable tableView = new JTable(tableModel);

        // Ajout du tableau à un JScrollPane pour permettre le défilement s'il y a beaucoup de données
        JScrollPane scrollPane = new JScrollPane(tableView);

        // Ajout du JScrollPane au contenu de la fenêtre
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    
    
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
                portefeuille.acheter(new ActionSimple("Action 1"), 10);
                portefeuille.acheter(new ActionSimple("Action 2"), 5);

                // Création et affichage de l'interface de consultation du portefeuille
                PortefeuilleInterface portefeuilleInterface = new PortefeuilleInterface(portefeuille);
                portefeuilleInterface.setVisible(true);
            }
        });
    }
}
