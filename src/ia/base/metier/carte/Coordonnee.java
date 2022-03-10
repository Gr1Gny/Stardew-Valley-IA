/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte;

import ia.base.metier.TypeMouvement;

/**
 * classe qui permet de gérer les coordonnées
 * @author Noham
 */
public class Coordonnee {
    private int ligne;
    private int colonne;
    
    /**
     * permet de récupérer la ligne sur laquel se trouve la case
     * @return un entier qui correspond à la ligne 
     */
    public int getLigne(){
        return this.ligne;
    }
    
    /**
     * permet de récupérer la colonne sur laquel se trouve la case
     * @return un entier qui correspond à la colonne
     */
    public int getColonne(){
        return this.colonne;
    }
    
    public Coordonnee(int ligne,int colonne){
        this.ligne = ligne;
        this.colonne = colonne;
    }

    /**
     * fonction qui permet de hasher la ligne et la colonne
     * @return un entier qui correspond au hashage
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.ligne;
        hash = 67 * hash + this.colonne;
        return hash;
    }

    /**
     * permet de comparer la carte avec un autre objet et dire si ce sont les mêmes
     * @param obj qui correspond à l'objet à comparer
     * @return true si ce sont les mêmes objets
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Coordonnee other = (Coordonnee) obj;
        if (this.ligne != other.ligne) {
            return false;
        }
        if (this.colonne != other.colonne) {
            return false;
        }
        return true;
    }
    
    /**
     * permet de récupérer le voisin d'une case en fonction d'un mouvement
     * @param mouvement à effectuer pour trouver le voisin
     * @return les coordonnées du voisin
     */
    public Coordonnee getVoisin(TypeMouvement mouvement){
        int ligneD = this.ligne;
        int colonneD = this.colonne;
        //si on déplace, on change soit de ligne soit de colonne
        switch (mouvement){
            case TOP : ligneD -= 1;break;
            case BOTTOM : ligneD += 1;break;
            case LEFT : colonneD -= 1;break;
            case RIGHT : colonneD += 1;break;
        }
        Coordonnee c = new Coordonnee(ligneD,colonneD);
        return c;
    }
    
    /**
     * permet de connaitre le mouvement à effectuer pour aller sur une autre case
     * @param destination correspond aux coordonnées de la case sur laquelle on doit aller
     * @return le type de mouvement à effectuer pour y aller
     */
    public TypeMouvement getMouvementPourAller(Coordonnee destination){
        TypeMouvement mouvement = null;
        //on utilise la méthode getVoisin pour se déplacer autour de la case actuel
        //si les coordonnées de la destination sont les mêmes, alors on sai quel mouvement réaliser
        if (destination.equals(getVoisin(TypeMouvement.TOP))){
            mouvement = TypeMouvement.TOP;
        }
        else if (destination.equals(getVoisin(TypeMouvement.BOTTOM))){
            mouvement = TypeMouvement.BOTTOM;
        }
        else if (destination.equals(getVoisin(TypeMouvement.RIGHT))){
            mouvement = TypeMouvement.RIGHT;
        }
        else if (destination.equals(getVoisin(TypeMouvement.LEFT))){
            mouvement = TypeMouvement.LEFT;
        }
        return mouvement;
    }

    /**
     * On retour les coordonnées
     * @return les coordonnées sous for de chaine de caractère
     */
    @Override
    public String toString() {
        return "(" + String.valueOf(ligne) + "," + String.valueOf(colonne) + ")";
    }

    
}
