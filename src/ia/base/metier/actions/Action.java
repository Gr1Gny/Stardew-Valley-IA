/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;

/**
 * classe qui permet la gestion des actions
 * @author nm344384
 */
public abstract class Action {
    
    /**
     * permet de récupérer le message à envoyer au server pour réaliser un action
     * @return le message sous forme de caractères
     */
    public abstract String getMessage();
    
    /**
     * permet de récupérer le type d'action
     * @return le type d'action
     */
    public abstract TypeAction getType() ;

    /**
     * permet de récupérer le type de mouvement
     * @return le type de mouvement
     */
    public abstract TypeMouvement getDirection();
}
