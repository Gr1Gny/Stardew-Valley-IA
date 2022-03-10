/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.objets;

import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.ressources.TypeRessource;
import java.util.HashMap;

/**
 * classe qui permet de gérer les objets
 * @author Noham
 */
public abstract class Objet {
    private Case position;

    /**
     * permet de récupérer la case sur laquelle l'objet se trouve
     * @return la position de l'objet 
     */
    public Case getPosition() {
        return position;
    }

    /**
     * on crée un objet sur une case précise
     * @param position qui correspond à la case sur laquelle l'objet se trouve
     */
    public Objet(Case position) {
        this.position = position;
    }
    
    /**
     * permet de savoir de quel objet il s'agit
     * @return le type de l'objet
     */
    public abstract TypeObjet getType();
    
    /**
     * permet de savoir si on peut se déplacer sur cette case
     * @return true si la si la case est inaccessible
     */
    public abstract boolean estBloquant();
    
    /**
     * permet de savoir si l'objet sur la case est récoltable ou non
     * @return true si l'objet est récoltable
     */
    public boolean estRecoltable(){
        return false;
    }
    
    /**
     * permet de savoir le cout d'unaction de récolte
     * @return le cout de l'action
     */
    public int coutRecolte(){
        return -1;
    }
    
    /**
     * permet de récupérer les loots quand on récolte quelque chose
     * @return une hashmap contenant le nombre et la ressource récoltée
     */
    public abstract HashMap<TypeRessource,Integer> getLoot();
}
