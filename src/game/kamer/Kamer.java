package game.kamer;

import game.Speler;
import game.vraag.Vraag;
import game.voorwerp.Voorwerp;



public class Kamer {
    private final int Kamernummer;
    private final String naam;
    protected final Vraag vraag;
    private final Voorwerp voorwerp;

    public Kamer(int Kamernummer, String naam, Vraag vraag, Voorwerp voorwerp) {
        this.Kamernummer = Kamernummer;
        this.naam = naam;
        this.vraag = vraag;
        this.voorwerp = voorwerp;
    }

    public int getKamerNummer() {
        return Kamernummer;
    }

    public String getNaam() {
        return naam;
    }

    public Voorwerp getVoorwerp() {
        return voorwerp;
    }

    public boolean voerUit(Speler speler) {
        System.out.println("Je bent in kamer " + Kamernummer + ": " + naam);
        return vraag.stelVraag(speler);
    }
}
