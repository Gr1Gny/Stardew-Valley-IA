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
import java.util.Collections;
import java.util.HashMap;

/**
 * classe qui permet d'implémenter Dijkstra
 * @author nm344384
 */
public class Dijkstra extends AlgorithmeCalculDistance {

    private HashMap<Case,Boolean> estVisite;
    private HashMap<Case,Case> predecesseur;
    private int infini;
    
    /**
     * constructeur de la classe Dijkstra
     * @param carte qui correspond à la carte du jeu
     */
    public Dijkstra(Carte carte) {
        super(carte);
        this.estVisite = new HashMap<>();
        this.predecesseur = new HashMap<>();
        infini = 1+16*this.getCarte().getTaille()*this.getCarte().getTaille();
    }

    /**
     * permet de récupérer le cout pour aller sur une case voisine
     * @param destination correspond à la case voisine dont on veut connaitre le cout
     * @return le cout en entier
     */
    private int coutMouvementVers(Case destination){
        int cout = 1;
        //si la case n'est pas accessible, alors le cout prend la valeur de l'infini
        if(!destination.estAccessible()){
            cout = infini;
        }
        //si il y a un objet récoltable sur la case, on ajoute le cout de récolte
        if(destination.getObjet() != null && destination.getObjet().estRecoltable()){
            cout += destination.getObjet().coutRecolte();
        }
        return cout;
    }
    
    /**
     * permet l'inilitialisation des différentes hashmap
     * @param depart correspond à la case départ
     */
    private void initialisation(Case depart){
        for(Case v : this.getCarte().getCases()){
            this.setDistance(v, infini);
            estVisite.put(v,false);
            predecesseur.put(v,null);
        }
        this.setDistance(depart, 0);
    }
    
    /**
     * permet de calculer le relachement entre deux cases
     * @param a correspond à la première case
     * @param b correspond à la deuxieme case
     */
    private void relachement(Case a, Case b){
        if(this.getDistance(b) > this.getDistance(a) + this.coutMouvementVers(b)){
            this.setDistance(b, this.getDistance(a) + this.coutMouvementVers(b));
            predecesseur.put(b,a);
        }
    }
    
    /**
     * permet de connaitre la case la plus proche
     * @return la case la plus proche
     */
    private Case getCaseLaPlusProche(){
        int distanceMin = this.infini;
        Case res = null;
        for(Case c : this.getCarte().getCases()){
            if(estVisite.get(c) == false && this.getDistance(c) < distanceMin){
                distanceMin = this.getDistance(c);
                res = c;
            }
        }
        return res;
    }
    
    /**
     * permet de calculer la distance de toutes les case par rapport à une case
     * @param depart correspond à la case depuis laquelle on souhaite calculer la distance
     */
    @Override
    public void calculerDistancesDepuis(Case depart) {
        this.initialisation(depart);
        Case caseLaPlusProche = this.getCaseLaPlusProche();
        while(caseLaPlusProche != null){
            estVisite.put(caseLaPlusProche,true);
            for(Case c : caseLaPlusProche.getVoisins()){
                this.relachement(caseLaPlusProche, c);
            }
            caseLaPlusProche = this.getCaseLaPlusProche();
        }
    }

    /**
     * permet de récupérer les mouvements à effectuer pour pouvoir se déplacer sur une autre case
     * @param arrivee casesur laquelle on souhaite se déplacer
     * @return un tableau contenant tous les mouvements
     */
    @Override
    public ArrayList<TypeMouvement> getChemin(Case arrivee) {
        //Initialisation
        ArrayList<TypeMouvement> resultat = new ArrayList<>() ;
        Case caseEnCours = arrivee;
        
        //calcul
        if(this.getDistance(caseEnCours) != null){
            //on récupère les mouvement pour aller des prédécesseurs à la case d'arrivée
            while (predecesseur.get(caseEnCours) != null){
                TypeMouvement t = predecesseur.get(caseEnCours).getMouvementPourAller(caseEnCours);
                resultat.add(t);
                caseEnCours = predecesseur.get(caseEnCours);
            }
        }
        Collections.reverse(resultat);
        return resultat;
    }
    
}
