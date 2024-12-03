package Conquest;

import Conquest.Menus.Menu;
import Conquest.Menus.MenuLoadTXT;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        Expedition expedition = new Expedition();

        //Main Menu
        if(args.length == 0)
        {
            Menu menu= new Menu(expedition);
            menu.afficherMenuCreationColonie();
            menu.afficherMenuCreationRessources();
            menu.afficherMenuConfiguration();
            menu.afficherMenuAffectation();

        }
        else if (args.length == 1)
        {
            MenuLoadTXT menu = new MenuLoadTXT(expedition);
            expedition.Importation(args[0]);
            menu.afficherMenuConfigurationLoadTXT();
        }
        else
        {
            throw new Exception("Too much arguments");
        }
    }
}
