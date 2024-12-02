package Conquest.Struct;//TODO change type to generic type ou juste String
import java.util.Objects;
import java.util.Stack;

public class Ressource
{

    private Stack<String> PileBase;
    private Stack<String> PileAux;

    public Ressource()
    {
        PileBase = new Stack<String>();
        PileAux = new Stack<String>();
    }


    public int addRessource(String n)
    {
        PileBase.push(n);
        return 0;
    }

    public int removeRessource(String n)
    {
        for(int i=0;i<PileBase.size();i++)
        {
            if (Objects.equals(PileBase.get(i), n))
            {
                PileBase.remove(i);
            }
        }
        return 0;
    }

    public String Depile()
    {
        if(PileBase.isEmpty())
            return null;
        String n = PileBase.pop();
        PileAux.push(n);

        return n;
    }

    public String Repile()
    {
        if(PileAux.isEmpty())
            return null;
        String n = PileAux.pop();
        PileBase.push(n);

        return n;
    }

    public String getRessource(int n)
    //Get value from index(Parser)
    {
        return PileBase.get(n);
    }

    public boolean contains(String n)
    {
        return PileBase.contains(n);
    }
    public int size()
    {
        return PileBase.size();
    }


}

