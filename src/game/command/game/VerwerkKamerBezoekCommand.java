package game.command.game;

import game.GameUI;
import game.Speler;
import game.command.Command;
import game.kamer.Kamer;

import java.util.List;

public class VerwerkKamerBezoekCommand implements Command {
    private final String input;
    private final Speler speler;
    private final List<Kamer> kamers;
    private final GameUI ui;

    public VerwerkKamerBezoekCommand(String input, Speler speler, List<Kamer> kamers, GameUI ui) {
        this.input = input;
        this.speler = speler;
        this.kamers = kamers;
        this.ui = ui;
    }

    public void voerUit() {
        try {
            int index = Integer.parseInt(input.replaceAll("\\D+", "")) - 1;

            if (index < 0 || index >= kamers.size()) {
                ui.toon("⚠️ Deze kamer bestaat niet.");
                return;
            }

            Kamer kamer = kamers.get(index);
            new BezoekKamerCommand(speler, kamer, ui, kamers).voerUit();
        } catch (NumberFormatException e) {
            ui.toon("⚠️ Ongeldige invoer.");
        }
    }
}
