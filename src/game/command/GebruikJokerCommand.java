package game.command;

import game.Speler;
import game.kamer.Kamer;

public class GebruikJokerCommand implements SpelerCommand {
    private final Speler speler;
    private final Kamer kamer;

    public GebruikJokerCommand(Speler speler, Kamer kamer) {
        this.speler = speler;
        this.kamer = kamer;
    }

    @Override
    public void execute() {
        speler.gebruikJoker(kamer);
    }
}
