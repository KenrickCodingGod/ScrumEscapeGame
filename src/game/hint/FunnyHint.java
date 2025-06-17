package game.hint;

import java.util.Random;

public class FunnyHint implements Hint {
    private static final String[] grappen = {
            "FunnyHint: Scrum is geen dansstijl, al denken sommigen van wel.",
            "FunnyHint: Scrum zonder retrospective is als pizza zonder kaas.",
            "FunnyHint: Daily stand-up is geen karaoke sessie.",
            "FunnyHint: De PO wil graag werkende software... geen PowerPoint.",
            "FunnyHint: Bugs zijn gewoon verborgen features, toch?"
    };

    @Override
    public String geefHint() {
        int index = new Random().nextInt(grappen.length);
        return grappen[index];
    }
}
