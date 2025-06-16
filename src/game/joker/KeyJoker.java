package game.joker;

import java.util.Arrays;
import java.util.List;

public class KeyJoker implements Joker {
    private boolean gebruikt = false;
    private final List<Integer> geldigeKamers = Arrays.asList(2, 4);

    @Override
    public void gebruik() {
        if (!gebruikt) {
            System.out.println("Je bent automatisch door deze kamer heen!");
            gebruikt = true;
        }
    }

    @Override
    public boolean magGebruikenInKamer(int kamerNummer) {
        return !gebruikt && geldigeKamers.contains(kamerNummer);
    }

    @Override
    public String getNaam() {
        return "KeyJoker (alleen kamer 2 en 4)";
    }
}
