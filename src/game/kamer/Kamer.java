package game.kamer;

import game.vraag.Vraag;
import game.voorwerp.Voorwerp;



public class Kamer {
    private final String naam;
    private final Vraag vraag;
    private final Voorwerp voorwerp;

    public Kamer(String naam, Vraag vraag, Voorwerp voorwerp) {
        this.naam = naam;
        this.vraag = vraag;
        this.voorwerp = voorwerp;
    }

    public boolean voerUit() {
        System.out.println("Je bent in de kamer: " + naam);
        return vraag.stelVraag();
    }

    public String getNaam() {
        return naam;
    }

    public Voorwerp getVoorwerp() {
        return voorwerp;
    }
}
