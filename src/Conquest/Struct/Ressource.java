package Conquest.Struct;
import Conquest.Expedition;
import Conquest.Menus.Menu;

import java.util.Objects;
import java.util.Stack;

/**
 * Classe Ressource
 *
 * Elle permet de gerer les ressources en tant qu'ensemble.
 *
 *
 * @see Expedition
 * @see Expedition#createRessource()
 * @see Expedition#getRessource(int)
 * @see Expedition#algoFavoriteFirst(int, Ressource) 
 * @see Expedition#algoBestPerfSUR(int, int, Ressource)
 * @see Expedition#algoBestPerfSUPER(Ressource) 
 * @see Expedition#affectation(int) 
 * @see Expedition#Importation(String) 
 * 
 * @see Menu#afficherMenuCreationRessources() 
 * @see Conquest.Menus.Menu#afficherMenuAffectation(int, int) 
 * @see Conquest.Menus.MenuLoadTXT#afficherMenuConfigurationLoadTXT()
 *
 * @see Conquest.Menus.Menu
 *
 * @author Devasenaradjounayagar Damien
 *
 * @version 1.0
 *
 */
public class Ressource
{
    /**
     * Pile de base ou seront stocké les ressources dans leur ordre d'insertions
     * Les ressources sont des String.
     *
     * @see Ressource()
     * @see Ressource#addRessource(String) 
     * @see Ressource#removeRessource(String)  
     * @see Ressource#Depile() 
     * @see Ressource#Repile() 
     * @see Ressource#getRessource(int)
     *
     *
     *
     */
    private final Stack<String> PileBase;

    /**
     * Pile auxiliaire qui va serivir a faire un transfert des Ressources a PileBase afin de ne pas perdre les ressources une fois affectées
     *
     * @see Ressource()
     * @see Ressource#removeRessource(String)
     * @see Ressource#Depile()
     * @see Ressource#Repile()
     *
     *
     */
    private final Stack<String> PileAux;

    /**
     * Constructeur de Ressource
     *
     * @see Expedition#createRessource()
     * @see Expedition#Importation(String) 
     *
     * @author Devasenaradjounayagar Damien
     *
     *
     */
    public Ressource()
    {
        PileBase = new Stack<String>();
        PileAux = new Stack<String>();
    }

    /**
     * Ajoute une Ressource a la structure.
     *
     * @param n
     * La ressource à retirer.
     *
     * @return 0 Si c'est un success.
     *
     * @see Menu#afficherMenuCreationRessources()
     * @see Expedition#Importation(String)
     *
     * @author Devasenaradjounayagar Damien
     *
     * @version 1.0
     *
     */
    public int addRessource(String n)
    {
        PileBase.push(n);
        return 0;
    }

    /**
     * Retire une Ressource a la structure.(Vers PileAux)
     *
     * @param n
     * La ressource à retirer.
     *
     * @return 0 Si c'est un success.
     *
     * @see Expedition#algoFavoriteFirst(int, Ressource)
     *
     * @author Devasenaradjounayagar Damien
     *
     * @version 1.0
     *
     */
    public int removeRessource(String n)
    {
        for(int i=0;i<PileBase.size();i++)
        {
            if (Objects.equals(PileBase.get(i), n))
            {
                PileAux.push(PileBase.remove(i));
            }
        }
        return 0;
    }

    /**
     * Vide les ressources.(Dans PileAux)
     *
     * @return
     * La ressource depilé ou 0 si il n'y a plus de ressources.
     *
     * @author Devasenaradjounayagar Damien
     *
     * @version 1.0
     *
     */
    public String Depile()
    {
        if(PileBase.isEmpty())
            return null;
        String n = PileBase.pop();
        PileAux.push(n);

        return n;
    }
    /**
     * Re remplit les ressources.(De PileAux vers la PileBase)
     *
     * @return
     * La ressource Repilé ou 0 si il n'y a plus de ressources.
     *
     * @author Devasenaradjounayagar Damien
     *
     *
     */
    public String Repile()
    {
        if(PileAux.isEmpty())
            return null;
        String n = PileAux.pop();
        PileBase.push(n);

        return n;
    }

    /**
     * Recupere une ressource un index donné.
     * Permet de parser les ressources.
     * @param n
     * Index de la ressource.
     *
     * @return
     * Retourne la ressource.
     *
     * @author Devasenaradjounayagar Damien
     *
     *
     */
    public String getRessource(int n)
    {
        return PileBase.get(n);
    }

    /**
     * Verifie si la ressource existe.
     *
     * @param n
     * La ressource a chercher.
     *
     * @return
     * Retouren true si la ressource existe sinon false.
     *
     * @author Devasenaradjounayagar Damien
     *
     *
     */
    public boolean contains(String n)
    {
        return PileBase.contains(n);
    }

    /**
     * Retourne le nombre de ressources.(De la PileBase)
     *
     * @return
     * Retourne la taille le nombre de ressources.
     *
     * @author Devasenaradjounayagar Damien
     *
     *
     */
    public int size()
    {
        return PileBase.size();
    }


}

