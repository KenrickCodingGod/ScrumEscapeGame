package game.hint;

import java.util.Random;

public class FunnyHint implements Hint {
    private static final String[] grappen = {
            "Scrum is geen dansstijl, al denken sommigen van wel.",
            "Scrum zonder retrospective is als pizza zonder kaas.",
            "Daily stand-up is geen karaoke sessie.",
            "De PO wil graag werkende software... geen PowerPoint.",
            "Bugs zijn gewoon verborgen features, toch?"
    };

    @Override
    public String geefHint() {
        int index = new Random().nextInt(grappen.length);
        return grappen[index];
    }
}
