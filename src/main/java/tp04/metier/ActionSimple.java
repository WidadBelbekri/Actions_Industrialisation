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
 * Imane : Quantite
 */
public class ActionSimple extends Action {

    // attribut lien
    private Map<Jour, Cours> mapCours;
    private int quantite;

    // Constructeur avec la quantité initiale
    public ActionSimple(String libelle, int quantite) {
        super(libelle);
        this.quantite = quantite;
        // init spécifique
        this.mapCours = new HashMap();
    }

    // Redéfinition de la méthode pour obtenir la quantité disponible IMANE
    @Override
    public int getQuantite() {
        return quantite;
    }
    
    // Implémentation de la méthode soustraireQuantite IMANE
    @Override
    public void soustraireQuantite(int quantite) {
        this.quantite -= quantite;
    }
    
     // Méthode pour ajouter une quantité à l'action après vente WIDAD
    public void ajouterQuantite(int quantite) {
        this.quantite += quantite;
    }

    // enrg possible si pas de cours pour ce jour
    public void enrgCours(Jour j, float v) {
        if (this.mapCours.containsKey(j) == false) {
            this.mapCours.put(j, new Cours(j, v));
        }
    }

    @Override
    public float valeur(Jour j) {
        if (this.mapCours.containsKey(j) == true) {
            return this.mapCours.get(j).getValeur();
        } else {
            return 0; // definition d'une constante possible
        }
    }
}
