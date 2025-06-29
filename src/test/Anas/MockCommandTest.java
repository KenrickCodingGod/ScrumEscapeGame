package test.Anas;

import game.Speler;
import game.command.CommandUitvoerder;
import game.command.speler.GebruikJokerCommand;
import game.command.speler.KiesJokerCommand;
import game.joker.Joker;
import game.kamer.Kamer;
import game.kamer.NormaleKamer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MockCommandTest {




    static class MockJoker implements Joker {
        boolean gebruikt = false;

        @Override
        public boolean gebruik(Speler speler, Kamer kamer) {
            gebruikt = true;
            return true;
        }

        @Override
        public String getNaam() {
            return "MockJoker";
        }
    }


    static class MockSpeler extends Speler {
        boolean gebruikJokerAangeroepen = false;
        boolean kiesJokerAangeroepen = false;
        Joker gekozenJokerRef;

        @Override
        public boolean gebruikJoker(Kamer kamer) {
            gebruikJokerAangeroepen = true;
            return super.gebruikJoker(kamer);
        }

        @Override
        public void kiesJoker(Joker joker) {
            kiesJokerAangeroepen = true;
            gekozenJokerRef = joker;
            super.kiesJoker(joker);
        }
    }

    @Test
    void testGebruikJokerCommandMetMock() {
        MockSpeler speler = new MockSpeler();
        Joker joker = new MockJoker();
        speler.kiesJoker(joker);

        Kamer kamer = new NormaleKamer(0,null,null,null,null,null,null,null,false,false);
        CommandUitvoerder.voerUit(new GebruikJokerCommand(speler, kamer));

        assertTrue(speler.gebruikJokerAangeroepen, "Joker is niet gebruikt");
        assertTrue(((MockJoker) joker).gebruikt, "Joker zelf is niet geactiveerd");
        assertTrue(speler.jokerAlGebruikt(), "Speler status moet jokerGebruikt zijn");
    }

    @Test
    void testKiesJokerCommandMetMock() {
        MockSpeler speler = new MockSpeler();
        Joker joker = new MockJoker();
        CommandUitvoerder.voerUit(new KiesJokerCommand(speler, joker));

        assertTrue(speler.kiesJokerAangeroepen, "kiesJoker is niet aangeroepen");
        assertEquals(joker, speler.getGekozenJoker(), "Gekozen joker komt niet overeen");
    }
}
