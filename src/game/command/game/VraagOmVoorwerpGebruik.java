package game.command.game;

import game.command.Command;
import game.GameUI;
import game.command.CommandUitvoerder;
import game.voorwerp.Weapon;
import game.voorwerp.Readable;
import game.kamer.Kamer;
import game.Monster;

public class VraagOmVoorwerpGebruik implements Command {
    private final Kamer kamer;
    private final Monster monster;
    private final GameUI ui;
    private boolean resultaat;

    public VraagOmVoorwerpGebruik(Kamer kamer, Monster monster, GameUI ui) {
        this.kamer = kamer;
        this.monster = monster;
        this.ui = ui;
    }
    public boolean getResultaat(){
    return resultaat;
    }

    @Override
    public void voerUit() {
        ui.toon("------------------------------\nEr is een monster: " + monster.getNaam());
        ui.toon("Gebruik een item: 'gebruik zwaard', 'gebruik boek' of doe niets\n------------------------------");
        String input = ui.leesInput().toLowerCase();
        Weapon zwaard = kamer.getZwaard();
        Readable boek = kamer.getBoek();

        switch (input) {
            case "gebruik zwaard":
                if (zwaard != null) {
                    ui.toon(zwaard.attack(monster));
                    resultaat = true;
                } else {
                    ui.toon("❌ Geen zwaard beschikbaar.");
                    resultaat = false;
                }
                break;

            case "gebruik boek":
                if (boek != null) {
                    ui.toon("📖 " + boek.showMessage());
                } else {
                    ui.toon("❌ Geen boek beschikbaar.");
                }
                resultaat = true;
                break;

            case "":
                resultaat = false;
                break;

            default:
                ui.toon("❓ Onbekend commando, probeer opnieuw.");
                break;
        }
    }
}
