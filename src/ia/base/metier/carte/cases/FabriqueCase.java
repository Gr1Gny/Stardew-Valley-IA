/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte.cases;

import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.objets.FabriquerObjet;
import ia.base.metier.carte.objets.Objet;

/**
 * classe qui permet la créeation d'une case
 * @author Noham
 */
public class FabriqueCase {
    
    /**
     * permet la fabriquation d'une case et d'y ajouter un objet
     * @param coordonnee correspond à l'emplacement de la case
     * @param lettre nous indique quelle case et quel objet créer
     * @return la case qui a été crée
     */
    public static Case creer(Coordonnee coordonnee, Character lettre){
        Case c = null;
        Objet objet;
        //en fonction de la lettre, on crée une case avec des TypeCase différents
        switch (lettre) {
                //pas besoin d'ajouter un objet sur la case eau
                case 'E':
                    c = new CaseEau(coordonnee); 
                    break;
                case 'H': case 'M': case 'D': case 'A': case 'S':
                    c = new CaseHerbe(coordonnee); 
                    break;
                case 'T' :
                    c = new CaseTerre(coordonnee);        
                    break;
        }
        //on appel la fabrique d'objet pour créer et ensuite rajouter un objet sur la case
        objet = FabriquerObjet.creer(c, lettre);
        c.setObjet(objet);
        return c;
    }
}