/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.actions;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.ressources.TypeRessource;
import static ia.base.metier.actions.TypeAction.PLANTER;


/**
 * action permettant de planter des graines
 * @author Noham
 */
public class ActionPlanter extends Action{

    private TypeRessource typeRessource;

    public ActionPlanter(TypeRessource typeRessource) {
        this.typeRessource = typeRessource;
    }
    
    @Override
    public String getMessage() {
        return "PLANT|" + typeRessource;
    }

    @Override
    public TypeAction getType() {
        return PLANTER;
    }

    @Override
    public TypeMouvement getDirection() {
        return null;
    }
    
}
