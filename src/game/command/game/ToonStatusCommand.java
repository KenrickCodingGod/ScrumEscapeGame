package game.command.game;

import game.command.Command;
import game.GameUI;
import game.kamer.Kamer;
import game.Speler;

public class ToonStatusCommand implements Command {
    private final Speler speler;
    private final Kamer huidigeKamer;
    private final int totaalKamers;
    private final GameUI ui;

    public ToonStatusCommand(Speler speler, Kamer huidigeKamer, int totaalKamers, GameUI ui) {
        this.speler = speler;
        this.huidigeKamer = huidigeKamer;
        this.totaalKamers = totaalKamers;
        this.ui = ui;
    }

    @Override
    public void voerUit() {
        ui.toonStatus(speler, huidigeKamer, totaalKamers);
    }
}
