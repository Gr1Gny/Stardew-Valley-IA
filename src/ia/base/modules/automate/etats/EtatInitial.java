/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Etat initial de l'automate
 * @author Noham
 */
public class EtatInitial extends Etat {

    /**
     * constructeur de la classe EtatInitial
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public EtatInitial(Automate automate) {
        super(automate);
    }

    /**
     * méthode qui permet de changer d'état
     * @return l'état suivant
     */
    @Override
    public Etat transition() {
        Etat etat = new EtatCheckCarte(this.getAutomate());
        return etat; 
    }

    /**
     * permet à l'automate d'effectuer une action
     * @return null car état de réflexion
     */
    @Override
    public Action action() {
        return null;
    }
    
}
