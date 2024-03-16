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


import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
/**
 *
 * @author widad
 */
public class PortefeuilleTest {
       @Test
    public void testAcheterEtVendreActions() {
        Portefeuille portefeuille = new Portefeuille();

        // Créer des actions simples pour le test
        ActionSimple action1 = new ActionSimple("Action1");
        ActionSimple action2 = new ActionSimple("Action2");

        // Acheter des actions
        portefeuille.acheter(action1, 10);
        portefeuille.acheter(action2, 5);

        // Vérifier que les actions ont été ajoutées correctement
        assertEquals(10, portefeuille.getQuantite(action1));
        assertEquals(5, portefeuille.getQuantite(action2));

        // Vendre des actions
        portefeuille.vendre(action1, 5);
        portefeuille.vendre(action2, 3);

        // Vérifier que les actions ont été vendues correctement
        assertEquals(5, portefeuille.getQuantite(action1));
        assertEquals(2, portefeuille.getQuantite(action2));
    }
    
    

    @Test
    public void testConsulterActionsSimples() {
        Portefeuille portefeuille = new Portefeuille();

        // Créer des actions simples pour le test
        ActionSimple action1 = new ActionSimple("Action1");
        ActionSimple action2 = new ActionSimple("Action2");

        // Ajouter des actions au portefeuille
        portefeuille.acheter(action1, 10);
        portefeuille.acheter(action2, 5);

        // Consulter les actions simples disponibles
        List<ActionSimple> actionsSimples = portefeuille.consulterActionsSimples();

        // Vérifier que les actions simples ont été récupérées correctement
        assertEquals(2, actionsSimples.size());
        assertTrue(actionsSimples.contains(action1));
        assertTrue(actionsSimples.contains(action2));
    }

}


