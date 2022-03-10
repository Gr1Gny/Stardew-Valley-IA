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
import ia.base.metier.carte.cases.TypeCase;
import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Etat qui permet d'aller planter
 * @author nm344384
 */
public class EtatAllerPlanter extends Etat {

    private boolean aPlante;
    
    /**
     * constructeur de la classe EtatAllerPlanter
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public EtatAllerPlanter(Automate automate) {
        super(automate);
        aPlante = false;
    }

    /**
     * méthode qui permet de changer d'état
     * @return l'état suivant
     */
    @Override
    public Etat transition() {
        Etat e;
        if(aPlante){
            e = new EtatCheckAction(this.getAutomate());
        }
        else{
            e = new EtatAllerArroser(this.getAutomate());
        }
        return e;
    }

    /**
     * permet à l'automate d'effectuer une action
     * @return null car état de réflexion
     */
    @Override
    public Action action() {
        System.out.println(this.getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.PARSNIPSEED));
        if(this.getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.PARSNIPSEED) >= 1){
            //On récupère la carte
            Carte carte = this.getAutomate().getModuleMemoire().getCarte();
            //On crée l'algo
            AlgorithmeCalculDistance algo = new Dijkstra(carte);
            //On récupère la case du joueur
            Case caseJoueur = this.getAutomate().getModuleMemoire().getCaseJoueur();
            //On lance les calculs de distance
            algo.calculerDistancesDepuis(caseJoueur);
            
            //initialisation
            Case caseTerreVide = null;
            int distanceMinimale = -1;
            
            for(Case c : carte.getCases()){
                //On récupère la case terre la plus proche sans objet
                if(c.getType() == TypeCase.TERRE && c.getObjet() ==  null){
                    if(caseTerreVide == null || algo.getDistance(c) < distanceMinimale){
                        caseTerreVide = c;
                        distanceMinimale = algo.getDistance(c);
                    }
                }
            }
            //On se déplace vers la case terre la plus proche sans objet et on y plante une graine
            if(!(caseTerreVide == null)){
                this.seDeplacerEn(caseTerreVide.getCoordonnee());
                this.getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionPlanter(TypeRessource.PARSNIPSEED));
                aPlante = true;
            }
        }
        return null;
    }
    
}
