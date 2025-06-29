package game.vraag;

import game.Speler;
import game.kamer.Kamer;

import java.util.Scanner;

public abstract class AbstracteVraag implements Vraag {
    protected final String vraagtekst;
    protected final String juistAntwoord;
    protected final Scanner scanner;
    protected final VraagInteractieHandler interactieHandler = new VraagInteractieHandler();

    public AbstracteVraag(String vraagtekst, String juistAntwoord) {
        this(vraagtekst, juistAntwoord, new Scanner(System.in));
    }

    public AbstracteVraag(String vraagtekst, String juistAntwoord, Scanner scanner) {
        this.vraagtekst = vraagtekst;
        this.juistAntwoord = juistAntwoord.toLowerCase();
        this.scanner = scanner;
    }

    protected abstract void toonVraagSpecifiek();

    @Override
    public boolean stelVraag(Speler speler, Kamer kamer) {
        toonVraagSpecifiek();

        while (true) {
            if (speler.isKamerOvergeslagen()) {
                speler.setKamerOvergeslagen(false);
                return true;
            }

            System.out.print("> ");
            String antwoord = scanner.nextLine().trim().toLowerCase();

            if (interactieHandler.verwerkInput(antwoord, speler, kamer)) continue;

            return antwoord.equals(juistAntwoord);
        }
    }
}
