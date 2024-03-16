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
package tp04.metier;

/**
 *
 * @author widad
 */
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Création d'une instance de Portefeuille
        Portefeuille portefeuille = new Portefeuille();
        
        // Création d'actions
        ActionSimple action1 = new ActionSimple("AAPL");
        ActionSimple action2 = new ActionSimple("GOOGL");
        
        // Achat d'actions
        portefeuille.acheter(action1, 10);
        portefeuille.acheter(action2, 5);
        
        // Vente d'actions
        portefeuille.vendre(action1, 3);
        
        // Consultation de la quantité d'actions détenues
        int quantiteAction1 = portefeuille.getQuantite(action1);
        int quantiteAction2 = portefeuille.getQuantite(action2);
        System.out.println("Quantité d'actions AAPL : " + quantiteAction1);
        System.out.println("Quantité d'actions GOOGL : " + quantiteAction2);
        
        // Calcul de la valeur du portefeuille pour une journée donnée
        Jour jour = new Jour(2024, 15);
        float valeurPortefeuille = portefeuille.valeur(jour);
        System.out.println("Valeur du portefeuille le " + jour.getNoJour() + " : " + valeurPortefeuille);
        
        // Consultation des actions simples détenues
        List<ActionSimple> actionsSimples = portefeuille.consulterActionsSimples();
        System.out.println("Actions simples détenues :");
        for (ActionSimple action : actionsSimples) {
            System.out.println(action.getLibelle() + " - " + action.valeur(jour)); // Utilisation de la méthode valeur(Jour j)
        }
    }
}
