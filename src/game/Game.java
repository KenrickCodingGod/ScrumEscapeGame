package game;

import game.assistent.*;
import game.joker.*;
import game.kamer.*;
import game.observer.GameStatusObserver;
import game.vraag.*;
import game.voorwerp.*;
import game.hint.*;

import java.util.*;

public class Game {
    private static final int FINALE_KAMER_NUMMER = 6;
    private final List<Kamer> kamers = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final DatabaseManager db = new DatabaseManager();
    private Speler speler;

    public Game() {
        speler = db.laadVoortgang();
        speler.voegObserverToe(new GameStatusObserver());
        initialiseerKamers();
    }

    private void initialiseerKamers() {
        kamers.add(new Kamer(1, "Sprint Planning",
                new InvulVraag("Wat is meestal het laatste op de planning als het gaat om coderen?", "testen"),
                new StandaardVoorwerp("Diamanten Zwaard", "🗡️ Je doet 10 hartjes damage!")
        ));

        kamers.add(new Kamer(2, "Daily Scrum",
                new InvulVraag("Welke mensen zitten er ALTIJD bij de Daily Scrum?", "developers"),
                new StandaardVoorwerp("All wetende boek", "📖 Je hebt geleerd dat het antwoord developers, product owners of scrum master is.")
        ));

        kamers.add(new Kamer(3, "Scrum Board",
                new MeerkeuzeVraag("Wat hoort NIET op een Scrum Board?", new String[]{
                        "A) Taken en user stories",
                        "B) De persoonlijke agenda van de Product Owner",
                        "C) Epics en bugs",
                        "D) Review / testen"
                }, "b"),
                new StandaardVoorwerp("Excalibur", "🗡️ Je vernietigt het monster met Excalibur!")
        ));

        kamers.add(new Kamer(4, "Sprint Review",
                new InvulVraag("In welke phase van de Sprint vindt de Sprint Review plaats?", "einde"),
                new StandaardVoorwerp("Scrum HandBoek", "📖 Je hebt geleerd dat er 3 fases zijn: begin, midden en einde.")
        ));

        kamers.add(new Kamer(5, "Sprint Retrospective",
                new InvulVraag("In een retrospective evalueer je 2 onderdelen. Eén is 'het proces', wat is de andere?", "samenwerking"),
                new StandaardVoorwerp("Katana", "🗡️ Je slaat het monster doormidden als in Fruit Ninja!")
        ));

        kamers.add(new KamerFinale(FINALE_KAMER_NUMMER));
    }

    public void start() {
        toonIntroductie();
        kiesJoker();

        while (speler.getPositie() < kamers.size()) {
            System.out.print(">> ");
            String input = scanner.nextLine().toLowerCase();
            verwerkInput(input);
        }

        System.out.println("🎉 Je hebt alle kamers doorlopen!");
    }

    private void toonIntroductie() {
        System.out.println("------------------------------\n🏢 Welkom bij Scrum Escape!\n------------------------------");
        System.out.println("Kies je joker:");
        System.out.println("1. HintJoker (bruikbaar in alle kamers) *gebruik door 'hintjoker' te typen*");
        System.out.println("2. KeyJoker (bruikbaar in kamer 2 en 4) *gebruik door 'keyjoker' te typen*\n------------------------------");
        System.out.println("Gebruik een assistent in kamer 1 en 3 door 'gebruik assistent' te typen.");
        System.out.println("Commando's: 'status', 'reset', 'ga naar kamer X'\n------------------------------");
    }

