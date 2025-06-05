package game.vraag;

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
    public boolean stelVraag() {
        System.out.println(vraagtekst);
        for (String optie : opties) {
            System.out.println(optie);
        }
        System.out.print("> ");
        String antwoord = scanner.nextLine().trim().toLowerCase();
        return antwoord.equals(juistAntwoord);
    }
}
