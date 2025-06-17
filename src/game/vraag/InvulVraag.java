package game.vraag;


import game.Speler;
import game.joker.Joker;
import game.joker.KeyJoker;

import java.util.Scanner;

public class InvulVraag implements Vraag {
    private String vraagtekst;
    private String juistAntwoord;
    private Scanner scanner = new Scanner(System.in);

    public InvulVraag(String vraagtekst, String juistAntwoord) {
        this.vraagtekst = vraagtekst;
        this.juistAntwoord = juistAntwoord.toLowerCase();
    }

    @Override
    public boolean stelVraag(Speler speler) {
        System.out.println(vraagtekst);
        System.out.print("> ");
        String antwoord = scanner.nextLine().trim().toLowerCase();

        if (antwoord.equals("keyjoker")) {
            if (speler.heeftJoker() && speler.getGekozenJoker() instanceof KeyJoker keyJoker) {
                if (keyJoker.magGebruikenInKamer(speler.getPositie())) {
                    keyJoker.gebruik();
                    System.out.println("🔑 Je hebt de KeyJoker gebruikt! De kamer wordt overgeslagen.");
                    return true; // Slaat vraag over
                } else {
                    System.out.println("❌ De KeyJoker mag alleen in kamer 2 of 4 worden gebruikt en slechts één keer.");
                    antwoord = scanner.nextLine().trim().toLowerCase();
                    return antwoord.equals(juistAntwoord);
                }
            } else {
                System.out.println("❌ Je hebt niet de KeyJoker gekozen.");
                antwoord = scanner.nextLine().trim().toLowerCase();
                return antwoord.equals(juistAntwoord);
            }
        }


        if (antwoord.equals("hintjoker")) {
            if (speler.heeftJoker()) {
                speler.gebruikJoker(speler.getPositie());
                antwoord = scanner.nextLine().trim().toLowerCase();
                return antwoord.equals(juistAntwoord);
            } else {
                System.out.println("❌ Je hebt geen hintjoker of je hebt hem al gebruikt.");
                antwoord = scanner.nextLine().trim().toLowerCase();
                return antwoord.equals(juistAntwoord);
            }
        }

        return antwoord.equals(juistAntwoord);
    }
}
