package game.kamer;

import game.Speler;
import game.vraag.InvulVraag;
import game.vraag.MeerkeuzeVraag;
import game.vraag.Vraag;
import game.voorwerp.StandaardVoorwerp;


public class KamerFinale extends Kamer {
    private Vraag vraag;


    public KamerFinale() {
        super(
                "Finale Kamer",
                this.vraag = new MeerkeuzeVraag(
                        "Wat is het belangrijkste doel van Scrum? (typ exact: samenwerking)",
                        new String[]{
                                "A) Team, Instructie, en Activiteit",
                                "B) Transparantie, Inspectie en Aanpassing",
                                "C) Technische Integratie Aanpak"
                        },

                        "b"
                ),
                new StandaardVoorwerp("Scimitar", "🗡️ Je verslaat het monster met de Ottomaanse Scimitar!")
        );

    }

    @Override
    public boolean voerUit(Speler speler) {
        System.out.println("Je bent in de kamer: " + getNaam());
        boolean juist = vraag.stelVraag(speler);
        if (juist) {
            System.out.println("✅ Geweldig! Je hebt het Scrum Escape Game gewonnen!");
        } else {
            System.out.println("❌ Fout! Je hebt het monster 'ScrumVergeetMonster' opgeroepen!");
        }
        return juist;
    }
}