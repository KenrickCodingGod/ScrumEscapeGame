package test.Semih;

import game.Speler;
import game.vraag.InvulVraag;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class StubTestInvulvraag  {

    @Test
    void testStelVraagMetStubAntwoordJuist() {
        // Stub input (alsof gebruiker 'scrum' invult)
        String stubInput = "scrum\n";
        Scanner stubScanner = new Scanner(new ByteArrayInputStream(stubInput.getBytes()));

        InvulVraag vraag = new InvulVraag("Wat is het raamwerk/werkwijze dat we gebruiken?", "scrum", stubScanner);
        boolean resultaat = vraag.stelVraag(new Speler()); // je mag ook null meegeven als speler niets doet

        assertTrue(resultaat, "De vraag zou als correct beantwoord moeten worden");
    }

    @Test
    void testStelVraagMetStubAntwoordFout() {
        String stubInput = "ditiseenfout\n";
        Scanner stubScanner = new Scanner(new ByteArrayInputStream(stubInput.getBytes()));

        InvulVraag vraag = new InvulVraag("Wat is het raamwerk/werkwijze dat we gebruiken?", "scrum", stubScanner);
        boolean resultaat = vraag.stelVraag(new Speler());

        assertFalse(resultaat, "De vraag zou fout beantwoord moeten worden");
    }
}
