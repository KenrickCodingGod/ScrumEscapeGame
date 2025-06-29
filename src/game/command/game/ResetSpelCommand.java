package game.command.game;

import game.GameEngine;
import game.command.Command;
import game.observer.GameStatusObserver;
import game.Speler;


public class ResetSpelCommand implements Command {
    private final GameEngine gameEngine;


    public ResetSpelCommand(GameEngine gameEngine) {
        this.gameEngine = gameEngine;

    }

    @Override
    public void voerUit() {
        Speler nieuweSpeler = new Speler();
        nieuweSpeler.setHuidigeKamer(null);
        nieuweSpeler.attach(new GameStatusObserver());
        gameEngine.setSpeler(nieuweSpeler);
        gameEngine.getUI().toon("🔁 Spel wordt opnieuw gestart...\n");
        gameEngine.start();
    }
}
