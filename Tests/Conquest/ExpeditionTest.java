package Conquest;

import Conquest.Struct.Colonie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ExpeditionTest
{
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

        Assertions.assertEquals(0,expedition.save("SaveTest",0));

    }

    //TODO All tests
}
