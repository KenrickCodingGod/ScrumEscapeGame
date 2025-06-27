package game.observer;

import game.Speler;
import game.kamer.Kamer;

public class GameStatusObserver implements SpelerObserver {
    private GameStatus status;
    private final GameStatusView view = new GameStatusView();

    @Override
    public void update(Speler speler) {
        Kamer kamer = speler.getHuidigeKamer();
        String kamerNaam = kamer != null ? kamer.getNaam() : "Onbekend";
        int kamerNummer = kamer != null ? kamer.getKamerNummer() : 0;

        String monsterNaam = speler.getMonsters().isEmpty()
                ? "Geen actieve monsters."
                : speler.getMonsters().get(speler.getMonsters().size() - 1).getNaam();

        status = new GameStatus(kamerNaam, kamerNummer, monsterNaam);
        view.toonStatus(status);
    }
}
