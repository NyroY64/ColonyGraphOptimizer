import java.util.ArrayList;
import java.util.List;

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

    public int calculCout()
    //TODO
    {

        return 0;
    }

    public int affectation(Ressource r, int fonction,int manuel)
    //TODO
    {
        return 0;
    }

    public int Importation(String path)
    //TODO PART 2
    {
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
