package game;

public class KamerReview extends Kamer {
    private Vraag vraag;

    public KamerReview() {
        super("Sprint Review");

        // Meerkeuzevraag instellen
        this.vraag = new MeerkeuzeVraag(
                "Een stakeholder zegt: 'Deze feature is niet bruikbaar.' Wat doe je?",
                new String[] {
                        "A) Je negeert het — de sprint is toch al klaar.",
                        "B) Je gebruikt de feedback om het product te verbeteren."
                },
                "b"
        );
    }

    @Override
    public boolean voerUit() {
        boolean juist = vraag.stelVraag();
        if (juist) {
            System.out.println("✅ Goed! Je laat zien dat feedback waardevol is.");
        } else {
            System.out.println("❌ Fout! Je hebt het monster 'Onbegrip' opgeroepen!");
        }
        return juist;
    }
}
