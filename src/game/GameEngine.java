package game;

import game.command.game.*;
import game.command.game.VerwerkInputCommand;
import game.kamer.Kamer;
import game.observer.GameStatusObserver;

import java.util.List;

import static game.command.CommandUitvoerder.voerUit;

public class GameEngine {
    private final GameUI ui;
    private final List<Kamer> kamers;
    private Speler speler;

    public GameEngine(GameUI ui, KamerFactory kamerFactory) {
        this.ui = ui;
        this.kamers = kamerFactory.maakKamers();
        this.speler = new Speler();
        speler.setHuidigeKamer(null);
        speler.attach(new GameStatusObserver());
    }

    public void start() {
        ui.toonIntroductie();
        voerUit(new KiesJokerMenuCommand(speler, ui));
        while (true) {
            String input = ui.leesInput().toLowerCase();
            voerUit(new VerwerkInputCommand(input, speler, kamers, ui, this));
        }
    }




    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    public GameUI getUI() {
        return this.ui;
    }


}