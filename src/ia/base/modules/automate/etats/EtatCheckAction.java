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
 * Etat permettant de vérifier si il y a une action à réaliser
 * @author Noham
 */
public class EtatCheckAction extends Etat {

    /**
     * constructeur de la classe EtatCheckAction
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public EtatCheckAction(Automate automate) {
        super(automate);
    }

    /**
     * méthode qui permet de changer d'état
     * @return l'état suivant
     */
    @Override
    public Etat transition() {
        Etat etat;
        if(!this.getAutomate().getListeDesActionsARealiser().isEmpty()){
            etat = new EtatRealiserAction(this.getAutomate());
        }
        else{
            etat = new EtatAllerRecolter(this.getAutomate());
        }
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
