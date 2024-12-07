package Conquest;

import Conquest.Exception.*;
import Conquest.Menus.Menu;
import Conquest.Menus.MenuLoadTXT;

/**
 * C'est la fonction qui permet de lancer les differents menu.
 * <p>

 *
 * @author Devasenaradjounayagar Damien
 *
 * @version 1.0
 *
 */
public class Main
{
    /**
     *
     * Il y a 2 modes de lancements:
     * -Sans Arguements
     *      Il va y avoir un menu pour Creer une colonie et, y affetecer leurs attributs, puis proposer une solution naive.
     *      Un autre menu va permettre de changer l'affectation d'un objet entre 2 colons et puis visualiser la colonie ainsi que le cout.
     * -Avec Arguments
     *      On ne prends que 1 argument, cet argument est le chemin du fichier texte contenant les informations de creations de colonies formalisés.
     * @params args
     * Il s'agit du chemin du fichier.

     * @throws Exception
     * Retourne une exception rencontré dans les Menys.
     *
     */
    public static void main(String[] args) throws Exception
    {
        Expedition expedition = new Expedition();

        //Main Menu
        if(args.length == 0)
        {
            Menu menu= new Menu(expedition);
            int currentColonie = menu.afficherMenuCreationColonie();
            int ressourceIndex = menu.afficherMenuCreationRessources();
            menu.afficherMenuConfiguration(currentColonie);
            menu.afficherMenuAffectation(currentColonie,ressourceIndex);

        }
        else if (args.length == 1)
        {
            MenuLoadTXT menu = new MenuLoadTXT(expedition);
            expedition.Importation(args[0]);

            //Check if Expedition Usable
            if(expedition.getColonie(0).getColons().size()!=expedition.getRessource(0).size())
                throw new NbColonDIfferentNbRessourcesException("Nombre de colons different du nombre de Ressource.");
            menu.afficherMenuConfigurationLoadTXT();
        }
        else
        {
            throw new ToMuchArgumentsException("Too much arguments");
        }
    }
}
