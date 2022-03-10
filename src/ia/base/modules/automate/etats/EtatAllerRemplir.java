/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate.etats;

import ia.base.metier.TypeMouvement;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.algorithmes.AlgorithmeCalculDistance;
import ia.base.metier.algorithmes.Dijkstra;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.cases.TypeCase;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Etat qui permet de remplir l'arrosoir
 * @author nm344384
 */
public class EtatAllerRemplir extends Etat {

    /**
     * constructeur de la classe EtatAllerRemplir
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public EtatAllerRemplir(Automate automate) {
        super(automate);
    }

    /**
     * méthode qui permet de changer d'état
     * @return l'état suivant
     */
    @Override
    public Etat transition() {
        Etat e = new EtatCheckAction(this.getAutomate());
        return e;
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
        Case caseBordDeLEauLaPlusProche = null;
        int distanceMinimale = -1;
        
        //on récupère la case au borde de l'eau la plus proche
        for(Case c : carte.getCases()){
            if(c.estAccessible()){
                boolean bordDeLEau = false;
                for(Case v : c.getVoisins()){
                    if(v.getType() == TypeCase.EAU){
                        bordDeLEau = true;
                    }
                }
                if(bordDeLEau){
                    if(caseBordDeLEauLaPlusProche == null || algo.getDistance(c) < distanceMinimale){
                        caseBordDeLEauLaPlusProche = c;
                        distanceMinimale = algo.getDistance(c);
                    }
                }
            }
        }
        //on se déplace vers cette case et on récupère de l'eau dans la direction ou se trouve la case d'eau la plus proche
        this.seDeplacerEn(caseBordDeLEauLaPlusProche.getCoordonnee());
        TypeMouvement direction = null;
        for(Case v : caseBordDeLEauLaPlusProche.getVoisins()){
            System.out.println(v.getType());
            if(v.getType() == TypeCase.EAU){
                direction = caseBordDeLEauLaPlusProche.getMouvementPourAller(v);
            }
        }
        this.getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionRemplir(direction)) ;
        return null;
    }
    
}
