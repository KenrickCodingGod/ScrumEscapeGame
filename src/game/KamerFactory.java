package game;

import game.joker.HintJoker;
import game.joker.KeyJoker;
import game.kamer.Kamer;
import game.kamer.NormaleKamer;
import game.kamer.KamerFinale;
import game.voorwerp.Boek;
import game.voorwerp.Zwaard;
import game.vraag.InvulVraag;
import game.vraag.MatchVraag;
import game.vraag.MeerkeuzeVraag;
import java.util.*;

public class KamerFactory {
    public List<Kamer> maakKamers() {
        List<Kamer> kamers = new ArrayList<>();


        kamers.add(new NormaleKamer(1, "Sprint Planning",
                new InvulVraag("Wat is meestal het laatste op de planning als het gaat om coderen?", "testen"),
                new Zwaard("Diamanten Zwaard", "🗡️ Je doet 10 hartjes damage!"),
                new Boek("Sprint Strategieboek", "📘 Je ontdekt dat testen vaak het sluitstuk is van een goede planning."),
                "Sprint Planning: Hoe check je fouten? Door te ......",
                new Monster("Scopezilla", "Te veel werk zonder afstemming toegevoegd."),
                "Sprint Planning: Hoe check je fouten? Door te ......",
                false,
                true
        ));


        kamers.add(new NormaleKamer(2, "Daily Scrum",
                new InvulVraag("Welke mensen zitten er ALTIJD bij de Daily Scrum?", "developers"),
                new Zwaard("Stand-up Speer", "🗡️ Je prikt het monster door met de scherpte van dagelijkse communicatie!"),
                new Boek("All wetende boek", "Je hebt geleerd dat het antwoord developers, product owners of scrum master is."),
                "Daily Scrum: Wat noem je een groep mensen die coderen?",
                new Monster("TeamStilte Zombie", "Je team communiceert niet goed."),
                "Daily Scrum: Wat noem je een groep mensen die coderen?",
                true,
                false
        ));


        kamers.add(new NormaleKamer(3, "Scrum Board",
                new MeerkeuzeVraag("Wat hoort NIET op een Scrum Board?", new String[]{
                        "A) Taken en user stories",
                        "B) De persoonlijke agenda van de Product Owner",
                        "C) Epics en bugs",
                        "D) Review / testen"
                }, "b"),
                new Zwaard("Excalibur", "🗡️ Je vernietigt het monster met Excalibur!"),
                new Boek("Scrum Bord Bijbel", "📘 Je leert dat persoonlijke agenda's niets te zoeken hebben op een professioneel Scrum Board."),
                "Scrum Board: Iets hoort hier echt niet thuis...",
                new Monster("Chaos Tornado", "Geen overzicht op de taken."),
                "Scrum Board: Iets hoort hier echt niet thuis...",
                false,
                true
        ));


        kamers.add(new NormaleKamer(4, "Sprint Review",
                new MatchVraag(
                        "\nWat hoort bij wie of wat tijdens de Sprint Review?",
                        new String[]{"Scrum Team", "Stakeholders", "Product Owner"},
                        new String[]{"Ontvangen informatie en geven feedback", "Het bespreken van de Product Backlog", "Bespreken wat is bereikt in de sprint"},
                        "A3 B1 C2"
                ),
                new Zwaard("Review Riek", "🗡️ Je steekt het monster neer met de scherpe feedback van stakeholders."),
                new Boek("Scrum HandBoek", "Je hebt geleerd dat er 3 fases zijn: begin, midden en einde."),
                "Sprint Review: Denk aan de rollen en hun taak.",
                new Monster("FeedbackFobie", "Stakeholders snappen het resultaat niet."),
                "Sprint Review: Denk aan de rollen en hun taak.",
                true,
                false
        ));


        kamers.add(new NormaleKamer(5, "Sprint Retrospective",
                new InvulVraag("In een retrospective evalueer je 2 onderdelen. Eén is 'het proces', wat is de andere?", "samenwerking"),
                new Zwaard("Katana", "🗡️ Je slaat het monster doormidden als in Fruit Ninja!"),
                new Boek("Retro Reflector", "📘 Je leert dat samenwerking en proces beide geëvalueerd worden in een goede retrospective."),
                "Retrospective: Unscramble het antwoord! : wsanmenerikg",
                new Monster("LoopSpook", "Je leert niet van fouten."),
                "Retrospective: Unscramble het antwoord! : wsanmenerikg",
                false,
                false
        ));


        kamers.add(new KamerFinale(6
        ));


        return kamers;
    }
}
