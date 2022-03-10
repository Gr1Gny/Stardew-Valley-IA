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
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 *
 * @author nm344384
 */
public class EtatVendre extends Etat {

    private boolean aVendre;
    
    public EtatVendre(Automate automate) {
        super(automate);
        aVendre = false;
    }

    @Override
    public Etat transition() {
        Etat e;
        if(aVendre){
            e = new EtatCheckAction(this.getAutomate());
        }
        else{
            e = new EtatAcheter(this.getAutomate());
        }
        return e;
    }

    @Override
    public Action action() {
        if(this.getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.PARSNIPMATURE) >= 0){
            //On récupère la carte
            Carte carte = this.getAutomate().getModuleMemoire().getCarte();
            //On crée l'algo
            AlgorithmeCalculDistance algo = new Dijkstra(carte);
            //On récupère la case du joueur
            Case caseJoueur = this.getAutomate().getModuleMemoire().getCaseJoueur();
            //On lance les calculs de distance
            algo.calculerDistancesDepuis(caseJoueur);
                
            //On récupère les coordonnées et cases ou se trouve le magasin
            Coordonnee CooMagasin1 = this.getAutomate().getModuleMemoire().getCarte().getCoordonneesMagasin().get(0);
            Coordonnee CooMagasin2 = this.getAutomate().getModuleMemoire().getCarte().getCoordonneesMagasin().get(1);
                
            Case Magasin1 = this.getAutomate().getModuleMemoire().getCarte().getCase(CooMagasin1);
            Case Magasin2 = this.getAutomate().getModuleMemoire().getCarte().getCase(CooMagasin2);
                
            Coordonnee MagasinPlusProche;
            //on récupère la case la plus proche
            if(algo.getDistance(Magasin1) < algo.getDistance(Magasin2)){
                MagasinPlusProche = this.getAutomate().getModuleMemoire().getCarte().getCoordonneesMagasin().get(0);
            }
            else{
                MagasinPlusProche = this.getAutomate().getModuleMemoire().getCarte().getCoordonneesMagasin().get(1);
            }
            aVendre = true;
            //on se déplace jusqu'au magasin et on y achète les ressources
            this.seDeplacerEn(MagasinPlusProche);
            this.getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionVendre(TypeRessource.PARSNIPMATURE));
        }
        return null;
    }
    
}
