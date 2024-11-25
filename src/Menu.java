import Exceptions.*;


import java.util.Scanner;

//TODO
public class Menu {
    private Expedition expedition;
    private int nombreTOTAL;

    private Scanner scanner;

    public Menu(Colonie colonie)
    {

        this.scanner = new Scanner(System.in);
    }

    public void afficherMenuConfiguration() throws ColonDejaExistantException, ColonInexistantException, RelationDejaExistanteException, RelationAvecSoiMemeException {
        System.out.println("Configuration de la colonie :");
        boolean enCours = true;
        while (enCours) {
            System.out.println("1) Ajouter une relation entre deux colons");
            System.out.println("2) Ajouter les préférences d’un colon");
            System.out.println("3) Fin");
            int choix = scanner.nextInt();
            scanner.nextLine();  // Consomme le saut de ligne

            switch (choix) {
                case 1:
                    System.out.print("Entrer les noms des deux colons (ex : A B) : ");
                    String colon1 = scanner.next();
                    String colon2 = scanner.next();
                    //colonie.ajouterRelation(colon1,colon2);
                    break;
                case 2:
                    System.out.print("Entrer le nom du colon et ses préférences (ex : A 1 2 3) : ");
                    String nom = scanner.next();
                    Colon colon = new Colon(nom);
                    while (scanner.hasNextInt()) {
                        colon.addPreference(scanner.nextInt());
                    }
                    colonie.ajouterColon(colon);
                    break;
                case 3:
                    enCours = false;
                    break;
                default:
                    System.out.println("Option invalide. Réessayez.");
            }
        }
    }

    
    public void afficherMenuAffectation() throws EchangeAvecSoiMemeException, ColonInexistantException {
        System.out.println("Gestion des affectations de ressources :");
        boolean enCours = true;
        while (enCours) {
            System.out.println("1) Échanger les ressources de deux colons");
            System.out.println("2) Afficher le nombre de colons jaloux");
            System.out.println("3) Fin");
            int choix = scanner.nextInt();

            switch (choix) {
                case 1:
                    System.out.print("Entrer les noms des deux colons à échanger : ");
                    String nom1 = scanner.next();
                    String nom2 = scanner.next();
                    //colonie.echangerRessources(nom1, nom2);
                    break;
                case 2:
                    //int cout = colonie.calculerColonsJaloux();
                    System.out.println("Nombre de colons jaloux : " + "cout");
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