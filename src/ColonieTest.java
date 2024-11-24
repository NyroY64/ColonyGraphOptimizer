import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

public class ColonieTest
{
    @Test
    void toStringTest()
    {
        Colon colon1 = new Colon("QING", new LinkedHashSet<Integer>(List.of(1,2,3,4,5,6,7)));
        Colon colon2 = new Colon("YAYOI", new LinkedHashSet<Integer>(List.of(3,2,1,4,5,6,7)));
        Colon colon3 = new Colon("FRANCAIS", new LinkedHashSet<Integer>(List.of(6,2,3,4,5,1,7)));
        Colon colon4 = new Colon("KLINGON", new LinkedHashSet<Integer>(List.of(3,2,7,4,5,6,1)));
        Colon colon5 = new Colon("MAGE", new LinkedHashSet<Integer>(List.of(2,7,4,3,5,6,1)));
        Colon colon6 = new Colon("", new LinkedHashSet<Integer>(List.of(1,2,3,4,7,6,5)));
        Colon colon7 = new Colon("Larry", new LinkedHashSet<Integer>(List.of(4,2,3,6,5,1,7)));



        Colonie terre = new Colonie(List.of(colon1,colon2,colon3,colon4,colon5,colon6,colon7));

        try
        {
            terre.ajouterRelation(colon1, new ArrayList<Colon>(List.of(colon7)));
        }catch(Exception e){};

        System.out.print(terre.toString());
    }
}
