import org.junit.jupiter.api.*;

public class RessourceTest
{
    private Ressource ressource;
    @Nested
    class basicMethodsTest
    {
        @Test
        void TestaddRessourcegetRessource()
        {
            ressource = new Ressource();
            ressource.addRessource(1);
            ressource.addRessource(6);
            ressource.addRessource(5);
            ressource.addRessource(1564);
            ressource.addRessource(11);
            ressource.addRessource(47);

            Assertions.assertEquals(47, ressource.getRessource(5));
            Assertions.assertEquals(6, ressource.getRessource(1));
            Assertions.assertEquals(1564, ressource.getRessource(3));
            Assertions.assertEquals(1, ressource.getRessource(0));
            Assertions.assertEquals(11, ressource.getRessource(4));
            Assertions.assertEquals(5, ressource.getRessource(2));
        }

        @Test
        void TestsizeRessources()
        {
            ressource = new Ressource();
            ressource.addRessource(1);
            ressource.addRessource(6);
            ressource.addRessource(5);
            ressource.addRessource(1564);
            ressource.addRessource(11);
            ressource.addRessource(47);

            Assertions.assertEquals(6, ressource.size());
        }

        @Test
        void TestremoveRessources()
        {
            ressource = new Ressource();
            ressource.addRessource(1);
            ressource.addRessource(6);
            ressource.addRessource(5);
            ressource.addRessource(1564);
            ressource.addRessource(11);
            ressource.addRessource(47);

            ressource.removeRessource(3);
            ressource.addRessource(58);
            Assertions.assertEquals(1564, ressource.getRessource(3));
            Assertions.assertEquals(58, ressource.getRessource(ressource.size() - 1));
        }
    }

    @Nested
    class TestDepileRepile
    {
        @Test
        void TestdeplieRessources()
        {
            ressource = new Ressource();
            ressource.addRessource(1);
            ressource.addRessource(6);
            ressource.addRessource(5);
            ressource.addRessource(1564);
            ressource.addRessource(11);
            ressource.addRessource(47);

            while (ressource.Depile()!=0);
            Assertions.assertEquals(0,ressource.size());
        }

        @Test
        void TestrepileRessources()
        {
            ressource = new Ressource();
            ressource.addRessource(1);
            ressource.addRessource(6);
            ressource.addRessource(5);
            ressource.addRessource(1564);
            ressource.addRessource(11);
            ressource.addRessource(47);
            while (ressource.Depile()!=0);

            while (ressource.Repile()!=0);
            Assertions.assertEquals(6,ressource.size());
        }
    }

}
