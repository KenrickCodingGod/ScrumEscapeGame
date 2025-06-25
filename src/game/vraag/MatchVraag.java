package game.vraag;

import game.command.*;
import game.Speler;
import game.assistent.*;
import game.joker.KeyJoker;
import game.kamer.Kamer;
import game.voorwerp.Readable;

import java.util.Scanner;

public class MatchVraag implements Vraag {
    private final String vraagtekst;
    private final String[] links;
    private final String[] rechts;
    private final String juistAntwoord;
    private final Scanner scanner;

    public MatchVraag(String vraagtekst, String[] links, String[] rechts, String juistAntwoord) {
        this.vraagtekst = vraagtekst;
        this.links = links;
        this.rechts = rechts;
        this.juistAntwoord = juistAntwoord.toLowerCase();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public boolean stelVraag(Speler speler, Kamer kamer) {
        int kamerNummer = kamer.getKamerNummer();
        toonVraag();

        while (true) {
            String antwoord = leesInput();

            switch (antwoord) {
                case "gebruik assistent" -> {
                    verwerkAssistent(kamerNummer);
                    toonPrompt();
                }
                case "keyjoker" -> {
                    if (verwerkKeyJoker(speler, kamerNummer)) return true;
                    toonPrompt();
                }
                case "hintjoker" -> {
                    verwerkHintJoker(speler, kamerNummer);
                    toonPrompt();
                }
                case "gebruik boek" -> {
                    Readable boek = kamer.getBoek();
                    if (boek != null) {
                        System.out.println("📖 " + boek.showMessage());
                    } else {
                        System.out.println("❌ Er is geen boek in deze kamer.");
                    }
                    toonPrompt();
                }
                default -> {
                    return antwoord.equals(juistAntwoord);
                }
            }
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

        System.out.print("> ");
    }


    private String leesInput() {
        return scanner.nextLine().trim().toLowerCase();
    }

    private void toonPrompt() {
        System.out.print("> ");
    }

    private void verwerkAssistent(int kamerNummer) {
        if (kamerNummer == 0 || kamerNummer == 2) {
            Assistent assistent = new Assistent(
                    new HintAssistent(kamerNummer),
                    new StappenplanHulpmiddel(),
                    new Motivator()
            );
            assistent.activeer();
        } else {
            System.out.println("❌ Assistent is niet beschikbaar in deze kamer.");
        }
    }

    private boolean verwerkKeyJoker(Speler speler, int kamerNummer) {
        if (!speler.heeftJoker() || !(speler.getGekozenJoker() instanceof KeyJoker keyJoker)) {
            System.out.println("❌ Je hebt niet de KeyJoker gekozen.");
            return false;
        }

        if (keyJoker.magGebruikenInKamer(kamerNummer)) {
            keyJoker.gebruik();
            System.out.println("🔑 Je hebt de KeyJoker gebruikt! De kamer wordt overgeslagen.");
            return true;
        } else {
            System.out.println("❌ De KeyJoker mag alleen in kamer 2 of 4 worden gebruikt en slechts één keer.");
            return false;
        }
    }

    private void verwerkHintJoker(Speler speler, int kamerNummer) {
        if (speler.heeftJoker()) {
            CommandUitvoerder.voerUit(new GebruikJokerCommand(speler, kamerNummer));
        } else {
            System.out.println("❌ Je hebt geen hintjoker of je hebt hem al gebruikt.");
        }
    }
}
