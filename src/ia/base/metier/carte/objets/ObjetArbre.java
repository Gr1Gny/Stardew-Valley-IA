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
 * classe qui permet de créer un arbre
 * @author Noham
 */
public class ObjetArbre extends Objet {

    /**
     * on crée un arbre à une certaine position
     * @param position correspond à la case sur laquelle on veut construire l'objet
     */
    public ObjetArbre(Case position) {
        super(position);
    }

    /**
     * permet de savoir quel objet il s'agit
     * @return le type de l'objet
     */
    @Override
    public TypeObjet getType() {
        return TypeObjet.ARBRE;
    }

    /**
     * permet de savoir si on peut se déplacer sur cette case
     * @return true si la si la case est inaccessible
     */
    @Override
    public boolean estBloquant() {
        return false;
    }
    
    /**
     * permet de savoir si l'objet sur la case est récoltable ou non
     * @return true si l'objet est récoltable
     */
    @Override
    public boolean estRecoltable(){
        return true;
    }
    
    /**
     * permet de savoir le cout d'unaction de récolte
     * @return le cout de l'action
     */
    @Override
    public int coutRecolte(){
        return 2;
    }

    /**
     * permet de récupérer les loots quand on récolte quelque chose
     * @return une hashmap contenant le nombre et la ressource récoltée
     */
    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> hasmap = new HashMap<>();
        hasmap.put(TypeRessource.BOIS,12);
        return hasmap;
    }
}
