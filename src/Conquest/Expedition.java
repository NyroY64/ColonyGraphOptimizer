package Conquest;

import Conquest.Menus.Menu;
import Conquest.Menus.MenuLoadTXT;
import Conquest.Struct.Colon;
import Conquest.Struct.Colonie;
import Conquest.Struct.Ressource;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Classe Expedition
 *
 * Une expedition sert a gerer plusieurs Colonies et a faire des actions sur elles. Comme la creation ou la sauvegarde.
 *
 * @see Expedition#Expedition()
 * @see Expedition#Expedition(List)
 * @see Expedition#getColonie(int)
 * @see Expedition#createColonie()
 * @see Expedition#algoFavoriteFirst(int, Ressource)
 * @see Expedition#algoBestPerfSUR(int, int, Ressource)
 * @see Expedition#affectation(int)
 * @see Expedition#Importation(String)
 * @see Expedition#save(String, int)
 * @see Menu
 * @see Menu#Menu(Expedition)
 * @see Menu#getExpedition()
 * @see MenuLoadTXT#afficherMenuConfigurationLoadTXT()
 * @see Main#main(String[])
 *
 * @author Devasenaradjounayagar Damien
 *
 * @version 1.0
 *
 */
public class Expedition
{
    /**
     * Liste des Colonies
     *
     * @see Expedition#Expedition()
     * @see Expedition#Expedition(List)
     * @see Expedition#getColonie(int)
     * @see Expedition#createColonie()
     * @see Expedition#algoFavoriteFirst(int, Ressource)
     * @see Expedition#algoBestPerfSUR(int, int, Ressource)
     * @see Expedition#affectation(int)
     * @see Expedition#Importation(String)
     * @see Expedition#save(String, int)
     *
     */
    private final List<Colonie> colonies;

    /**
     * Liste des Ressources
     *
     * @see Expedition#Expedition()
     * @see Expedition#Expedition(List)
     * @see Expedition#createRessource()
     * @see Expedition#algoFavoriteFirst(int, Ressource)
     * @see Expedition#algoBestPerfSUR(int, int, Ressource)
     * @see Expedition#affectation(int)
     * @see Expedition#Importation(String)
     *
     */
    private final List<Ressource> ressources;

    /**
     * Constructeur d'Expedition.
     *
     * @see Main#main(String[])
     *
     */
    Expedition()
    {
        colonies = new ArrayList<Colonie>();
        ressources = new ArrayList<>();
    }

    /**
     * Constructeur d'Expedition via une liste de Colonie.
     *
     * @param colonies
     * Liste de Colonie.
     *
     */
    Expedition(List<Colonie> colonies)
    {
        this.colonies=colonies;
        ressources = new ArrayList<>();
    }

    /**
     * Retourne une colonie a l'index donné.
     *
     * @param index
     * Indexe de la colonie.
     *
     * @return
     * Retourne une colonie a l'index donné.
     *
     * @see Menu#afficherMenuCreationColonie()
     * @see Menu#afficherMenuConfiguration(int)
     * @see Menu#afficherMenuAffectation(int, int)
     * @see Main#main(String[])
     *
     */
    public Colonie getColonie(int index)
    {
        return colonies.get(index);
    }
    /**
     * Cree et ajoute une nouvelle Colonie a l'expedition.
     *
     * @return
     * L'indice la Colonie.
     *
     * @see Menu#afficherMenuCreationColonie()
     *
     */
    public int createColonie()
    {
        Colonie colonie = new Colonie();
        colonies.add(colonie);
        return colonies.size()-1;
    }

    /**
     * Cree et ajoute une nouvelle Ressource a l'expedition.
     *
     * @return
     * L'indice la Ressource.
     *
     * @see Menu#afficherMenuCreationRessources()
     *
     */
    public int createRessource()
    //TODO unitest
    {
        Ressource ressource = new Ressource();
        ressources.add(ressource);
        return ressources.size()-1;
    }

    /**
     * Retourne les ressources associées a l'index donné.
     *
     * @param index
     * Index des Ressources.
     *
     * @return
     * Retourne les Ressources.
     *
     * @see Main#main(String[])
     * @see Menu#afficherMenuCreationRessources()
     * @see Menu#afficherMenuAffectation(int, int)
     *
     */
    public Ressource getRessource(int index)
    //TODO unitest
    {
        return ressources.get(index);
    }

