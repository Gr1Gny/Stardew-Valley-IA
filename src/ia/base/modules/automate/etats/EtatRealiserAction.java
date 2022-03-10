/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import static ia.base.metier.actions.TypeAction.MOUVEMENT;
import ia.base.metier.actions.TypeActionRecolte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import static ia.base.metier.carte.objets.TypeObjet.ARBRE;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Etat permettant la réalisatiion d'une action
 * @author Noham
 */
public class EtatRealiserAction extends Etat {

    /**
     * constructeur de la classe EtatRealiserAction
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public EtatRealiserAction(Automate automate) {
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
     * permet à l'automate de réaliser une action
     * @return l'action à réaliser
     */
    @Override
    public Action action() {
        //on récupère la première action de la liste des actions à réaliser
        Action action = this.getAutomate().getListeDesActionsARealiser().get(0);
        //si l'action est de type mouvement
        if(action.getType()==MOUVEMENT){
            //on récupère les coordonnées de destination puis la case
            Coordonnee coordonneeDestination = this.getAutomate().getModuleMemoire().getCaseJoueur().getCoordonnee().getVoisin(action.getDirection());
            Case destination = this.getAutomate().getModuleMemoire().getCarte().getCase(coordonneeDestination);
            //si il y a un objet de type arbre sur la case
            if(destination.getObjet()!=null && destination.getObjet().getType()==ARBRE){
                this.getAutomate().getListeDesActionsARealiser().add(0, FabriqueAction.creerActionRecolte(TypeActionRecolte.COUPERARBRE, action.getDirection()));
                this.getAutomate().getListeDesActionsARealiser().add(0, FabriqueAction.creerActionRecolte(TypeActionRecolte.COUPERARBRE, action.getDirection()));
            }
        }
        action = this.getAutomate().getListeDesActionsARealiser().get(0);
        this.getAutomate().getListeDesActionsARealiser().remove(0);
        return action;
    }
    
}
