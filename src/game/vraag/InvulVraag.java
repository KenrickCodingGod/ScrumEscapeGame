package game.vraag;

import game.Speler;
import game.assistent.*;
import game.command.*;
import game.joker.KeyJoker;
import game.kamer.Kamer;
import game.voorwerp.Readable;

import java.util.Scanner;

public class InvulVraag implements Vraag {
    private final String vraagtekst;
    private final String juistAntwoord;
    private final Scanner scanner;

    public InvulVraag(String vraagtekst, String juistAntwoord) {
        this(vraagtekst, juistAntwoord, new Scanner(System.in));
    }

    //Constructor voor test
    public InvulVraag(String vraagtekst, String juistAntwoord, Scanner scanner) {
        this.vraagtekst = vraagtekst;
        this.juistAntwoord = juistAntwoord.toLowerCase();
        this.scanner = scanner;
    }

    @Override
    public boolean stelVraag(Speler speler, Kamer kamer) {
        int kamerNummer = speler.getPositie();
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
        System.out.print("> ");
    }

    private void toonPrompt() {
        System.out.print("> ");
    }

    private String leesInput() {
        return scanner.nextLine().trim().toLowerCase();
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
            CommandUitvoerder.voerUit(new GebruikJokerCommand(speler, speler.getPositie()));
        } else {
            System.out.println("❌ Je hebt geen hintjoker of je hebt hem al gebruikt.");
        }
    }
}
