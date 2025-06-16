package game;

import game.Speler;

public class Utils {
    public static void printStatus(Speler speler) {
        System.out.println("\n--- SPELER STATUS ---");
        int index = speler.getPositie();
        System.out.println("Kamer " + (index + 1));
        System.out.println("Actieve monsters: " +
                (speler.getMonsters().isEmpty() ? "Geen" : speler.getMonsterNamenAlsString()));
        System.out.println("----------------------");
    }

    public static void toonMonsterFout(String monsterNaam) {
        System.out.println("Fout! Je hebt het monster '" + monsterNaam + "' opgeroepen!");
    }
}
