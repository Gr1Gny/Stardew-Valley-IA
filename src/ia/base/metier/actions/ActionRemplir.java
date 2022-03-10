/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import static ia.base.metier.actions.TypeAction.REMPLIR;

/**
 * action permettant de remplir l'arrosoir
 * @author Noham
 */
public class ActionRemplir extends Action {
    
    private TypeMouvement typeMouvement;

    /**
     * constructeur de la classe ActionRemplir
     * @param typeMouvement mouvement dans lequel on souhaite aller
     */
    public ActionRemplir(TypeMouvement typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    /**
     * permet de récupérer le message à envoyer au server pour réaliser un action
     * @return le message sous forme de chaine de caractères
     */
    @Override
    public String getMessage() {
        return "FILL|" + typeMouvement;
    }

    /**
     * permet de récupérer le type d'action
     * @return le type d'action
     */
    @Override
    public TypeAction getType() {
        return REMPLIR;
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
