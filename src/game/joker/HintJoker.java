package game.joker;

import java.util.HashMap;
import java.util.Map;

public class HintJoker implements Joker {
    private boolean gebruikt = false;

    private static final Map<Integer, String> hintsPerKamer = new HashMap<>();

    static {
        hintsPerKamer.put(0, "Sprint Planning: Denk na over wat haalbaar is in 2 weken.");
        hintsPerKamer.put(1, "Daily Scrum: Wat bespreek je elke dag?");
        hintsPerKamer.put(2, "Scrum Board: Iets hoort hier echt niet thuis...");
        hintsPerKamer.put(3, "Sprint Review: Wat moet je tonen?");
        hintsPerKamer.put(4, "Retrospective: Hoe verbeter je?");
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
