package game.joker;

import java.util.HashMap;
import java.util.Map;

public class DefaultHintProvider implements HintProvider {
    private final Map<Integer, String> hints = new HashMap<>();

    public DefaultHintProvider() {
        hints.put(1, "Sprint Planning: Hoe check je fouten? Door te ......");
        hints.put(2, "Daily Scrum: Wat noem je een groep mensen die coderen?");
        hints.put(3, "Scrum Board: Iets hoort hier echt niet thuis...");
        hints.put(4, "Sprint Review: Denk aan de rollen en hun taak.");
        hints.put(5, "Retrospective: Unscramble het antwoord! : wsanmenerikg");
        hints.put(6, "Finale: Gebruik je alle kennis uit vorige kamers?");
    }

    @Override
    public String getHintVoorKamer(int kamerNummer) {
        return hints.getOrDefault(kamerNummer, "Geen hint beschikbaar.");
    }
}
