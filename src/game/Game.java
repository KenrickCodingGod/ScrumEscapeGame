package game;

import game.assistent.Assistent;
import game.assistent.HintAssistent;
import game.assistent.Motivator;
import game.assistent.StappenplanHulpmiddel;
import game.joker.HintJoker;
import game.joker.KeyJoker;

import java.security.Key;
import java.util.Scanner;
import game.hint.FunnyHint;
import game.hint.HelpHint;
import game.hint.Hint;
import game.kamer.Kamer;
import game.kamer.KamerFinale;
import game.observer.GameStatusObserver;
import game.vraag.InvulVraag;
import game.vraag.MeerkeuzeVraag;
import game.voorwerp.StandaardVoorwerp;
import game.hint.HintFactory;
import game.hint.HintProvider;

import java.util.*;

public class Game {
    private final List<Kamer> kamers = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final DatabaseManager db = new DatabaseManager();
    private Speler speler;

    public Game() {
        speler = db.laadVoortgang();
        speler.voegObserverToe(new GameStatusObserver());

        kamers.add(new Kamer(1,
                "Sprint Planning",
                new InvulVraag("Wat is meestal het laatste op de planning als het gaat om coderen?", "testen"),
                new StandaardVoorwerp("Diamanten Zwaard", "🗡️ Je doet 10 hartjes damage!")
        ));

        kamers.add(new Kamer(2,
                "Daily Scrum",
                new InvulVraag("Welke mensen zitten er ALTIJD bij de Daily Scrum?", "developers"),
                new StandaardVoorwerp("All wetende boek", "📖 Je hebt het monster verslagen met je enorme kennis en je hebt geleerd dat het antwoord developers, product owners of scrum master is.")
        ));

        kamers.add(new Kamer(3,
                "Scrum Board",
                new MeerkeuzeVraag(
                        "Wat hoort NIET op een Scrum Board?",
                        new String[]{
                                "A) Taken en user stories",
                                "B) De persoonlijke agenda van de Product Owner",
                                "C) Epics en bugs",
                                "D) Review / testen"
                        },
                        "b"
                ),
                new StandaardVoorwerp("Excalibur", "🗡️ Je gebruikt de magische krachten van Excalibur om het monster te vernietigen!")
        ));

        kamers.add(new Kamer(4,
                "Sprint Review",
                new InvulVraag("In welke phase van de Sprint vindt de Sprint Review plaats?", "einde"),
                new StandaardVoorwerp("Scrum HandBoek", "📖 Het monster kan niet op tegen je HandBoek van trucjes! Je hebt ook geleerd dat er 3 phases zijn ; begin, midden en einde")
        ));

        kamers.add(new Kamer(5,
                "Sprint Retrospective",
                new InvulVraag("In een retrospective evalueer je 2 onderdelen, één van deze is 'het process' wat is de andere?", "samenwerking"),
                new StandaardVoorwerp("Katana", "🗡️ Je slaat het monster doormidden alsof het een fruit is uit Fruit Ninja!")
        ));

        kamers.add(new KamerFinale(6));
    }

