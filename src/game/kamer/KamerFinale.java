package game.kamer;

import game.Speler;
import game.vraag.MeerkeuzeVraag;
import game.voorwerp.StandaardVoorwerp;

public class KamerFinale extends Kamer {

    public KamerFinale() {
        super(
                "Finale Kamer",
                new MeerkeuzeVraag(
                        "Waar staat TIA voor in scrum?",
                        new String[]{
                                "A) Team, Instructie, en Activiteit",
                                "B) Transparantie, Inspectie en Aanpassing",
                                "C) Technische Integratie Aanpak"
                        },
                        "B" // Consistent met de antwoordopties
                ),
                new StandaardVoorwerp("Scimitar", "🗡️ Je verslaat het monster met de Ottomaanse Scimitar!")
        );
    }

    @Override
    public boolean voerUit(Speler speler) {
        System.out.println("Je bent in de kamer: " + getNaam());
        boolean juist = getVraag().stelVraag(speler);
        if (juist) {
            System.out.println("✅ Geweldig! Je hebt het Scrum Escape Game gewonnen!");
        } else {
            System.out.println("❌ Fout! Je hebt het monster 'ScrumVergeetMonster' opgeroepen!");
        }
        return juist;
    }
}
