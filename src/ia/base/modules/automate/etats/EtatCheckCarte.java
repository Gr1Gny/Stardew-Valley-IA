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
 * Etat permettant de vérifier si l'auromate possède la carte
 * @author Noham
 */
public class EtatCheckCarte extends Etat {

    /**
     * constructeur de la classe EtatCheckCarte
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public EtatCheckCarte(Automate automate) {
        super(automate);
    }

    /**
     * méthode qui permet de changer d'état
     * @return l'état suivant
     */
    @Override
    public Etat transition() {
        Etat etat;
        if(this.getAutomate().getModuleMemoire().hasCarte()){
            etat = new EtatCheckAction(this.getAutomate());
        }
        else{
            etat = new EtatDemanderCarte(this.getAutomate());
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
