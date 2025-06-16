package game.observer;

import game.Speler;

public class GameStatusObserver implements SpelerObserver {
    @Override
    public void update(Speler speler) {
        System.out.println("📍 Positie: " + speler.getPositie());
        System.out.println("🧌 Monsters: " + speler.getMonsterNamenAlsString());

        if (!speler.getMonsters().isEmpty()) {
            System.out.println("🧌 Laatste monster: " +
                    speler.getMonsters().get(speler.getMonsters().size() - 1).getNaam());
        }

        System.out.println("🚪 Deur naar kamer: " + (speler.getPositie() + 1));
    }
}