    /**
     * Algorithme d'affectation qui donne dans un ordre stricte les ressources favorites de chaque colon.
     * Algorithe lineaire.
     *
     * @param colonieN
     * Index de la colonie.
     *
     * @param r
     * Ressources a affecter.
     *
     * @return
     * Le coute de l'affectation.
     *
     * @throws Exception
     * Nombre de colonies different du nombrede ressources.
     *
     * @see Expedition#algoBestPerfSUPER(Ressource)
     *
     */
    public int algoFavoriteFirst(int colonieN, Ressource r) throws Exception
    {
        Colonie colonie = colonies.get(colonieN);
        if (colonie.getColons().size() != r.size())
        {
            throw new Exception("Not enough colonies for the ressources");
        }
        for(Colon colon : colonie.getColons())
        {
            for(int i=0;i<colon.getPreferences().size();i++)
            {
                if(r.contains(colon.getPreferenceAT(i)))
                {
                    colon.affectationRessource(colon.getPreferenceAT(i));
                    r.removeRessource(colon.getPreferenceAT(i));
                    break;
                }
            }
        }

        while(r.Depile()!=null);
        while(r.Repile()!=null);
        return colonie.cout();
    }

    /**
     * Algorithme d'affectation naif, il utilise le resultat d'une affectation par preference et fait des substitution d'objet pour optimiser le cout.
     * Cet algorithme ne garanti pas un meilleur cout mais il essaie avec un nombre de tentative donné.
     *
     * @param maxTentatives
     * Nombre de tentatives.
     *
     * @param colonieN
     * Index de la colonie.
     *
     * @param r
     * Ressrouces a affecter.
     *
     * @return
     * retourne le cout de l'affectation.
     *
     * @throws Exception
     * Erreur lors de l'echange ou le calcul du cout.
     *
     * @see Expedition#affectation(int)
     * @see Conquest.Menus.Menu#afficherMenuAffectation(int, int)
     *
     */
    public int algoBestPerfSUR(int maxTentatives,int colonieN, Ressource r) throws Exception
    {
        //TODO Debug

        Colonie colonie = colonies.get(colonieN);
        int cout1=Integer.MAX_VALUE;
        try
        {
            algoFavoriteFirst(colonieN, r);
        }catch (Exception e)
        {
            System.out.println(e.getMessage());
            return 1;
        }
        int cout2;
        int i=0;

        while(i<maxTentatives && cout1!=0)
        {
            Random generator = new Random();
            int randomIndex1 = generator.nextInt(colonie.getColons().size());
            int randomIndex2;
            do {
                randomIndex2 = generator.nextInt(colonie.getColons().size());
            } while (randomIndex2 == randomIndex1);

            Colon colon1 = colonie.getColons().get(randomIndex1);
            Colon colon2 = colonie.getColons().get(randomIndex2);

            colonie.echangerRessources(colon1, colon2);
            cout2=colonie.cout();

            if(cout1<cout2) //Cancel trade
            {
                colonie.echangerRessources(colon2,colon1);
            }
            cout1=cout2;
            i++;
        }

        return colonie.cout();
    }


    public int algoBestPerfSUPER(Ressource r)
    //TODO return le cout avec calculCout()
    {
        return 0;
    }

    /**
     * Affecte les ressources aux colons d'une colonie avec le meilleur algorithme disponible.
     *
     * @param colonieIndex
     * Index de la colonie.
     *
     * @return
     * retourne le cout de l'affectation.
     *
     * @throws Exception
     * Erreures liés a l'affectation.
     *
     * @see MenuLoadTXT#afficherMenuConfigurationLoadTXT()
     *
     */
    public int affectation(int colonieIndex) throws Exception
    // simuler le partage des ressources entre colons
    {
        Colonie colonie = colonies.get(colonieIndex);
        Ressource ressource = ressources.get(colonieIndex);
        int maxTentatives=100;
        //Algo usage
        return algoBestPerfSUR(maxTentatives, colonieIndex, ressource);
    }

