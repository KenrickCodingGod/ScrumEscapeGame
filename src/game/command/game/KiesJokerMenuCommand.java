package game.command.game;

import game.GameUI;
import game.Speler;
import game.command.Command;
import game.command.CommandUitvoerder;
import game.command.speler.KiesJokerCommand;
import game.joker.HintJoker;
import game.joker.Joker;
import game.joker.KeyJoker;

public class KiesJokerMenuCommand implements Command {
    private final Speler speler;
    private final GameUI ui;

    public KiesJokerMenuCommand(Speler speler, GameUI ui) {
        this.speler = speler;
        this.ui = ui;
    }

    public void voerUit() {
        String gekozen = ui.leesInput();
        String bericht;
        Joker gekozenJoker;

        if (gekozen.equals("1")) {
            gekozenJoker = new HintJoker();
            bericht = "Je hebt gekozen voor de HintJoker.";
        } else if (gekozen.equals("2")) {
            gekozenJoker = new KeyJoker();
            bericht = "Je hebt gekozen voor de KeyJoker.";
        } else {
            ui.toon("Ongeldige keuze, probeer opnieuw.");
            CommandUitvoerder.voerUit(new KiesJokerMenuCommand(speler, ui));
            return;
        }

        CommandUitvoerder.voerUit(new KiesJokerCommand(speler, gekozenJoker));
        ui.toon(bericht);
    }
}
