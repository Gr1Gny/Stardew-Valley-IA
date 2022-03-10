/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import static ia.base.metier.actions.TypeAction.RECOLTE;

/**
 * classe qui permet de couper un arbre
 * @author nm344384
 */
public class ActionCouperArbre extends Action {
    private TypeMouvement typeMouvement;

    /**
     * constructeur de la classe ActionCouperArbre
     * @param typeMouvement mouvement dans lequel on souhaite aller
     */
    public ActionCouperArbre(TypeMouvement typeMouvement) {
        this.typeMouvement = typeMouvement;
    }
    
    /**
     * permet de récupérer le message à envoyer au server pour réaliser un action
     * @return le message sous forme de chaine de caractères
     */
    @Override
    public String getMessage() {
        return "HARVEST|" + typeMouvement;
    }

    /**
     * permet de récupérer le type d'action
     * @return le type d'action
     */
    @Override
    public TypeAction getType() {
        return RECOLTE;
    }

    /**
     * permet de récupérer le type de mouvement
     * @return un objet de type TypeMouvement
     */
    @Override
    public TypeMouvement getDirection() {
        return this.typeMouvement;
    }
    
}
