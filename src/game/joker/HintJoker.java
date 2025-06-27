package game.joker;

import game.Speler;
import game.kamer.Kamer;

public class HintJoker implements Joker {
    private boolean gebruikt = false;



    @Override
    public boolean gebruik(Speler speler, Kamer kamer) {
        if (gebruikt) {
            System.out.println("❌ Je hebt de HintJoker al gebruikt.");
            return false;
        }
        gebruikt = true;
        System.out.println("💡 Hint: " + kamer.getHintJoker());
        return true;
    }

    @Override
    public String getNaam() {
        return "HintJoker";
    }
}
