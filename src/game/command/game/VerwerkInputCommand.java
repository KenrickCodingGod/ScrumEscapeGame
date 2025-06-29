package game.command.game;

import game.GameEngine;
import game.GameUI;
import game.Speler;
import game.command.Command;
import game.command.CommandUitvoerder;
import game.kamer.Kamer;
import java.util.List;

public class VerwerkInputCommand implements Command {
    private final String input;
    private final Speler speler;
    private final List<Kamer> kamers;
    private final GameUI ui;
    private final GameEngine gameEngine;


    public VerwerkInputCommand(String input, Speler speler, List<Kamer> kamers, GameUI ui, GameEngine gameEngine) {
        this.input = input;
        this.speler = speler;
        this.kamers = kamers;
        this.ui = ui;
        this.gameEngine = gameEngine;


    }

    public void voerUit() {
        switch (input) {
            case "status" -> CommandUitvoerder.voerUit(new ToonStatusCommand(speler, speler.getHuidigeKamer(), kamers.size(), ui)
            );
            case "reset" -> CommandUitvoerder.voerUit(new ResetSpelCommand(gameEngine));
            case "gebruik assistent" -> CommandUitvoerder.voerUit(new GebruikAssistentCommand(speler, ui)
            );
            default -> {
                if (input.startsWith("ga naar kamer")) {
                    CommandUitvoerder.voerUit(
                            new VerwerkKamerBezoekCommand(input, speler, kamers, ui)
                    );
                } else {
                    ui.toon("❓ Onbekend commando.");
                }
            }
        }
    }
}
