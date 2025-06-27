package game.command.speler;

import game.Speler;
import game.command.Command;
import game.kamer.Kamer;

public class GebruikJokerCommand implements Command {
    private final Speler speler;
    private final Kamer kamer;

    public GebruikJokerCommand(Speler speler, Kamer kamer) {
        this.speler = speler;
        this.kamer = kamer;
    }

    @Override
    public void voerUit() {
        speler.gebruikJoker(kamer);
    }
}
