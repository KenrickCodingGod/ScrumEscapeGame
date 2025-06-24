package game.command;

import game.Speler;

public class SetPositieCommand implements SpelerCommand {
    private final Speler speler;
    private final int positie;

    public SetPositieCommand(Speler speler, int positie) {
        this.speler = speler;
        this.positie = positie;
    }

    @Override
    public void execute() {
        speler.setPositie(positie);
    }
}
