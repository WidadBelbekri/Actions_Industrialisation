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
import java.util.Map;

public class PortefeuilleWindow extends JFrame {

    private DefaultTableModel tableModel;
    private JTable tableView;

    public PortefeuilleWindow(Portefeuille portefeuille) {
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

        // Configuration de la fenêtre
        setTitle("Portefeuille du client");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fermer uniquement cette fenêtre
        setContentPane(new JScrollPane(tableView)); // Ajout de la table dans un JScrollPane
        pack(); // Redimensionner la fenêtre selon le contenu
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
    }
}

