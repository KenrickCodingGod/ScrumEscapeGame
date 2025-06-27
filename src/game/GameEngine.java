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
import game.joker.HintJoker;
import game.joker.Joker;
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
        speler.setHuidigeKamer(null);
        speler.attach(new GameStatusObserver());
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
            kiesJoker(); // herhaal keuze als onjuist
            return;
        }

        CommandUitvoerder.voerUit(new KiesJokerCommand(speler, gekozenJoker));
        ui.toon(bericht);
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
        speler.setHuidigeKamer(null);
        speler.attach(new GameStatusObserver());
        ui.toon("🔁 Spel wordt opnieuw gestart...\n");
        start();
    }

    public void gebruikAssistent() {
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


    public void verwerkKamerBezoek(String input) {
        try {
            int index = Integer.parseInt(input.replaceAll("\\D+", "")) - 1; // "kamer 2" → index 1


            if (index < 0 || index >= kamers.size()) {
                ui.toon("⚠️ Deze kamer bestaat niet.");
                return;
            }

            Kamer kamer = kamers.get(index);
            bezoekKamer(kamer);
        } catch (NumberFormatException e) {
            ui.toon("⚠️ Ongeldige invoer.");
        }
    }



    private void bezoekKamer(Kamer kamer) {
        int huidigeIndex = speler.getHuidigeKamer() == null ? -1 : kamers.indexOf(speler.getHuidigeKamer());
        int doelIndex = kamers.indexOf(kamer);

        if (doelIndex != huidigeIndex + 1) {
            ui.toon("🚫 Je mag alleen naar de eerstvolgende kamer: " + (huidigeIndex + 2));
            return;
        }


        boolean correct = kamer.voerUit(speler);
        if (correct) {
            speler.setHuidigeKamer(kamer);
            verwerkVoltooideKamer();
            return;
        }


        Monster monster = kamer.getMonster();
        CommandUitvoerder.voerUit(new VoegMonsterToeCommand(speler, monster));

        if (ui.bevestig("------------------------------\n❓ Wil je een hint?")) {
            toonHintVoorKamer(kamer);
        }


        boolean monsterVerslagen = false;
        while (!monsterVerslagen) {
            monsterVerslagen = vraagOmVoorwerpGebruik(kamer, monster);
            if (!monsterVerslagen) {
                ui.toon("🛑 Monster niet verslagen. Probeer nogmaals.");
            }
        }


        boolean opnieuwJuist = false;
        while (!opnieuwJuist) {
            ui.toon("Probeer nu opnieuw de vraag te beantwoorden:");
            opnieuwJuist = kamer.voerUit(speler);
            if (!opnieuwJuist) {
                ui.toon("❌ Fout antwoord, probeer het nogmaals.");
            }
        }

        speler.setHuidigeKamer(kamer);
        verwerkVoltooideKamer();
    }

    private boolean vraagOmVoorwerpGebruik(Kamer kamer, Monster monster) {
        ui.toon("------------------------------\nEr is een monster: " + monster.getNaam());
        ui.toon("Gebruik een item: 'gebruik zwaard', 'gebruik boek' of doe niets\n------------------------------");
        String input = ui.leesInput().toLowerCase();
        Weapon zwaard = kamer.getZwaard();
        game.voorwerp.Readable boek = kamer.getBoek();

        switch (input) {
            case "gebruik zwaard":
                if (zwaard != null) {
                    ui.toon(zwaard.attack(monster));
                    // Controleer hier of monster dood is, bijvoorbeeld:
                    return true;
                } else {
                    ui.toon("❌ Geen zwaard beschikbaar.");
                    return false;
                }
            case "gebruik boek":
                if (boek != null) {
                    ui.toon("📖 " + boek.showMessage());

                } else {
                    ui.toon("❌ Geen boek beschikbaar.");

                }
                return true;
            case "":
                return false;
            default:
                ui.toon("❓ Onbekend commando, probeer opnieuw.");
                return vraagOmVoorwerpGebruik(kamer, monster);
        }
    }



    private void toonHintVoorKamer(Kamer kamer) {
        HintFactory factory = new HintFactory();
        Hint hint = factory.getRandomProvider(kamer.getHint()).geefHint();
        ui.toon(hint.geefHint());
    }

    private void verwerkVoltooideKamer() {
        ui.toon("✅ Goedzo, ga zo door!");
        int huidigeIndex = kamers.indexOf(speler.getHuidigeKamer());

        if (huidigeIndex + 1 < kamers.size()) {
            ui.toon("➡️ Typ 'ga naar kamer " + (huidigeIndex + 2) + "' om door te gaan.");
        } else {
            ui.toon("🎉 Je hebt alle kamers doorlopen!");
            System.exit(0);
        }
    }


}