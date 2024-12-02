package Conquest;

import Conquest.Menus.Menu;
import Conquest.Struct.Colonie;

public class Main
{
    public static void main(String[] args)
    {
        Colonie colonie=new Colonie();
        Menu menu= new Menu(colonie);
        menu.afficherMenuAffectation();
    }
}
