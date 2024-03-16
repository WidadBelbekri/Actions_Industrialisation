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
import tp04.metier.Portefeuille;

public class AccueilInterface extends JFrame {

    public AccueilInterface() {
        setTitle("Accueil");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);

        // Création des boutons
        JButton consulterButton = new JButton("Consulter le portefeuille");
        JButton venteButton = new JButton("Interface de vente");
        JButton achatButton = new JButton("Interface d'achat");

        // Ajout des ActionListener aux boutons
        consulterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir l'interface de consultation du portefeuille
                Portefeuille portefeuille = new Portefeuille();
                PortefeuilleInterface portefeuilleInterface = new PortefeuilleInterface(portefeuille);
                portefeuilleInterface.setVisible(true);
                dispose(); // Fermer l'interface d'accueil
            }
        });

        venteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir l'interface de vente
                VenteInterface venteInterface = new VenteInterface();
                venteInterface.setVisible(true);
                dispose(); // Fermer l'interface d'accueil
            }
        });

        achatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Ouvrir l'interface d'achat
                AchatInterface achatInterface = new AchatInterface();
                achatInterface.setVisible(true);
                dispose(); // Fermer l'interface d'accueil
            }
        });

        // Création du panneau principal
        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(consulterButton);
        panel.add(venteButton);
        panel.add(achatButton);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AccueilInterface accueilInterface = new AccueilInterface();
                accueilInterface.setVisible(true);
            }
        });
    }
}
