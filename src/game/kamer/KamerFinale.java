package game.kamer;

import game.vraag.InvulVraag;
import game.vraag.Vraag;

public class KamerFinale extends Kamer {
    private Vraag vraag;

    public KamerFinale() {
        super("Finale TIA Kamer");

        this.vraag = new InvulVraag(
                "Wat is het belangrijkste doel van Scrum? (typ exact: samenwerking)",
                "samenwerking"
        );
    }

    @Override
    public boolean voerUit() {
        boolean juist = vraag.stelVraag();
        if (juist) {
            System.out.println("🎉 Geweldig! Je hebt het Scrum Escape Game gewonnen!");
            System.out.println("💡 TIA = Transparantie, Inspectie en Aanpassing.");
        } else {
            System.out.println("❌ Fout! Je hebt het monster 'ScrumVergeetMonster' opgeroepen!");
        }
        return juist;
    }
}
