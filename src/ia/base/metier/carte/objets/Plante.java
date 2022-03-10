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
 * Classe qui permet de créer une plante
 * @author nm344384
 */
public abstract class Plante extends Objet {

    private int age;
    private boolean estArrose;

    /**
     * permet de récupérer l'âge de la plante
     * @return un entier correspondant à l'âge
     */
    public int getAge() {
        return age;
    }
    
    /**
     * permet de savoir si la plante est arrosée
     * @return true si elle est arrosée
     */
    public boolean isEstArrose() {
        return estArrose;
    }

    /**
     * permet de dire si la plante est arrosée ou non
     * @param estArrose est égal à true si la plate est arrosée
     */
    public void setEstArrose(boolean estArrose) {
        this.estArrose = estArrose;
    }
    
    /**
     * constructeur de la classe Plante
     * @param position correspond à la position où on souhaite créer la plante 
     */
    public Plante(Case position) {
        super(position);
        estArrose = false;
        age = 0;
    }
    
    /**
     * permet de savoir quel objet il s'agit
     * @return le type de l'objet
     */
    @Override
    public abstract TypeObjet getType();

    /**
     * permet de savoir si on peut se déplacer sur cette case
     * @return true si la si la case est inaccessible
     */
    @Override
    public boolean estBloquant() {
        return false;
    }

    /**
     * permet de récupérer les loots quand on récolte quelque chose
     * @return une hashmap contenant le nombre et la ressource récoltée
     */
    @Override
    public abstract HashMap<TypeRessource, Integer> getLoot();
    
    /**
     * permet de savoir si la plante est mature
     * @return true si elle a finis de pousser
     */
    public abstract boolean estMature();

    /**
     * permet d'augmenter l'âge de la plante
     */
    public void grandir(){
        age++;
    }
}
