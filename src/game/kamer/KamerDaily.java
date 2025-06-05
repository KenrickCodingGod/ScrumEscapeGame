package game.kamer;

import game.Monster;
import game.voorwerp.ScrumTimer;
import game.voorwerp.Voorwerp;

import java.util.Scanner;

public class KamerDaily extends Kamer {
    private Scanner scanner = new Scanner(System.in);
    private Monster monster = new Monster("Vertraging", "Je team communiceert niet goed.");
    private Voorwerp voorwerp = new ScrumTimer();

    public KamerDaily() {
        super("Daily Scrum");
    }

    @Override
    public boolean voerUit() {
        System.out.println("📍 Je bent in de kamer: Daily Scrum");
        System.out.println("Scrum-vraag:");
        System.out.println("Wat is een goed voorbeeld van een status-update?");
        System.out.println("A) Ik heb gisteren taak X gedaan, vandaag doe ik taak Y");
        System.out.println("B) Geen idee wat ik doe");
        System.out.print("> ");

        String antwoord = scanner.nextLine().trim().toLowerCase();

        if (antwoord.equals("a")) {
            System.out.println("✅ Goed! Je mag door.");
            return true;
        } else {
            System.out.println("❌ Fout! Je hebt het monster '" + monster.getNaam() + "' opgeroepen!");
            System.out.println(voorwerp.gebruik(monster)); // automatische voorwerp-reactie
            return false;
        }
    }
}
