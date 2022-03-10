/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import static ia.base.metier.actions.TypeAction.VENDRE;
import ia.base.metier.carte.ressources.TypeRessource;

/**
 * Action qui permet de vendre des ressources
 * @author Noham
 */
public class ActionVendre extends Action{

    private TypeRessource typeRessource;

    /**
     * constructeur de la classe ActionVendre
     * @param typeRessource ressource qu'on souhaite acheter
     */
    public ActionVendre(TypeRessource typeRessource) {
        this.typeRessource = typeRessource;
    } 
    
    /**
     * permet de récupérer le message à envoyer au server pour réaliser un action
     * @return le message sous forme de chaine de caractères
     */
    @Override
    public String getMessage() {
        return "SELL|" + typeRessource + "|1";
    }

    /**
     * permet de récupérer le type d'action
     * @return le type d'action
     */
    @Override
    public TypeAction getType() {
        return VENDRE;
    }

    /**
     * permet de récupérer le type de mouvement
     * @return un objet de type TypeMouvement
     */
    @Override
    public TypeMouvement getDirection() {
        return null;
    }
    
}
