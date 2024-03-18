/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author somebody
 */
public abstract class Action {

    private String libelle;

    private StringProperty libelleProperty; // Propriété observable pour le libellé
    
    //IMANE +++
    private List<Client> clients;
    
    public Action(String libelle) {
        this.libelle = libelle;
        //IMANE +++
        this.clients = new ArrayList<>();

    }

    // Méthode pour ajouter un client à cette action IMANE +++
    public void addClient(Client client) {
        clients.add(client);
    }
    
    // Méthode pour récupérer la liste des clients associés à cette action IMANE +++
    public List<Client> getClients() {
        return clients;
    }
    //Imane
    public abstract int getQuantite();
    
    // Méthode pour soustraire la quantité achetée de la quantité disponible IMANE
    public abstract void soustraireQuantite(int quantite);
    
        // Méthode pour accéder à la propriété observable du libellé
    public StringProperty libelleProperty() {
        if (libelleProperty == null) {
            libelleProperty = new SimpleStringProperty(this, "libelle");
        }
        return libelleProperty;
    }
    
    /**
     * Get the value of libelle
     *
     * @return the value of libelle
     */
    public String getLibelle() {
        return libelle;
    }

    public abstract float valeur(Jour j);

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.libelle);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Action other = (Action) obj;
        if (!Objects.equals(this.libelle, other.libelle)) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.getLibelle();
    }
}
