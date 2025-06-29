package game.vraag;

import game.Speler;
import game.kamer.Kamer;

import java.util.Scanner;

public class MeerkeuzeVraag extends AbstracteVraag {
    private final String[] opties;

    public MeerkeuzeVraag(String vraagtekst, String[] opties, String juistAntwoord) {
        super(vraagtekst, juistAntwoord);
        this.opties = opties;
    }

    @Override
    protected void toonVraagSpecifiek() {
        System.out.println(vraagtekst);
        for (String optie : opties) {
            System.out.println(optie);
        }
    }
}
