package game.joker;

import game.Speler;
import game.kamer.Kamer;

public class KeyJoker implements Joker {
    private boolean gebruikt = false;

    @Override
    public boolean gebruik(Speler speler, Kamer kamer) {
        if (gebruikt) {
            System.out.println("❌ Je hebt de KeyJoker al gebruikt.");
            return false;
        }
        if (!kamer.isKeyJokerToegestaan()) {
            System.out.println("❌ KeyJoker mag alleen in bepaalde kamers worden gebruikt.");
            return false;
        }
        gebruikt = true;
        speler.setKamerOvergeslagen(true);
        System.out.println("🚪 Je hebt deze kamer overgeslagen met de KeyJoker!");
        return true;
    }

    @Override
    public String getNaam() {
        return "KeyJoker";
    }
}
