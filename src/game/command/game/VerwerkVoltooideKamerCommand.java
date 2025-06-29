package game.command.game;

import game.command.Command;
import game.GameUI;
import game.kamer.Kamer;
import game.Speler;

import java.util.List;

public class VerwerkVoltooideKamerCommand implements Command {
    private final Speler speler;
    private final List<Kamer> kamers;
    private final GameUI ui;

    public VerwerkVoltooideKamerCommand(Speler speler, List<Kamer> kamers, GameUI ui) {
        this.speler = speler;
        this.kamers = kamers;
        this.ui = ui;
    }

    @Override
    public void voerUit() {
        ui.toon("✅ Goedzo, ga zo door!");
        int huidigeIndex = kamers.indexOf(speler.getHuidigeKamer());

        if (huidigeIndex + 1 < kamers.size()) {
            ui.toon("➡️ Typ 'ga naar kamer " + (huidigeIndex + 2) + "' om door te gaan.");
        } else {
            ui.toon("🎉 Je hebt alle kamers doorlopen!");
            System.exit(0);
        }
    }
}
