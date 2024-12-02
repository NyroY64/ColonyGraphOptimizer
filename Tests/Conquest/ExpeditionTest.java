package Conquest;

import org.junit.jupiter.api.Test;

public class ExpeditionTest
{
    @Test
    void TestImportationExpedition() throws Exception
    {
        Expedition expedition = new Expedition();
        expedition.Importation("src\\fichiertesteColon.txt");


        System.out.println(expedition.getColonie(0).toString());
    }
}
