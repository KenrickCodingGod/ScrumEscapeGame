package game.vraag;

import game.Speler;
import game.assistent.Assistent;
import game.assistent.HintAssistent;
import game.assistent.Motivator;
import game.assistent.StappenplanHulpmiddel;
import game.joker.KeyJoker;

import java.util.Scanner;

public class MeerkeuzeVraag implements Vraag {
    private String vraagtekst;
    private String[] opties;
    private String juistAntwoord;
    private Scanner scanner;

    public MeerkeuzeVraag(String vraagtekst, String[] opties, String juistAntwoord) {
        this(vraagtekst, opties, juistAntwoord, new Scanner(System.in));
    }

    // Constructor voor testbaarheid
    public MeerkeuzeVraag(String vraagtekst, String[] opties, String juistAntwoord, Scanner scanner) {
        this.vraagtekst = vraagtekst;
        this.opties = opties;
        this.juistAntwoord = juistAntwoord.toLowerCase();
        this.scanner = scanner;
    }

    @Override
    public boolean stelVraag(Speler speler) {
        toonVraag();
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

    private void toonVraag() {
        System.out.println(vraagtekst);
        for (String optie : opties) {
            System.out.println(optie);
        }
        System.out.print("> ");
    }

    private String leesAntwoord() {
        return scanner.nextLine().trim().toLowerCase();
    }

    private boolean verwerkAssistent(Speler speler) {
        if (speler.getPositie() == 0 || speler.getPositie() == 2) { // kamer 1 of 3
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
            System.out.println("❌ Je hebt geen KeyJoker gekozen.");
        }
        return false;
    }

    private boolean verwerkHintJoker(Speler speler) {
        if (speler.heeftJoker()) {
            speler.gebruikJoker(speler.getPositie());
            return leesAntwoord().equals(juistAntwoord);
        } else {
            System.out.println("Je hebt geen joker of je hebt hem al gebruikt.");
            return leesAntwoord().equals(juistAntwoord);
        }
    }
}
