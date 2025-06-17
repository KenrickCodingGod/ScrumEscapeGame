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
    private Scanner scanner = new Scanner(System.in);

    public MeerkeuzeVraag(String vraagtekst, String[] opties, String juistAntwoord) {
        this.vraagtekst = vraagtekst;
        this.opties = opties;
        this.juistAntwoord = juistAntwoord.toLowerCase();
    }

    @Override
    public boolean stelVraag(Speler speler) {
        System.out.println(vraagtekst);
        for (String optie : opties) {
            System.out.println(optie);
        }
        System.out.print("> ");
        String antwoord = scanner.nextLine().trim().toLowerCase();

        if (antwoord.equals("gebruik assistent")) {
            if (speler.getPositie() == 0 || speler.getPositie() == 2) { // kamer 1 of 3 (positie 0 of 2)
                Assistent assistent = new Assistent(
                        new HintAssistent(speler.getPositie()),
                        new StappenplanHulpmiddel(),
                        new Motivator()
                );
                assistent.activeer();
                antwoord = scanner.nextLine().trim().toLowerCase();
                return antwoord.equals(juistAntwoord);
            } else {
                System.out.println("❌ Assistent is niet beschikbaar in deze kamer.");
                antwoord = scanner.nextLine().trim().toLowerCase();
                return antwoord.equals(juistAntwoord);
            }
        }

        if (antwoord.equals("keyjoker")) {
            if (speler.heeftJoker() && speler.getGekozenJoker() instanceof KeyJoker keyJoker) {
                if (keyJoker.magGebruikenInKamer(speler.getPositie())) {
                    keyJoker.gebruik();
                    System.out.println("🔑 Je hebt de KeyJoker gebruikt! De kamer wordt overgeslagen.");
                    return true; // Slaat vraag over
                } else {
                    System.out.println("❌ De KeyJoker mag alleen in kamer 2 of 4 worden gebruikt en slechts één keer.");
                }
            } else {
                System.out.println("❌ Je hebt geen KeyJoker gekozen.");
            }
            return false; // Beëindig vraag zonder goed antwoord
        }

        if (antwoord.equals("joker")) {
            if (speler.heeftJoker()) {
                speler.gebruikJoker(speler.getPositie());
                antwoord = scanner.nextLine().trim().toLowerCase();
                return antwoord.equals(juistAntwoord);
            } else {
                System.out.println("Je hebt geen joker of je hebt hem al gebruikt.");
                antwoord = scanner.nextLine().trim().toLowerCase();
                return antwoord.equals(juistAntwoord);
            }
        }

        return antwoord.equals(juistAntwoord);
    }
}
