package tp04.metier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main extends JFrame {

    private JTable tableView;
    private DefaultTableModel tableModel;
    private List<Action> actionsDisponibles;

    public Main() {
        // Création d'une liste d'actions disponibles
        actionsDisponibles = new ArrayList<>();
        actionsDisponibles.add(new ActionSimple("Action 1", 10)); 
        actionsDisponibles.add(new ActionSimple("Action 2", 20)); 
        actionsDisponibles.add(new ActionSimple("Action 3", 30)); 
        
        // Création du portefeuille
        Portefeuille portefeuille = new Portefeuille();
        // Ajoutez les actions au portefeuille
        portefeuille.acheter(new ActionSimple("Action 1", 10), 10);
        portefeuille.acheter(new ActionSimple("Action 2", 20), 20);
        portefeuille.acheter(new ActionSimple("Action 3", 30), 30);

        // Créez la fenêtre du portefeuille en utilisant le même objet Portefeuille
        PortefeuilleWindow portefeuilleWindow = new PortefeuilleWindow(portefeuille);
        portefeuilleWindow.setVisible(true);

        // Création de la table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Libellé");
        tableModel.addColumn("Quantité disponible"); // Nouvelle colonne pour afficher la quantité disponible
        for (Action action : actionsDisponibles) {
            tableModel.addRow(new Object[]{action.getLibelle(), action.getQuantite()});
        }
        tableView = new JTable(tableModel);

        

        // Création d'un bouton pour acheter l'action sélectionnée
        JButton acheterButton = new JButton("Acheter");
        acheterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableView.getSelectedRow();
                if (selectedRow != -1) {
                    Action actionSelectionnee = actionsDisponibles.get(selectedRow);
                    System.out.println("Vous avez sélectionné l'action : " + actionSelectionnee.getLibelle());

                    // Demander à l'utilisateur de saisir la quantité à acheter
                    String quantiteStr = JOptionPane.showInputDialog("Entrez la quantité à acheter : ");
                    if (quantiteStr != null && !quantiteStr.isEmpty()) {
                        int quantite = Integer.parseInt(quantiteStr);

                        // Mettre à jour le portefeuille du client en ajoutant l'action achetée
                        Portefeuille portefeuille = new Portefeuille();
                        portefeuille.acheter(actionSelectionnee, quantite);

                        // Soustraire la quantité achetée de la quantité disponible dans la liste d'actions disponibles
                        actionSelectionnee.soustraireQuantite(quantite);

                        // Mettre à jour l'interface graphique pour refléter les changements
                        updateTableModel(portefeuille);

                        // Afficher la fenêtre du portefeuille avec l'action achetée en plus
                        PortefeuilleWindow portefeuilleWindow = new PortefeuilleWindow(portefeuille);
                        portefeuilleWindow.setVisible(true);
                    } else {
                        System.out.println("Quantité invalide.");
                    }
                } else {
                    System.out.println("Aucune action sélectionnée.");
                }
            }
        });

        // Création du conteneur pour les boutons d'action
        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(acheterButton, BorderLayout.NORTH);

        // Création du conteneur principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tableView), BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        // la création du bouton "Voir actionnaire majoritaire" pour chaque action
        JButton voirActionnaireButton = new JButton("Voir actionnaire majoritaire");
        voirActionnaireButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableView.getSelectedRow();
                if (selectedRow != -1) {
                    Action actionSelectionnee = actionsDisponibles.get(selectedRow);
                    Portefeuille portefeuille = new Portefeuille();
                    // Affichez l'actionnaire majoritaire dans une boîte de dialogue
                    Action actionnaireMajoritaire = portefeuille.actionnaireMajoritaire();
                    if (actionnaireMajoritaire != null) {
                        JOptionPane.showMessageDialog(null, "L'actionnaire majoritaire est : " + actionnaireMajoritaire.getLibelle());
                    } else {
                        JOptionPane.showMessageDialog(null, "Aucun actionnaire majoritaire.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Aucune action sélectionnée.");
                }
            }
        });

        // Ajoutez le bouton "Voir actionnaire majoritaire" au conteneur des boutons d'action
        buttonsPanel.add(voirActionnaireButton, BorderLayout.CENTER);

        
        // Configuration de la fenêtre principale
        setTitle("Sélection d'actions à vendre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
    }

    // Méthode pour mettre à jour le modèle de la table avec les données du portefeuille
    private void updateTableModel(Portefeuille portefeuille) {
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            Action action = actionsDisponibles.get(i);
            tableModel.setValueAt(action.getQuantite(), i, 1); // Mettre à jour la quantité dans la table
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
