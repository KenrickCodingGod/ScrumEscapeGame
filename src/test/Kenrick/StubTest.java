package test.Kenrick;

import game.kamer.Kamer;
import game.kamer.NormaleKamer;
import game.vraag.Vraag;
import game.voorwerp.Zwaard;
import game.voorwerp.Boek;
import game.Monster;
import game.Speler;

import java.util.List;

public class StubTest {

    static class StubVraag implements Vraag {
        @Override
        public boolean stelVraag(Speler speler, Kamer kamer) {
            return true;
        }
    }

    public static void main(String[] args) {
        NormaleKamer kamer = new NormaleKamer(
                "Stub Kamer",
                new StubVraag(),
                new Zwaard("Testzwaard", "Stub!"),
                new Boek("Testboek", "Stubboek"),
                "stub hint",
                new Monster("StubMonster", "Doet niks"),
                "hintJokerTest",
                false,
                false
        );

        Speler speler = new Speler();
        speler.setHuidigeKamer(kamer);

        List<Kamer> kamers = List.of(kamer);

        boolean resultaat = kamer.voerUit(speler, kamers);

        if (resultaat) {
            System.out.println("✅ Stub test geslaagd: voerUit gaf true.");
        } else {
            System.out.println("❌ Stub test gefaald: voerUit gaf false.");
        }
    }
}
