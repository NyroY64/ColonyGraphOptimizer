import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        Colonie colonie=new Colonie();
        Menu  menu= new Menu(colonie);
        menu.afficherMenuAffectation();
    }
}
