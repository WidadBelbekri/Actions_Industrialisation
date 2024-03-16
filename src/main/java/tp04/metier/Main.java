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
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Création d'une liste d'actions disponibles
        ObservableList<Action> actionsDisponibles = FXCollections.observableArrayList();
        actionsDisponibles.add(new ActionSimple("Action 1"));
        actionsDisponibles.add(new ActionSimple("Action 2"));
        actionsDisponibles.add(new ActionSimple("Action 3"));

        // Création de la TableView
        TableView<Action> tableView = new TableView<>();
        TableColumn<Action, String> libelleColumn = new TableColumn<>("Libellé");
        libelleColumn.setCellValueFactory(cellData -> cellData.getValue().libelleProperty());
        tableView.getColumns().add(libelleColumn);
        tableView.setItems(actionsDisponibles);

        // Création d'un bouton pour vendre l'action sélectionnée
        Button vendreButton = new Button("Vendre");
        vendreButton.setOnAction(event -> {
            Action actionSelectionnee = tableView.getSelectionModel().getSelectedItem();
            if (actionSelectionnee != null) {
                System.out.println("Vous avez sélectionné l'action : " + actionSelectionnee.getLibelle());
                // Ajoutez ici la logique pour vendre l'action sélectionnée
            } else {
                System.out.println("Aucune action sélectionnée.");
            }
        });

        // Création de la disposition de la scène
        VBox root = new VBox();
        root.getChildren().addAll(tableView, vendreButton);

        // Création de la scène
        Scene scene = new Scene(root, 300, 250);

        // Configuration de la scène principale
        primaryStage.setTitle("Sélection d'actions à vendre");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
