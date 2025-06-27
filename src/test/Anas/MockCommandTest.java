/*package test.Anas;

import game.Speler;
import game.command.CommandUitvoerder;
import game.command.speler.GebruikJokerCommand;
import game.command.speler.KiesJokerCommand;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MockCommandTest {

    static class MockSpeler extends Speler {
        boolean gebruikJokerAangeroepen = false;
        boolean kiesJokerAangeroepen = false;
        int gekozenKamer = -1;
        Joker gekozenJoker = null;

        @Override
        public void gebruikJoker(int kamer) {
            gebruikJokerAangeroepen = true;
            gekozenKamer = kamer;
        }

        @Override
        public void kiesJoker(Joker joker) {
            kiesJokerAangeroepen = true;
            gekozenJoker = joker;
        }
    }

    static class MockJoker implements Joker {
        public MockJoker() {
            super();
        }

        @Override
        public boolean magGebruikenInKamer(int kamerNummer) {
            return false;
        }

        @Override
        public void gebruikInKamer(int kamerNummer) {

        }

        @Override
        public String getNaam() {
            return "";
        }
    }

    @Test
    void testGebruikJokerCommandMetHandmatigeMock() {
        MockSpeler speler = new MockSpeler();
        int kamer = 4;
        CommandUitvoerder.voerUit(new GebruikJokerCommand(speler, kamer));
        assertTrue(speler.gebruikJokerAangeroepen);
        assertEquals(kamer, speler.gekozenKamer);
    }

    @Test
    void testKiesJokerCommandMetHandmatigeMock() {
        MockSpeler speler = new MockSpeler();
        MockJoker joker = new MockJoker();
        CommandUitvoerder.voerUit(new KiesJokerCommand(speler, joker));
        assertTrue(speler.kiesJokerAangeroepen);
        assertEquals(joker, speler.gekozenJoker);
    }
}*/