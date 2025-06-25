package test.Semih;

import game.Speler;
import game.voorwerp.Boek;
import game.voorwerp.Zwaard;
import game.vraag.InvulVraag;
import org.junit.jupiter.api.Test;
import game.kamer.Kamer;
import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StubTestInvulvraag {

    @Test
    void testStelVraagMetStubAntwoordJuist() {
        String stubInput = "scrum\n";
        Scanner stubScanner = new Scanner(new ByteArrayInputStream(stubInput.getBytes()));

        InvulVraag vraag = new InvulVraag("Wat is het raamwerk/werkwijze dat we gebruiken?", "scrum", stubScanner);
        Speler speler = new Speler();

        Kamer dummyKamer = new Kamer(
                0,
                "Dummy",
                vraag,
                new Zwaard("TestZwaard", "Test zwaard effect"),
                new Boek("TestBoek", "Test boek boodschap")
        );

        boolean resultaat = vraag.stelVraag(speler, dummyKamer);

        assertTrue(resultaat, "De vraag zou als correct beantwoord moeten worden");
    }

    @Test
    void testStelVraagMetStubAntwoordFout() {
        String stubInput = "ditiseenfout\n";
        Scanner stubScanner = new Scanner(new ByteArrayInputStream(stubInput.getBytes()));

        InvulVraag vraag = new InvulVraag("Wat is het raamwerk/werkwijze dat we gebruiken?", "scrum", stubScanner);
        Speler speler = new Speler();

        Kamer dummyKamer = new Kamer(
                0,
                "Dummy",
                vraag,
                new Zwaard("TestZwaard", "Test zwaard effect"),
                new Boek("TestBoek", "Test boek boodschap")
        );

        boolean resultaat = vraag.stelVraag(speler, dummyKamer);
        assertFalse(resultaat, "De vraag zou fout beantwoord moeten worden");
    }
}