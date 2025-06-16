package game;

import game.hint.FunnyHint;
import game.hint.HelpHint;
import game.hint.Hint;
import game.kamer.Kamer;
import game.kamer.KamerFinale;
import game.observer.GameStatusObserver;
import game.vraag.InvulVraag;
import game.vraag.MeerkeuzeVraag;
import game.voorwerp.StandaardVoorwerp;

import java.util.*;

public class Game {
    private final List<Kamer> kamers = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final DatabaseManager db = new DatabaseManager();
    private Speler speler;

    public Game() {
        speler = db.laadVoortgang();
        speler.voegObserverToe(new GameStatusObserver());

        kamers.add(new Kamer(
                "Sprint Planning",
                new InvulVraag("Noem één taak die past binnen een sprint van 2 weken.", "testen"),
                new StandaardVoorwerp("ScrumChart", "Je bekijkt het Scrum Chart voor overzicht.")
        ));

        kamers.add(new Kamer(
                "Daily Scrum",
                new InvulVraag("Wat bespreek je tijdens een Daily Scrum?", "voortgang"),
                new StandaardVoorwerp("ScrumTimer", "De tijd wordt goed bewaakt.")
        ));

        kamers.add(new Kamer(
                "Scrum Board",
                new MeerkeuzeVraag(
                        "Wat hoort NIET op een Scrum Board?",
                        new String[]{
                                "A) Taken en user stories",
                                "B) De persoonlijke agenda van de PO",
                                "C) Epics en bugs"
                        },
                        "b"
                ),
                new StandaardVoorwerp("ScrumMasterWhistle", "Je fluit en herinnert iedereen aan de regels van Scrum!")
        ));

        kamers.add(new Kamer(
                "Sprint Review",
                new InvulVraag("Wat toon je tijdens een Sprint Review?", "oplevering"),
                new StandaardVoorwerp("RetroMirror", "Je reflecteert met de RetroMirror.")
        ));

        kamers.add(new Kamer(
                "Sprint Retrospective",
                new InvulVraag("Wat bespreek je in een Retrospective?", "verbeterpunten"),
                new StandaardVoorwerp("KamerInfoBoek", "Je leest de uitleg over deze kamer.")
        ));

        kamers.add(new KamerFinale());
    }

    public void start() {
        System.out.println("🏢 Welkom bij Scrum Escape!");
        System.out.println("Typ 'status', 'reset' of 'ga naar kamer X'");

        while (speler.getPositie() < kamers.size()) {
            System.out.print(">> ");
            String input = scanner.nextLine().toLowerCase();

            if (input.equals("status")) {
                toonStatus();
            } else if (input.equals("reset")) {
                db.resetVoortgang();
                speler = new Speler();
                speler.voegObserverToe(new GameStatusObserver());
                System.out.println("🔁 Spel is opnieuw gestart.");
            } else if (input.startsWith("ga naar kamer")) {
                try {
                    int kamerNr = Integer.parseInt(input.replaceAll("\\D+", ""));
                    if (kamerNr - 1 == speler.getPositie()) {
                        Kamer huidigeKamer = kamers.get(speler.getPositie());

                        // 👇 Eerst normaal de vraag stellen
                        boolean correct = huidigeKamer.voerUit();

                        if (correct) {
                            speler.setPositie(speler.getPositie() + 1);
                            db.slaVoortgangOp(speler.getPositie(), speler.getMonsters());
                        } else {
                            // ❌ Fout → monster en hint automatisch
                            Monster monster = bepaalMonsterVoorKamer(huidigeKamer.getNaam());
                            speler.voegMonsterToe(monster);
                            db.slaVoortgangOp(speler.getPositie(), speler.getMonsters());

                            System.out.println("🧌 Monster opgeroepen: " + monster.getNaam());

                            // 🧠 Hint automatisch na fout
                            Hint automatischeHint = new Random().nextBoolean()
                                    ? new HelpHint("Tip: Herlees goed wat de vraag vraagt.")
                                    : new FunnyHint();
                            System.out.println("💡 Hint: " + automatischeHint.geefHint());

                            // 🔨 Vraag om voorwerp
                            System.out.println("❓ Wil je het voorwerp '" + huidigeKamer.getVoorwerp().getNaam() +
                                    "' gebruiken om het monster te verslaan? (j/n)");
                            String keuze = scanner.nextLine().toLowerCase();

                            if (keuze.equals("j")) {
                                huidigeKamer.getVoorwerp().gebruik(monster);
                                System.out.println("✅ Monster verslagen. Je mag de vraag opnieuw beantwoorden.");

                                // ⏩ Tweede kans: speler mag opnieuw proberen (nu mét optionele hint)
                                System.out.println("Wil je een extra hint? Typ 'hint' of druk op Enter:");
                                String extraKeuze = scanner.nextLine().toLowerCase();
                                if (extraKeuze.equals("hint")) {
                                    Hint extraHint = new Random().nextBoolean()
                                            ? new HelpHint("Let op de Scrum-regels die je hebt geleerd.")
                                            : new FunnyHint();
                                    System.out.println("💡 Extra hint: " + extraHint.geefHint());
                                }

                                boolean opnieuwJuist = huidigeKamer.voerUit();
                                if (opnieuwJuist) {
                                    speler.setPositie(speler.getPositie() + 1);
                                    db.slaVoortgangOp(speler.getPositie(), speler.getMonsters());
                                }
                            } else {
                                System.out.println("⚠️ Je hebt het monster niet verslagen. Probeer later opnieuw.");
                            }
                        }
                    } else {
                        System.out.println("🚫 Je mag alleen naar de eerstvolgende kamer.");
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

    private Monster bepaalMonsterVoorKamer(String kamerNaam) {
        return switch (kamerNaam) {
            case "Sprint Planning" -> new Monster("Scope Creep", "Te veel werk zonder afstemming toegevoegd.");
            case "Daily Scrum" -> new Monster("Vertraging", "Je team communiceert niet goed.");
            case "Scrum Board" -> new Monster("Chaos", "Geen overzicht op de taken.");
            case "Sprint Review" -> new Monster("Onbegrip", "Stakeholders snappen het resultaat niet.");
            case "Sprint Retrospective" -> new Monster("Herhaling", "Je leert niet van fouten.");
            case "Finale TIA Kamer" -> new Monster("ScrumVergeetMonster", "Je bent de kern van Scrum vergeten: TIA.");
            default -> new Monster("Onbekend", "Onbekende fout.");
        };
    }
}