    /**
     * Importe une colonie depuis un fichier et l'ajoute a l'expedition.
     *
     * @param path
     * Chemin du fichier
     *
     * @return
     * 0 si c'est un success.
     *
     * @throws Exception
     * Erreur de creation.
     *
     * @see Main#main(String[])
     *
     */
    public int Importation(String path) throws Exception
    {
        List<String> keyClasses = Arrays.asList("colon","ressource","deteste","preferences");
        List<String> linesOfFiles = Files.readAllLines(Paths.get(path));
        Colonie newColonie = new Colonie();
        Ressource newRessource = new Ressource();

        //REGEX
        String colonRegex = "^colon\\(([a-z0-9_]+)\\).$";
        String ressourceRegex = "^ressource\\(([a-z0-9_]+)\\).$";
        String detesteRegex = "^deteste\\(([a-z0-9_]+)(?:,([a-z0-9_]+))+\\).$";
        String preferencesRegex = "^preferences\\(([a-z0-9_]+),([a-z0-9_,]+)\\)\\.$";

        Pattern colonPatern = Pattern.compile(colonRegex);
        Pattern ressourcePatern = Pattern.compile(ressourceRegex);
        Pattern detestePatern = Pattern.compile(detesteRegex);
        Pattern preferencesPatern = Pattern.compile(preferencesRegex);

        for(int i=0;i<linesOfFiles.size();i++)
        {
            // Colon Case
            String line = linesOfFiles.get(i);
            String keytest = keyClasses.get(0);
            Boolean t = linesOfFiles.get(i).startsWith(keyClasses.get(0));

            if(linesOfFiles.get(i).startsWith(keyClasses.get(0)))
            {
                Matcher matcher = colonPatern.matcher(linesOfFiles.get(i));
                if(matcher.find())
                    newColonie.ajouterColon(new Colon(matcher.group(1)));
            }

            // Ressource Case
            else if (linesOfFiles.get(i).startsWith(keyClasses.get(1)))
            {
                Matcher matcher = ressourcePatern.matcher(linesOfFiles.get(i));
                if(matcher.find())
                    newRessource.addRessource(matcher.group(1));
            }

            // Hate Case
            else if (linesOfFiles.get(i).startsWith(keyClasses.get(2)))
            {
                Matcher matcher = detestePatern.matcher(linesOfFiles.get(i));
                if(matcher.find())
                {
                    Colon colonDeBase = newColonie.getColonObjet(matcher.group(1));
                    Colon colonHated = newColonie.getColonObjet(matcher.group(2));
                    colonDeBase.addRelation(colonHated);
                }
            }

            //Preferences Case //TODO FIX
            else if (linesOfFiles.get(i).startsWith(keyClasses.get(3)))
            {
                Matcher matcher = preferencesPatern.matcher(linesOfFiles.get(i));
                if (matcher.find())
                {
                    Colon colon = newColonie.getColonObjet(matcher.group(1));
                    String preferencesList = matcher.group(2);
                    String[] preferences = preferencesList.split(",");
                    for (String preference : preferences)
                    {
                        colon.addPreference(preference.trim());
                    }
                }
            }
        }

        colonies.add(newColonie);
        ressources.add(newRessource);
        return 0;
    }

    /**
     * Savuegarde une solution d'une colonie donnée de l'expedition dans le dossier SolutionSave avec le nom donné.
     *
     * @param nomFichier
     * Nom du fichier sauvegarde.
     *
     * @param colonieIndex
     * Index de la colonie a sauvegarder.
     *
     * @return
     * Retourne le chemin du fichier sauvegardé.
     *
     * @see MenuLoadTXT#afficherMenuConfigurationLoadTXT()
     *
     */
    public String save(String nomFichier, int colonieIndex)
    {
        // Folder
        String folderName = "SolutionSaves";
        File folder = new File(folderName);

        // Ensure the folder exists
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                System.out.println("Failed to create directory: " + folderName);
            }
        }

        // Folder + filename
        File saveFile = new File(folderName + File.separator + nomFichier);

        // Create File
        try {
            if (saveFile.createNewFile()) {
                System.out.println("File created: " + saveFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while creating the file.");
        }

        // Write to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile))) {
            for (Colon colon : colonies.get(colonieIndex).getColons()) {
                writer.write(colon.getNom() + ":" + colon.getRessource());
                writer.newLine();
            }
            writer.write("\nCout: " + colonies.get(colonieIndex).cout());
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return saveFile.getAbsolutePath();
    }

}
