package game.kamer;

import game.Monster;
import game.Speler;
import game.joker.HintJoker;
import game.joker.Joker;
import game.joker.KeyJoker;
import game.voorwerp.Readable;
import game.voorwerp.Weapon;
import game.vraag.Vraag;

public class NormaleKamer extends Kamer {
    public NormaleKamer(int nummer, String naam, Vraag vraag, Weapon zwaard, Readable boek, String hint,
                        Monster monster, String hintJoker, boolean keyJokerToegestaan, boolean assistentToegestaan) {
        super(nummer, naam, vraag, zwaard, boek, hint, monster, hintJoker, keyJokerToegestaan, assistentToegestaan);
    }


    @Override
    public boolean voerUit(Speler speler) {
        System.out.println("Je bent in kamer " + getKamerNummer() + ": " + getNaam());
        return vraag.stelVraag(speler, this);
    }
}
