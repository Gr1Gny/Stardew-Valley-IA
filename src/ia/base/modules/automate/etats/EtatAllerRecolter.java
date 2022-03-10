/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.algorithmes.AlgorithmeCalculDistance;
import ia.base.metier.algorithmes.Dijkstra;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.objets.Plante;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 *
 * @author nm344384
 */
public class EtatAllerRecolter extends Etat {

    private boolean aRecolte;
            
    public EtatAllerRecolter(Automate automate) {
        super(automate);
        aRecolte = false;
    }

    @Override
    public Etat transition() {
        Etat e;
        if(aRecolte){
            e = new EtatCheckAction(this.getAutomate());
        }
        else{
            e = new EtatVendre(this.getAutomate());
        }
        return e;
    }

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
        Case caseARecolter = null;
        int distanceMinimale = -1;
           
        //pour toutes les plantes sur la map
        for(Plante p : this.getAutomate().getModuleMemoire().getListePlantes()){
            if(p.estMature()){
                //on récupère la case mature la plus proche
                if(caseARecolter == null || algo.getDistance(p.getPosition()) < distanceMinimale){
                    caseARecolter = p.getPosition();
                    distanceMinimale = algo.getDistance(caseARecolter);
                    }
                }
            }
        //on se déplace vers la case mature la plus proche et on la récolte
        if(!(caseARecolter == null)){
            this.seDeplacerEn(caseARecolter.getCoordonnee());
            this.getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionCueillir());
            aRecolte = true;
        }   
        return null;
    }
    
}
