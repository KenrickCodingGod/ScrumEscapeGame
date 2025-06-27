package game.command.game;

import game.command.Command;
import game.command.CommandUitvoerder;
import game.command.speler.VoegMonsterToeCommand;
import game.GameUI;
import game.kamer.Kamer;
import game.Monster;
import game.Speler;

public class BezoekKamerCommand implements Command {
    private final Speler speler;
    private final Kamer kamer;
    private final GameUI ui;

    public BezoekKamerCommand(Speler speler, Kamer kamer, GameUI ui) {
        this.speler = speler;
        this.kamer = kamer;
        this.ui = ui;
    }

    @Override
    public void voerUit() {
        boolean correct = kamer.voerUit(speler);
        if (correct) {
            voerUit();
            CommandUitvoerder.voerUit(new VerwerkVoltooideKamerCommand(speler, speler.getAlleKamers(), ui));
            return;
        }

        Monster monster = kamer.getMonster();
        CommandUitvoerder.voerUit(new VoegMonsterToeCommand(speler, monster));

        if (ui.bevestig("------------------------------\n❓ Wil je een hint?")) {
            CommandUitvoerder.voerUit(new ToonHintVoorKamerCommand(kamer, ui));
        }

        VraagOmVoorwerpGebruik voorwerpCommand = new VraagOmVoorwerpGebruik(kamer, monster, ui);
        voorwerpCommand.voerUit();

        boolean opnieuwJuist = kamer.voerUit(speler);
        if (opnieuwJuist) {
            CommandUitvoerder.voerUit(new VerwerkVoltooideKamerCommand(speler, speler.getAlleKamers(), ui));
        } else {
            ui.toon("⚠️ Je hebt het monster niet verslagen. Probeer later opnieuw.");
        }
    }
}
