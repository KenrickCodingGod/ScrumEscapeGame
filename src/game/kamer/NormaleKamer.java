package game.kamer;

import game.Monster;
import game.Speler;
import game.joker.HintJoker;
import game.joker.Joker;
import game.joker.KeyJoker;
import game.voorwerp.Readable;
import game.voorwerp.Weapon;
import game.vraag.Vraag;

import java.util.List;

public class NormaleKamer extends Kamer {
    public NormaleKamer(String naam, Vraag vraag, Weapon zwaard, Readable boek, String hint,
                        Monster monster, String hintJoker, boolean keyJokerToegestaan, boolean assistentToegestaan) {
        super(naam, vraag, zwaard, boek, hint, monster, hintJoker, keyJokerToegestaan, assistentToegestaan);
    }


    @Override
    public boolean voerUit(Speler speler, List<Kamer> kamers) {
        int index = kamers.indexOf(this);
        System.out.println("Je bent in kamer " + (index + 1) + ": " + getNaam());
        return vraag.stelVraag(speler, this);
    }
}
