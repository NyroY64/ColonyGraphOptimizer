package Conquest.Menus;

import Conquest.Exception.ColonInexistantException;
import Conquest.Exception.RessourceDejaExistatneException;
import Conquest.Struct.Colon;
import Conquest.Struct.Colonie;
import Conquest.Expedition;
import Conquest.Struct.Ressource;

import java.util.ArrayList;
import java.util.Scanner;


public class Menu
{
    private Expedition expedition;
    private Scanner scanner;
    private int n;

    public Menu(Expedition expedition)
    {
        this.scanner = new Scanner(System.in);
        this.expedition=expedition;
        this.n = 0;
    }

    public Expedition getExpedition()
    {
        return expedition;
    }

    public Scanner getScanner()
    {
        return scanner;
    }

    public int afficherMenuCreationColonie() throws Exception
    {
        int colonieIndex = expedition.createColonie();
        Colonie colonie = expedition.getColonie(colonieIndex);

        System.out.println("Configuration de la colonie :");

        //Creation de Colonie
        while (true)
        {
            try
            {
                System.out.print("Combien de colons voulez-vous ajouter ? ");
                n = Integer.parseInt(scanner.nextLine());
                if (n <= 0)
                {
                    System.out.println("Veuillez entrer un nombre positif.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
        for (int i = 0; i < n; i++)
        {
            System.out.print("Entrez le nom du colon " + (i + 1) + " : ");
            String nomColon = scanner.nextLine();
            Colon colon = new Colon(nomColon);
            try
            {
                colonie.ajouterColon(colon);
            }catch (Exception e)
            {
                System.out.println(e.getMessage());
                i--;
            }
        }

        return colonieIndex;
    }

    public int afficherMenuCreationRessources() throws Exception
    {
        int ressourceIndex = expedition.createRessource();
        Ressource ressources = expedition.getRessource(ressourceIndex);

        System.out.println("Configuration des ressources :");
            for(int i=0; i<n; i++)
            {
                System.out.print("Entrez le nom de la ressource " + (i + 1) + " : ");
                String ressource = scanner.nextLine();
                ressources.addRessource(ressource);
                
                if(ressources.contains(ressource)){
                    throw new RessourceDejaExistatneException("cette ressource "+ ressource +"a deja etais ajouter !");
                }
            }
            return ressourceIndex;
    }

    public void afficherMenuConfiguration(int colonieIndex) throws Exception
    {
        Colonie colonie = expedition.getColonie(colonieIndex);

        //Manipulation de Colonie
        boolean enCours = true;
        while (enCours)
        {
            System.out.println();
            System.out.println("1) Ajouter une relation entre deux colons");
            System.out.println("2) Ajouter les préférences d’un colon");
            System.out.println("3) Fin");
            System.out.println();

            int choix;
            while (true) {
                System.out.print("Veuillez entrer votre choix : ");
                try {
                    choix = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e)
                {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
                }
            }

            switch (choix)
            {
                case 1:
                    System.out.print("Entrer les noms des deux colons (ex : A B) : ");
                    String colon1 = scanner.next();
                    String colon2 = scanner.next();
                    scanner.nextLine();

                    Colon c1 = null;
                    Colon c2 = null;
                    ArrayList<Colon> colons = null;
                    try
                    {
                        c1 = colonie.getColonObjet(colon1);
                        c2 = colonie.getColonObjet(colon2);
                        colons = new ArrayList<>();
                        colons.add(c2);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    try
                    {
                        c1.ajouterRelations(colons);
                    }
                    catch (Exception e)
                    {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Entrer le nom du colon et ses préférences (ex : A 1 2 3) : ");
                    String input = scanner.nextLine();
                    String[] parts = input.split(" ");

                    String nom = parts[0];
                    Colon colon;
                    try
                    {
                        colon = colonie.getColonObjet(nom);
                    } catch (ColonInexistantException e)
                    {
                        throw new RuntimeException(e);
                    }

                    for (int i = 1; i < parts.length; i++) {
                        colon.addPreference(parts[i]);
                    }
                    break;

                case 3:
                    enCours = false;
                    for(Colon c : colonie.getColons())
                    {
                        if(c.getPreferences().size()!=n)
                        {
                            System.out.println("Attention ! "+c.getNom()+" n'a que"+c.getPreferences().size()+"/"+n);
                            enCours = true;
                            break;
                        }
                    }
                    break;
                default:
                    System.out.println("Option invalide. Réessayez.");
            }
        }

    }

    
    public void afficherMenuAffectation(int colonieIndex, int ressourceIndex) throws Exception
    {
        Colonie colonie = expedition.getColonie(colonieIndex);
        Ressource ressources = expedition.getRessource(ressourceIndex);

        expedition.algoBestPerfSUR(100,colonieIndex,ressources);

        System.out.println("Gestion des affectations de ressources :");
        System.out.println(colonie.toString());
        System.out.println("Le cout est "+colonie.cout());

        boolean enCours = true;
        while (enCours) {
            System.out.println();
            System.out.println("1) Échanger les ressources de deux colons");
            System.out.println("2) Afficher le nombre de colons jaloux");
            System.out.println("3) Fin");
            System.out.println();

            int choix;
            while (true)
            {
                System.out.print("Veuillez entrer votre choix : ");
                try
                {
                    choix = Integer.parseInt(scanner.nextLine());
                    break;
                } catch (NumberFormatException e)
                {
                    System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
                }
            }

            try {
                switch (choix)
                {
                    case 1:
                        System.out.print("Entrer les noms des deux colons à échanger : ");
                        String nom1 = scanner.nextLine();
                        String nom2 = scanner.nextLine();
                        Colon c1 = colonie.getColonObjet(nom1);
                        Colon c2 = colonie.getColonObjet(nom2);

                        colonie.echangerRessources(c1, c2);
                        colonie.afficherAffectations();
                        break;
                    case 2:
                        int cout = colonie.cout();
                        System.out.println("Nombre de colons jaloux : " + cout+"[cout]");
                        colonie.afficherAffectations();
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