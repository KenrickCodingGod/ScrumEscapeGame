package game.vraag;

import game.Speler;
import game.assistent.Assistent;
import game.assistent.HintAssistent;
import game.assistent.Motivator;
import game.assistent.StappenplanHulpmiddel;
import game.joker.KeyJoker;
import game.kamer.Kamer;

import java.util.Scanner;

public class MeerkeuzeVraag implements Vraag {
    private final String vraagtekst;
    private final String[] opties;
    private final String juistAntwoord;
    private final Scanner scanner;

    public MeerkeuzeVraag(String vraagtekst, String[] opties, String juistAntwoord) {
        this.vraagtekst = vraagtekst;
        this.opties = opties;
        this.juistAntwoord = juistAntwoord.toLowerCase();
        this.scanner = new Scanner(System.in); // beter via constructor injecteren
    }

    @Override
    public boolean stelVraag(Speler speler, Kamer kamer) {
        toonVraag();

        while (true) {
            String antwoord = leesInput();

            switch (antwoord) {
                case "gebruik assistent" -> {
                    verwerkAssistent(speler);
                    toonPrompt();
                }

                case "keyjoker" -> {
                    boolean overgeslagen = verwerkKeyJoker(speler);
                    if (overgeslagen) return true;
                    toonPrompt();
                }

                case "hintjoker" -> {
                    verwerkHintJoker(speler);
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
        for (String optie : opties) {
            System.out.println(optie);
        }
        System.out.print("> ");
    }

    private void toonPrompt() {
        System.out.print("> ");
    }

    private String leesInput() {
        return scanner.nextLine().trim().toLowerCase();
    }

    private void verwerkAssistent(Speler speler) {
        int kamerIndex = speler.getPositie();
        boolean assistentBeschikbaar = kamerIndex == 0 || kamerIndex == 2;

        if (assistentBeschikbaar) {
            Assistent assistent = new Assistent(
                    new HintAssistent(kamerIndex),
                    new StappenplanHulpmiddel(),
                    new Motivator()
            );
            assistent.activeer();
        } else {
            System.out.println("❌ Assistent is niet beschikbaar in deze kamer.");
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

    private void verwerkHintJoker(Speler speler) {
        if (speler.heeftJoker()) {
            speler.gebruikJoker(speler.getPositie());
        } else {
            System.out.println("Je hebt geen joker of je hebt hem al gebruikt.");
        }
    }
}
