/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.actions.TypeActionRecolte;
import ia.base.metier.algorithmes.AlgorithmeCalculDistance;
import ia.base.metier.algorithmes.Dijkstra;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.objets.TypeObjet;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Etat permettant d'aller ver l'arbre le plus proche
 * @author Noham
 */
public class EtatAllerVersArbre extends Etat {

    private boolean arbreTrouve;
    
    /**
     * constructeur de la classe EtatAllerVersArbre
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public EtatAllerVersArbre(Automate automate) {
        super(automate);
        arbreTrouve = false;
    }

    /**
     * méthode qui permet de changer d'état
     * @return l'état suivant
     */
    @Override
    public Etat transition() {
        Etat etat;
        if(arbreTrouve == false){
            etat = new EtatAllerDormir(this.getAutomate());
        }
        else{
            etat = new EtatCheckAction(this.getAutomate());
        }
        return etat;
    }

    /**
     * permet à l'automate d'effectuer une action
     * @return null car état de réflexion
     */
    @Override
    public Action action() {
        //On récupère la carte
        Carte carte = this.getAutomate().getModuleMemoire().getCarte();
        //On crée l'algo
        AlgorithmeCalculDistance algo = new Dijkstra(carte);
        //On récupère la case du joueur
        Case caseJoueur = this.getAutomate().getModuleMemoire().getCaseJoueur();
        //On lance les calculs de distance
        algo.calculerDistancesDepuis(caseJoueur);
        
        //initialisation
        Case caseAvecArbreLaPlusProche = null;
        int distanceMinimale = -1;
        
        //on récupère l'abre le plus proche
        for(Case c: carte.getCases()){
            //si la case a un objet de type arbre
            if(c.getObjet() != null && c.getObjet().getType() == TypeObjet.ARBRE) {
                if(caseAvecArbreLaPlusProche == null || algo.getDistance(c) < distanceMinimale){
                    this.arbreTrouve = true;
                    caseAvecArbreLaPlusProche = c;
                    distanceMinimale = algo.getDistance(c); 
                }
            }
        }
        if(caseAvecArbreLaPlusProche != null){
            //on se déplace vers l'arbre le plus proche si la case n'est pas null
            this.seDeplacerEn(caseAvecArbreLaPlusProche.getCoordonnee());
            if(!this.getAutomate().getListeDesActionsARealiser().isEmpty()) {
                Action action = this.getAutomate().getListeDesActionsARealiser().get(this.getAutomate().getListeDesActionsARealiser().size()-1);
                this.getAutomate().getListeDesActionsARealiser().remove(this.getAutomate().getListeDesActionsARealiser().size()-1);
                this.getAutomate().getListeDesActionsARealiser().add(FabriqueAction.
                creerActionRecolte(TypeActionRecolte.COUPERARBRE, action.getDirection())) ;
                this.getAutomate().getListeDesActionsARealiser().add(FabriqueAction.
                creerActionRecolte(TypeActionRecolte.COUPERARBRE, action.getDirection())) ;
            }
        }
        return null;
    }
    
}
