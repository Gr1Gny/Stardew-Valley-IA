/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.algorithmes;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.cases.Case;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * classe qui permet de gérer les algorithmes de calcul de distances
 * @author nm344384
 */
public abstract class AlgorithmeCalculDistance {
    private Carte carte;
    private HashMap<Case,Integer> distances;

    /**
     * constructeur de la classe AlgorithmeCalculDistance
     * @param carte permet d'initialiser la carte de la classe
     */
    public AlgorithmeCalculDistance(Carte carte) {
        this.carte = carte;
        this.distances = new HashMap<>(); 
    }
    
    /**
     * permet de récupérer la carte
     * @return la carte
     */
    protected Carte getCarte(){
        return this.carte;
    }

    /**
     * permet de rajouter des valeurs dans la hashmap
     * @param position correspond à la case où on veut calculer la distance
     * @param valeur correspond à la distance en entier
     */
    protected void setDistance(Case position, int valeur){
        distances.put(position, valeur);
    }
    
    /**
     * permet de récupérer la distance
     * @param arrivee correspond à la case où on souhaite arriver
     * @return la distance sous forme d'entier
     */
    public Integer getDistance(Case arrivee){
        return distances.get(arrivee);
    }
    
    /**
     * permet de vider la hashmap distance
     */
    protected void reinitialisationDistances(){
        distances.clear();
    }
    
    /**
     * permet de calculer la distance depuis une case
     * @param depart correspond à la case de départ
     */
    public abstract void calculerDistancesDepuis(Case depart);
    
    /**
     * permet de trouver le chemin afin d'aller d'une case à une autre
     * @param arrivee correspond à la case sur laquelle on souhaite arriver
     * @return un tableau contenant tous les mouvements à effectuer
     */
    public abstract ArrayList<TypeMouvement> getChemin(Case arrivee);
}
