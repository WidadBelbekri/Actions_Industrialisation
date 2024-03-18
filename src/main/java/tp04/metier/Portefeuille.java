/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author somebody
* Modification Imane
 */

public class Portefeuille {

    private Map<Action, LignePortefeuille> mapLignes;

    public Portefeuille() {
        this.mapLignes = new HashMap<>();
    }

    public void acheter(Action a, int q) {
        if (!this.mapLignes.containsKey(a)) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
        }
    }

    public void vendre(Action a, int q) {
        if (this.mapLignes.containsKey(a)) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
            } else if (this.mapLignes.get(a).getQte() == q) {
                this.mapLignes.remove(a);
            }
        }
    }

    public int getQuantite(Action action) {
        return mapLignes.containsKey(action) ? mapLignes.get(action).getQte() : 0;
    }

    public Map<Action, LignePortefeuille> getMapLignes() {
        return mapLignes;
    }

    public class LignePortefeuille {

        private Action action;
        private int qte;

        public int getQte() {
            return qte;
        }

        public void setQte(int qte) {
            this.qte = qte;
        }

        public Action getAction() {
            return action;
        }

        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }

        @Override
        public String toString() {
            return Integer.toString(qte);
        }
        
        
    }
    
    // Méthode Imane
public Action actionnaireMajoritaire() {
    Action actionnaireMajoritaire = null;
    int maxQuantite = 0;

    for (Map.Entry<Action, LignePortefeuille> entry : mapLignes.entrySet()) {
        Action action = entry.getKey();
        int quantite = entry.getValue().getQte();
        if (quantite > maxQuantite) {
            maxQuantite = quantite;
            actionnaireMajoritaire = action;
        }
    }

    return actionnaireMajoritaire;
} 

}
