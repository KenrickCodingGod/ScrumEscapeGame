package game.command.speler;

import game.Speler;
import game.command.Command;
import game.joker.Joker;  // Zorg dat je dit importeert!

public class KiesJokerCommand implements Command {
    private final Speler speler;
    private final Joker joker;

    public KiesJokerCommand(Speler speler, Joker joker) {
        this.speler = speler;
        this.joker = joker;
    }

    @Override
    public void voerUit() {
        speler.kiesJoker(joker);
    }
}
