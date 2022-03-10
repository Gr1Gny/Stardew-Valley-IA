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
 * classe qui permet de créer un ChouFleu
 * @author nm344384
 */
public class PlanteChouFleur extends Plante {

    /**
     * constructeur de la classe PlanteChouFleur
     * @param position correspond à la position où on souhaite créer la plante 
     */
    public PlanteChouFleur(Case position) {
        super(position);
    }

    /**
     * permet de savoir quel objet il s'agit
     * @return le type de l'objet
     */
    @Override
    public TypeObjet getType() {
        return TypeObjet.CHOUFLEUR;
    }

    /**
     * permet de récupérer les loots quand on récolte quelque chose
     * @return une hashmap contenant le nombre et la ressource récoltée
     */
    @Override
    public HashMap<TypeRessource, Integer> getLoot() {
        HashMap<TypeRessource, Integer> hasmap = new HashMap<>();
        hasmap.put(TypeRessource.CAULIFLOWERMATURE,2);
        return hasmap;
    }
    
    /**
     * permet de savoir si la plante est mature
     * @return true si elle a finis de pousser
     */
    @Override
    public boolean estMature() {
        boolean b = false;
        if(getAge() >= 13){
            b = true;
        }
        return b;
    }
    
}
