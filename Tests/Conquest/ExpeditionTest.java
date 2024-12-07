package Conquest;

import Conquest.Struct.Colonie;
import Conquest.Struct.Ressource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExpeditionTest
{

    @Test
    void TestgetColonie()
    {
        Expedition expedition = new Expedition();
        int index = expedition.createColonie();
        Colonie colonie = expedition.getColonie(index);
        Assertions.assertEquals(colonie,expedition.getColonie(index));
    }

    @Test
    void TestcreateColonieANDgetColonie()
    {
        Expedition expedition = new Expedition();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
                    expedition.getColonie(0);
                });
        int index = expedition.createColonie();
        Assertions.assertInstanceOf(Colonie.class,expedition.getColonie(index));
    }

    @Test
    void TestcreateRessourceANDgetRessource()
    {
        Expedition expedition = new Expedition();
        Assertions.assertThrows(IndexOutOfBoundsException.class, () ->
        {
            expedition.getRessource(0);
        });
        int index = expedition.createRessource();
        Assertions.assertInstanceOf(Ressource.class, expedition.getRessource(index));
    }

    @Test
    void Testaffectation() throws Exception
    {
        Expedition expedition = new Expedition();
        expedition.Importation("src\\fichiertesteColon.txt");
        Assertions.assertTrue(expedition.affectation(0)>-1);
    }

    @Test
    void TestImportationANDsaveExpedition() throws Exception
    {
        Expedition expedition = new Expedition();
        expedition.Importation("src\\fichiertesteColon.txt");

        Assertions.assertEquals
                (
                        "Colon nom_colon_1 (Ressource allouée : aucune), Préférences : [nom_ressource_1, nom_ressource_2, nom_ressource_3], Ennemis : {nom_colon_2} et n'est jaloux de personne\n" +
                                "Colon nom_colon_2 (Ressource allouée : aucune), Préférences : [nom_ressource_2, nom_ressource_1, nom_ressource_3], Ennemis : {nom_colon_1, nom_colon_3} et n'est jaloux de personne\n" +
                                "Colon nom_colon_3 (Ressource allouée : aucune), Préférences : [nom_ressource_3, nom_ressource_1, nom_ressource_2], Ennemis : {nom_colon_2} et n'est jaloux de personne\n",
                        expedition.getColonie(0).toString()
                );

        Colonie colonie =expedition.getColonie(0);

        colonie.getColons().get(0).affectationRessource("BANANA");
        colonie.getColons().get(1).affectationRessource("CHOCO");
        colonie.getColons().get(2).affectationRessource("Selecto");

        Assertions.assertTrue(expedition.save("SaveTest",0).endsWith("\\SolutionSaves\\SaveTest"));

    }
}
