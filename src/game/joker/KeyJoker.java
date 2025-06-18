package game.joker;


import java.util.Arrays;
import java.util.List;

public class KeyJoker implements Joker {
    private boolean gebruikt = false;

    @Override
    public void gebruik() {
        if (gebruikt) {
            System.out.println("❌ Je hebt deze joker al gebruikt.");
        } else {
            gebruikt = true;
        }
    }


    @Override
    public void gebruikInKamer(int kamerNummer) {

    }

    @Override
    public boolean magGebruikenInKamer(int kamerNummer) {
        return !gebruikt && (kamerNummer == 1 || kamerNummer == 3);
    }

    @Override
    public String getNaam() {
        return "KeyJoker (alleen kamer 2 en 4)";
    }
}
