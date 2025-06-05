package game.vraag;

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
    public boolean stelVraag() {
        System.out.println(vraagtekst);
        System.out.print("> ");
        String antwoord = scanner.nextLine().trim().toLowerCase();
        return antwoord.equals(juistAntwoord);
    }
}
