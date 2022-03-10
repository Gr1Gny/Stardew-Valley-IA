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
import ia.base.metier.carte.ressources.TypeRessource;
import ia.base.modules.automate.Automate;
import ia.base.modules.automate.Etat;

/**
 * Etat qui permet d'aller arroser une plante
 * @author nm344384
 */
public class EtatAllerArroser extends Etat {

    private boolean aArroser;
    
    /**
     * constructeur de la classe EtatAllerArroser
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public EtatAllerArroser(Automate automate) {
        super(automate);
        aArroser = false;
    }

    /**
     * méthode qui permet de changer d'état
     * @return l'état suivant
     */
    @Override
    public Etat transition() {
        Etat e;
        if(aArroser){
            e = new EtatCheckAction(this.getAutomate());
        }
        else{
            if(this.getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.EAU) == 0){
                e = new EtatAllerRemplir(this.getAutomate());
            }
            else{
                e = new EtatAllerDormir(this.getAutomate());
            }
        }
        return e;
    }

    /**
     * permet à l'automate d'effectuer une action
     * @return null car état de réflexion
     */
    @Override
    public Action action() {
        //si il reste de l'eau dans l'arrosoir
        if(this.getAutomate().getModuleMemoire().getQuantiteRessource(TypeRessource.EAU) > 0){
            //On récupère la carte
            Carte carte = this.getAutomate().getModuleMemoire().getCarte();
            //On crée l'algo
            AlgorithmeCalculDistance algo = new Dijkstra(carte);
            //On récupère la case du joueur
            Case caseJoueur = this.getAutomate().getModuleMemoire().getCaseJoueur();
            //On lance les calculs de distance
            algo.calculerDistancesDepuis(caseJoueur);
            
            //initialisation
            Case caseAArroser = null;
            int distanceMinimale = -1;
            
            //pour toutes les plantes sur la map
            for(Plante p : this.getAutomate().getModuleMemoire().getListePlantes()){
                if(!p.isEstArrose()){
                    //on récupère la case non arrosée la plus proche
                    if(caseAArroser == null || algo.getDistance(p.getPosition()) < distanceMinimale){
                        caseAArroser = p.getPosition();
                        distanceMinimale = algo.getDistance(caseAArroser);
                        }
                    }
                }
            //on se déplace vers la case non arrosée la plus proche et on l'arrose
            if(!(caseAArroser == null)){
                this.seDeplacerEn(caseAArroser.getCoordonnee());
                this.getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerActionArroser());
                aArroser = true;
            }   
        }
        return null;
    }
}
