import Exceptions.*;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;



public class Menu {
    private Expedition expedition;

    private int nombreTOTAL;

    private Scanner scanner;
    private Colonie colonie;
    public Menu(Colonie colonie) {
        this.scanner = new Scanner(System.in);
        this.colonie=colonie;
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
                    Colon c1 =new Colon(colon1);
                    Colon c2 =new Colon(colon2);
                    
                    ArrayList<Colon> colons = new ArrayList<>();
                    colons.add(c2);
                    colonie.ajouterRelation(c1, colons);
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

    
    public void afficherMenuAffectation() {
        System.out.println("Gestion des affectations de ressources :");
        boolean enCours = true;
        while (enCours) {
            System.out.println("1) Échanger les ressources de deux colons");
            System.out.println("2) Afficher le nombre de colons jaloux");
            System.out.println("3) Fin");
            int choix = scanner.nextInt();

            try {
                switch (choix) {
                    case 1:
                        System.out.print("Entrer les noms des deux colons à échanger : ");
                        String nom1 = scanner.next();
                        String nom2 = scanner.next();
                        Colon c1 = new Colon(nom1);
                        Colon c2 = new Colon(nom2);

                        colonie.echangerRessources(c1, c2);
                        break;
                    case 2:
                        int cout = colonie.calculerColonsJaloux();
                        System.out.println("Nombre de colons jaloux : " + cout);
                        break;
                    case 3:
                        enCours = false;
                        break;
                    default:
                        System.out.println("Option invalide. Réessayez.");
                }
             } catch (Exception e) {
                System.out.println("Erreur inattendue : " + e.getMessage());
            }
        }
    }
}