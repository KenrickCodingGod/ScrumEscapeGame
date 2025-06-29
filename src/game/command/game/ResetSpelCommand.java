package game.command.game;

import game.GameEngine;
import game.command.Command;
import game.observer.GameStatusObserver;
import game.Speler;
import game.kamer.Kamer;

import java.util.List;

public class ResetSpelCommand implements Command {
    private final GameEngine gameEngine;
    private final List<Kamer> kamers;

    public ResetSpelCommand(GameEngine gameEngine, List<Kamer> kamers) {
        this.gameEngine = gameEngine;
        this.kamers = kamers;
    }

    @Override
    public void voerUit() {
        Speler nieuweSpeler = new Speler();
        nieuweSpeler.setHuidigeKamer(null);
        nieuweSpeler.attach(new GameStatusObserver(kamers));
        gameEngine.setSpeler(nieuweSpeler);
        gameEngine.getUI().toon("🔁 Spel wordt opnieuw gestart...\n");
        gameEngine.start();
    }
}
