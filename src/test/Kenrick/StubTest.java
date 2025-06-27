package test.Kenrick;

import game.kamer.NormaleKamer;
import game.vraag.Vraag;
import game.voorwerp.Zwaard;

import game.voorwerp.Boek;
import game.Monster;
import game.Speler;

public class StubTest {


    static class StubVraag implements Vraag {
        @Override
        public boolean stelVraag(Speler speler, game.kamer.Kamer kamer) {
            return true;
        }
    }

    public static void main(String[] args) {
        NormaleKamer kamer = new NormaleKamer(

                1,
                "Stub Kamer",
                new StubVraag(),
                new Zwaard("Testzwaard", "Stub!"),
                new Boek("Testboek", "Stubboek"),
                "stub hint",
                new Monster("StubMonster", "Doet niks")
        );

        Speler speler = new Speler();
        boolean resultaat = kamer.voerUit(speler);



        if (resultaat) {
            System.out.println("✅ Stub test geslaagd: voerUit gaf true.");
        } else {
            System.out.println("❌ Stub test gefaald: voerUit gaf false.");
        }
    }
}
