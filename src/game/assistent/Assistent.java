package game.assistent;

import game.hint.Hint;

public class Assistent {
    private final HintAssistent hint;
    private final EducatiefHulpmiddel hulpmiddel;
    private final Motivator motivator;

    public Assistent(HintAssistent hint, EducatiefHulpmiddel hulpmiddel, Motivator motivator) {
        this.hint = hint;
        this.hulpmiddel = hulpmiddel;
        this.motivator = motivator;
    }

    public void activeer() {
        System.out.println("🧑‍💻 Assistent geactiveerd!");
        hint.voerUit();
        hulpmiddel.toon();
        motivator.toonMelding();
    }
}
