package game;

import java.util.Scanner;

public class KamerPlanning extends Kamer {
    private Scanner scanner = new Scanner(System.in);

    public KamerPlanning() {
        super("Sprint Planning");
    }

    @Override
    public boolean voerUit() {
        System.out.println("📍 Je bent in de kamer: Sprint Planning");
        System.out.println("Welke taken passen binnen een sprint van 2 weken?");
        System.out.println("A) Alles wat we kunnen bedenken");
        System.out.println("B) Alleen taken die passen binnen de capaciteit van het team");
        System.out.print("> ");

        String antwoord = scanner.nextLine().trim().toLowerCase();
        if (antwoord.equals("b")) {
            System.out.println("✅ Goed! Je mag door.");
            return true;
        } else {
            System.out.println("❌ Fout! Je hebt het monster 'Scope Creep' opgeroepen!");
            return false;
        }
    }
}

