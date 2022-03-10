/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.metier.carte;

import ia.base.metier.TypeMouvement;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.cases.FabriqueCase;
import static ia.base.metier.carte.cases.TypeCase.HERBE;
import static ia.base.metier.carte.cases.TypeCase.TERRE;
import ia.base.metier.carte.objets.FabriquerObjet;
import ia.base.metier.carte.objets.Objet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 * classe qui permet de gérer la carte
 * @author Noham
 */
public class Carte {
    private HashMap<Coordonnee,Case> cases;
    private int taille;
    private Coordonnee CoordonneeDepart;
    private ArrayList<Coordonnee> CoordonneesMagasin;
    
    /**
     * permet de récupérer la taille de la carte
     * @return la taille sous forme de nombre entier
     */
    public int getTaille(){
        return this.taille;
    }

    /**
     * méthode qui permet de récupérer les coordonnées du magasin
     * @return un tableau contenant les coordonnées
     */
    public ArrayList<Coordonnee> getCoordonneesMagasin() {
        return CoordonneesMagasin;
    }
   
    /**
     * constructeur de la carte
     * @param messageRecu message envoyé par le serveur
     */
    public Carte(String messageRecu){
        this.cases = new HashMap<>() ;
        this.taille = (int) Math.sqrt(messageRecu.length()) ;
        for(int i=0 ;i<this.taille ;i++) {
            for(int j=0 ;j<this.taille ;j++) {
                this.ajouterCase(new Coordonnee(i,j), messageRecu.charAt(j+this.taille*i));
            }
        }
        
        //Gestions des voisins
        for(int i=0 ;i<this.taille ;i++) {
            for(int j=0 ;j<this.taille ;j++) {
                Coordonnee cooCase = new Coordonnee(i,j) ;
                    for(TypeMouvement mouvement : TypeMouvement.values()) {
                        Coordonnee cooVoisin = cooCase.getVoisin(mouvement) ;
                            if(this.cases.get(cooVoisin) != null) {
                                this.cases.get(cooCase).ajouterVoisin(this.cases.get(cooVoisin)) ;
                    }
                }
            }
        }
        
        //on récupère ici les coordonnées des trois cases en dessous du départ
        Coordonnee S1 = CoordonneeDepart.getVoisin(TypeMouvement.BOTTOM);
        Coordonnee S2 = S1.getVoisin(TypeMouvement.LEFT);
        Coordonnee S3 = S1.getVoisin(TypeMouvement.RIGHT);
        
        //on récupère les cases et on y ajoute des escaliers
        Case c1 = cases.get(S1);
        Objet j1 = FabriquerObjet.creer(c1, 'S');
        c1.setObjet(j1);
        Case c2 = cases.get(S2);
        Objet j2 = FabriquerObjet.creer(c2, 'S');
        c2.setObjet(j2);
        Case c3 = cases.get(S3);
        Objet j3 = FabriquerObjet.creer(c3, 'S');
        c3.setObjet(j3);
        
        //on initialise l'Arraylist CoordonneesMagasin
        CoordonneesMagasin = new ArrayList<>();
        //on cherche maintenant les coordonnées des cases du magasin
        Coordonnee c = this.CoordonneeDepart.getVoisin(TypeMouvement.BOTTOM);
        c = c.getVoisin(TypeMouvement.BOTTOM);
        c = c.getVoisin(TypeMouvement.LEFT);
        c = c.getVoisin(TypeMouvement.LEFT);
        //les cases se trouvent 2 cases en bas et 3/4 cases à gauche du départ
        Coordonnee Magasin1 = c.getVoisin(TypeMouvement.LEFT);
        Coordonnee Magasin2 = Magasin1.getVoisin(TypeMouvement.LEFT);
        //on ajoute ces coordonnee à l'ArrayList
        CoordonneesMagasin.add(Magasin1);
        CoordonneesMagasin.add(Magasin2);
        System.out.println(Magasin1.toString());
        System.out.println(Magasin2.toString());
        
    }
    
    /**
     * permet d'ajouter une case à la carte
     * @param coordonnee permet de définir la position de la case à créer
     * @param lettre permet de définir la case et l'objet à créer
     */
    private void ajouterCase(Coordonnee coordonnee, Character lettre){
        Case c = FabriqueCase.creer(coordonnee, lettre);
        cases.put(coordonnee, c);
        if (lettre.equals('D')){
            this.CoordonneeDepart = coordonnee;
        }
    }
    
    /**
     * permet d'afficher la carte dans la console
     */
    public void afficheConsole() {
        for(int i=0 ;i<this.taille ;i++) {
            for(int j=0 ;j<this.taille ;j++) {
                String affichage = "E" ;
                Case caseEnCours = this.cases.get(new Coordonnee(i,j)) ;
                if (caseEnCours.getType() == HERBE) {
                    if(caseEnCours.getObjet() == null) {
                        affichage = "H" ;
                    }
                    else {
                        switch(caseEnCours.getObjet().getType()) {
                            case ARBRE : affichage = "A" ; break ;
                            case MAISON : affichage = "M" ; break ;
                            case ESCALIER : affichage = "S" ; break ;
                            case DEPART : affichage = "D" ; break ;
                        }
                    }
                }
                else if(caseEnCours.getType() == TERRE) {
                    affichage = "T" ;
                }
                System.out.print(affichage) ;
            }
            System.out.println("") ;
        }
    }

    /**
     * permet de récupérer les coordonnées du départ
     * @return les cooridonnées
     */
    public Coordonnee getCoordonneeDepart() {
        return CoordonneeDepart;
    }
    
    /**
     * permet de récupérer une case
     * @param coordonnee correspond à l'emplacement de la case
     * @return la case
     */
    public Case getCase(Coordonnee coordonnee){
        return cases.get(coordonnee);
    }
    
    /**
     * permet de récupérer toutes les cases de ma carte
     * @return un collection contenant les cases
     */
    public Collection<Case> getCases(){
        return cases.values();
    }
    
}
