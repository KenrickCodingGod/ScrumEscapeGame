package game.joker;

import game.Speler;
import game.kamer.Kamer;

public abstract class AbstractJoker implements Joker {
    private boolean gebruikt = false;

    @Override
    public boolean gebruik(Speler speler, Kamer kamer) {
        if (gebruikt) {
            toonAlGebruiktMelding();
            return false;
        }
        gebruikt = true;
        voerEffectUit(speler, kamer);
        return true;
    }

    protected void toonAlGebruiktMelding() {
        System.out.println("❌ Je hebt deze joker al gebruikt.");
    }

    protected abstract void voerEffectUit(Speler speler, Kamer kamer);
}
