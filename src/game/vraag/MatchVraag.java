package game.vraag;

import game.Speler;
import game.kamer.Kamer;

import java.util.Scanner;

public class MatchVraag implements Vraag {
    private final String vraagtekst;
    private final String[] links;
    private final String[] rechts;
    private final String juistAntwoord;
    private final Scanner scanner;
    private final VraagInteractieHandler interactieHandler = new VraagInteractieHandler();

    public MatchVraag(String vraagtekst, String[] links, String[] rechts, String juistAntwoord) {
        this.vraagtekst = vraagtekst;
        this.links = links;
        this.rechts = rechts;
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
        System.out.println("Koppel de juiste paren, bijvoorbeeld: A2 B1 C3");

        int maxLength = 0;
        for (String link : links) {
            if (link.length() > maxLength) {
                maxLength = link.length();
            }
        }

        for (int i = 0; i < links.length; i++) {
            String linkerLabel = (char) ('A' + i) + ") " + links[i];
            String rechterLabel = (i + 1) + ") " + rechts[i];
            String spaties = " ".repeat(maxLength - links[i].length() + 4);
            System.out.println(linkerLabel + spaties + rechterLabel);
        }
    }
}
