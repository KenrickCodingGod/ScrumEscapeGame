package game.vraag;

import game.Speler;
import game.kamer.Kamer;

public interface Vraag {
    boolean stelVraag(Speler speler, Kamer kamer);
}