import org.junit.jupiter.api.*;

import java.util.LinkedHashSet;

public class ColonTest
{
    private Colon colon;

    @Test
    void TestconstructColon()
    {
        String colonName = "Ming";
        LinkedHashSet<Integer> pref = new LinkedHashSet<>();
        pref.add(1);
        pref.add(3);
        pref.add(2);
        pref.add(4);
        pref.add(5);
        pref.add(7);
        pref.add(6);

        Colon colon = new Colon(colonName);
        Assertions.assertEquals(colonName,colon.getNom());

        Colon colon2 = new Colon(colonName,pref);
        Assertions.assertEquals(pref.size(), colon2.getPreferences().size());

        int i = 0;
        for(Integer prefValue : pref)
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

            colon.addPreference(345);
            colon.addPreference(227);
            colon.addPreference(667);

            Assertions.assertEquals(345,colon.getPreferenceAT(0));
            Assertions.assertEquals(227,colon.getPreferenceAT(1));
            Assertions.assertEquals(667,colon.getPreferenceAT(2));
        }
        @Test
        void TestgetPreferencesColon()
        {
            String colonName = "QING";
            colon = new Colon(colonName);

            colon.addPreference(345);
            colon.addPreference(227);
            colon.addPreference(667);

            Assertions.assertEquals(3,colon.getPreferences().size());
        }
    }
     @Test
        void TestaffectationANDGetRessource()
        {
            colon = new Colon("HAN");
            colon.affectationRessource(420);

            Assertions.assertEquals(420,colon.getRessource());
        }
}
