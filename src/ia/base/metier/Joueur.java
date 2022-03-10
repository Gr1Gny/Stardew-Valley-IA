/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier;

import ia.base.metier.carte.Coordonnee;

/**
 * classe qui permet de gérer le joueur
 * @author Noham
 */
public class Joueur {
    private Coordonnee coordonnee;

    /**
     * permet de savoir où se trouve le joueur
     * @return les coordonnées du joueur 
     */
    public Coordonnee getCoordonnee() {
        return coordonnee;
    }

    /**
     * permet de créer un joueur
     * @param coordonnee correspond à l'endroit ou on souhaite le créer
     */
    public Joueur(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
    }
    
    /**
     * permet au joueur de se déplacer
     * @param mouvement permet de savoir ou le joueur se déplace
     */
    public void deplacer(TypeMouvement mouvement){
        this.coordonnee = this.coordonnee.getVoisin(mouvement);
    }
}
