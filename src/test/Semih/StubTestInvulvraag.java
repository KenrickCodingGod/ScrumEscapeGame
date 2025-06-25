package test.Semih;

import game.Speler;
import game.voorwerp.Voorwerp;
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
        Kamer dummyKamer = new Kamer(0, "Dummy", vraag, (Voorwerp) null);

        boolean resultaat = vraag.stelVraag(speler, dummyKamer);

        assertTrue(resultaat, "De vraag zou als correct beantwoord moeten worden");
    }


    @Test
    void testStelVraagMetStubAntwoordFout() {
        String stubInput = "ditiseenfout\n";
        Scanner stubScanner = new Scanner(new ByteArrayInputStream(stubInput.getBytes()));

        InvulVraag vraag = new InvulVraag("Wat is het raamwerk/werkwijze dat we gebruiken?", "scrum", stubScanner);
        Speler speler = new Speler();
        Kamer dummyKamer = new Kamer(0, "Dummy", vraag, (Voorwerp) null);

        boolean resultaat = vraag.stelVraag(speler, dummyKamer);

        assertFalse(resultaat, "De vraag zou fout beantwoord moeten worden");
    }
}

