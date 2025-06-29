package game;
import game.kamer.Kamer;

import java.util.List;
import java.util.Scanner;

public class GameUI {
    private final Scanner scanner = new Scanner(System.in);

    public void toonIntroductie() {
        System.out.println("------------------------------\n Welkom bij Scrum Escape!\n------------------------------");
        System.out.println("Kies je joker:");
        System.out.println("1. HintJoker *gebruik door 'hintjoker' te typen*");
        System.out.println("2. KeyJoker *gebruik door 'keyjoker' te typen je mag hem alleen gebruiken in 2 en 4*\n------------------------------");
        System.out.println("Gebruik 'gebruik assistent' in kamer 1 en 3.");
        System.out.println("Gebruik 'gebruik boek' is altijd beschickbaar 'gebruik zwaard' kan je alleen activeren als er een monster is.");
        System.out.println("Commando's: 'status', 'reset', 'ga naar kamer X'\n------------------------------");
    }

    public String leesInput() {
        System.out.print(">> ");
        return scanner.nextLine();
    }

    public void toon(String bericht) {
        System.out.println(bericht);
    }

    public void toonStatus(Speler speler, Kamer kamer, List<Kamer> kamers) {
        int totaalKamers = kamers.size();
        int kamerIndex = kamer == null ? -1 : kamers.indexOf(kamer);
        System.out.println("\n--- SPELER STATUS ---");
        System.out.println("Kamer " + (kamerIndex + 1) + " van " + totaalKamers);
        System.out.println("Actieve monsters: " + speler.getMonsterNamenAlsString());
    }


    public boolean bevestig(String vraag) {
        System.out.println(vraag + " (ja/nee)\n------------------------------");
        return scanner.nextLine().equalsIgnoreCase("ja");
    }
}