package game.command.game;

import game.assistent.Assistent;
import game.assistent.HintAssistent;
import game.assistent.Motivator;
import game.assistent.StappenplanHulpmiddel;
import game.command.Command;
import game.GameUI;
import game.kamer.Kamer;
import game.Speler;

import java.util.List;

public class GebruikAssistentCommand implements Command {
    private final Speler speler;
    private final GameUI ui;

    public GebruikAssistentCommand(Speler speler, GameUI ui) {
        this.speler = speler;
        this.ui = ui;
    }

    @Override
    public void voerUit() {
        Kamer huidigeKamer = speler.getHuidigeKamer();
        if (huidigeKamer.isAssistentToegestaan()) {
            Assistent assistent = new Assistent(List.of(
                    new HintAssistent(),
                    new StappenplanHulpmiddel(),
                    new Motivator()
            ));
            assistent.activeer();
        } else {
            ui.toon("❌ Geen assistent in deze kamer.");
        }
    }
}
