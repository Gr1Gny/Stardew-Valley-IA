/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.actions.TypeDemande;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Etat permettant de demander la carte
 * @author Noham
 */
public class EtatDemanderCarte extends Etat {

    /**
     * constructeur de la classe EtatDemanderCarte
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public EtatDemanderCarte(Automate automate) {
        super(automate);
    }

    /**
     * méthode qui permet de changer d'état
     * @return l'état suivant
     */
    @Override
    public Etat transition() {
        Etat etat = new EtatCheckAction(this.getAutomate());
        return etat;
    }

    /**
     * permet à l'automate d'effectuer une action
     * @return une action permettant de récupérer la carte
     */
    @Override
    public Action action() {
        Action action = FabriqueAction.creerDemande(TypeDemande.CARTE);
        return action;
    }
    
}
