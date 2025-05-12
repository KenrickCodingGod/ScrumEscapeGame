package game;



import java.util.Scanner;

public class KamerBoard extends Kamer {
    private Scanner scanner = new Scanner(System.in);

    public KamerBoard() {
        super("Scrum Board");
    }

    @Override
    public boolean voerUit() {
        System.out.println("📍 Je bent in de kamer: Scrum Board");
        System.out.println("Welke van de volgende hoort NIET op een Scrum Board?");
        System.out.println("A) Taken, user stories en epics");
        System.out.println("B) De persoonlijke planning van de Product Owner");
        System.out.print("> ");

        String antwoord = scanner.nextLine().trim().toLowerCase();
        if (antwoord.equals("b")) {
            System.out.println("✅ Correct! Je mag door.");
            return true;
        } else {
            System.out.println("❌ Fout! Je hebt het monster 'Chaos' opgeroepen!");
            return false;
        }
    }
}
