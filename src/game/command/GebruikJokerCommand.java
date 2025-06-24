package game.command;

import game.Speler;

public class GebruikJokerCommand implements SpelerCommand {
    private final Speler speler;
    private final int kamerNummer;

    public GebruikJokerCommand(Speler speler, int kamerNummer) {
        this.speler = speler;
        this.kamerNummer = kamerNummer;
    }

    @Override
    public void execute() {
        speler.gebruikJoker(kamerNummer);
    }
}
