/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.objets;

import ia.base.metier.carte.cases.Case;

/**
 * classe qui permet la fabriquation d'un objet
 * @author Noham
 */
public class FabriquerObjet {
    
    /**
     * permet de créer un objet
     * @param position correspond à la position de l'objet
     * @param lettre qui permet de savoir quel objet créer
     * @return l'objet créé
     */
    public static Objet creer(Case position, Character lettre){
        Objet o = null;
        //On construit en fonction de la lettre une case différente
        switch (lettre) {
            //arbre
            case 'A':
                o = new ObjetArbre(position); break;
            //maison
            case 'M':
                o = new ObjetMaison(position); break;
            //escalier
            case 'S' :
                o = new ObjetEscalier(position); break;
            //départ
            case 'D' :
                o = new ObjetDepart(position); break;
        }
        return o;
        
    }
    
    /**
     * permet de créer une plante
     * @param position correspond à la case ou la plante est crée
     * @param type correspond au type de plante à créer
     * @return la plante crée
     */
    public static Plante creerPlante(Case position, TypeObjet type){
        Plante p = null;
        //On crée la plante en fonction de son type
        switch (type) {
            case PANAIS : p = new PlantePanais(position); break;
            case CHOUFLEUR : p = new PlanteChouFleur(position); break;
        }
        return p;
    }
}
