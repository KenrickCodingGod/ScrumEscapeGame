package game.observer;

import game.Speler;

public class GameStatusObserver implements SpelerObserver {
    @Override
    public void update(Speler speler) {
        System.out.println("📍 Je bent nu in kamer: " + speler.getPositie());
        System.out.println("🧟‍♂️ Monster: " + speler.getMonsters().get(speler.getMonsters().size() - 1).getNaam());


        System.out.println("🚪 Je hebt toegang tot kamer: " + (speler.getPositie() + 1));
    }
}
