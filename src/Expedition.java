import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expedition
{
    private List<Colonie> colonies;

    Expedition()
    {
        colonies = new ArrayList<Colonie>();
    }
    Expedition(List<Colonie> colonies)
    {
        this.colonies=colonies;
    }

    public int algoBestPerfNAIF(Ressource r)
    //TODO return le cout avec calculCout()
    {

        return 0;
    }

    public int algoBestPerfSUR(Ressource r)
    //TODO return le cout avec calculCout()
    {

        return 0;
    }

    public int algoBestPerfSUPER(Ressource r)
    //TODO return le cout avec calculCout()
    {

        return 0;
    }

    public int affectation(Ressource r, int fonction,int manuel)
    //TODO
    {
        return 0;
    }

    public int Importation(String path) throws IOException
    //TODO PART 2
    {
        List<String> keyClasses = Arrays.asList("Colon","ressource","deteste","preferences");
        List<String> linesOfFiles = Files.readAllLines(Paths.get(path));
        Colonie newColonie = new Colonie();

        //REGEX
        String colonRegex = "^colon(([a-z0-9]+)).$";
        String ressourceRegex = "^ressource(([a-z0-9]+)).$";
        String detesteRegex = "^deteste(([a-z0-9]+)(?:,([a-z0-9_]+))+).$";
        String preferencesRegex = "^preferences(([a-z0-9]+)(?:,([a-z0-9_]+))+).$";

        Pattern colonPatern = Pattern.compile(colonRegex);
        Pattern ressourcePatern = Pattern.compile(ressourceRegex);
        Pattern detestePatern = Pattern.compile(detesteRegex);
        Pattern preferencesPatern = Pattern.compile(preferencesRegex);

        for(int i=0;i<linesOfFiles.size();i++)
        {
            // Colon Case
            if(linesOfFiles.get(i).startsWith(keyClasses.get(0)))
            {
                Matcher matcher = colonPatern.matcher(linesOfFiles.get(i));
                //if(matcher.find())
                  //  newColonie.ajouterColon(new Colon(matcher.group(1)));
            }

            // Ressource Case
            else if (linesOfFiles.get(i).startsWith(keyClasses.get(1)))
            {
                Matcher matcher = ressourcePatern.matcher(linesOfFiles.get(i));
            }
            else if (linesOfFiles.get(i).startsWith(keyClasses.get(2)))
            {
                Matcher matcher = detestePatern.matcher(linesOfFiles.get(i));
            }
            else if (linesOfFiles.get(i).startsWith(keyClasses.get(3)))
            {
                Matcher matcher = preferencesPatern.matcher(linesOfFiles.get(i));
            }
        }

        return 0;
    }

    public int save(String nomFichier)
    //TODO PART2
    {
        return 0;
    }

    public String HashResult(Colonie coco)
    {
        StringBuffer hashResult = new StringBuffer();


        return hashResult.toString();
    }
}
