package test.Mick;

import game.Monster;
import game.Speler;
import game.kamer.Kamer;
import game.kamer.NormaleKamer;
import game.observer.GameStatusObserver;
import game.vraag.Vraag;
import game.voorwerp.Boek;
import game.voorwerp.Zwaard;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StubTestGameStatusObserverTest {

    static class StubVraag implements Vraag {
        public boolean stelVraag(Speler speler, Kamer kamer) {
            return true;
        }
    }

    Kamer maakStubKamer(String naam) {
        return new NormaleKamer(
                naam,
                new StubVraag(),
                new Zwaard("Testzwaard", "Effect"),
                new Boek("Testboek", "Inhoud"),
                "Hint",
                new Monster("Stubmonster", "Beschrijving"),
                "hintJokerTest",
                false,
                false
        );
    }

    @Test
    void testGameStatusObserverMetMonster() {
        Kamer kamer1 = maakStubKamer("Sprint Planning");
        List<Kamer> kamers = List.of(kamer1);

        Speler speler = new Speler();
        speler.setHuidigeKamer(kamer1);
        speler.voegMonsterToe(new Monster("Zombie", "Doet niets"));

        GameStatusObserver observer = new GameStatusObserver(kamers);
        observer.update(speler);


        assertTrue(true);
    }

    @Test
    void testGameStatusObserverZonderMonster() {
        Kamer kamer1 = maakStubKamer("Daily Scrum");
        List<Kamer> kamers = List.of(kamer1);

        Speler speler = new Speler();
        speler.setHuidigeKamer(kamer1);

        GameStatusObserver observer = new GameStatusObserver(kamers);
        observer.update(speler);

        // Wederom alleen procesverificatie
        assertTrue(true);
    }
}
