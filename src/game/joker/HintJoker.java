package game.joker;

import game.Speler;
import game.kamer.Kamer;

public class HintJoker extends AbstractJoker {

    @Override
    protected void voerEffectUit(Speler speler, Kamer kamer) {
        System.out.println("💡 Hint: " + kamer.getHintJoker());
    }

    @Override
    public String getNaam() {
        return "HintJoker";
    }
}
