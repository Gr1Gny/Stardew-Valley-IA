/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.carte.Coordonnee;

/**
 * permet de créer une case terre
 * @author Noham
 */
public class CaseTerre extends Case {
    
    /**
     * constructeur de la classe CaseTerre
     * @param coordonnee correspond à l'emplacement ou on souhaite créer la case 
     */
    public CaseTerre(Coordonnee coordonnee) {
        super(coordonnee);
    }

    /**
     * on veut récupérer le type de la case
     * @return le type de la case
     */
    @Override
    public TypeCase getType() {
        return TypeCase.TERRE;
    }
    
    /**
     * permet de savoir si une case est accessible
     * @return true si on peut aller dessus
     */
    @Override
    public boolean estAccessible() {
        return true;
    }
    
}
