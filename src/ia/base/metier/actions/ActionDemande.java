/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import static ia.base.metier.actions.TypeAction.DEMANDE;

/**
 * classe qui permet de demander un action
 * @author nm344384
 */
public class ActionDemande extends Action{
    private String message;

    /**
     * constructeur de la classe à partir d'un message
     * @param message permet de savoir l'action qu'on devra effectuer
     */
    public ActionDemande(String message) {
        this.message = message;
    }

    /**
     * permet de récupérer le message à envoyer au server pour réaliser un action
     * @return le message sous forme de chaine de caractères
     */
    @Override
    public String getMessage() {
        return message;
    }

    /**
     * permet de récupérer le type d'action
     * @return le type d'action
     */
    @Override
    public TypeAction getType() {
        return DEMANDE;
    }

    /**
     * permet de récupérer le type de mouvement
     * @return null car on crée pas de mouvements
     */
    @Override
    public TypeMouvement getDirection() {
        return null;
    }
}
