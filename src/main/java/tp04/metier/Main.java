package tp04.metier;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {

    private JTable tableView;
    private DefaultTableModel tableModel;
    private List<Action> actionsDisponibles;

    public Main() {
        // Création d'une liste d'actions disponibles
        actionsDisponibles = new ArrayList<>();
        actionsDisponibles.add(new ActionSimple("Action 1"));
        actionsDisponibles.add(new ActionSimple("Action 2"));
        actionsDisponibles.add(new ActionSimple("Action 3"));

        // Création de la table
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Libellé");
        for (Action action : actionsDisponibles) {
            tableModel.addRow(new Object[]{action.getLibelle()});
        }
        tableView = new JTable(tableModel);

        // Création d'un bouton pour vendre l'action sélectionnée
        JButton vendreButton = new JButton("Vendre");
        vendreButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableView.getSelectedRow();
                if (selectedRow != -1) {
                    Action actionSelectionnee = actionsDisponibles.get(selectedRow);
                    System.out.println("Vous avez sélectionné l'action : " + actionSelectionnee.getLibelle());
                    // Ajoutez ici la logique pour vendre l'action sélectionnée
                } else {
                    System.out.println("Aucune action sélectionnée.");
                }
            }
        });

        // Création d'un bouton pour acheter l'action sélectionnée
        JButton acheterButton = new JButton("Acheter");
        acheterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tableView.getSelectedRow();
                if (selectedRow != -1) {
                    Action actionSelectionnee = actionsDisponibles.get(selectedRow);
                    System.out.println("Vous avez sélectionné l'action : " + actionSelectionnee.getLibelle());
                    // Ajoutez ici la logique pour acheter l'action sélectionnée
                } else {
                    System.out.println("Aucune action sélectionnée.");
                }
            }
        });

        // Création du conteneur pour les boutons d'action
        JPanel buttonsPanel = new JPanel(new BorderLayout());
        buttonsPanel.add(acheterButton, BorderLayout.NORTH);
        buttonsPanel.add(vendreButton, BorderLayout.SOUTH);

        // Création du conteneur principal
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JScrollPane(tableView), BorderLayout.CENTER);
        panel.add(buttonsPanel, BorderLayout.SOUTH);       

        // Configuration de la fenêtre principale
        setTitle("Sélection d'actions à vendre");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null); // Centrer la fenêtre sur l'écran
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
