package game;

import java.util.Scanner;

public class KamerRetrospective extends Kamer {
    private Scanner scanner = new Scanner(System.in);

    public KamerRetrospective() {
        super("Sprint Retrospective");
    }

    @Override
    public boolean voerUit() {
        System.out.println("📍 Je bent in de kamer: Sprint Retrospective");
        System.out.println("Wat bespreek je in een retrospective?");
        System.out.println("A) Alleen wat goed ging");
        System.out.println("B) Wat goed ging, wat beter kon, en acties voor verbetering");
        System.out.print("> ");

        String antwoord = scanner.nextLine().trim().toLowerCase();
        if (antwoord.equals("b")) {
            System.out.println("✅ Correct! Je leert als team.");
            return true;
        } else {
            System.out.println("❌ Fout! Je hebt het monster 'Herhaling' opgeroepen!");
            return false;
        }
    }
}
