package ia.base.modules;

import ia.base.IA;

/**
 * Classe abstraite des différents modules de l'IA
 * @author Matthieu
 */
public abstract class Module {
    private IA ia;
    
    /**
     * constructeur de la classe module
     * @param ia correspond à un objet de type IA
     */
    public Module(IA ia) {
        this.ia = ia;
    }
    
    /**
     * permet de récupérer l'ia
     * @return une IA
     */
    public IA getIA() {
        return this.ia;
    }
    
}
