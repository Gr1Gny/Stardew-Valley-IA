/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate;

import ia.base.metier.TypeMouvement;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.FabriqueAction;
import ia.base.metier.algorithmes.AlgorithmeCalculDistance;
import ia.base.metier.algorithmes.Dijkstra;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import java.util.List;

/**
 *
 * @author nm344384
 */
public abstract class Etat {
    private Automate automate;

    /**
     * constructeur de la classe Etat
     * @param automate correspond à l'automate auquel appartient l'état
     */
    public Etat(Automate automate) {
        this.automate = automate;
    }

    /**
     * méthode qui permet de changer d'état
     * @return l'état suivant
     */
    public abstract Etat transition();

    /**
     * permet à l'automate d'effectuer une action
     * @return l'action
     */
    public abstract Action action();
    
    /**
     * méthode qui permet de récupérer l'automate
     * @return l'automate
     */
    protected Automate getAutomate(){
        return this.automate;
    }
    
    /**
     * permet de déplacer le joueur sur la carte
     * @param coordonnee correspond aux coordonnées où on souhaite se déplacer
     */
    protected void seDeplacerEn(Coordonnee coordonnee){
        System.out.println("--- Je veux aller en "+coordonnee+" ---");
        //On récupère la carte
        Carte carte = this.getAutomate().getModuleMemoire().getCarte();
        //On crée l'algo
        AlgorithmeCalculDistance algo = new Dijkstra(carte);
        //On récupère la case du joueur
        Case caseJoueur = this.getAutomate().getModuleMemoire().getCaseJoueur();
        //On lance les calculs de distance
        algo.calculerDistancesDepuis(caseJoueur);
        //On détermine la case de destination
        Case destination = carte.getCase(coordonnee);
        //On calcule le chemin
        List<TypeMouvement> listeMouvement = algo.getChemin(destination);
        //On crée les actions
        for(TypeMouvement typeMouvement : listeMouvement) {
            this.getAutomate().getListeDesActionsARealiser().add(FabriqueAction.creerMouvement(typeMouvement));
        }
    }
}