    public void start() {
        Speler speler = new Speler();
        speler.voegObserverToe(new GameStatusObserver());
        Scanner scanner = new Scanner(System.in);

        System.out.println("🏢 Welkom bij Scrum Escape!");
        System.out.println("Kies je joker:");
        System.out.println("Je hebt ook toegang tot een Hulpassistent in kamer 1 en 3 door 'gebruik assistent' te typen.");
        System.out.println("1. HintJoker (bruikbaar in alle kamers) *gebruik je door 'hintjoker' te typen*");
        System.out.println("2. KeyJoker (bruikbaar in kamer 2 en 4) *gebruik je door 'keyjoker' te typen*");
        System.out.println("Maak een keuze (1 of 2): vervolgens Typ 'status', 'reset' of 'ga naar kamer X'.");

        while (speler.getPositie() < kamers.size()) {
            System.out.print(">> ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("status")) {
                toonStatus(speler, kamers);
            } else if (input.equals("reset")) {
                db.resetVoortgang();
                speler = new Speler();
                speler.voegObserverToe(new GameStatusObserver());
                System.out.println("🔁 Spel is opnieuw gestart.");
            } else if (input.equals("1")) {
                speler.kiesJoker(new HintJoker());
                System.out.println("Je hebt gekozen voor de HintJoker.");
            } else if (input.equals("gebruik assistent")) {
                if (speler.getPositie() == 0 || speler.getPositie() == 2) { // kamer 1 en 3
                    Assistent assistent = new Assistent(
                            new HintAssistent(speler.getPositie()),
                            new StappenplanHulpmiddel(),
                            new Motivator()
                    );
                    assistent.activeer();
                } else {
                    System.out.println("❌ In deze kamer is geen assistent beschikbaar.");
                }
            } else if (input.equals("2")) {
                speler.kiesJoker(new KeyJoker());
                System.out.println("Je hebt gekozen voor de KeyJoker.");
            }  else if (input.startsWith("ga naar kamer")) {
                try {
                    int kamerNr = Integer.parseInt(input.replaceAll("\\D+", ""));
                    Optional<Kamer> kamerOpt = kamers.stream()
                            .filter(k -> k.getKamerNummer() == kamerNr)
                            .findFirst();

                    if (kamerOpt.isPresent()) {
                        Kamer kamer = kamerOpt.get();

                        if (kamer.getKamerNummer() == speler.getPositie() + 1) {
                            boolean correct = kamer.voerUit(speler);

                            if (correct) {
                                if (kamerNr < 6) {
                                    System.out.println("Goedzo ga zo door!");
                                }
                                speler.setPositie(speler.getPositie() + 1);
                                db.slaVoortgangOp(speler.getPositie(), speler.getMonsters());
                            } else {
                                Monster monster = bepaalMonsterVoorKamer(kamer.getNaam());
                                speler.voegMonsterToe(monster);
                                db.slaVoortgangOp(speler.getPositie(), speler.getMonsters());

                                System.out.println("❓ Wil je een hint? (ja/nee)\n------------------------------");
                                String hintKeuze = scanner.nextLine().toLowerCase();

                                if (hintKeuze.equals("ja")) {
                                    String inhoudelijkeHint = switch (kamer.getNaam()) {
                                        case "Sprint Planning" -> "HelpHint: Wat doen developers aan het einde van hun process?";
                                        case "Daily Scrum" -> "HelpHint: Wie werken er dagelijks(daily) aan de code / project?";
                                        case "Scrum Board" -> "HelpHint: Denk goed na: is dit een werkbord of een persoonlijke planner?";
                                        case "Sprint Review" -> "HelpHint: Wanneer toon je het werk aan de stakeholders?";
                                        case "Sprint Retrospective" -> "HelpHint: Denk aan de situatie binnen de groep";
                                        case "Finale Kamer" -> "HelpHint: TIA staat voor drie kernwaarden van Scrum. Wat betekenen ze?";
                                        default -> "HelpHint: Gebruik je Scrum-kennis goed!";
                                    };

                                    HintFactory hintFactory = new HintFactory();
                                    HintProvider provider = hintFactory.getRandomProvider(inhoudelijkeHint);
                                    Hint hint = provider.geefHint();
                                    System.out.println(hint.geefHint());
                                }

                                System.out.println("------------------------------\n❓ Wil je het voorwerp '" + kamer.getVoorwerp().getNaam() +
                                        "' gebruiken om het monster te verslaan? (ja/nee)\n------------------------------");
                                String keuze = scanner.nextLine().toLowerCase();

                                if (keuze.equals("ja")) {
                                    System.out.println(kamer.getVoorwerp().gebruik(monster));
                                    System.out.println("✅ Monster verslagen. Je mag de vraag opnieuw beantwoorden.");

                                    boolean opnieuwJuist = kamer.voerUit(speler);
                                    if (opnieuwJuist) {
                                        System.out.println("Goedzo ga zo door!");
                                        speler.setPositie(speler.getPositie() + 1);
                                        db.slaVoortgangOp(speler.getPositie(), speler.getMonsters());
                                    }
                                } else {
                                    System.out.println("⚠️ Je hebt het monster niet verslagen. Probeer later opnieuw.");
                                }
                            }
                        } else {
                            System.out.println("🚫 Je mag alleen naar de eerstvolgende kamer: " + (speler.getPositie() + 1));
                        }
                    } else {
                        System.out.println("⚠️ Deze kamer bestaat niet.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("⚠️ Ongeldige invoer.");
                }
            } else {
                System.out.println("❓ Onbekend commando.");
            }
        }

        System.out.println("🎉 Je hebt alle kamers doorlopen!");
    }



    private void toonStatus(Speler speler, List<Kamer> kamers) {
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


    private Monster bepaalMonsterVoorKamer(String kamerNaam) {
        return switch (kamerNaam) {
            case "Sprint Planning" -> new Monster("Scopezilla", "Te veel werk zonder afstemming toegevoegd.");
            case "Daily Scrum" -> new Monster("TeamStilte Zombie", "Je team communiceert niet goed.");
            case "Scrum Board" -> new Monster("Chaos Tornado", "Geen overzicht op de taken.");
            case "Sprint Review" -> new Monster("FeedbackFobie", "Stakeholders snappen het resultaat niet.");
            case "Sprint Retrospective" -> new Monster("LoopSpook", "Je leert niet van fouten.");
            case "Finale TIA Kamer" -> new Monster("TIAverslinder", "Je bent de kern van Scrum vergeten: TIA.");
            default -> new Monster("Onbekend", "Onbekende fout.");
        };
    }
}
