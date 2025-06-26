package game.observer;

import game.Speler;

public class GameStatusObserver implements SpelerObserver {
    private GameStatus status;

    @Override
    public void update(Speler speler) {
        String monsterNaam = speler.getMonsters().isEmpty()
                ? "Geen actieve monsters."
                : speler.getMonsters().get(speler.getMonsters().size() - 1).getNaam();

        status = new GameStatus(speler.getPositie(), monsterNaam);
    }

    public GameStatus getStatus() {
        return status;
    }
}

