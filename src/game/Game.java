package game;

import java.util.*;

public class Game {
    private final List<Kamer> kamers = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);
    private final DatabaseManager db = new DatabaseManager();
    private Speler speler;

    public Game() {
        speler = db.laadVoortgang(); // voortgang laden

        
        speler.voegObserverToe(new StatusDisplay());
        speler.voegObserverToe(new MonsterLogger());
        speler.voegObserverToe(new DeurController());

        kamers.add(new KamerPlanning());
        kamers.add(new KamerDaily());
        kamers.add(new KamerBoard());
        kamers.add(new KamerReview());
        kamers.add(new KamerRetrospective());
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

                // voeg opnieuw observers toe na reset
                speler.voegObserverToe(new StatusDisplay());
                speler.voegObserverToe(new MonsterLogger());
                speler.voegObserverToe(new DeurController());

                System.out.println("🔁 Spel is opnieuw gestart.");
            } else if (input.startsWith("ga naar kamer")) {
                try {
                    int kamerNr = Integer.parseInt(input.replaceAll("\\D+", ""));
                    if (kamerNr - 1 == speler.getPositie()) {
                        boolean correct = kamers.get(speler.getPositie()).voerUit();
                        if (correct) {
                            speler.setPositie(speler.getPositie() + 1);
                            db.slaVoortgangOp(speler.getPositie(), speler.getMonsters());
                        } else {
                            Monster monster = bepaalMonsterVoorKamer(kamers.get(speler.getPositie()).naam);
                            speler.voegMonsterToe(monster);
                            db.slaVoortgangOp(speler.getPositie(), speler.getMonsters());
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
        System.out.println("📍 Kamer " + (index + 1) + " van " + kamers.size());

        if (index < kamers.size()) {
            System.out.println("🔹 Naam van huidige kamer: " + kamers.get(index).naam);
        } else {
            System.out.println("🏁 Je hebt het spel voltooid!");
        }

        System.out.println("✅ Kamers voltooid: " + index);
        System.out.println("🧌 Actieve monsters: " + speler.getMonsterNamenAlsString());
        System.out.println("💡 Typ 'ga naar kamer " + (index + 1) + "' om verder te gaan.");
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
