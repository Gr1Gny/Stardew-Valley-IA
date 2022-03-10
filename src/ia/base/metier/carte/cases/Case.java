/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.objets.Objet;
import java.util.ArrayList;

/**
 * classe qui permet la gestion d'une case
 * @author Noham
 */
public abstract class Case {
    private Coordonnee coordonnee;
    private Objet objet;
    private ArrayList<Case> voisins;

    /**
     * méthode qui permet de recupérer les coordonnées de la case
     * @return les coordonnées
     */
    public Coordonnee getCoordonnee() {
        return this.coordonnee;
    }

    /**
     * permet de savoir quel objet se trouve sur cette case
     * @return l'objet qui se trouve sur la case
     */
    public Objet getObjet() {
        return this.objet;
    }

    /**
     * permet de placer un objet sur une case
     * @param objet qu'on souhaite ajouter
     */
    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    /**
     * constructeur de la classe case
     * @param coordonnee correspond à l'emplacement de la case
     */
    public Case(Coordonnee coordonnee) {
        this.coordonnee = coordonnee;
        voisins = new ArrayList();
    }

    /**
     * permet de récupérer les voisins d'une case
     * @return un tableau contenant les voisins
     */
    public ArrayList<Case> getVoisins() {
        return voisins;
    }

    /**
     * permet d'ajouter un voisin dans le tableau contenant tous les voisins
     * @param voisin qui correspond à un voisin
     */
    public void ajouterVoisin(Case voisin){
        voisins.add(voisin);
    }
    
    /**
     * on veut récupérer le type de la case
     * @return le type de la case
     */
    public abstract TypeCase getType();
    
    /**
     * permet de savoir si une case est accessible
     * @return true si on peut aller dessus
     */
    public abstract boolean estAccessible();
    
    /**
     * permet de connaitre le mouvement à effectuer pour aller sur une case voisine
     * @param arrivee case sur laquelle on souhaite arriver
     * @return le type de mouvement à effectuer
     */
    public TypeMouvement getMouvementPourAller(Case arrivee){
        return this.getCoordonnee().getMouvementPourAller(arrivee.getCoordonnee());
    }
}
