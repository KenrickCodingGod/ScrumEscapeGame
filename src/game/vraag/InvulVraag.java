package game.vraag;

import game.Speler;
import game.kamer.Kamer;

import java.util.Scanner;

public class InvulVraag extends AbstracteVraag {

    public InvulVraag(String vraagtekst, String juistAntwoord) {
        super(vraagtekst, juistAntwoord);
    }

    public InvulVraag(String vraagtekst, String juistAntwoord, Scanner scanner) {
        super(vraagtekst, juistAntwoord, scanner);
    }

    @Override
    protected void toonVraagSpecifiek() {
        System.out.println(vraagtekst);
    }
}
