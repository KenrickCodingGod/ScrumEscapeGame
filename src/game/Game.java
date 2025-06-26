package game;

import game.assistent.*;
import game.command.*;
import game.joker.*;
import game.kamer.*;
import game.observer.GameStatusObserver;
import game.voorwerp.Readable;
import game.vraag.*;
import game.voorwerp.*;
import game.hint.*;

import java.util.*;

public class Game {
    private static final int FINALE_KAMER_NUMMER = 6;
    private final List<Kamer> kamers = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private Speler speler;

    public Game() {
        speler = new Speler();
        initialiseerKamers();
        speler.setHuidigeKamer(kamers.getFirst());
        speler.voegObserverToe(new GameStatusObserver());
    }

    private void initialiseerKamers() {
        kamers.add(new Kamer(1, "Sprint Planning",
                new InvulVraag("Wat is meestal het laatste op de planning als het gaat om coderen?", "testen"),
                new Zwaard("Diamanten Zwaard", "🗡️ Je doet 10 hartjes damage!"),
                new Boek("Sprint Strategieboek", "📘 Je ontdekt dat testen vaak het sluitstuk is van een goede planning."),
                "HelpHint: Wat doen developers aan het einde van hun process?",
                new Monster("Scopezilla", "Te veel werk zonder afstemming toegevoegd.")
        ));

        kamers.add(new Kamer(2, "Daily Scrum",
                new InvulVraag("Welke mensen zitten er ALTIJD bij de Daily Scrum?", "developers"),
                new Zwaard("Stand-up Speer", "🗡️ Je prikt het monster door met de scherpte van dagelijkse communicatie!"),
                new Boek("All wetende boek", "Je hebt geleerd dat het antwoord developers, product owners of scrum master is."),
                "HelpHint: Wie werken er dagelijks aan de code / project?",
                new Monster("TeamStilte Zombie", "Je team communiceert niet goed.")
        ));

        kamers.add(new Kamer(3, "Scrum Board",
                new MeerkeuzeVraag("Wat hoort NIET op een Scrum Board?", new String[]{
                        "A) Taken en user stories",
                        "B) De persoonlijke agenda van de Product Owner",
                        "C) Epics en bugs",
                        "D) Review / testen"
                }, "b"),
                new Zwaard("Excalibur", "🗡️ Je vernietigt het monster met Excalibur!"),
                new Boek("Scrum Bord Bijbel", "📘 Je leert dat persoonlijke agenda's niets te zoeken hebben op een professioneel Scrum Board."),
                "HelpHint: Is dit een werkbord of persoonlijke planner?",
                new Monster("Chaos Tornado", "Geen overzicht op de taken.")
        ));

        kamers.add(new Kamer(4, "Sprint Review",
                new MatchVraag(
                        "\nWat hoort bij wie of wat tijdens de Sprint Review?",
                        new String[]{"Scrum Team", "Stakeholders", "Product Owner"},
                        new String[]{"Ontvangen informatie en geven feedback", "Het bespreken van de Product Backlog", "Bespreken wat is bereikt in de sprint"},
                        "A3 B1 C2"
                ),
                new Zwaard("Review Riek", "🗡️ Je steekt het monster neer met de scherpe feedback van stakeholders."),
                new Boek("Scrum HandBoek", "Je hebt geleerd dat er 3 fases zijn: begin, midden en einde."),
                "HelpHint: Kijk goed naar de antwoorden.",
                new Monster("FeedbackFobie", "Stakeholders snappen het resultaat niet.")
        ));

        kamers.add(new Kamer(5, "Sprint Retrospective",
                new InvulVraag("In een retrospective evalueer je 2 onderdelen. Eén is 'het proces', wat is de andere?", "samenwerking"),
                new Zwaard("Katana", "🗡️ Je slaat het monster doormidden als in Fruit Ninja!"),
                new Boek("Retro Reflector", "📘 Je leert dat samenwerking en proces beide geëvalueerd worden in een goede retrospective."),
                "HelpHint: Denk aan samenwerking in het team.",
                new Monster("LoopSpook", "Je leert niet van fouten.")
        ));

        kamers.add(new KamerFinale(FINALE_KAMER_NUMMER));
    }


    public void start() {
        toonIntroductie();
        kiesJoker();

        while (true) {
            System.out.print(">> ");
            String input = scanner.nextLine().toLowerCase();
            verwerkInput(input);
        }
    }

    private void toonIntroductie() {
        System.out.println("------------------------------\n🏢 Welkom bij Scrum Escape!\n------------------------------");
        System.out.println("Kies je joker:");
        System.out.println("1. HintJoker (bruikbaar in alle kamers) *gebruik door 'hintjoker' te typen*");
        System.out.println("2. KeyJoker (bruikbaar in kamer 2 en 4) *gebruik door 'keyjoker' te typen*\n------------------------------");
        System.out.println("Gebruik een assistent in kamer 1 en 3 door 'gebruik assistent' te typen.");
        System.out.println("Je kan ook een boek activeren tijdens elke fase van het spel door 'gebruik boek' te type");
        System.out.println("Zwaard activeer je op dezelfde manier. Maar is niet altijd beschickbaar");
        System.out.println("Commando's: 'status', 'reset', 'ga naar kamer X'\n------------------------------");
    }

