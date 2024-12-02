package Conquest;

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

public class Expedition
{
    private final List<Colonie> colonies;
    private final List<Ressource> ressources;

    Expedition()
    {
        colonies = new ArrayList<Colonie>();
        ressources = new ArrayList<>();
    }
    Expedition(List<Colonie> colonies)
    {
        this.colonies=colonies;
        ressources = new ArrayList<>();
    }

    public Colonie getColonie(int index)
    {
        return colonies.get(index);
    }

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
                    break;
                }
            }
        }

        return colonie.cout();
    }

    public int algoBestPerfSUR(int maxTentatives,int colonieN, Ressource r) throws Exception
    {
        Colonie colonie = colonies.get(colonieN);
        int cout1 = algoFavoriteFirst(colonieN, r);
        int cout2;
        int i=0;

        while(i<maxTentatives && cout1!=0)
        {
            Random generator = new Random();
            int randomIndex1 = generator.nextInt(colonie.getColons().size());
            int randomIndex2 = generator.nextInt(colonie.getColons().size());
            Colon colon1 = colonie.getColons().get(randomIndex1);
            Colon colon2 = colonie.getColons().get(randomIndex2);

            colonie.echangerRessources(colon1, colon2);
            cout2=colonie.cout();

            if(cout1>cout2) //Cancel trade
            {
                colonie.echangerRessources(colon2,colon1);
            }
            i++;
        }
        return colonie.cout();
    }

    public int algoBestPerfSUPER(Ressource r)
    //TODO return le cout avec calculCout()
    {
        return 0;
    }

    public int affectation(int colonieIndex,Ressource r) throws Exception
    // simuler le partage des ressources entre colons
    {
        Colonie colonie = colonies.get(colonieIndex);
        int maxTentatives=100;
        //Algo usage
        return algoBestPerfSUR(maxTentatives, colonieIndex, r);
    }

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

    public int save(String nomFichier, int colonieIndex)
    {
        // Folder
        String folderName = "SolutionSaves";
        File folder = new File(folderName);

        // Ensure the folder exists
        if (!folder.exists()) {
            if (!folder.mkdir()) {
                System.out.println("Failed to create directory: " + folderName);
                return -3; // Return an error code for folder creation failure
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
            return -1;
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
            return -2;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return 0;
    }

}
