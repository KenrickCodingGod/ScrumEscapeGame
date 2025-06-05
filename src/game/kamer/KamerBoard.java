package game.kamer;

import game.voorwerp.KamerInfoBoek;
import game.voorwerp.Voorwerp;
import game.voorwerp.Zwaard;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KamerBoard extends Kamer {
    private final Scanner scanner = new Scanner(System.in);
    private final List<Voorwerp> voorwerpen = new ArrayList<>();

    public KamerBoard() {
        super("Scrum Board");

        // Voeg voorwerpen toe bij creatie
        voorwerpen.add(new KamerInfoBoek("Gebruik het Scrum Board om overzicht te houden op de sprinttaken."));
        voorwerpen.add(new Zwaard("Chaos-monster"));
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

            // Toon voorwerpen bij juist antwoord
            for (Voorwerp v : voorwerpen) {
                v.gebruik();
            }

            return true;
        } else {
            System.out.println("❌ Fout! Je hebt het monster 'Chaos' opgeroepen!");
            return false;
        }
    }
}
