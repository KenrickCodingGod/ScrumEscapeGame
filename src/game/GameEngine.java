package game;

import game.assistent.Assistent;
import game.assistent.HintAssistent;
import game.assistent.Motivator;
import game.assistent.StappenplanHulpmiddel;
import game.command.CommandUitvoerder;
import game.command.KiesJokerCommand;
import game.command.VoegMonsterToeCommand;
import game.hint.Hint;
import game.hint.HintFactory;
import game.joker.DefaultHintProvider;
import game.joker.HintJoker;
import game.joker.KeyJoker;
import game.kamer.Kamer;
import game.observer.GameStatusObserver;
import game.voorwerp.Weapon;

import java.util.List;
import java.util.Optional;

public class GameEngine {
    private final GameUI ui;
    private final List<Kamer> kamers;
    private Speler speler;

    public GameEngine(GameUI ui, KamerFactory kamerFactory) {
        this.ui = ui;
        this.kamers = kamerFactory.maakKamers();
        this.speler = new Speler();
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
        if (gekozen.equals("1")) {
            CommandUitvoerder.voerUit(new KiesJokerCommand(speler, new HintJoker(new DefaultHintProvider())));
            ui.toon("Je hebt gekozen voor de HintJoker.");
        } else if (gekozen.equals("2")) {
            CommandUitvoerder.voerUit(new KiesJokerCommand(speler, new KeyJoker()));
            ui.toon("Je hebt gekozen voor de KeyJoker.");
        }
    }

    private void verwerkInput(String input) {
        switch (input) {
            case "status" -> toonStatus();
            case "reset" -> resetSpel();
            case "gebruik assistent" -> gebruikAssistent();
            default -> {
                if (input.startsWith("ga naar kamer")) {
                    verwerkKamerBezoek(input);
                } else {
                    ui.toon("❓ Onbekend commando.");
                }
            }
        }
    }

    public void toonStatus() {
        ui.toonStatus(speler, speler.getHuidigeKamer(), kamers.size());
    }

    public void resetSpel() {
        speler = new Speler();
        speler.setHuidigeKamer(kamers.getFirst());
        speler.voegObserverToe(new GameStatusObserver());
        ui.toon("🔁 Spel wordt opnieuw gestart...\n");
        start();
    }

    public void gebruikAssistent() {
        int kamerNr = speler.getHuidigeKamer().getKamerNummer();
        if (kamerNr == 1 || kamerNr == 3) {
            Assistent assistent = new Assistent(List.of(
                    new HintAssistent(speler),
                    new StappenplanHulpmiddel(),
                    new Motivator()
            ));
            assistent.activeer();
        } else {
            ui.toon("❌ Geen assistent in deze kamer.");
        }
    }

    public void verwerkKamerBezoek(String input) {
        try {
            int kamerNr = Integer.parseInt(input.replaceAll("\\D+", ""));
            Optional<Kamer> kamerOpt = kamers.stream()
                    .filter(k -> k.getKamerNummer() == kamerNr)
                    .findFirst();

            if (kamerOpt.isPresent()) {
                bezoekKamer(kamerOpt.get());
            } else {
                ui.toon("⚠️ Deze kamer bestaat niet.");
            }
        } catch (NumberFormatException e) {
            ui.toon("⚠️ Ongeldige invoer.");
        }
    }

    private void bezoekKamer(Kamer kamer) {
        int verwachteKamer = kamers.indexOf(speler.getHuidigeKamer()) + 1;
        if (kamer.getKamerNummer() != verwachteKamer) {
            ui.toon("🚫 Je mag alleen naar de eerstvolgende kamer: " + verwachteKamer);
            return;
        }

        boolean correct = kamer.voerUit(speler);
        if (correct) {
            verwerkVoltooideKamer();
            return;
        }

        Monster monster = kamer.getMonster();
        CommandUitvoerder.voerUit(new VoegMonsterToeCommand(speler, monster));

        if (ui.bevestig("------------------------------\n❓ Wil je een hint?")) {
            toonHintVoorKamer(kamer);
        }

        if (vraagOmVoorwerpGebruik(kamer, monster)) {
            boolean opnieuwJuist = kamer.voerUit(speler);
            if (opnieuwJuist) verwerkVoltooideKamer();
        } else {
            ui.toon("⚠️ Je hebt het monster niet verslagen. Probeer later opnieuw.");
        }
    }

    private boolean vraagOmVoorwerpGebruik(Kamer kamer, Monster monster) {
        ui.toon("------------------------------\nEr is een monster: " + monster.getNaam());
        ui.toon("Gebruik een item: 'gebruik zwaard', 'gebruik boek' of doe niets\n------------------------------");
        String input = ui.leesInput().toLowerCase();
        Weapon zwaard = kamer.getZwaard();
        game.voorwerp.Readable boek = kamer.getBoek();

        return switch (input) {
            case "gebruik zwaard" -> {
                if (zwaard != null) {
                    ui.toon(zwaard.attack(monster));
                    yield true;
                } else {
                    ui.toon("❌ Geen zwaard beschikbaar.");
                    yield false;
                }
            }
            case "gebruik boek" -> {
                if (boek != null) {
                    ui.toon("📖 " + boek.showMessage());
                } else {
                    ui.toon("❌ Geen boek beschikbaar.");
                }
                yield false;
            }
            case "" -> false;
            default -> vraagOmVoorwerpGebruik(kamer, monster);
        };
    }

    private void toonHintVoorKamer(Kamer kamer) {
        HintFactory factory = new HintFactory();
        Hint hint = factory.getRandomProvider(kamer.getHint()).geefHint();
        ui.toon(hint.geefHint());
    }

    private void verwerkVoltooideKamer() {
        ui.toon("Goedzo, ga zo door!");
        int huidigeIndex = kamers.indexOf(speler.getHuidigeKamer());
        if (huidigeIndex + 1 < kamers.size()) {
            speler.setHuidigeKamer(kamers.get(huidigeIndex + 1));
        } else {
            ui.toon("🎉 Je hebt alle kamers doorlopen!");
            System.exit(0);
        }
    }
}