package game.command;

import game.Speler;
import game.joker.Joker;

public class KiesJokerCommand implements SpelerCommand {
    private final Speler speler;
    private final Joker joker;

    public KiesJokerCommand(Speler speler, Joker joker) {
        this.speler = speler;
        this.joker = joker;
    }

    @Override
    public void execute() {
        speler.kiesJoker(joker);
    }
}
