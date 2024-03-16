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
import java.awt.event.*;
import tp04.metier.ActionSimple;
import tp04.metier.Jour;
import tp04.metier.Portefeuille;

public class InterfacePortefeuille extends JFrame {
    private Portefeuille portefeuille;

    private JTextField txtAction;
    private JTextField txtQuantite;
    private JTextArea txtAreaPortefeuille;

    public InterfacePortefeuille(Portefeuille portefeuille) {
        this.portefeuille = portefeuille;

        setTitle("Gestion du portefeuille");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelTop = new JPanel(new GridLayout(3, 2));
        panelTop.add(new JLabel("Action:"));
        txtAction = new JTextField();
        panelTop.add(txtAction);
        panelTop.add(new JLabel("Quantité:"));
        txtQuantite = new JTextField();
        panelTop.add(txtQuantite);

        JButton btnAcheter = new JButton("Acheter");
        btnAcheter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String actionStr  = txtAction.getText();
                int quantite = Integer.parseInt(txtQuantite.getText());
                ActionSimple actionsimple = new ActionSimple(actionStr);
                portefeuille.acheter(actionsimple, quantite);
                updatePortefeuille();
            }
        });
        panelTop.add(btnAcheter);

        JButton btnVendre = new JButton("Vendre");
        btnVendre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String actionStr = txtAction.getText();
                int quantite = Integer.parseInt(txtQuantite.getText());
                ActionSimple actionsimple = new ActionSimple(actionStr);
                portefeuille.vendre(actionsimple, quantite);
                updatePortefeuille();
            }
        });
        panelTop.add(btnVendre);

        JButton btnConsulter = new JButton("Consulter");
        btnConsulter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePortefeuille();
            }
        });
        panelTop.add(btnConsulter);

        add(panelTop, BorderLayout.NORTH);

        txtAreaPortefeuille = new JTextArea();
        add(new JScrollPane(txtAreaPortefeuille), BorderLayout.CENTER);
    }

    private void updatePortefeuille() {
        txtAreaPortefeuille.setText(portefeuille.toString());
    }

    public static void main(String[] args) {
    Portefeuille portefeuille = new Portefeuille();
    
    // Ajout de quelques actions fictives
    ActionSimple action1 = new ActionSimple("AAPL");
    ActionSimple action2 = new ActionSimple("GOOGL");
    
    // Enregistrement de quelques cours fictifs pour chaque action
    action1.enrgCours(new Jour(2024, 15), 150.0f);
    action2.enrgCours(new Jour(2024, 15), 2000.0f);
    
    // Achat de quelques actions fictives
    portefeuille.acheter(action1, 10);
    portefeuille.acheter(action2, 5);
    
        InterfacePortefeuille ui = new InterfacePortefeuille(portefeuille);
        ui.setVisible(true);
    }
}
