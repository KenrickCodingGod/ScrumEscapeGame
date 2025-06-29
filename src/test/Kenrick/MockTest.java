package test.Kenrick;

import game.kamer.Kamer;
import game.kamer.NormaleKamer;
import game.vraag.Vraag;
import game.voorwerp.Zwaard;
import game.voorwerp.Boek;
import game.Monster;
import game.Speler;

import java.util.List;

public class MockTest {

    static class MockVraag implements Vraag {
        boolean isAangeroepen = false;

        @Override
        public boolean stelVraag(Speler speler, Kamer kamer) {
            isAangeroepen = true;
            return false;
        }

        public boolean isOpgeroepen() {
            return isAangeroepen;
        }
    }

    public static void main(String[] args) {
        MockVraag mock = new MockVraag();

        NormaleKamer kamer = new NormaleKamer(
                "Mock Kamer",
                mock,
                new Zwaard("MockZwaard", "🗡️"),
                new Boek("MockBoek", "📘"),
                "mock hint",
                new Monster("MockMonster", "test"),
                "hintJokerTest",
                false,
                false
        );

        Speler speler = new Speler();
        speler.setHuidigeKamer(kamer);

        List<Kamer> kamers = List.of(kamer);

        kamer.voerUit(speler, kamers);

        if (mock.isOpgeroepen()) {
            System.out.println("✅ Mock test geslaagd: stelVraag werd aangeroepen.");
        } else {
            System.out.println("❌ Mock test gefaald: stelVraag werd NIET aangeroepen.");
        }
    }
}
