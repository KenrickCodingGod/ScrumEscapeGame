package game.joker;

import java.util.Set;


public class KeyJoker extends AbstractJoker {
    private static final Set<Integer> TOEGESTAAN_KAMERS = Set.of(2, 4);

    @Override
    public void gebruikInKamer(int kamerNummer) {
        if (!isGebruikt()) {
            if (TOEGESTAAN_KAMERS.contains(kamerNummer)) {
                markeerAlsGebruikt();
            } else {
                System.out.println("❌ Deze joker is niet toegestaan in kamer " + kamerNummer + ".");
            }
        } else {
            System.out.println("❌ Je hebt deze joker al gebruikt.");
        }
    }

    @Override
    public boolean magGebruikenInKamer(int kamerNummer) {
        return super.magGebruikenInKamer(kamerNummer) && TOEGESTAAN_KAMERS.contains(kamerNummer);
    }

    @Override
    public String getNaam() {
        return "KeyJoker";
    }
}
