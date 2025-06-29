package game.vraag;

import game.Speler;
import game.kamer.Kamer;

import java.util.Scanner;

public class MatchVraag extends AbstracteVraag {
    private final String[] links;
    private final String[] rechts;

    public MatchVraag(String vraagtekst, String[] links, String[] rechts, String juistAntwoord) {
        super(vraagtekst, juistAntwoord);
        this.links = links;
        this.rechts = rechts;
    }

    @Override
    protected void toonVraagSpecifiek() {
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
