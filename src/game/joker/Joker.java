package game.joker;

import game.Speler;
import game.kamer.Kamer;

public interface Joker {
    boolean gebruik(Speler speler, Kamer kamer);
    String getNaam();
}