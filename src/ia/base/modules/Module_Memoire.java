package ia.base.modules;

import ia.base.IA;
import ia.base.metier.Joueur;
import ia.base.metier.actions.Action;
import ia.base.metier.actions.TypeAction;
import ia.base.metier.carte.Carte;
import ia.base.metier.carte.Coordonnee;
import ia.base.metier.carte.cases.Case;
import ia.base.metier.carte.objets.FabriquerObjet;
import ia.base.metier.carte.objets.Objet;
import ia.base.metier.carte.objets.Plante;
import ia.base.metier.carte.objets.TypeObjet;
import ia.base.metier.carte.ressources.TypeRessource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Module en charge de la mémorisation et de la restitution des informations obtenues
 * @author Matthieu
 */
public class Module_Memoire extends Module  {
    private Carte carte;
    private Joueur joueur;
    private HashMap<TypeRessource,Integer> inventaire;
    private HashMap<TypeRessource,Integer> stockMagasin;
    private ArrayList<Plante> listePlantes;
    
    /**
     * Constructeur
     * @param ia l'IA dont ce module fait partie
     */
    public Module_Memoire(IA ia) {
        super(ia);
        inventaire = new HashMap<>();
        for(TypeRessource type : TypeRessource.values()){
            inventaire.put(type,0);
        }
        inventaire.put(TypeRessource.GOLD, 500);
        inventaire.put(TypeRessource.EAU, 20);
        stockMagasin = null;
        listePlantes = new ArrayList<>();
    }
    
    /**
     * méthode qui permet de générer la carte
     * @param messageRecu message du serveur
     */
    public void genererCarte(String messageRecu){
        this.carte = new Carte(messageRecu);
        this.genererJoueur(carte.getCoordonneeDepart());
        System.out.println(joueur.getCoordonnee());
    }

    /**
     * permet de savoir si le module mémoire a la carte
     * @return true si le module mémoire a la carte
     */
    public boolean hasCarte(){
        boolean b = false;
        if (this.carte != null){
            b = true;
        }
        return b;
    }

    /**
     * permet de renvoyer la carte
     * @return la carte
     */
    public Carte getCarte() {
        return carte;
    }
    
    /**
     * permet de savoir si le module mémoire possède un joueur
     * @return true si il y a un joueur
     */
    public boolean hasJoueur(){
        boolean b = false;
        if (this.joueur == null){
            b = true;
        }
        return b;
    }

    /**
     * permet de renvoyer le joueur
     * @return le joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }
    
    /**
     * permet de générer le joueur
     * @param coordonnee ou le joueur apparait
     */
    public void genererJoueur(Coordonnee coordonnee){
        this.joueur = new Joueur(coordonnee);
    }

    /**
     * permet de récupérer la case où se trouve le joueur
     * @return une case
     */
    public Case getCaseJoueur(){
        Coordonnee c = joueur.getCoordonnee();
        return carte.getCase(c);
    }
    
