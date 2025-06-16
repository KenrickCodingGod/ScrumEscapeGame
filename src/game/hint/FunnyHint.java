package game.hint;

import java.util.Random;

public class FunnyHint implements Hint {
    private static final String[] grappen = {
            "🃏 Hint: Scrum is geen dansstijl, al denken sommigen van wel.",
            "🃏 Hint: Scrum zonder retrospective is als pizza zonder kaas.",
            "🃏 Hint: Daily stand-up is geen karaoke sessie.",
            "🃏 Hint: De PO wil graag werkende software... geen PowerPoint.",
            "🃏 Hint: Bugs zijn gewoon verborgen features, toch?"
    };

    @Override
    public String geefHint() {
        int index = new Random().nextInt(grappen.length);
        return grappen[index];
    }
}
