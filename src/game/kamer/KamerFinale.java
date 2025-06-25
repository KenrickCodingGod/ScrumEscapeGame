package game.kamer;

import game.Speler;
import game.voorwerp.Boek;
import game.voorwerp.Zwaard;
import game.vraag.MeerkeuzeVraag;

public class KamerFinale extends Kamer {
    public KamerFinale(int nummer) {
        super(
                nummer,
                "Finale Kamer",
                new MeerkeuzeVraag(
                        "Waar staat TIA voor in scrum?",
                        new String[]{
                                "A) Team, Instructie, en Activiteit",
                                "B) Transparantie, Inspectie en Aanpassing",
                                "C) Technische Integratie Aanpak"
                        },
                        "b"
                ),
                new Zwaard("Scimitar", "🗡️ Je verslaat het monster met de Ottomaanse Scimitar!"),
                new Boek("g","g")
        );
    }

    public boolean voerUit(Speler speler) {
        System.out.println("Je bent in de kamer: " + getNaam());
        boolean juist = vraag.stelVraag(speler, this);
        if (juist) {
            System.out.println("✅ Geweldig! Je hebt het Scrum Escape Game gewonnen!");
        } else {
            System.out.println("❌ Fout! Je hebt het monster 'ScrumVergeetMonster' opgeroepen!");
        }
        return juist;
    }
}
