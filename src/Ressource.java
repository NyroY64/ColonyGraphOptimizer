
import java.util.Stack;

public class Ressource
{

    private Stack<Integer> PileBase;
    private Stack<Integer> PileAux;

    public Ressource()
    {
        PileBase = new Stack<Integer>();
        PileAux = new Stack<Integer>();
    }


    public int addRessource(int n)
    //0 is reserved value ! and no duplicates
    {
        PileBase.push(n);
        return 0;
    }

    public int removeRessource(int n)
    {
        for(int i=0;i<PileBase.size();i++)
        {
            if (PileBase.get(i)==n)
            {
                PileBase.remove(i);
            }
        }
        return 0;
    }

    public int Depile()
    {
        if(PileBase.isEmpty())
            return 0;
        int n = PileBase.pop();
        PileAux.push(n);

        return n;
    }

    public int Repile()
    {
        if(PileAux.isEmpty())
            return 0;
        int n = PileAux.pop();
        PileBase.push(n);

        return n;
    }

    public int getRessource(int n)
    //Get value from index(Parser)
    {
        return PileBase.get(n);
    }

    public int size()
    {
        return PileBase.size();
    }


}

