package game.command.game;

import game.command.Command;
import game.GameUI;
import game.kamer.Kamer;
import game.Speler;

import java.util.List;

public class ToonStatusCommand implements Command {
    private final Speler speler;
    private final Kamer huidigeKamer;
    private final GameUI ui;
    private final List<Kamer> kamers;

    public ToonStatusCommand(Speler speler, Kamer huidigeKamer, GameUI ui, List<Kamer> kamers) {
        this.speler = speler;
        this.huidigeKamer = huidigeKamer;
        this.ui = ui;
        this.kamers = kamers;
    }

    @Override
    public void voerUit() {
        ui.toonStatus(speler, huidigeKamer, kamers);
    }
}
