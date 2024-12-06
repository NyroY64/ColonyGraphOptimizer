package Conquest;

import Conquest.Struct.Colon;
import org.junit.jupiter.api.*;

import java.util.*;

public class ColonTest
{
    private Colon colon;

    @Test
    void TestconstructColon()
    {
        String colonName = "Ming";
        LinkedHashSet<String> pref = new LinkedHashSet<>();
        pref.add("1");
        pref.add("3");
        pref.add("2");
        pref.add("4");
        pref.add("5");
        pref.add("7");
        pref.add("6");

        Colon colon = new Colon(colonName);
        Assertions.assertEquals(colonName,colon.getNom());

        Colon colon2 = new Colon(colonName,pref);
        Assertions.assertEquals(pref.size(), colon2.getPreferences().size());

        int i = 0;
        for(String prefValue : pref)
        {
            Assertions.assertEquals(prefValue, colon2.getPreferenceAT(i));
            i++;
        }
    }
    @Test
    void TestgetNomColon()
    {
        String colonName = "QING";
        colon = new Colon(colonName);
        Assertions.assertEquals(colonName,colon.getNom());
    }

    @Nested
    class PreferencesMethodsTest
    {
        @Test
        void TestaddAndgetPreferenceAtColon()
        {
            String colonName = "QING";
            colon = new Colon(colonName);

            colon.addPreference("345");
            colon.addPreference("227");
            colon.addPreference("667");

            Assertions.assertEquals("345",colon.getPreferenceAT(0));
            Assertions.assertEquals("227",colon.getPreferenceAT(1));
            Assertions.assertEquals("667",colon.getPreferenceAT(2));

            //

            String colonName2 = "KONG";
            colon = new Colon(colonName2);

            colon.addPreferences(new LinkedHashSet<>(Arrays.asList("345", "227", "667")));
            Assertions.assertEquals("345", colon.getPreferenceAT(0));
            Assertions.assertEquals("227", colon.getPreferenceAT(1));
            Assertions.assertEquals("667", colon.getPreferenceAT(2));


            colon = new Colon(colonName2);

            colon.setPreferences(new LinkedHashSet<>(Arrays.asList("345", "227", "667")));
            Assertions.assertEquals("345", colon.getPreferenceAT(0));
            Assertions.assertEquals("227", colon.getPreferenceAT(1));
            Assertions.assertEquals("667", colon.getPreferenceAT(2));

        }
        @Test
        void TestgetPreferencesColon()
        {
            String colonName = "QING";
            colon = new Colon(colonName);

            colon.addPreference("345");
            colon.addPreference("227");
            colon.addPreference("667");

            Assertions.assertEquals(3,colon.getPreferences().size());
        }
        @Test
        void TestgetPreferenceIndex()
        {
            String colonName = "QING";
            colon = new Colon(colonName);
            colon.addPreference("Chocolat");
            colon.addPreference("Vanille");
            colon.addPreference("Fraise");
            colon.addPreference("el mordjene");

            Assertions.assertEquals(2,colon.getPreferenceIndex("Fraise"));

        }
    }
     @Test
        void TestaffectationANDGetRessource()
        {
            colon = new Colon("HAN");
            colon.affectationRessource("420");

            Assertions.assertEquals("420",colon.getRessource());
        }

    @Nested
    class RelationsMethodsTest
    {
        Colon colon1 = new Colon("QING");
        Colon colon2 = new Colon("YAYOI");
        Colon colon3 = new Colon("FRANCAIS");
        Colon colon4 = new Colon("KLINGON");
        Colon colon5 = new Colon("MAGE");
        Colon colon6 = new Colon("");
        Colon colon7 = new Colon("Larry");

        @Test
        void TestgetANDaddPreferences() throws Exception
        {
            colon1.addRelation(colon3);
            colon1.addRelation(colon2);
            colon1.addRelation(colon5);

            colon7.addRelation(colon6);
            colon7.addRelation(colon4);

            LinkedHashSet<Colon> detestes1 = new LinkedHashSet<>();
            detestes1.add(colon3);
            detestes1.add(colon2);
            detestes1.add(colon5);
            LinkedHashSet<Colon> detestes2 = new LinkedHashSet<>();
            detestes2.add(colon6);
            detestes2.add(colon4);

            Assertions.assertTrue(colon1.getRelationsDetestables().equals(detestes1));
            Assertions.assertTrue(colon7.getRelationsDetestables().equals(detestes2));
        }
        @Test
        void TestremovePreferences() throws Exception
        {
            colon1 = new Colon("DA");
            colon1.addRelation(colon3);
            colon1.removeRelation(colon3);
            Assertions.assertTrue(colon1.getRelationsDetestables().isEmpty());
        }
        @Test
        void TestajouterRelations() throws Exception
        {
            List<Colon> listcolons = new ArrayList<Colon>();
            listcolons.add(colon6);
            listcolons.add(colon3);
            listcolons.add(colon7);
            colon1.ajouterRelations(listcolons);

            Assertions.assertTrue(colon1.getRelationsDetestables().containsAll(listcolons));

        }
    }
    @Test
    void TesttoString()
    {
        Colon colon5 = new Colon("MAGE", new LinkedHashSet<String>(List.of("2", "7", "4", "3", "5", "6", "1")));
        Assertions.assertEquals("Colon MAGE (Ressource allouée : aucune), Préférences : [2, 7, 4, 3, 5, 6, 1], Ennemis : aucun",colon5.toString());
    }
}
