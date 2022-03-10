/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import static ia.base.metier.actions.TypeAction.MOUVEMENT;

/**
 * classe qui permet de créer un mouvement à envoyer au serveur
 * @author nm344384
 */
public class ActionMouvement extends Action{
    private TypeMouvement typeMouvement;

    /**
     * constructeyr de la classe ActionMouvement
     * @param typeMouvement correspond au mouvement qu'on voudra effectuer
     */
    public ActionMouvement(TypeMouvement typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    /**
     * permet de récupérer le message à envoyer au server pour réaliser un action
     * @return le message sous forme de chaine de caractères
     */
    @Override
    public String getMessage() {
        return "MOVE|" + typeMouvement;
    }
    
    /**
     * permet de récupérer le type d'action
     * @return le type d'action
     */
    @Override
    public TypeAction getType() {
        return MOUVEMENT;
    }

    /**
     * permet de récupérer le type de mouvement
     * @return un objet de type TypeMouvement
     */
    @Override
    public TypeMouvement getDirection() {
        return typeMouvement;
    }
}