    private void kiesJoker() {
        System.out.print(">> ");
        String gekozenjoker = scanner.nextLine();
        if (gekozenjoker.equals("1")) {
            CommandUitvoerder.voerUit(new KiesJokerCommand(speler, new HintJoker(new DefaultHintProvider())));
            System.out.println("Je hebt gekozen voor de HintJoker.");
        }
        else if (gekozenjoker.equals("2")) {
            CommandUitvoerder.voerUit(new KiesJokerCommand(speler, new KeyJoker()));
            System.out.println("Je hebt gekozen voor de KeyJoker.");
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
                    System.out.println("❓ Onbekend commando.");
                }
            }
        }
    }

    private void gebruikAssistent() {
        int kamerNr = speler.getHuidigeKamer().getKamerNummer();
        if (kamerNr == 1 || kamerNr == 3) {
            Assistent assistent = new Assistent(List.of(
                    new HintAssistent(speler),
                    new StappenplanHulpmiddel(),
                    new Motivator()
            ));
            assistent.activeer();

        } else {
            System.out.println("❌ In deze kamer is geen assistent beschikbaar.");
        }
    }


    private void verwerkKamerBezoek(String input) {
        try {
            int kamerNr = Integer.parseInt(input.replaceAll("\\D+", ""));
            Optional<Kamer> kamerOpt = kamers.stream()
                    .filter(k -> k.getKamerNummer() == kamerNr)
                    .findFirst();

            if (kamerOpt.isPresent()) {
                bezoekKamer(kamerOpt.get());
            } else {
                System.out.println("⚠️ Deze kamer bestaat niet.");
            }
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Ongeldige invoer.");
        }
    }

    private void verwerkVoltooideKamer() {
        System.out.println("Goedzo ga zo door!");
        int huidigeIndex = kamers.indexOf(speler.getHuidigeKamer());
        if (huidigeIndex + 1 < kamers.size()) {
            speler.setHuidigeKamer(kamers.get(huidigeIndex + 1));
        } else {
            System.out.println("🎉 Je hebt alle kamers doorlopen!");
            System.exit(0);
        }
    }


    private void bezoekKamer(Kamer kamer) {
        int verwachteKamer = kamers.indexOf(speler.getHuidigeKamer()) + 1;
        if (kamer.getKamerNummer() != verwachteKamer) {
            System.out.println("🚫 Je mag alleen naar de eerstvolgende kamer: " + verwachteKamer);
            return;
        }

        boolean correct = kamer.voerUit(speler);
        if (correct) {
            verwerkVoltooideKamer();
            return;
        }

        Monster monster = kamer.getMonster();
        CommandUitvoerder.voerUit(new VoegMonsterToeCommand(speler, monster));

        if (vraagOmHint(kamer)) {
            toonHintVoorKamer(kamer);
        }

        if (vraagOmVoorwerpGebruik(kamer, monster)) {
            boolean opnieuwJuist = kamer.voerUit(speler);
            if (opnieuwJuist) {
                verwerkVoltooideKamer();
            }
        } else {
            System.out.println("⚠️ Je hebt het monster niet verslagen. Probeer later opnieuw.");
        }
    }

    private boolean vraagOmHint(Kamer kamer) {
        System.out.println("❓ Wil je een hint? (ja/nee)\n------------------------------");
        return scanner.nextLine().equalsIgnoreCase("ja");
    }

    private void toonHintVoorKamer(Kamer kamer) {
        HintFactory hintFactory = new HintFactory();
        Hint hint = hintFactory.getRandomProvider(kamer.getHint()).geefHint();
        System.out.println(hint.geefHint());
    }


    private boolean vraagOmVoorwerpGebruik(Kamer kamer, Monster monster) {
        Weapon zwaard = kamer.getZwaard();
        Readable boek = kamer.getBoek();

        System.out.println("------------------------------\nEr is een monster verschenen: " + monster.getNaam() + "\nJe ziet de volgende items die je zouden kunnen helpen:\nzwaard (type 'gebruik zwaard')\nboek (type 'gebruik boek')\nje kan ook niets doen\n");
        System.out.print(">");


        String input = scanner.nextLine().toLowerCase();

        switch (input) {
            case "gebruik zwaard" -> {
                if (zwaard != null) {
                    System.out.println(zwaard.attack(monster));
                    System.out.println("✅ Monster verslagen. Je mag de vraag opnieuw beantwoorden.");
                    return true;
                } else {
                    System.out.println("❌ Je hebt geen zwaard.");
                    return false;
                }
            }
            case "gebruik boek" -> {
                if (boek != null) {
                    System.out.println("📖 " + boek.showMessage());
                    System.out.println("ℹ️ Helaas helpt een boek niet tegen een monster :/.");
                } else {
                    System.out.println("❌ Je hebt geen boek.");
                }
                return false;
            }
            case "" -> {
                System.out.println("⚠️ Je hebt het monster niet verslagen.");
                return false;
            }
            default -> {
                System.out.println("❌ Ongeldig commando.");
                return vraagOmVoorwerpGebruik(kamer, monster); // probeer opnieuw
            }
        }
    }

    private void resetSpel() {
        speler = new Speler();
        speler.voegObserverToe(new GameStatusObserver());
        System.out.println("🔁 Spel wordt opnieuw gestart...\n");
        start();
    }

    private void toonStatus() {
        Kamer kamer = speler.getHuidigeKamer();
        System.out.println("\n--- SPELER STATUS ---");
        System.out.println("Kamer " + (kamer.getKamerNummer()-1) + " van " + kamers.size());
        System.out.println("Actieve monsters: " + speler.getMonsterNamenAlsString());
    }
}
