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
 * Etat permetant d'acheter des ressources au magasin
 * @author nm344384
 */
public class EtatAcheter extends Etat {

    private boolean vaAcheter;
    
    /**
     * constructeur de la classe EtatAcheter
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public EtatAcheter(Automate automate) {
        super(automate);
    }

    /**
     * méthode qui permet de changer d'état
     * @return l'état suivant
     */
    @Override
    public Etat transition() {
        Etat etat;
        if(this.getAutomate().getModuleMemoire().hasStockMagasin()){
            if(vaAcheter){
                etat = new EtatCheckAction(this.getAutomate());
            }
            else{
                etat = new EtatAllerPlanter(this.getAutomate());
            }  
        }
        else{
            etat = new EtatDemandeMagasin(this.getAutomate());
        }
        return etat;
    }

    /**
     * permet à l'automate d'effectuer une action
     * @return null car état de réflexion
     */
    @Override
    public Action action() {
        if(this.getAutomate().getModuleMemoire().hasStockMagasin()){
            if(this.getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.GOLD) >= 20 && this.getAutomate().getModuleMemoire().getStockMagasin(TypeRessource.PARSNIPSEED) >= 1){
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
                vaAcheter = true;
                //on se déplace jusqu'au magasin et on y achète les ressources
                this.seDeplacerEn(MagasinPlusProche);
                this.getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionAcheter(TypeRessource.PARSNIPSEED));
            }
        }
        return null;
    }
    
}