    /**
     * permet d'effectuer une action
     * @param action à effectuer
     */
    public void effectuerAction(Action action){
        
        switch(action.getType()){
        //cas où l'action est une action de type mouvement
            case MOUVEMENT : 
                this.joueur.deplacer(action.getDirection()) ; 
            break;

        //cas où l'action est de type récolte
            case RECOLTE :
                if(action.getDirection() != null) {
                    Case caseDestination = this.carte.getCase(this.getCaseJoueur().getCoordonnee().getVoisin(action.getDirection())) ;
                    this.recolter(caseDestination.getObjet());
                    caseDestination.setObjet(null) ;
                }
            break;
            
        //cas où l'action est de type statique
            case ACTIONSTATIQUE :
                stockMagasin = null;
                for(Plante p : this.listePlantes){
                    if(p.isEstArrose()){
                        p.grandir();
                    }
                        p.setEstArrose(false);      
                } 
            break;
           
        //cas où l'action est un achat
            case ACHAT :
                //on retire une graine au magasin
                Integer stock = this.stockMagasin.get(TypeRessource.PARSNIPSEED);
                this.stockMagasin.put(TypeRessource.PARSNIPSEED, stock - 1);
            
                //on rajoute une graine à l'inventaire
                Integer graineInventaire = getQuantiteRessource(TypeRessource.PARSNIPSEED);
                this.inventaire.put(TypeRessource.PARSNIPSEED, graineInventaire + 1);
            
                //on retire 20 ors à l'inventaire
                Integer orInventaire = getQuantiteRessource(TypeRessource.GOLD);
                this.inventaire.put(TypeRessource.GOLD, orInventaire - 20);
           break;
          
        //cas où l'action permet de planter
            case PLANTER :
                this.addPlante(TypeObjet.PANAIS);
                Integer grainePanais = getQuantiteRessource(TypeRessource.PARSNIPSEED);
                this.inventaire.put(TypeRessource.PARSNIPSEED, grainePanais - 1);
            break;
        
        //cas où l'action permet d'arroser
            case ARROSER :
                for(Plante p : this.listePlantes){
                    if(p.getPosition() == this.getCaseJoueur()){
                        p.setEstArrose(true);
                    }
                }
                Integer eau = getQuantiteRessource(TypeRessource.EAU);
                this.inventaire.put(TypeRessource.EAU, eau - 1);
            break;
            
        //cas où l'action permet de remplir le sceau
            case REMPLIR :
                inventaire.put(TypeRessource.EAU, 20);
            break;
        
        //cas où l'action permet de cueillir
            case CUEILLIR :
                recolterPlante();
            break;
        
        //cas où l'action permet de vendre
            case VENDRE :
                Integer orInventaire2 = getQuantiteRessource(TypeRessource.GOLD);
                this.inventaire.put(TypeRessource.GOLD, orInventaire2 + 35);
            
                Integer panaisInventaire = getQuantiteRessource(TypeRessource.PARSNIPMATURE);
                this.inventaire.put(TypeRessource.PARSNIPMATURE, panaisInventaire - 1);
            break;       
        }
    }
    
    /**
     * permet de modifier l'inventaire quand on récolte un objet
     * @param objet qu'on récolte
     */
    private void recolter(Objet objet){
        if(objet != null){
            Set<TypeRessource> types = objet.getLoot().keySet();
            for (TypeRessource type : types){
                inventaire.put(type,objet.getLoot().get(type));
            }
        }
    }
    
    /**
     * permet de récupérer ce qu'on a dans l'inventaire
     * @param type de ressource dont on veut savoir le nom
     * @return la quantitée de cette ressource
     */
    public int getQuantiteRessource(TypeRessource type){
        return inventaire.get(type);
    }

    /**
     * permet de générer les stock du magasin
     * @param nbGrainePanais nombre de granies de panais disponibles
     * @param nbGraineChouxFleur nombre de graines de chouxfleur disponibles
     */
    public void genererStockMagasin(int nbGrainePanais,int nbGraineChouxFleur){
        stockMagasin = new HashMap<>();
        stockMagasin.put(TypeRessource.PARSNIPSEED, nbGrainePanais);
        stockMagasin.put(TypeRessource.CAULIFLOWERSEED, nbGraineChouxFleur);
    }
    
    /**
     * permet de savoir si on connait le stock du magasin
     * @return true si on connait le stock
     */
    public boolean hasStockMagasin(){
        boolean b = false;
        if(stockMagasin != null){
            b= true;
        }
        return b;
    }

    /**
     * permet de récupérer le stock du magasin
     * @param type de la ressource qu'on souhaite récupérer
     * @return la quantitée de cette ressource disponible
     */
    public int getStockMagasin(TypeRessource type){
        return stockMagasin.get(type);
    }

    /**
     * permet de récupérer la liste des plantes
     * @return un tableau contenant les plantes
     */
    public ArrayList<Plante> getListePlantes() {
        return listePlantes;
    }   
    
    /**
     * permet d'ajouter une plante à la liste de plante
     * @param type correspond au type de la plante qu'on ajoute
     */
    private void addPlante(TypeObjet type){
        Plante plante = FabriquerObjet.creerPlante(this.getCaseJoueur(), type);
        this.getCaseJoueur().setObjet(plante);
        this.listePlantes.add(plante);
    }
    
    /**
     * permet de récolter une plante
     */
    private void recolterPlante(){
        Objet o = this.getCaseJoueur().getObjet();
        this.recolter(o);
        this.listePlantes.remove(o);
        this.getCaseJoueur().setObjet(null);
    }


}
