package game.assistent;

import java.util.Random;

public class Motivator {
    private static final String[] boodschappen = {
            "💪 Je denkt als een echte product owner!",
            "🚀 Goed bezig, hou dit vast!",
            "🧠 Scrum-master vibes detected!",
            "✅ Jij komt hier wel uit!",
            "💪 Je bent een strijder",
            "🦸‍♂️ You got this!"
    };

    public void toonMelding() {
        String boodschap = boodschappen[new Random().nextInt(boodschappen.length)];
        System.out.println("🎯 Motivatie: " + boodschap);
    }
}
