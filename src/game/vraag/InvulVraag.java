package game.vraag;

import game.Speler;
import game.kamer.Kamer;

import java.util.Scanner;

public class InvulVraag implements Vraag {
    private final String vraagtekst;
    private final String juistAntwoord;
    private final Scanner scanner;
    private final VraagInteractieHandler interactieHandler = new VraagInteractieHandler();

    public InvulVraag(String vraagtekst, String juistAntwoord) {
        this(vraagtekst, juistAntwoord, new Scanner(System.in));
    }

    public InvulVraag(String vraagtekst, String juistAntwoord, Scanner scanner) {
        this.vraagtekst = vraagtekst;
        this.juistAntwoord = juistAntwoord.toLowerCase();
        this.scanner = scanner;
    }

    @Override
    public boolean stelVraag(Speler speler, Kamer kamer) {
        toonVraag();

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


    private void toonVraag() {
        System.out.println(vraagtekst);
    }
}
