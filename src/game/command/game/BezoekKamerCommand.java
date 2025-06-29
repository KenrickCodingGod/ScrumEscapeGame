package game.command.game;

import game.command.Command;
import game.command.CommandUitvoerder;
import game.command.speler.VoegMonsterToeCommand;
import game.GameUI;
import game.kamer.Kamer;
import game.Monster;
import game.Speler;

import java.util.List;

public class BezoekKamerCommand implements Command {
    private final Speler speler;
    private final Kamer kamer;
    private final GameUI ui;
    private final List<Kamer> kamers;

    public BezoekKamerCommand(Speler speler, Kamer kamer, GameUI ui, List<Kamer> kamers) {
        this.speler = speler;
        this.kamer = kamer;
        this.ui = ui;
        this.kamers = kamers;
    }

    public void voerUit() {
        int huidigeIndex = speler.getHuidigeKamer() == null ? -1 : kamers.indexOf(speler.getHuidigeKamer());
        int doelIndex = kamers.indexOf(kamer);

        if (doelIndex != huidigeIndex + 1) {
            ui.toon("🚫 Je mag alleen naar de eerstvolgende kamer: " + (huidigeIndex + 2));
            return;
        }

        boolean correct = kamer.voerUit(speler, kamers);
        if (!correct) {
            Monster monster = kamer.getMonster();
            CommandUitvoerder.voerUit(new VoegMonsterToeCommand(speler, monster));

            if (ui.bevestig("------------------------------\n❓ Wil je een hint?")) {
                CommandUitvoerder.voerUit(new ToonHintVoorKamerCommand(kamer, ui));
            }

            boolean monsterVerslagen = false;
            while (!monsterVerslagen) {
                VraagOmVoorwerpGebruik vraagCommand = new VraagOmVoorwerpGebruik(kamer, monster, ui);
                vraagCommand.voerUit();
                monsterVerslagen = vraagCommand.getResultaat();
                if (!monsterVerslagen) {
                    ui.toon("🛑 Monster niet verslagen. Probeer nogmaals.");
                }
            }

            boolean opnieuwJuist = false;
            while (!opnieuwJuist) {
                ui.toon("Probeer nu opnieuw de vraag te beantwoorden:");
                opnieuwJuist = kamer.voerUit(speler, kamers);
                if (!opnieuwJuist) {
                    ui.toon("❌ Fout antwoord, probeer het nogmaals.");
                }
            }
        }

        speler.setHuidigeKamer(kamer);
        CommandUitvoerder.voerUit(new VerwerkVoltooideKamerCommand(speler, kamers, ui));
    }

}
