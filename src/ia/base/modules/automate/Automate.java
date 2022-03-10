/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia.base.modules.automate;

import ia.base.metier.actions.Action;
import ia.base.modules.Module_Decision;
import ia.base.modules.Module_Memoire;
import ia.base.modules.automate.etats.EtatInitial;
import java.util.ArrayList;

/**
 *
 * @author nm344384
 */
public class Automate {
    private Module_Decision ModuleDecision;
    private ArrayList<Action> listeDesActionsARealiser;
    private Etat etatCourant;

    /**
     * 
     * @param ModuleDecision 
     */
    public Automate(Module_Decision ModuleDecision) {
        this.ModuleDecision = ModuleDecision;
        listeDesActionsARealiser = new ArrayList<>();
        this.etatCourant = new EtatInitial(this);   
    }

    /**
     * méthode qui permet de récupérer le module mémoire
     * @return le module mémoire
     */
    public Module_Decision getModuleDecision() {
        return ModuleDecision;
    }

    /**
     * méthode qui permet de récupérer le module décision
     * @return le module décision
     */
    public Module_Memoire getModuleMemoire() {
        return this.ModuleDecision.getIA().getModuleMemoire();
        
    }

    /**
     * méthode qui permet de récupérer la liste des actions à réaliser
     * @return un tableau contenant les actions à réaliser
     */
    public ArrayList<Action> getListeDesActionsARealiser() {
        return listeDesActionsARealiser;
    }
    
    /**
     * permet à l'automate de changer d'état
     * @return l'action de l'état courant
     */
    public Action evoluer(){
        Action prochaineAction = null;
        while(prochaineAction == null){
            etatCourant = etatCourant.transition();
            prochaineAction = etatCourant.action();
        }
        return prochaineAction;
    }
    
}