    private void kiesJoker() {
        System.out.print(">> ");
        String gekozenjoker = scanner.nextLine();
        if (gekozenjoker.equals("1")) {
            speler.kiesJoker(new HintJoker());
            System.out.println("Je hebt gekozen voor de HintJoker.");
        }
        else if (gekozenjoker.equals("2")) {
            speler.kiesJoker(new KeyJoker());
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
        int positie = speler.getPositie();
        if (positie == 0 || positie == 2) {
            Assistent assistent = new Assistent(
                    new HintAssistent(positie),
                    new StappenplanHulpmiddel(),
                    new Motivator()
            );
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
        speler.setPositie(speler.getPositie() + 1);
        db.slaVoortgangOp(speler.getPositie(), speler.getMonsters());
    }


    private void bezoekKamer(Kamer kamer) {
        if (kamer.getKamerNummer() != speler.getPositie() + 1) {
            System.out.println("🚫 Je mag alleen naar de eerstvolgende kamer: " + (speler.getPositie() + 1));
            return;
        }

        boolean correct = kamer.voerUit(speler);
        if (correct) {
            verwerkVoltooideKamer();
            return;
        }

        Monster monster = bepaalMonsterVoorKamer(kamer.getKamerNummer());
        speler.voegMonsterToe(monster);
        db.slaVoortgangOp(speler.getPositie(), speler.getMonsters());

        if (vraagOmHint(kamer)) {
            toonHintVoorKamer(kamer.getKamerNummer());
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

    private void toonHintVoorKamer(int kamerNummer) {
        String inhoudelijkeHint = switch (kamerNummer) {
            case 1 -> "HelpHint: Wat doen developers aan het einde van hun process?";
            case 2 -> "HelpHint: Wie werken er dagelijks aan de code / project?";
            case 3 -> "HelpHint: Is dit een werkbord of persoonlijke planner?";
            case 4 -> "HelpHint: Wanneer toon je werk aan stakeholders?";
            case 5 -> "HelpHint: Denk aan samenwerking in het team.";
            case 6 -> "HelpHint: Wat betekent TIA binnen Scrum?";
            default -> "HelpHint: Gebruik je Scrum-kennis goed!";
        };

        HintFactory hintFactory = new HintFactory();
        Hint hint = hintFactory.getRandomProvider(inhoudelijkeHint).geefHint();
        System.out.println(hint.geefHint());
    }

    private boolean vraagOmVoorwerpGebruik(Kamer kamer, Monster monster) {
        System.out.println("------------------------------\n❓ Wil je het voorwerp '" +
                kamer.getVoorwerp().getNaam() + "' gebruiken om het monster te verslaan? (ja/nee)\n------------------------------");

        if (scanner.nextLine().equalsIgnoreCase("ja")) {
            System.out.println(kamer.getVoorwerp().gebruik(monster));
            System.out.println("✅ Monster verslagen. Je mag de vraag opnieuw beantwoorden.");
            return true;
        }

        return false;
    }

    private void resetSpel() {
        db.resetVoortgang();
        speler = new Speler();
        speler.voegObserverToe(new GameStatusObserver());
        System.out.println("🔁 Spel is opnieuw gestart.");
    }

    private void toonStatus() {
        System.out.println("\n--- SPELER STATUS ---");
        int index = speler.getPositie();
        System.out.println("Kamer " + (index + 1) + " van " + kamers.size());

        if (index < kamers.size()) {
            System.out.println("Huidige kamer: " + kamers.get(index).getNaam());
        } else {
            System.out.println("Je hebt het spel voltooid!");
        }

        System.out.println("Kamers voltooid: " + index);
        System.out.println("Actieve monsters: " + speler.getMonsterNamenAlsString());
    }

    private Monster bepaalMonsterVoorKamer(int kamerNummer) {
        return switch (kamerNummer) {
            case 1 -> new Monster("Scopezilla", "Te veel werk zonder afstemming toegevoegd.");
            case 2 -> new Monster("TeamStilte Zombie", "Je team communiceert niet goed.");
            case 3 -> new Monster("Chaos Tornado", "Geen overzicht op de taken.");
            case 4 -> new Monster("FeedbackFobie", "Stakeholders snappen het resultaat niet.");
            case 5 -> new Monster("LoopSpook", "Je leert niet van fouten.");
            case 6 -> new Monster("TIAverslinder", "Je bent de kern van Scrum vergeten: TIA.");
            default -> new Monster("Onbekend", "Onbekende fout.");
        };
    }
}
