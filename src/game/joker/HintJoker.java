package game.joker;

import java.util.HashMap;
import java.util.Map;

public class HintJoker implements Joker {
    private boolean gebruikt = false;

    private static final Map<Integer, String> hintsPerKamer = new HashMap<>();

    static {
        hintsPerKamer.put(0, "Sprint Planning: Hoe check je fouten? Door te ......");
        hintsPerKamer.put(1, "Daily Scrum: Wat noem je een groep mensen die coderen?");
        hintsPerKamer.put(2, "Scrum Board: Iets hoort hier echt niet thuis...");
        hintsPerKamer.put(3, "Sprint Review: Denk aan de rollen en hun taak.");
        hintsPerKamer.put(4, "Retrospective: Unscramble het antwoord! : wsanmenerikg");
        hintsPerKamer.put(5, "Finale: Gebruik je alle kennis uit vorige kamers?");
    }

    @Override
    public void gebruik() {

    }

    @Override
    public void gebruikInKamer(int kamerNummer) {
        if (!gebruikt) {
            String hint = hintsPerKamer.getOrDefault(kamerNummer, "Geen hint beschikbaar.");
            System.out.println("💡 Joker Hint - " + hint);
            gebruikt = true;
        } else {
            System.out.println("⚠️ Je hebt deze joker al gebruikt.");
        }
    }

    @Override
    public boolean magGebruikenInKamer(int kamerNummer) {
        return !gebruikt;
    }

    @Override
    public String getNaam() {
        return "HintJoker";
    }
}
