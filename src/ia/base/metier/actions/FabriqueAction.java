/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;

/**
 * classe qui permet de fabriquer une action
 * @author nm344384
 */
public class FabriqueAction {
    
    /**
     * On fabrique un objet de type ActionMouvement
     * @param mouvement correspond au type du mouvement
     * @return l'action crée
     */
    public static Action creerMouvement(TypeMouvement mouvement){
        Action action = new ActionMouvement(mouvement);
        return action;
    }
    
    /**
     * On fabrique un objet de type ActionDemande
     * @param demande correspond au type de la demande
     * @return l'action crée
     */
    public static Action creerDemande(TypeDemande demande){
        Action action = null;
        switch(demande){
            case CARTE : action = new ActionDemande("MAP"); break;
            case MAGASIN : action = new ActionDemande("STORE"); break;
        }
        return action;
    }
    
    /**
     * permet de créer une action statique
     * @param type correspond au type de l'action statique
     * @return l'action crée
     */
    public static Action creerActionStatique(TypeActionStatique type){
        Action action = null;
        switch(type){
            case DORMIR : action = new ActionDormir(); break;
        }
        return action;
    }
    
    /**
     * permet de créer un action de récolte
     * @param type correspond au type de récolte
     * @param direction direction dans laquelle on effectue la récolte
     * @return l'action crée
     */
    public static Action creerActionRecolte(TypeActionRecolte type, TypeMouvement direction){
        Action action = null;
        switch(type){
            case COUPERARBRE : action = new ActionCouperArbre(direction); break;
        }
        return action;
    }
    
    /**
     * permet de créer une action d'achat
     * @param typeRessource correspond à la ressource qu'on souhaite acheter
     * @return l'action crée
     */
    public static Action creerActionAcheter(TypeRessource typeRessource){
        Action action = null;
        switch(typeRessource){
            case PARSNIPSEED : action = new ActionAcheter(typeRessource); break;
            case CAULIFLOWERSEED : action = new ActionAcheter(typeRessource); break;
            case CHICKEN : action = new ActionAcheter(typeRessource); break;
        }
        return action;
    }
    
    /**
     * permet de créer une action pour planter
     * @param typeRessource correspond à la ressource qu'on souhaite planter
     * @return l'action crée
     */
    public static Action creerActionPlanter(TypeRessource typeRessource){
        Action action = null;
        switch(typeRessource){
            case PARSNIPSEED : action = new ActionPlanter(typeRessource); break;
            case CAULIFLOWERSEED : action = new ActionPlanter(typeRessource); break;
        }
        return action;
    }
    
    /**
     * permet de créer une action pouir arroser
     * @return l'action crée
     */
    public static Action creerActionArroser(){
        Action action = new ActionArroser();
        return action;
    }
    
    /**
     * permet de créer un action pour remplir le seau d'eau
     * @param direction dans laquelle se trouve la case d'eau
     * @return l'action crée
     */
    public static Action creerActionRemplir(TypeMouvement direction){
        Action action = new ActionRemplir(direction);
        return action;
    }
    
    /**
     * permet de créer une action pour cueillir des plantes
     * @return l'action crée
     */
    public static Action creerActionCueillir(){
        Action action = new ActionCueillir();
        return action;
    }
    
    /**
     * permet de créer une action pour vendre des ressources
     * @param typeRessource correspond au type de la ressource à vendre
     * @return l'action crée
     */
    public static Action creerActionVendre(TypeRessource typeRessource){
        Action action = null;
        switch(typeRessource){
            case PARSNIPMATURE : action = new ActionVendre(typeRessource); break;
            case CAULIFLOWERMATURE : action = new ActionVendre(typeRessource); break;
            case EGG : action = new ActionVendre(typeRessource); break;
            case MAYONNAISE : action = new ActionVendre(typeRessource); break;
        }
        return action;
    }
}
