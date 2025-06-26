package game.vraag;

import game.Speler;
import game.kamer.Kamer;

import java.util.Scanner;

public class MeerkeuzeVraag implements Vraag {
    private final String vraagtekst;
    private final String[] opties;
    private final String juistAntwoord;
    private final Scanner scanner;
    private final VraagInteractieHandler interactieHandler = new VraagInteractieHandler();

    public MeerkeuzeVraag(String vraagtekst, String[] opties, String juistAntwoord) {
        this.vraagtekst = vraagtekst;
        this.opties = opties;
        this.juistAntwoord = juistAntwoord.toLowerCase();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean stelVraag(Speler speler, Kamer kamer) {
        toonVraag();

        while (true) {
            System.out.print("> ");
            String antwoord = scanner.nextLine().trim().toLowerCase();

            if (interactieHandler.verwerkInput(antwoord, speler, kamer)) continue;
            return antwoord.equals(juistAntwoord);
        }
    }

    private void toonVraag() {
        System.out.println(vraagtekst);
        for (String optie : opties) {
            System.out.println(optie);
        }
    }
}
