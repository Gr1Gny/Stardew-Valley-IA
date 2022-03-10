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

/**
 * Classe qui permet d'effectuer un parcours en largeur
 * @author nm344384
 */
public class ParcoursLargeur extends AlgorithmeCalculDistance {

    /**
     * constructeur de la classe ParcoursLargeur
     * @param carte permet d'initialiser la carte de la classe
     */
    public ParcoursLargeur(Carte carte) {
        super(carte);
    }

    @Override
    public void calculerDistancesDepuis(Case depart) {
        //Remise à zéro
        ArrayList<Case> aTraiter = new ArrayList<>();
        this.reinitialisationDistances();
        
        //Initialisation
        aTraiter.add(depart);
        this.setDistance(depart,0);
        
        //Calcul
        while(!aTraiter.isEmpty()){
            Case caseEnCours = aTraiter.get(0);
            aTraiter.remove(0);
            for(Case v : caseEnCours.getVoisins()){
                if (this.getDistance(v) == null){
                    if (v.estAccessible()){
                        Integer v_distance = getDistance(caseEnCours) + 1;
                        setDistance(v,v_distance);
                        aTraiter.add(v);
                    } 
                }
            }
        }
    }

    /**
     * permet de trouver le chemin afin d'aller d'une case à une autre
     * @param arrivee correspond à la case sur laquelle on souhaite arriver
     * @return un tableau contenant tous les mouvements à effectuer
     */
    @Override
    public ArrayList<TypeMouvement> getChemin(Case arrivee) {
        //Initialisation
        ArrayList<TypeMouvement> resultat = new ArrayList<>() ;
        Case caseEnCours = arrivee;
        
        if(this.getDistance(caseEnCours) != null){
        //Calcul
            while (getDistance(caseEnCours)!= 0){
                //on récupère les voisins de caseEnCours et on cherche lequel est à la bonne distance
                ArrayList<Case> voisins = caseEnCours.getVoisins();
                Case casePrecedente = null;
                for (Case v : voisins){
                    if ((getDistance(v)!= null)  && (getDistance(v) == (getDistance(caseEnCours)-1))){
                        casePrecedente = v;
                    }
                }
                //on cherche ensuite le mouvement à effectuer pour aller de casePrecedente à caseEnCours
                TypeMouvement t = casePrecedente.getMouvementPourAller(caseEnCours);
                resultat.add(t);
                caseEnCours = casePrecedente;
            }
            Collections.reverse(resultat);
        }
        return resultat;
    }
    
}
