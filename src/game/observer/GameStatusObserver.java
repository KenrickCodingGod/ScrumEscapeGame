package game.observer;

import game.Speler;

public class GameStatusObserver implements SpelerObserver {
    public void update(Speler speler) {
        System.out.println("------------------------------\n📍 Je bent nu in kamer: " + speler.getPositie());

        if (!speler.getMonsters().isEmpty()) {
            System.out.println("🧟‍♂️ Monster: " + speler.getMonsters().get(speler.getMonsters().size() - 1).getNaam());
        } else {
            System.out.println("🧟‍♂️ Monster: Geen actieve monsters.");
        }

        System.out.println("🚪 Je hebt toegang tot kamer: " + (speler.getPositie() + 1)+ "\n------------------------------");
    }
}
