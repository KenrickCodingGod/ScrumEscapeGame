package game.joker;

import game.Speler;
import game.kamer.Kamer;

public class KeyJoker extends AbstractJoker {

    @Override
    public boolean gebruik(Speler speler, Kamer kamer) {
        if (!kamer.isKeyJokerToegestaan()) {
            System.out.println("❌ KeyJoker mag alleen in bepaalde kamers worden gebruikt.");
            return false;
        }
        return super.gebruik(speler, kamer);
    }

    @Override
    protected void voerEffectUit(Speler speler, Kamer kamer) {
        speler.setKamerOvergeslagen(true);
        System.out.println("🚪 Je hebt deze kamer overgeslagen met de KeyJoker!");
    }

    @Override
    public String getNaam() {
        return "KeyJoker";
    }
}
