import org.junit.jupiter.api.*;

public class ColonTest
{
    private Colon colon;

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
