package game.command;

import game.Speler;
import game.kamer.Kamer;

public class SetPositieCommand implements SpelerCommand {
    private final Speler speler;
    private final Kamer nieuweKamer;

    public SetPositieCommand(Speler speler, Kamer nieuweKamer) {
        this.speler = speler;
        this.nieuweKamer = nieuweKamer;
    }

    @Override
    public void execute() {
        speler.setHuidigeKamer(nieuweKamer);
    }
}
