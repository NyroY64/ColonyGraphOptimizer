import Exceptions.ColonInexistantException;
import Exceptions.EchangeAvecSoiMemeException;
import Exceptions.PreferencesIncompletesException;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class ColonieTest
{
    @Test
    void TestconstructANDgetColonsColonie() throws Exception
    {
        Colon colon1 = new Colon("QING", new LinkedHashSet<Integer>(List.of(1,2,3,4,5,6,7)));
        Colon colon2 = new Colon("YAYOI", new LinkedHashSet<Integer>(List.of(3,2,1,4,5,6,7)));
        Colon colon3 = new Colon("FRANCAIS", new LinkedHashSet<Integer>(List.of(6,2,3,4,5,1,7)));
        Colon colon4 = new Colon("KLINGON", new LinkedHashSet<Integer>(List.of(3,2,7,4,5,6,1)));
        Colon colon5 = new Colon("MAGE", new LinkedHashSet<Integer>(List.of(2,7,4,3,5,6,1)));
        Colon colon6 = new Colon("", new LinkedHashSet<Integer>(List.of(1,2,3,4,7,6,5)));
        Colon colon7 = new Colon("Larry", new LinkedHashSet<Integer>(List.of(4,2,3,6,5,1,7)));

        Colonie rx= new Colonie();
        rx.ajouterColon(colon1);
        rx.ajouterColon(colon2);
        rx.ajouterColon(colon3);
        rx.ajouterColon(colon4);
        rx.ajouterColon(colon5);
        rx.ajouterColon(colon6);
        rx.ajouterColon(colon7);

        Colonie vx= new Colonie(Arrays.asList(colon1, colon2, colon3, colon4, colon5, colon6, colon7));

        Assertions.assertEquals(rx.getColons().get(0),colon1);
        Assertions.assertEquals(rx.getColons().get(1),colon2);
        Assertions.assertEquals(rx.getColons().get(2),colon3);
        Assertions.assertEquals(rx.getColons().get(3),colon4);
        Assertions.assertEquals(rx.getColons().get(4),colon5);
        Assertions.assertEquals(rx.getColons().get(5),colon6);
        Assertions.assertEquals(rx.getColons().get(6),colon7);


        Assertions.assertEquals(vx.getColons().get(0), colon1);
        Assertions.assertEquals(vx.getColons().get(1), colon2);
        Assertions.assertEquals(vx.getColons().get(2), colon3);
        Assertions.assertEquals(vx.getColons().get(3), colon4);
        Assertions.assertEquals(vx.getColons().get(4), colon5);
        Assertions.assertEquals(vx.getColons().get(5), colon6);
        Assertions.assertEquals(vx.getColons().get(6), colon7);

    }

    @Test
    void TestAjouterColonAvecRelations() throws Exception
    {
        Colonie USA = new Colonie();
        Colon colon1 = new Colon("Colon1");
        Colon colon2 = new Colon("Colon2");
        Colon colon3 = new Colon("Colon3");
        colon2.addRelation(colon1);


        USA.ajouterColon(colon1);
        USA.ajouterColon(colon2);
        USA.ajouterColon(colon3);

        Assertions.assertTrue(colon2.getRelationsDetestables().contains(colon1));
        Assertions.assertTrue(colon1.getRelationsDetestables().contains(colon2));

        Assertions.assertTrue(colon3.getRelationsDetestables().isEmpty());
    }

    @Test
    void TestRetireColonSuccess() throws Exception
    {
        Colonie colonie = new Colonie();
        Colon colon1 = new Colon("Colon1");
        Colon colon2 = new Colon("Colon2");
        Colon colon3 = new Colon("Colon3");

        colonie.ajouterColon(colon1);
        colonie.ajouterColon(colon2);
        colonie.ajouterColon(colon3);

        colon2.addRelation(colon1);
        colon3.addRelation(colon1);

        colonie.retireColon(colon1);

        Assertions.assertFalse(colonie.getColons().contains(colon1));
        Assertions.assertFalse(colon2.getRelationsDetestables().contains(colon1));
        Assertions.assertFalse(colon3.getRelationsDetestables().contains(colon1));
    }

    @Nested
    class GetColonsMethodsText
    {
        Colon colon1 = new Colon("QING", new LinkedHashSet<Integer>(List.of(1,2,3,4,5,6,7)));
        Colon colon2 = new Colon("YAYOI", new LinkedHashSet<Integer>(List.of(3,2,1,4,5,6,7)));
        Colon colon3 = new Colon("FRANCAIS", new LinkedHashSet<Integer>(List.of(6,2,3,4,5,1,7)));
        Colon colon4 = new Colon("KLINGON", new LinkedHashSet<Integer>(List.of(3,2,7,4,5,6,1)));
        Colon colon5 = new Colon("MAGE", new LinkedHashSet<Integer>(List.of(2,7,4,3,5,6,1)));
        Colon colon6 = new Colon("", new LinkedHashSet<Integer>(List.of(1,2,3,4,7,6,5)));
        Colon colon7 = new Colon("Larry", new LinkedHashSet<Integer>(List.of(4,2,3,6,5,1,7)));

        Colonie vx= new Colonie(Arrays.asList(colon1, colon2, colon3, colon4, colon5, colon6, colon7));

        @Test
        void TestgetColonObjet() throws ColonInexistantException
        {
            Assertions.assertEquals(colon5,vx.getColonObjet("MAGE"));
        }

        @Test
        void TestgetColonName() throws ColonInexistantException
        {
            Assertions.assertEquals("Larry", vx.getColonName(colon7));
        }
    }

    @Test
    void TestToutesLesPreferencesAttribuees() throws Exception
    {
        Colonie colonie = new Colonie();

        Colon alice = new Colon("Alice");
        Colon bob = new Colon("Bob");
        Colon charlie = new Colon("Charlie");

        alice.setPreferences(new LinkedHashSet<>(Arrays.asList(1, 2, 3)));
        bob.setPreferences(new LinkedHashSet<>(Arrays.asList(4, 5)));
        charlie.setPreferences(new LinkedHashSet<>(Arrays.asList(6)));

        colonie.ajouterColon(alice);
        colonie.ajouterColon(bob);
        colonie.ajouterColon(charlie);

        Assertions.assertTrue(colonie.toutesLesPreferencesAttribuees());

        charlie.setPreferences(new LinkedHashSet<>());
        Assertions.assertFalse(colonie.toutesLesPreferencesAttribuees());
    }

    @Test
    void TestVerifierPreferencesCompletesSuccess() throws Exception
    {
        Colonie colonie = new Colonie();

        Colon alice = new Colon("Alice");
        Colon bob = new Colon("Bob");
        Colon charlie = new Colon("Charlie");

        alice.setPreferences(new LinkedHashSet<>(Arrays.asList(1, 2, 3)));
        bob.setPreferences(new LinkedHashSet<>(Arrays.asList(4, 5, 6)));
        charlie.setPreferences(new LinkedHashSet<>(Arrays.asList(7, 8, 9)));

        colonie.ajouterColon(alice);
        colonie.ajouterColon(bob);
        colonie.ajouterColon(charlie);

        int result = colonie.verifierPreferencesCompletes(3);
        Assertions.assertEquals(0, result);
    }

    @Test
    void TestVerifierPreferencesCompletesFailure() throws Exception
    {
        Colonie colonie = new Colonie();

        Colon alice = new Colon("Alice");
        Colon bob = new Colon("Bob");

        alice.setPreferences(new LinkedHashSet<>(Arrays.asList(1, 2, 3)));
        bob.setPreferences(new LinkedHashSet<>(Arrays.asList(4, 5))); // 2 préférences

        colonie.ajouterColon(alice);
        colonie.ajouterColon(bob);

        Assertions.assertThrows(PreferencesIncompletesException.class,
                () -> colonie.verifierPreferencesCompletes(3));
    }

    @Test
    void TestEchangerRessources() throws Exception {
        Colonie colonie = new Colonie();
        Colon colon1 = new Colon("Colon1", new LinkedHashSet<>(Arrays.asList(1, 2, 3)));
        Colon colon2 = new Colon("Colon2", new LinkedHashSet<>(Arrays.asList(2, 3, 1)));

        colonie.ajouterColon(colon1);
        colonie.ajouterColon(colon2);

        colon1.affectationRessource(1);
        colon2.affectationRessource(2);

        colonie.echangerRessources(colon1, colon2);

        Assertions.assertEquals(2, colon1.getRessource());
        Assertions.assertEquals(1, colon2.getRessource());
    }

    @Test
    void TestEchangerRessourcesAvecSoiMeme() throws Exception
    {
        Colonie colonie = new Colonie();
        Colon colon1 = new Colon("Colon1", new LinkedHashSet<>(Arrays.asList(1, 2, 3)));

        colonie.ajouterColon(colon1);

        Exception exception = Assertions.assertThrows(EchangeAvecSoiMemeException.class, () -> {
            colonie.echangerRessources(colon1, colon1);
        });

        Assertions.assertEquals("Erreur : un colon ne peut pas echanger d'objet avec lui-même (Colon1).", exception.getMessage());
    }

    @Test
    void TestEchangerRessourcesColonInexistant() throws Exception
    {
        Colonie colonie = new Colonie();
        Colon colon1 = new Colon("Colon1", new LinkedHashSet<>(Arrays.asList(1, 2, 3)));
        Colon colon2 = new Colon("Colon2", new LinkedHashSet<>(Arrays.asList(2, 3, 1)));

        colonie.ajouterColon(colon1);

        Exception exception = Assertions.assertThrows(ColonInexistantException.class, () -> {
            colonie.echangerRessources(colon1, colon2);
        });

        Assertions.assertEquals("Erreur : au moins un des colons n'existe pas (Colon1, Colon2).", exception.getMessage());
    }

    @Test
    void Testcout()
    {
        Colonie colonie = new Colonie();
        Colon A = new Colon("A", new LinkedHashSet<>(Arrays.asList(1, 2, 3,4)));
        Colon B = new Colon("B", new LinkedHashSet<>(Arrays.asList(1, 3, 2,4)));
        Colon C = new Colon("C", new LinkedHashSet<>(Arrays.asList(3, 2, 1,4)));
        Colon D = new Colon("D", new LinkedHashSet<>(Arrays.asList(1, 4, 2,3)));

        B.addRelation(A);
        B.addRelation(C);
        B.addRelation(D);

        B.affectationRessource(1);
        C.affectationRessource(3);
        A.affectationRessource(2);
        D.affectationRessource(4);

        Assertions.assertEquals(2,colonie.cout());
    }

    @Test
    void TestafficherRessourcesDesColons()
    {
        Colon colon1 = new Colon("QING", new LinkedHashSet<Integer>(List.of(1,2,3,4,5,6,7)));
        Colon colon2 = new Colon("YAYOI", new LinkedHashSet<Integer>(List.of(3,2,1,4,5,6,7)));
        Colon colon3 = new Colon("FRANCAIS", new LinkedHashSet<Integer>(List.of(6,2,3,4,5,1,7)));
        Colon colon4 = new Colon("KLINGON", new LinkedHashSet<Integer>(List.of(3,2,7,4,5,6,1)));
        Colon colon5 = new Colon("MAGE", new LinkedHashSet<Integer>(List.of(2,7,4,3,5,6,1)));
        Colon colon6 = new Colon("", new LinkedHashSet<Integer>(List.of(1,2,3,4,7,6,5)));
        Colon colon7 = new Colon("Larry", new LinkedHashSet<Integer>(List.of(4,2,3,6,5,1,7)));

        Colonie vx= new Colonie(Arrays.asList(colon1, colon2, colon3, colon4, colon5, colon6, colon7));

        Assertions.assertEquals("QING Got 0 ressource.\n" +
                "YAYOI Got 0 ressource.\n" +
                "FRANCAIS Got 0 ressource.\n" +
                "KLINGON Got 0 ressource.\n" +
                "MAGE Got 0 ressource.\n" +
                " Got 0 ressource.\n" +
                "Larry Got 0 ressource.\n",vx.afficherRessourcesDesColons());

        colon7.affectationRessource(9);
        colon2.affectationRessource(342);
        colon4.affectationRessource(766576576);

        Assertions.assertEquals("QING Got 0 ressource.\n" +
                "YAYOI Got 342 ressource.\n" +
                "FRANCAIS Got 0 ressource.\n" +
                "KLINGON Got 766576576 ressource.\n" +
                "MAGE Got 0 ressource.\n" +
                " Got 0 ressource.\n" +
                "Larry Got 9 ressource.\n",vx.afficherRessourcesDesColons());
    }

    @Test
    void ToStringTest() throws Exception
    {
        Colon colon1 = new Colon("QING", new LinkedHashSet<Integer>(List.of(1,2,3,4,5,6,7)));
        Colon colon2 = new Colon("YAYOI", new LinkedHashSet<Integer>(List.of(3,2,1,4,5,6,7)));
        Colon colon3 = new Colon("FRANCAIS", new LinkedHashSet<Integer>(List.of(6,2,3,4,5,1,7)));
        Colon colon4 = new Colon("KLINGON", new LinkedHashSet<Integer>(List.of(3,2,7,4,5,6,1)));
        Colon colon5 = new Colon("MAGE", new LinkedHashSet<Integer>(List.of(2,7,4,3,5,6,1)));
        Colon colon6 = new Colon("", new LinkedHashSet<Integer>(List.of(1,2,3,4,7,6,5)));
        Colon colon7 = new Colon("Larry", new LinkedHashSet<Integer>(List.of(4,2,3,6,5,1,7)));


        colon1.addRelation(colon7);
        colon1.addRelation(colon2);
        colon1.addRelation(colon3);
        List<Colon> listCoco = new ArrayList<>();
        colon1.ajouterRelations(listCoco);

        Colonie terre = new Colonie(List.of(colon1,colon2,colon3,colon4,colon5,colon6,colon7));

        try
        {
            colon1.ajouterRelations(List.of(colon7));
        }catch(Exception ignored){};

        String expected = "Colon QING (Ressource allouée : 0), Préférences : [1, 2, 3, 4, 5, 6, 7], Ennemis : {Larry, YAYOI, FRANCAIS} et n'est jaloux de personne\n" +
                "Colon YAYOI (Ressource allouée : 0), Préférences : [3, 2, 1, 4, 5, 6, 7], Ennemis : {QING} et n'est jaloux de personne\n" +
                "Colon FRANCAIS (Ressource allouée : 0), Préférences : [6, 2, 3, 4, 5, 1, 7], Ennemis : {QING} et n'est jaloux de personne\n" +
                "Colon KLINGON (Ressource allouée : 0), Préférences : [3, 2, 7, 4, 5, 6, 1], Ennemis : aucun et n'est jaloux de personne\n" +
                "Colon MAGE (Ressource allouée : 0), Préférences : [2, 7, 4, 3, 5, 6, 1], Ennemis : aucun et n'est jaloux de personne\n" +
                "Colon  (Ressource allouée : 0), Préférences : [1, 2, 3, 4, 7, 6, 5], Ennemis : aucun et n'est jaloux de personne\n" +
                "Colon Larry (Ressource allouée : 0), Préférences : [4, 2, 3, 6, 5, 1, 7], Ennemis : {QING} et n'est jaloux de personne\n";
        Assertions.assertEquals(expected,terre.toString());
    }

}
