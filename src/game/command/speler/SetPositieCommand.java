package game.command.speler;

import game.Speler;
import game.command.Command;
import game.kamer.Kamer;

public class SetPositieCommand implements Command {
    private final Speler speler;
    private final Kamer nieuweKamer;

    public SetPositieCommand(Speler speler, Kamer nieuweKamer) {
        this.speler = speler;
        this.nieuweKamer = nieuweKamer;
    }

    @Override
    public void voerUit() {
        speler.setHuidigeKamer(nieuweKamer);
    }
}
