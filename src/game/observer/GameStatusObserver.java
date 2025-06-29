package game.observer;

import game.Speler;
import game.kamer.Kamer;

import java.util.List;

public class GameStatusObserver implements SpelerObserver {
    private GameStatus status;
    private final GameStatusView view = new GameStatusView();
    private final List<Kamer> kamers;

    public GameStatusObserver(List<Kamer> kamers) {
        this.kamers = kamers;
    }

    @Override
    public void update(Speler speler) {
        Kamer kamer = speler.getHuidigeKamer();
        String kamerNaam = kamer != null ? kamer.getNaam() : "Onbekend";
        int kamerIndex = kamer != null ? kamers.indexOf(kamer) : -1;

        String monsterNaam = speler.getMonsters().isEmpty()
                ? "Geen actieve monsters."
                : speler.getMonsters().get(speler.getMonsters().size() - 1).getNaam();

        status = new GameStatus(kamerNaam, kamerIndex, monsterNaam);
        view.toonStatus(status);
    }
}
