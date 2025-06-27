package game;

import game.command.speler.*;
import game.command.game.*;

import game.joker.HintJoker;
import game.joker.Joker;
import game.joker.KeyJoker;
import game.kamer.Kamer;
import game.observer.GameStatusObserver;

import java.util.List;
import java.util.Optional;

import static game.command.CommandUitvoerder.voerUit;

public class GameEngine {
    private final GameUI ui;
    private final List<Kamer> kamers;
    private Speler speler;

    public GameEngine(GameUI ui, KamerFactory kamerFactory) {
        this.ui = ui;
        this.kamers = kamerFactory.maakKamers();
        this.speler = new Speler();
        speler.setAlleKamers(kamers); // belangrijk voor VerwerkVoltooideKamerCommand
        speler.setHuidigeKamer(kamers.getFirst());
        speler.voegObserverToe(new GameStatusObserver());
    }

    public void start() {
        ui.toonIntroductie();
        kiesJoker();
        while (true) {
            String input = ui.leesInput().toLowerCase();
            verwerkInput(input);
        }
    }

    private void kiesJoker() {
        String gekozen = ui.leesInput();
        String bericht = null;
        Joker gekozenJoker = null;

        if (gekozen.equals("1")) {
            gekozenJoker = new HintJoker();
            bericht = "Je hebt gekozen voor de HintJoker.";
        } else if (gekozen.equals("2")) {
            gekozenJoker = new KeyJoker();
            bericht = "Je hebt gekozen voor de KeyJoker.";
        } else {
            ui.toon("Ongeldige keuze, probeer opnieuw.");
            kiesJoker();
            return;
        }

        voerUit(new KiesJokerCommand(speler, gekozenJoker));
        ui.toon(bericht);
    }

    private void verwerkInput(String input) {
        switch (input) {
            case "status" -> voerUit(new ToonStatusCommand(speler, speler.getHuidigeKamer(), kamers.size(), ui));
            case "reset" -> voerUit(new ResetSpelCommand(this, kamers));
            case "gebruik assistent" -> voerUit(new GebruikAssistentCommand(speler, ui));
            default -> {
                if (input.startsWith("ga naar kamer")) {
                    verwerkKamerBezoek(input);
                } else {
                    ui.toon("❓ Onbekend commando.");
                }
            }
        }
    }

    private void verwerkKamerBezoek(String input) {
        try {
            int kamerNr = Integer.parseInt(input.replaceAll("\\D+", ""));
            Optional<Kamer> kamerOpt = kamers.stream()
                    .filter(k -> k.getKamerNummer() == kamerNr)
                    .findFirst();
            System.out.println(kamerOpt.isPresent());
            System.out.println(kamerOpt);
            kamerOpt.ifPresentOrElse(
                    kamer -> voerUit(new BezoekKamerCommand(speler, kamer, ui)),
                    () -> ui.toon("⚠️ Deze kamer bestaat niet.")
            );
        } catch (NumberFormatException e) {
            ui.toon("⚠️ Ongeldige invoer.");
        }
    }

    // Nodig voor ResetSpelCommand
    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    public GameUI getUI() {
        return this.ui;
    }
}
