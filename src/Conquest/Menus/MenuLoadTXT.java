package Conquest.Menus;

import Conquest.Expedition;

import java.util.Scanner;

/**
 * Classe MenuLoadTXT
 * <p>
 * Cette classe sert de Menu specifique au lancement via un fichier en argument.
 * <p>
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
    /**
     * Constructeur de MenuLoadTXT
     * @param expedition
     * L'expedition a gerer.
     *
     * @see Conquest.Main#main(String[])
     *
     */
    public MenuLoadTXT(Expedition expedition)
    {
        super(expedition);
    }

    /**
     * Menu pour le lancement avec un fichier en argument.
     * <p>
     * 1) Resolution automatique
     * 2) Sauvegarde de la solution actuelle
     * 3) Fin
     *
     * @see Conquest.Main#main(String[])
     */
    public void afficherMenuConfigurationLoadTXT()
    {
        Scanner scanner = super.getScanner();
        Expedition expedition = super.getExpedition();

        System.out.println("Configuration de la colonie :");
        boolean enCours = true;
        while (enCours)
        {
            System.out.print("\nVoici la colonie : ");
            System.out.println(expedition.getColonie(0).toString());

            System.out.println("1) Resolution automatique");
            System.out.println("2) Sauvegarde de la solution actuelle");
            System.out.println("3) Fin");
            try
            {
                System.out.print("Veuillez entrer votre choix : ");
                int choix = scanner.nextInt();
                scanner.nextLine();  // Consomme le saut de ligne

                switch (choix)
                {
                    case 1:
                        System.out.println("\nResolution automatique-");
                        System.out.println("Cout de la solution: " + expedition.affectation(0) + "\n");
                        break;
                    case 2:
                        System.out.println("L'ecriture sous un nom de fichier deja existant ecrasera l'ancien nom.");
                        System.out.println("\nEntrez le nom du fichier a creer.(Si vous voulez une extension incluez la.)");
                        String nomFichier = scanner.nextLine();
                        System.out.println("Solution actuelle sauvegardé: " + expedition.save(nomFichier, 0));

                        break;
                    case 3:
                        enCours = false;
                        break;
                    default:
                        System.out.println("Option invalide. Réessayez.");
                }
            }catch (Exception e)
            {
                System.out.println("Option invalide. Réessayez.");
                scanner.nextLine();
            }
        }
    }
}
