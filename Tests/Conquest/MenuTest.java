package Conquest;

import Conquest.Struct.Colon;
import Conquest.Struct.Colonie;
import Conquest.Struct.Ressource;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class MenuTest
{
    @Test
    void tester() throws Exception
    {
        Expedition expedition = new Expedition();
        Colonie colonie = expedition.getColonie(expedition.createColonie());
        Colon A = new Colon("A",new LinkedHashSet<String>(List.of("1", "2", "3", "4")));
        Colon B = new Colon("B",new LinkedHashSet<String>(List.of("1", "3", "2", "4")));
        Colon C = new Colon("C",new LinkedHashSet<String>(List.of("3", "2", "1", "4")));
        Colon D = new Colon("D",new LinkedHashSet<String>(List.of("1", "4", "2", "3")));

        List<Colon> listcolons = new ArrayList<Colon>();
        listcolons.add(A);
        listcolons.add(C);
        listcolons.add(D);
        B.ajouterRelations(listcolons);

        colonie.ajouterColon(A);
        colonie.ajouterColon(B);
        colonie.ajouterColon(C);
        colonie.ajouterColon(D);

        Ressource ressource = new Ressource();
        ressource.addRessource("1");
        ressource.addRessource("2");
        ressource.addRessource("3");
        ressource.addRessource("4");

        //System.out.println(colonie);
        System.out.println("cout: "+expedition.algoBestPerfSUR(100,0,ressource));

        //System.out.println(colonie);
        System.out.println("cout: "+expedition.algoBestPerfSUR(100,0,ressource));

        //System.out.println(colonie);
        System.out.println("cout: "+expedition.algoBestPerfSUR(100,0,ressource));

        //System.out.println(colonie);
        System.out.println("cout: "+expedition.algoBestPerfSUR(100,0,ressource));

        //System.out.println(colonie);
        System.out.println("cout: "+expedition.algoBestPerfSUR(100,0,ressource));

        //System.out.println(colonie);
        System.out.println("cout: "+expedition.algoBestPerfSUR(100,0,ressource));

        //System.out.println(colonie);
        System.out.println("cout: "+expedition.algoBestPerfSUR(100,0,ressource));

        //System.out.println(colonie);
        System.out.println("cout: "+expedition.algoBestPerfSUR(100,0,ressource));

    }

}
