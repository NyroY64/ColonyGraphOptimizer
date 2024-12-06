package Conquest.Menus;

import Conquest.Expedition;

import java.util.Scanner;

/**
 * Classe MenuLoadTXT
 *
 * Cette classe sert de Menu specifique au lancement via un fichier en argument.
 *
 * 1) Resolution automatique
 * 2) Sauvegarde de la solution actuelle
 * 3) Fin
 *
 * @see Conquest.Main#main(String[])
 *
 * @author Devasenaradjounayagar Damien
 *
 * @version 1.0
 *
 */
public class MenuLoadTXT extends Menu
{
    public MenuLoadTXT(Expedition expedition)
    {
        super(expedition);
    }

    public void afficherMenuConfigurationLoadTXT() throws Exception
    {
        Scanner scanner = super.getScanner();
        Expedition expedition = super.getExpedition();

        System.out.println("Configuration de la colonie :");
        boolean enCours = true;
        while (enCours)
        {
            System.out.println("1) Resolution automatique");
            System.out.println("2) Sauvegarde de la solution actuelle");
            System.out.println("3) Fin");
            int choix = scanner.nextInt();
            scanner.nextLine();  // Consomme le saut de ligne

            switch (choix)
            {
                case 1:
                    System.out.println("Cout de la solution: "+expedition.affectation(0)+"\n");
                    break;
                case 2:
                    String nomFichier = scanner.nextLine();
                    System.out.println("Solution actuelle sauvegardé: "+expedition.save(nomFichier,0));
                    System.out.println("L'ecriture sous un nom de fichier deja existant ecrasera l'ancien nom.");
                    break;
                case 3:
                    enCours = false;
                    break;
                default:
                    System.out.println("Option invalide. Réessayez.");
            }
        }
    }
}
