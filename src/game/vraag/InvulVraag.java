package game.vraag;

import game.Speler;
import game.assistent.*;
import game.joker.KeyJoker;

import java.util.Scanner;

public class InvulVraag implements Vraag {
    private String vraagtekst;
    private String juistAntwoord;
    private Scanner scanner;

    public InvulVraag(String vraagtekst, String juistAntwoord) {
        this(vraagtekst, juistAntwoord, new Scanner(System.in));
    }

    public InvulVraag(String vraagtekst, String juistAntwoord, Scanner scanner) {
        this.vraagtekst = vraagtekst;
        this.juistAntwoord = juistAntwoord.toLowerCase();
        this.scanner = scanner;
    }

    @Override
    public boolean stelVraag(Speler speler) {
        System.out.println(vraagtekst);
        System.out.print("> ");
        String antwoord = leesAntwoord();

        switch (antwoord) {
            case "gebruik assistent":
                return verwerkAssistent(speler);
            case "keyjoker":
                return verwerkKeyJoker(speler);
            case "hintjoker":
                return verwerkHintJoker(speler);
            default:
                return antwoord.equals(juistAntwoord);
        }
    }

    private String leesAntwoord() {
        return scanner.nextLine().trim().toLowerCase();
    }

    private boolean verwerkAssistent(Speler speler) {
        if (speler.getPositie() == 0 || speler.getPositie() == 2) {
            Assistent assistent = new Assistent(
                    new HintAssistent(speler.getPositie()),
                    new StappenplanHulpmiddel(),
                    new Motivator()
            );
            assistent.activeer();
            return leesAntwoord().equals(juistAntwoord);
        } else {
            System.out.println("❌ Assistent is niet beschikbaar in deze kamer.");
            return leesAntwoord().equals(juistAntwoord);
        }
    }

    private boolean verwerkKeyJoker(Speler speler) {
        if (speler.heeftJoker() && speler.getGekozenJoker() instanceof KeyJoker keyJoker) {
            if (keyJoker.magGebruikenInKamer(speler.getPositie())) {
                keyJoker.gebruik();
                System.out.println("🔑 Je hebt de KeyJoker gebruikt! De kamer wordt overgeslagen.");
                return true;
            } else {
                System.out.println("❌ De KeyJoker mag alleen in kamer 2 of 4 worden gebruikt en slechts één keer.");
            }
        } else {
            System.out.println("❌ Je hebt niet de KeyJoker gekozen.");
        }
        return leesAntwoord().equals(juistAntwoord);
    }

    private boolean verwerkHintJoker(Speler speler) {
        if (speler.heeftJoker()) {
            speler.gebruikJoker(speler.getPositie());
        } else {
            System.out.println("❌ Je hebt geen hintjoker of je hebt hem al gebruikt.");
        }
        return leesAntwoord().equals(juistAntwoord);
    }
}
